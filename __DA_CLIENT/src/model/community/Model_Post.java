package model.community;

import org.json.JSONObject;

public class Model_Post {
	private int postId;
	private int projectId;
	private String userName;
	private String  timing;
	private String content;
	
	public Model_Post(int postId, int projectId, String userName, String timing, String content) {
		this.postId = postId;
		this.projectId = projectId;
		this.userName = userName;
		this.timing = timing;
		this.content = content;
	}
	
    public Model_Post(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	postId = obj.getInt("postId");
        	projectId = obj.getInt("projectId");
        	userName = obj.getString("userName");
        	timing = obj.getString("timing");
        	content = obj.getString("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JSONObject toJsonObject() {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", "postNews");
			json.put("postId", postId);
			json.put("projectId", projectId);
			json.put("userName", userName);
			json.put("timing", timing);
			json.put("content", content);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public Model_Post() {
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
