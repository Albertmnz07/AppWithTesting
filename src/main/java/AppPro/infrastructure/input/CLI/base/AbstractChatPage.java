package AppPro.infrastructure.input.CLI.base;


import AppPro.domain.entities.Message;
import AppPro.domain.entities.User;
import AppPro.infrastructure.input.CLI.adapters.ChatAdapter; // Asegúrate de importar tu nuevo adaptador
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;

import java.util.List;

/**
 * Plantilla base para cualquier página que implique una conversación.
 * Gestiona la carga inicial de mensajes y la conexión con el adaptador visual.
 */
public abstract class AbstractChatPage extends AbstractCLIPage {

    public AbstractChatPage(UIManager ui, CLINavigator navigator) {
        super(ui, navigator);
    }

    @Override
    public void runSafe() {
        // 1. Recopilamos los datos necesarios mediante los métodos abstractos (Hooks)
        User currentUser = getCurrentUser();
        String chatTitle = getChatTitle();

        // 2. Creamos el adaptador visual
        // Nota: Delegamos la creación al UIManager para mantener la inyección de dependencias limpia
        ChatAdapter chatAdapter = ui.createChatAdapter(
                currentUser,
                chatTitle,
                this::getMessageHistory,
                this::onSendMessage // Pasamos la acción de enviar como referencia a método
        );

        // 3. Cedemos el control total a la UI del chat
        chatAdapter.show();

        // Al volver de .show() (cuando el usuario pulsa ESC), la página termina.
    }

    // --- MÉTODOS QUE DEBEN IMPLEMENTAR LAS PÁGINAS CONCRETAS ---

    /**
     * @return El usuario que está usando la aplicación (necesario para saber qué mensajes alinear a la derecha).
     */
    protected abstract User getCurrentUser();

    /**
     * @return El nombre del chat o del otro usuario (para la cabecera).
     */
    protected abstract String getChatTitle();

    /**
     * Carga inicial de mensajes desde el Caso de Uso o Repositorio.
     */
    protected abstract List<Message> getMessageHistory();

    /**
     * Lógica de negocio para enviar un mensaje (llamar al SendMessageUseCase).
     * @param text El texto escrito por el usuario.
     */
    protected abstract void onSendMessage(String text);
}