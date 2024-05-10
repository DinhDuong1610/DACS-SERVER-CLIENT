package service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import org.json.JSONObject;

public class ClientHandler extends Thread{
    private BufferedReader in;
    private DataOutputStream out;
    private ArrayList<ClientHandler> clients;
    private Service service;
    private String userId;
      
    

	public ClientHandler(String userId, Service service, BufferedReader in, DataOutputStream out, ArrayList<ClientHandler> clients) {
        this.userId = userId;
    	this.service = service;
    	this.in = in;
        this.out = out;
        this.clients = clients;
        clients.add(this);
        start();
    }
    
    public void sendMessage(JSONObject jsonData) {
            try {
    			out.writeBytes(jsonData.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    }

	@Override
	public void run() {
        while (true) {
            try {
                String message = in.readLine();
                if (message == null) { // Client disconnected
                    break;
                }
//                System.out.println("server: " + message);
                service.listen(this, message);
//                broadcast(message);
            } catch (Exception e) {
            	try {
					in.close();
	            	out.close();
				} catch (IOException e1) {
//					e1.printStackTrace();
				}
                e.printStackTrace();
                System.out.println("đóng clientHandler");
                break;
            }
        }
//        try {
//            in.close();
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
