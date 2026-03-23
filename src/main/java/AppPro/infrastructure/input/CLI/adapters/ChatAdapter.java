package AppPro.infrastructure.input.CLI.adapters;

import AppPro.domain.entities.Message;
import AppPro.domain.entities.User;
import AppPro.infrastructure.input.CLI.exceptions.BackNavigationException;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class ChatAdapter {

    private final Screen screen;
    private final TextGraphics tg;
    private final User currentUser;
    private final String otherUserName;
    private final List<Message> messages;
    private final Consumer<String> onSendMessage;

    // Estado del componente
    private final StringBuilder inputBuffer = new StringBuilder();
    private int selectedMessageIndex = -1; // -1 significa que estamos escribiendo, >= 0 seleccionando mensajes

    // Configuración visual
    private static final int HEADER_HEIGHT = 2;
    private static final int INPUT_HEIGHT = 3; // 1 linea separadora + 1 texto + 1 margen
    private static final int SIDE_MARGIN = 2;

    public ChatAdapter(Screen screen,
                       User currentUser,
                       String otherUserName,
                       List<Message> messages,
                       Consumer<String> onSendMessage) {
        this.screen = screen;
        this.tg = screen.newTextGraphics();
        this.currentUser = currentUser;
        this.otherUserName = otherUserName;
        // Hacemos una copia para evitar problemas de concurrencia básica o inmutabilidad
        this.messages = new ArrayList<>(messages);
        // Invertimos la lista para que el índice 0 sea el MENSAJE MÁS RECIENTE (facilita la lógica de abajo a arriba)
        Collections.reverse(this.messages);
        this.onSendMessage = onSendMessage;
    }

    public void show() {
        boolean running = true;
        while (running) {
            try {
                render();
                KeyStroke key = screen.readInput();

                if (key == null) continue;

                switch (key.getKeyType()) {
                    case Escape -> {
                        running = false;
                        throw new BackNavigationException();
                    }
                    case Enter -> handleEnter();
                    case Backspace -> handleBackspace();
                    case ArrowUp -> moveSelection(1); // Ir al pasado (arriba visualmente)
                    case ArrowDown -> moveSelection(-1); // Ir al futuro (abajo visualmente)
                    case Character -> {
                        // Si empieza a escribir, quitamos la selección del historial
                        selectedMessageIndex = -1;
                        inputBuffer.append(key.getCharacter());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

    private void handleEnter() {
        if (selectedMessageIndex == -1 && !inputBuffer.isEmpty()) {
            // Enviar mensaje
            String text = inputBuffer.toString();
            onSendMessage.accept(text);

            // Añadir visualmente el mensaje (simulación optimista) o recargar la lista
            // Aquí asumimos que recargamos o añadimos manualmente para ver el efecto inmediato
            // Nota: En una app real, el observer actualizaría esto.
            // Por ahora, simulamos refresco limpiando input y reseteando scroll.
            inputBuffer.setLength(0);
            selectedMessageIndex = -1;
            // *Nota*: Aquí deberías añadir el mensaje a 'this.messages' si no recargas la página entera
        }
    }

    private void handleBackspace() {
        if (selectedMessageIndex == -1 && !inputBuffer.isEmpty()) {
            inputBuffer.deleteCharAt(inputBuffer.length() - 1);
        }
    }

    private void moveSelection(int delta) {
        int newIndex = selectedMessageIndex + delta;

        // Límites:
        // -1: Foco en el Input
        // 0 a messages.size()-1: Foco en los mensajes
        if (newIndex < -1) newIndex = -1;
        if (newIndex >= messages.size()) newIndex = messages.size() - 1;

        selectedMessageIndex = newIndex;
    }

    // --- LÓGICA DE RENDERIZADO (El Núcleo Complejo) ---

    private void render() throws IOException {
        screen.clear();
        TerminalSize size = screen.getTerminalSize();
        int width = size.getColumns();
        int height = size.getRows();

        // 1. Pintar Header
        drawHeader(width);

        // 2. Pintar Input Area (Fijo abajo)
        drawInputArea(width, height);

        // 3. Pintar Mensajes (De abajo hacia arriba)
        // El área disponible para mensajes es: Altura total - Header - Input
        int availableHeight = height - HEADER_HEIGHT - INPUT_HEIGHT;
        int currentY = height - INPUT_HEIGHT - 1; // Empezamos justo encima del input

        // Iteramos sobre los mensajes (recordad: la lista está invertida, 0 es el más nuevo)
        // Empezamos a pintar desde el scrollOffset (en este caso gestionado por selectedIndex si quisiéramos paginar)
        // Para simplificar, pintamos todo lo que quepa empezando por el más reciente.

        // Un "truco" para el scroll: Si seleccionamos un mensaje muy antiguo que se sale de pantalla,
        // tendríamos que calcular un 'viewOffset'. Para esta versión v1, dejaremos que el foco se pierda arriba
        // si la lista es enorme, pero el highlight funcionará. (Implementar scroll de ventana es el paso "Experto").

        for (int i = 0; i < messages.size(); i++) {
            if (currentY < HEADER_HEIGHT) break; // Nos hemos quedado sin espacio arriba

            Message msg = messages.get(i);
            boolean isMe = msg.getSenderId().equals(currentUser.getUserId());
            boolean isSelected = (i == selectedMessageIndex);

            // Calcular ancho máximo de la burbuja (50% de la pantalla)
            int bubbleMaxWidth = width / 2;
            List<String> wrappedLines = wrapText(msg.getMessageContent().getValue(), bubbleMaxWidth);

            // Calculamos dónde empieza este bloque de mensaje
            int messageHeight = wrappedLines.size();
            int startY = currentY - messageHeight + 1;

            // Si el mensaje cabe (aunque sea parcialmente), lo pintamos
            if (startY < HEADER_HEIGHT) {
                // Caso borde: el mensaje se corta arriba. Por simplicidad, paramos aquí.
                break;
            }

            drawMessageBubble(wrappedLines, startY, width, isMe, isSelected);

            // Movemos el cursor Y hacia arriba para el siguiente mensaje
            currentY -= (messageHeight + 1); // +1 por un pequeño margen vertical entre mensajes
        }

        screen.refresh();
    }

    private void drawHeader(int width) {
        tg.setBackgroundColor(TextColor.ANSI.BLUE);
        tg.setForegroundColor(TextColor.ANSI.WHITE);
        tg.putString(0, 0, " ".repeat(width));
        tg.putString(2, 0, "Chat con: " + otherUserName);
        tg.setBackgroundColor(TextColor.ANSI.DEFAULT);
    }

    private void drawInputArea(int width, int height) {
        int startY = height - INPUT_HEIGHT;

        // Línea separadora
        tg.setForegroundColor(TextColor.ANSI.WHITE);
        tg.putString(0, startY, "-".repeat(width));

        // Prompt
        tg.setForegroundColor(TextColor.ANSI.GREEN);
        tg.putString(2, startY + 1, "Tú: ");

        // Texto actual
        tg.setForegroundColor(TextColor.ANSI.WHITE);
        String visibleInput = inputBuffer.toString();
        tg.putString(6, startY + 1, visibleInput + "_");

        // Indicador de modo
        if (selectedMessageIndex > -1) {
            tg.setForegroundColor(TextColor.ANSI.YELLOW);
            tg.putString(width - 20, startY + 1, "[MODO HISTORIAL]");
        }
    }

    private void drawMessageBubble(List<String> lines, int startY, int screenWidth, boolean isMe, boolean isSelected) {
        // Colores
        if (isSelected) {
            tg.enableModifiers(SGR.REVERSE); // Invertir colores para destacar
        } else {
            if (isMe) {
                tg.setForegroundColor(TextColor.ANSI.CYAN);
            } else {
                tg.setForegroundColor(TextColor.ANSI.WHITE);
            }
        }

        // Posición X
        int maxLineLength = lines.stream().mapToInt(String::length).max().orElse(0);
        int startX;

        if (isMe) {
            // Alineado a la derecha
            startX = screenWidth - maxLineLength - SIDE_MARGIN;
        } else {
            // Alineado a la izquierda
            startX = SIDE_MARGIN;
        }

        // Pintar líneas
        for (int i = 0; i < lines.size(); i++) {
            tg.putString(startX, startY + i, lines.get(i));
        }

        // Limpiar modificadores
        tg.disableModifiers(SGR.REVERSE);
        tg.setForegroundColor(TextColor.ANSI.DEFAULT);
    }

    // --- UTILIDADES ---

    /**
     * Divide un texto largo en varias líneas para que quepa en el ancho dado.
     */
    private List<String> wrapText(String text, int maxWidth) {
        List<String> lines = new ArrayList<>();
        if (text == null || text.isEmpty()) return lines;

        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (currentLine.length() + word.length() + 1 > maxWidth) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder();
            }
            if (!currentLine.isEmpty()) {
                currentLine.append(" ");
            }
            currentLine.append(word);
        }
        if (!currentLine.isEmpty()) {
            lines.add(currentLine.toString());
        }
        return lines;
    }
}