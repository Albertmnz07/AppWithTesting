package AppPro.infrastructure.input.CLI.adapters;

import AppPro.domain.entities.Message;
import AppPro.domain.entities.User;
import AppPro.infrastructure.input.CLI.exceptions.BackNavigationException;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Adaptador de chat para Lanterna.
 *
 * Coordenadas del layout (de arriba a abajo):
 *   [0..HEADER_HEIGHT-1]                    → Cabecera fija
 *   [HEADER_HEIGHT..height-INPUT_HEIGHT-1]  → Área de mensajes (scroll)
 *   [height-INPUT_HEIGHT..height-1]         → Área de input fija
 *
 * El eje de scroll trabaja en LÍNEAS (no en mensajes) para manejar
 * correctamente burbujas multilínea. Los mensajes se almacenan en orden
 * cronológico ascendente (índice 0 = más antiguo) tal como los devuelve
 * el repositorio. El renderizado los recorre de más reciente a más antiguo,
 * pintando de abajo hacia arriba.
 */
public class ChatAdapter {

    // -----------------------------------------------------------------------
    // Dependencias
    // -----------------------------------------------------------------------

    private final Screen screen;
    private final TextGraphics tg;
    private final User currentUser;
    private final String otherUserName;
    private final Supplier<List<Message>> historyProvider;
    private final Consumer<String> onSendMessage;

    // -----------------------------------------------------------------------
    // Estado mutable
    // -----------------------------------------------------------------------

    /** Mensajes en orden cronológico ascendente (0 = más antiguo). */
    private List<Message> messages = new ArrayList<>();

    /** Índice del mensaje resaltado en modo historial; -1 = modo escritura. */
    private int selectedMessageIndex = -1;

    /**
     * Líneas "scrolleadas" hacia el pasado.
     * 0 → vista anclada en los mensajes más recientes (comportamiento normal).
     * N → se han ocultado N líneas del fondo para mostrar mensajes más antiguos.
     */
    private int viewOffsetLines = 0;

    /** Texto que el usuario está escribiendo actualmente. */
    private final StringBuilder inputBuffer = new StringBuilder();

    // -----------------------------------------------------------------------
    // Constantes de layout
    // -----------------------------------------------------------------------

    private static final int HEADER_HEIGHT = 2;  // filas de la cabecera
    private static final int INPUT_HEIGHT   = 3;  // filas del área de input
    private static final int SIDE_MARGIN    = 2;  // margen lateral en columnas
    private static final int BUBBLE_RATIO   = 2;  // ancho_pantalla / BUBBLE_RATIO = ancho_máximo_burbuja

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    public ChatAdapter(Screen screen,
                       User currentUser,
                       String otherUserName,
                       Supplier<List<Message>> historyProvider,
                       Consumer<String> onSendMessage) {
        this.screen          = screen;
        this.tg              = screen.newTextGraphics();
        this.currentUser     = currentUser;
        this.otherUserName   = otherUserName;
        this.historyProvider = historyProvider;
        this.onSendMessage   = onSendMessage;

        reloadMessages();
    }

    // -----------------------------------------------------------------------
    // Ciclo principal
    // -----------------------------------------------------------------------

    public void show() {
        boolean running = true;
        while (running) {
            try {
                screen.doResizeIfNecessary();
                render();

                KeyStroke key = screen.readInput();
                if (key == null) continue;

                switch (key.getKeyType()) {
                    case Escape    -> { running = false; throw new BackNavigationException(); }
                    case Enter     -> handleEnter();
                    case Backspace -> handleBackspace();
                    case ArrowUp   -> moveSelection(-1); // visualmente hacia arriba = mensaje más antiguo
                    case ArrowDown -> moveSelection(+1); // visualmente hacia abajo  = mensaje más reciente
                    case Character -> handleCharacter(key.getCharacter());
                    default        -> { /* resto de teclas ignoradas */ }
                }
            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

    // -----------------------------------------------------------------------
    // Gestión de entrada
    // -----------------------------------------------------------------------

    private void handleEnter() {
        if (selectedMessageIndex != -1 || inputBuffer.isEmpty()) return;

        onSendMessage.accept(inputBuffer.toString());
        inputBuffer.setLength(0);
        viewOffsetLines = 0;
        reloadMessages();
    }

    private void handleBackspace() {
        if (selectedMessageIndex == -1 && !inputBuffer.isEmpty()) {
            inputBuffer.deleteCharAt(inputBuffer.length() - 1);
        }
    }

    private void handleCharacter(char c) {
        // Escribir cancela el modo historial y ancla la vista al fondo
        selectedMessageIndex = -1;
        viewOffsetLines      = 0;
        inputBuffer.append(c);
    }

    /**
     * Mueve la selección {@code delta} posiciones.
     * +1 = hacia el pasado (ArrowUp), -1 = hacia el futuro (ArrowDown).
     * -1 como índice significa "modo escritura" (sin selección).
     */
    private void moveSelection(int delta) {
        // Recargar siempre al entrar en modo historial desde escritura,
        // por si la lista estaba vacía en el constructor
        if (selectedMessageIndex == -1 && delta > 0) {
            reloadMessages();
            if (messages.isEmpty()) return;
            selectedMessageIndex = messages.size() - 1;
            recalcViewOffset();
            return;
        }

        // ArrowDown desde el mensaje más reciente → volver a modo escritura
        if (selectedMessageIndex == messages.size() - 1 && delta < 0) {
            selectedMessageIndex = -1;
            viewOffsetLines = 0;
            return;
        }

        int next = Math.max(0, Math.min(selectedMessageIndex + delta, messages.size() - 1));
        selectedMessageIndex = next;
        recalcViewOffset();
    }

    // -----------------------------------------------------------------------
    // Gestión de datos
    // -----------------------------------------------------------------------

    /**
     * Recarga los mensajes desde el proveedor.
     * Se mantiene el orden cronológico ascendente que devuelve el repositorio
     * (el índice 0 es el mensaje más antiguo). NO se invierte la lista.
     */
    private void reloadMessages() {
        this.messages = new ArrayList<>(historyProvider.get());
    }

    // -----------------------------------------------------------------------
    // Cálculo de scroll
    // -----------------------------------------------------------------------

    /**
     * Ajusta {@code viewOffsetLines} para garantizar que el mensaje
     * {@code selectedMessageIndex} quede completamente visible.
     *
     * El renderizado pinta de más reciente (N-1) a más antiguo (0),
     * de abajo hacia arriba. Por tanto, la "distancia desde el fondo" de
     * un mensaje i es la suma de alturas de todos los mensajes con índice > i.
     */
    private void recalcViewOffset() {
        TerminalSize size      = screen.getTerminalSize();
        int available          = size.getRows() - HEADER_HEIGHT - INPUT_HEIGHT;
        int bubbleMaxWidth     = size.getColumns() / BUBBLE_RATIO;

        // Líneas que hay por debajo del mensaje seleccionado (mensajes más recientes)
        int linesBelow = 0;
        for (int i = messages.size() - 1; i > selectedMessageIndex; i--) {
            linesBelow += messageHeight(i, bubbleMaxWidth) + 1; // +1 margen entre burbujas
        }

        int selHeight  = messageHeight(selectedMessageIndex, bubbleMaxWidth);
        int bottomEdge = linesBelow;              // líneas desde el fondo hasta el borde inferior del mensaje
        int topEdge    = linesBelow + selHeight;  // líneas desde el fondo hasta el borde superior del mensaje

        // Si el borde superior queda fuera de la ventana → scroll hacia arriba
        if (topEdge > viewOffsetLines + available) {
            viewOffsetLines = topEdge - available;
        }
        // Si el borde inferior queda por debajo de la ventana → scroll hacia abajo
        if (bottomEdge < viewOffsetLines) {
            viewOffsetLines = Math.max(0, bottomEdge);
        }
    }

    /** Número de líneas que ocupa el mensaje {@code index} tras el wrapping. */
    private int messageHeight(int index, int bubbleMaxWidth) {
        return wrapText(messages.get(index).getMessageContent().getValue(), bubbleMaxWidth).size();
    }

    // -----------------------------------------------------------------------
    // Renderizado
    // -----------------------------------------------------------------------

    private void render() throws IOException {
        screen.clear();
        TerminalSize size = screen.getTerminalSize();
        int width  = size.getColumns();
        int height = size.getRows();

        drawHeader(width);
        drawInputArea(width, height);
        drawMessages(width, height);

        screen.refresh();
    }

    /**
     * Pinta los mensajes de más reciente a más antiguo, de abajo hacia arriba.
     *
     * {@code viewOffsetLines} indica cuántas líneas del fondo se omiten para
     * simular el scroll. Se acumulan en {@code skippedLines} hasta alcanzar
     * el offset; a partir de ahí se pintan normalmente.
     */
    private void drawMessages(int width, int height) {
        int bubbleMaxWidth = width / BUBBLE_RATIO;
        int currentY       = height - INPUT_HEIGHT - 1; // primera fila disponible desde abajo
        int skippedLines   = 0;

        for (int i = messages.size() - 1; i >= 0; i--) {
            if (currentY < HEADER_HEIGHT) break;

            List<String> lines = wrapText(
                    messages.get(i).getMessageContent().getValue(), bubbleMaxWidth);
            int msgHeight  = lines.size();
            int totalSlot  = msgHeight + 1; // altura + margen inferior

            // ---- Aplicar scroll ----
            if (skippedLines + totalSlot <= viewOffsetLines) {
                // El mensaje cae completamente fuera del viewport inferior → saltar
                skippedLines += totalSlot;
                continue;
            }

            // Líneas de ESTE mensaje que hay que omitir por el offset parcial
            int skipInMsg = Math.max(0, viewOffsetLines - skippedLines);
            skippedLines  = viewOffsetLines; // offset ya consumido en su totalidad

            List<String> visibleLines = lines.subList(skipInMsg, lines.size());
            int startY = currentY - visibleLines.size() + 1;

            // ---- Clip superior: si el mensaje sobresale por encima del header ----
            if (startY < HEADER_HEIGHT) {
                int clip = HEADER_HEIGHT - startY;
                if (clip >= visibleLines.size()) {
                    // El mensaje queda completamente oculto; no hay más espacio
                    break;
                }
                visibleLines = visibleLines.subList(clip, visibleLines.size());
                startY = HEADER_HEIGHT;
            }

            if (!visibleLines.isEmpty()) {
                boolean isMe       = messages.get(i).getSenderId().equals(currentUser.getUserId());
                boolean isSelected = (i == selectedMessageIndex);
                drawMessageBubble(visibleLines, startY, width, isMe, isSelected);
            }

            currentY -= (visibleLines.size() + 1);
        }
    }

    private void drawHeader(int width) {
        tg.setBackgroundColor(TextColor.ANSI.BLUE);
        tg.setForegroundColor(TextColor.ANSI.WHITE);
        tg.putString(0, 0, " ".repeat(width));
        tg.putString(2, 0, "Chat con: " + otherUserName);
        tg.setBackgroundColor(TextColor.ANSI.DEFAULT);
        tg.setForegroundColor(TextColor.ANSI.DEFAULT);
    }

    private void drawInputArea(int width, int height) {
        int separatorY = height - INPUT_HEIGHT;
        int inputY     = separatorY + 1;

        // Separador
        tg.setForegroundColor(TextColor.ANSI.WHITE);
        tg.putString(0, separatorY, "─".repeat(width));

        // Etiqueta
        tg.setForegroundColor(TextColor.ANSI.GREEN);
        tg.putString(2, inputY, "Tú: ");

        // Input con cursor (cursor solo visible en modo escritura)
        tg.setForegroundColor(TextColor.ANSI.WHITE);
        String cursor         = (selectedMessageIndex == -1) ? "█" : " ";
        int    maxInputWidth  = Math.max(1, width - 7 - 16); // reservar espacio para el indicador de modo
        String display        = inputBuffer.toString();
        if (display.length() > maxInputWidth) {
            // Mostrar siempre el extremo derecho (texto más reciente)
            display = display.substring(display.length() - maxInputWidth);
        }
        tg.putString(6, inputY, display + cursor);

        // Indicador de modo historial
        if (selectedMessageIndex != -1) {
            tg.setForegroundColor(TextColor.ANSI.YELLOW);
            tg.putString(width - 14, inputY, "[HISTORIAL↑↓]");
        }

        tg.setForegroundColor(TextColor.ANSI.DEFAULT);
    }

    private void drawMessageBubble(List<String> lines, int startY, int screenWidth,
                                   boolean isMe, boolean isSelected) {
        if (isSelected) {
            tg.enableModifiers(SGR.REVERSE);
        } else {
            tg.setForegroundColor(isMe ? TextColor.ANSI.CYAN : TextColor.ANSI.WHITE);
        }

        int maxLen = lines.stream().mapToInt(String::length).max().orElse(0);
        int startX = isMe
                ? Math.max(SIDE_MARGIN, screenWidth - maxLen - SIDE_MARGIN)
                : SIDE_MARGIN;

        for (int i = 0; i < lines.size(); i++) {
            tg.putString(startX, startY + i, lines.get(i));
        }

        tg.disableModifiers(SGR.REVERSE);
        tg.setForegroundColor(TextColor.ANSI.DEFAULT);
    }

    // -----------------------------------------------------------------------
    // Utilidades de texto
    // -----------------------------------------------------------------------

    /**
     * Divide {@code text} en líneas de como máximo {@code maxWidth} caracteres.
     * Las palabras más largas que {@code maxWidth} se parten forzosamente para
     * evitar que se pierdan o desborden la pantalla.
     *
     * @param text     texto a envolver; puede ser null o vacío.
     * @param maxWidth ancho máximo en caracteres; si es ≤ 0 se devuelve el texto sin partir.
     * @return lista de líneas, nunca null.
     */
    private List<String> wrapText(String text, int maxWidth) {
        List<String> lines = new ArrayList<>();
        if (text == null || text.isBlank()) return lines;
        if (maxWidth <= 0) { lines.add(text); return lines; }

        for (String word : text.split(" ", -1)) {
            // Partir palabras que por sí solas superan maxWidth
            while (word.length() > maxWidth) {
                lines.add(word.substring(0, maxWidth));
                word = word.substring(maxWidth);
            }

            // Añadir la palabra a la línea actual o abrir una nueva
            if (lines.isEmpty() || lastLine(lines).length() + 1 + word.length() > maxWidth) {
                lines.add(word);
            } else {
                lines.set(lines.size() - 1, lastLine(lines) + " " + word);
            }
        }

        return lines;
    }

    /** Devuelve la última línea de la lista sin extraerla. */
    private static String lastLine(List<String> lines) {
        return lines.get(lines.size() - 1);
    }
}