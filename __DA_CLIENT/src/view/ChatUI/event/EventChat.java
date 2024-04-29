package view.ChatUI.event;

import model.Chat.Model_Receive_Message;
import model.Chat.Model_Send_Message;

public interface EventChat {

    public void sendMessage(Model_Send_Message data);

    public void receiveMessage(Model_Receive_Message data);
}
