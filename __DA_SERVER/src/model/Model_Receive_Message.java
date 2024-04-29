package model;

import org.json.JSONObject;

public class Model_Receive_Message {
    private int fromUserID;
    private String text;

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Model_Receive_Message(int fromUserID, String text) {
        this.fromUserID = fromUserID;
        this.text = text;
    }

    public Model_Receive_Message() {

    }
    
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("fromUserID", fromUserID);
			json.put("text", text);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }


}
