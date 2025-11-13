import controller.ChatController;
import model.User;
import view.ChatWindow;

public class Main {
    public static void main(String[] args) {
        ChatController chatController = new ChatController();
        chatController.registerWindow(new ChatWindow(chatController, new User("Sender 1")));
    } 
}
