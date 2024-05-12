package service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Chat.Model_Message;
import model.Chat.Model_Receive_Message;
import model.Chat.Model_User_Account;
import model.calendar.Model_Calendar;
import model.community.Model_Meeting;
import model.community.Model_Post;
import model.community.Model_Project;
import view.MainUI;
import view.ChatUI.event.PublicEvent;
import view.ChatUI.form.Login;

public class Service {
	private static Service instance;
	private Socket client;
	private final int PORT_NUMBER = 1610;
	private final String IP = "localhost";
	private Model_User_Account user;
	BufferedReader in;
	DataOutputStream out;
	private Model_Message model_message;
	private Login login;
	private MainUI main;
	
	public static Service getInstance(MainUI main) {
		if(instance == null) {
			instance = new Service(main);
		}
		return instance;
	}
	
    public static Service getInstance() {
    	return instance;
    }
	
	private Service(MainUI main) {
		this.main = main;
	}
	
    public void startClient(){
    	try {
        	client = new Socket(IP, PORT_NUMBER);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new DataOutputStream(client.getOutputStream());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
        new Thread(() -> {
            while (true) {
                try {
                    String message = in.readLine();
                    if (message != null) {
//                        System.out.println("client: " + message + "\n");
                        listen(message);
                    } else {
                        System.out.println("Client disconnected");
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    public void listen(String newdata) {
    	JSONObject jsonData;
    	String data=new String(newdata);
		try {
			jsonData = new JSONObject(data);
			System.out.println("server: " + jsonData.toString() + "\n");
	    	if(jsonData.getString("type").equals("register")) {
	            String message = jsonData.getString("message");
	            boolean action = jsonData.getBoolean("action");
	            model_message = new Model_Message(action, message);	 
	            Service.getInstance().getMain().getLogin().getRegister().checkRegister();      
	    	}
	    	else if(jsonData.getString("type").equals("login")) {
	            String message = jsonData.getString("message");
	            boolean action = jsonData.getBoolean("action");
	            model_message = new Model_Message(action, message);	 
	            Service.getInstance().getMain().getLogin().getLogin().checkLogin();   
	    	}
	    	else if(jsonData.getString("type").equals("loadLogin")) {
//	    		System.out.println(jsonData);
	            user = new Model_User_Account(jsonData); 
	            Service.getInstance().getMain().setUser(user);
	            main.loadUser(user);
	    	}
	    	else if(jsonData.getString("type").equals("list_user")) {
//	    		System.out.println("list_user: " + jsonData);
	    		Model_User_Account list_user = new Model_User_Account(jsonData);
	    		if(!list_user.getUserName().equals(user.getUserName())) {
	    			PublicEvent.getInstance().getEventMenuLeft().newUser(list_user);
	    		}
	    	}
	    	else if(jsonData.getString("type").equals("receiveMessage")) {
                Model_Receive_Message message = new Model_Receive_Message(jsonData);
                PublicEvent.getInstance().getEventChat().receiveMessage(message);
	    	}
	    	else if(jsonData.getString("type").equals("historyMessage")) {
                String history = jsonData.getString("history");
                main.getHome().getChat().getChatBody().loadHistory(history);
	    	}
	    	else if(jsonData.getString("type").equals("addProject")) {
	    		Model_Project project = new Model_Project(jsonData);
	    		main.getHome_community().getMenuLeft().addProject(project);
	    	}
	    	else if(jsonData.getString("type").equals("listProject")) {
	    		Model_Project project = new Model_Project(jsonData);
	    		main.getHome_community().getMenuLeft().addProject(project);
	    	}
	    	else if(jsonData.getString("type").equals("postNews")) {
	    		Model_Post post = new Model_Post(jsonData);
	    		main.getHome_community().getBody().getPage().getNews().post(post);
	    	}
	    	else if(jsonData.getString("type").equals("listPost")) {
	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject json = jsonArray.getJSONObject(i);
	                Model_Post post = new Model_Post(json);
	                main.getHome_community().getBody().getPage().getNews().post(post);	            
	            }
	    	}
	    	else if(jsonData.getString("type").equals("listMember")) {
	    		Model_User_Account user = new Model_User_Account(jsonData);
	    		main.getHome_community().getBody().getMember().addMember(user);
	    	}
	    	else if(jsonData.getString("type").equals("addMember")) {
	    		Model_User_Account user = new Model_User_Account(jsonData);
	    		main.getHome_community().getBody().getMember().addMember(user);
	    	}
	    	else if(jsonData.getString("type").equals("listCalendar")) {
	    		Model_Calendar item = new Model_Calendar(jsonData);
	    		main.getCalendarUI().addCalendarFromServer(item);
	    	}
	    	else if(jsonData.getString("type").equals("addMeeting")) {
	    		Model_Meeting meeting = new Model_Meeting(jsonData);
	    		main.getHome_community().getBody().getPage().getMeets().addMeet(meeting);
	    	}
	    	else if(jsonData.getString("type").equals("listMeeting")) {
	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
	    		List<Model_Meeting> list = new ArrayList<>();
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject json = jsonArray.getJSONObject(i);
	                Model_Meeting meeting = new Model_Meeting(json);
		    		main.getHome_community().getBody().getPage().getMeets().addMeet(meeting);
	            }
	    	}

		} catch (JSONException e) {
			e.printStackTrace();
		}
    }

    public void sendLogin(JSONObject jsonData) {
        new Thread(() -> {
            try {
    			out.writeBytes(jsonData.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();
    }
    
    public void sendRegister(JSONObject jsonData) {
        new Thread(() -> {
            try {
    			out.writeBytes(jsonData.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void registerInfo(JSONObject jsonData) {
        new Thread(() -> {
            try {
    			out.writeBytes(jsonData.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void updateInfo(JSONObject jsonData) {
        new Thread(() -> {
            try {
    			out.writeBytes(jsonData.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void sendMessage(JSONObject jsonData) {
        new Thread(() -> {
            try {
    			out.writeBytes(jsonData.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void historyMessage(int user_Id2) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "historyMessage");
			json.put("user_Id2", user_Id2);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
    			out.writeBytes(json.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void addProject(JSONObject jsonData) {
        new Thread(() -> {
            try {
    			out.writeBytes(jsonData.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void listProject() {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listProject");
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
    			out.writeBytes(json.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void listPost(int projectId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listPost");
			json.put("projectId", projectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
    			out.writeBytes(json.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }

    
    public void listMember(int projectId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listMember");
			json.put("projectId", projectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
    			out.writeBytes(json.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    
    public void postNews(JSONObject jsonData) {
        new Thread(() -> {
            try {
    			out.writeBytes(jsonData.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void addMember(String userName, int projectId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "addMember");
			json.put("userName", userName);
			json.put("projectId", projectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
    			out.writeBytes(json.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void addCalendar(JSONObject jsonData) {
    	try {
			jsonData.put("type", "addCalendar");
		} catch (JSONException e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
    			out.writeBytes(jsonData.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void updateCalendar(JSONObject jsonData) {
    	try {
			jsonData.put("type", "updateCalendar");
		} catch (JSONException e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
    			out.writeBytes(jsonData.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void listCalendar() {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listCalendar");
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
    			out.writeBytes(json.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }    
    
    public void addMeeting(JSONObject jsonData) {
        new Thread(() -> {
            try {
    			out.writeBytes(jsonData.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public synchronized void listMeeting(int projectId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listMeeting");
			json.put("projectId", projectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
    			out.writeBytes(json.toString() + "\n");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    
	
    public Socket getClient() {
        return client;
    }
    
    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }

	public Model_Message getModel_message() {
		return model_message;
	}

	public Login getLogin() {
		return login;
	}

	public MainUI getMain() {
		return main;
	}
	
	


}
