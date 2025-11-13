package controller;

import java.util.ArrayList;
import java.util.List;
import model.Message;
import view.ChatWindow;

public class ChatController {

    List<ChatWindow> chatWindowList = new ArrayList<>();

    public void registerWindow(ChatWindow window) {
        if (!chatWindowList.contains(window)) {
            chatWindowList.add(window);
        }
    }

    public void unRegisterWindow(ChatWindow window) {
        if (chatWindowList.contains(window)) {
            chatWindowList.remove(window);
        }
    }

    public void broadcastMessage(Message message) {
        for (ChatWindow window : chatWindowList) {
            window.displayMessage(message.getUser().getName(), message.getContent());
        }
    }

    public boolean isUserExists(String name) {
        for (ChatWindow window : chatWindowList) {
            if (window.getUser() != null && window.getUser().getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
