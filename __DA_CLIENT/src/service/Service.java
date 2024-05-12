package service;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

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
	private BufferedReader in;
	private DataOutputStream out;
    private InputStream in_image;
    private OutputStream out_image;
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
            in_image = client.getInputStream();
            out_image = client.getOutputStream();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
        new Thread(() -> {
            while (true) {
                try {
//                    String message = in.readLine(); 
                    String message;
                    synchronized (in) {
                        message = in.readLine();
                    }
                    
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
        
//        new Thread(() -> {
//            while (true) {
//                try {
////                    BufferedImage img = ImageIO.read(in_image); 
//                    BufferedImage img;
//                    synchronized (in_image) {
//                        img = ImageIO.read(in_image);
//                    }
//                    listenImage(img);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        
        
    	

//    	new Thread(()->{
//    		boolean checkImg = true;
//    		boolean checkJson = true;
//            while (true) {
//            	if(checkJson) {
//                    try {
//                        String message = in.readLine();
//                        if(message != null) {
//                        	listen(message);
//                        	checkImg = false;
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        System.out.println("đóng clientHandler");
////                        break;
//                    }
//            	}
//
//            	if(checkImg) {
//	              try {
//	                  BufferedImage img = ImageIO.read(in_image);
//	                  listenImage(img);
//	                  checkJson = false;
//	              } catch (Exception e) {
//	                  e.printStackTrace();
//	                  break;
//	              }
//            	}
//            	checkImg = true;
//            }
//    	}).start();
    	
        
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
    
	public void listenImage(BufferedImage img) {
		main.getHome_community().getMeeting_room().getScreen().getPanel().getGraphics().drawImage(img, 0, 0, main.getHome_community().getMeeting_room().getScreen().getPanel().getWidth(), main.getHome_community().getMeeting_room().getScreen().getPanel().getHeight(), null);
	}
	
	public static AudioFormat getaudioformat() {
		float sampleRate = 8000.0F;
		int sampleSizeInbits = 16;
		int channel = 2;
		boolean signed = true;
		boolean bigEndian = false;
		return new AudioFormat(sampleRate, sampleSizeInbits, channel, signed, signed);
	}
	
	
	public void listenMeeting(int userId, int meetingId) {
        new Thread(() -> {
        	int UDP_PORT = userId;
        	int UDP_PORT_SERVER = meetingId;
        	TargetDataLine audio_in;
        	SourceDataLine audio_out;
        	DatagramSocket dout;
        	try {
        		AudioFormat format = getaudioformat();
        		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        		if (!AudioSystem.isLineSupported(info)) {
        			System.out.println("Not support");
        		}
        		audio_in = (TargetDataLine) AudioSystem.getLine(info);
        		audio_in.open(format);
        		audio_in.start();
        		
        		InetAddress inet = InetAddress.getByName("localhost");
        		dout = new DatagramSocket(UDP_PORT);
        		
        		byte byte_buff[] = new byte[512];
        		new Thread(()-> {
                    while (true) {
                    	int i = 0;
                        try {
                            audio_in.read(byte_buff, 0, byte_buff.length);
                            DatagramPacket data = new DatagramPacket(byte_buff, byte_buff.length, inet, UDP_PORT_SERVER);
//                            System.out.println("Send #" + i++);
                            dout.send(data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }       			                    
        		}).start();
  
        		
       			AudioFormat format2 = getaudioformat();
    			DataLine.Info info_out = new DataLine.Info(SourceDataLine.class, format2);
    			if (!AudioSystem.isLineSupported(info_out)) {
    				System.out.println("Not support");
    				System.exit(0);
    			}
    			audio_out = (SourceDataLine) AudioSystem.getLine(info_out);
    			audio_out.open(format);
    			audio_out.start();
        		byte[] buffer = new byte[512];
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
                System.out.println("haha");
                while (true) {
                    try {
                        dout.receive(incoming);
                        buffer = incoming.getData();
//                        System.out.println(buffer);
                        // Phát dữ liệu âm thanh từ server ra loa
                        audio_out.write(buffer, 0, buffer.length);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }).start();
        
        new Thread(()->{
        	try {
            	int UDP_PORT = userId + 1000;
            	int UDP_PORT_SERVER = meetingId + 1000;
            	DatagramSocket dout;
        		byte[] buffer = new byte[65507];
//        		byte[] buffer = new byte[1024 * 1024];
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
    			InetAddress inet = InetAddress.getByName("localhost");
    			dout = new DatagramSocket(UDP_PORT);        		
                
		        new Thread(() -> {
					while(true) {
						try {
							Robot rob = new Robot();  
							Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                            BufferedImage img = rob.createScreenCapture(new Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
                            
//                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                            ImageIO.write(img, "jpg", baos);
//                            byte[] imageData = baos.toByteArray();
//                            System.out.println("imageSize : " + imageData.length);
//                            baos.close();
                            
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
                            ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
                            writer.setOutput(ios);
                            ImageWriteParam param = writer.getDefaultWriteParam();
                            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                            param.setCompressionQuality(0.1f); // Chất lượng ảnh 10%
                            writer.write(null, new IIOImage(img, null, null), param);
                            writer.dispose();
                            ios.close();

                            byte[] imageData = baos.toByteArray();
                            baos.close();
                            
                            DatagramPacket packet = new DatagramPacket(imageData, imageData.length, inet, UDP_PORT_SERVER);
                            dout.send(packet);
			               
						} catch (Exception e1) {
							e1.printStackTrace();
						}
	                    try {
	                        Thread.sleep(500);
	                    } catch (Exception e1) {
	                    	e1.printStackTrace();
	                    }
					}

		        }).start();
    			
    			while (true) {
                    try {
                        dout.receive(incoming);

                        byte[] imageData = incoming.getData();
                        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
                        BufferedImage image = ImageIO.read(bais);
                        
                        if (image != null) {
                            listenImage(image);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
			} catch (Exception e) {
				e.printStackTrace();
			}
        }).start();
	}
	
	public void share(BufferedImage img) {
        try {
            ByteArrayOutputStream ous = new ByteArrayOutputStream();
			ImageIO.write(img, "png", ous);
			out.write(ous.toByteArray());
			out.flush();
		} catch (IOException e) {
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
    
    public synchronized void openMeeting(int meetingId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "openMeeting");
			json.put("meetingId", meetingId);
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
    
    
    
    public void newMeetingRoom(int projectId) {
    	main.getHome_community().newMeetingRoom(projectId);
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
