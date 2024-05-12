package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DatabaseConnection;
import model.Model_Community;
import model.Model_Meeting;
import model.Model_Post;
import model.Model_Project;
import model.Model_User_Account;

public class ServiceCommunity {
    private final Connection con;
    private int user_Id;
    private final String INSERT_PROJECT = "INSERT INTO project (projectName) VALUES (?)";
    private final String INSERT_COMMUNITY = "INSERT INTO community_contact (projectId, user_Id) VALUES (?, ?)";
    private final String SELECT_PROJECT = "SELECT project.projectId, project.projectName FROM project JOIN community_contact ON project.projectId = community_contact.projectId WHERE community_contact.user_Id=?";
    private final String INSERT_POST = "INSERT INTO post (projectId, userName, avatarPath, timing, content) VALUES (?, ?, ?, ?, ?)";
    private final String SELECT_POST = "SELECT postId, projectId, userName, avatarPath, timing, content FROM post WHERE projectId=?";
    private final String SELECT_MEMBER = "select user_account.User_ID, UserName,fullName, Email, Phone, Address, Avatar_path FROM user_account JOIN community_contact ON user_account.User_Id=community_contact.user_Id WHERE projectId=?";
    private final String SELECT_USER = "select User_ID, UserName,fullName, Email, Phone, Address, Avatar_path from user_account where userName=?";
    private final String INSERT_MEETING = "INSERT INTO meeting (projectId, title, time) VALUES (?,?,?)";
    private final String SELECT_MEETING = "SELECT meetingId, projectId, title, time FROM meeting WHERE projectId=?";
    
    public ServiceCommunity(int user_Id) {
        this.con = DatabaseConnection.getInstance().getConnection();
        this.user_Id = user_Id;
    }
    
    public Model_Project addProject(String projectName) {
    	Model_Project project = new Model_Project();
    	try {
    		PreparedStatement p = con.prepareStatement(INSERT_PROJECT, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setString(1, projectName);
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int projectId = r.getInt(1);
            project.setProjectId(projectId);
            project.setProjectName(projectName);
            r.close();
            p.close();
            //  Create user account
            p = con.prepareStatement(INSERT_COMMUNITY);
            p.setInt(1, projectId);
            p.setInt(2, user_Id);
            p.execute();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return project;
    }
    
    public Model_Post postNews(Model_Post post) {
    	try {
    		PreparedStatement p = con.prepareStatement(INSERT_POST, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setInt(1, post.getProjectId());
            p.setString(2, post.getUserName());
            p.setString(3, post.getAvatarPath());
            p.setString(4, post.getTiming());
            p.setString(5, post.getContent());
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int postId = r.getInt(1);
            post.setPostId(postId);
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return post;
    }
    
    public List<Model_Project> getProject(int User_Id) {
        List<Model_Project> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_PROJECT);
            p.setInt(1, User_Id);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int projectId = r.getInt(1);
            	String projectName = r.getString(2);
            	Model_Project project = new Model_Project(projectId, projectName);
            	list.add(project);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
    }
    
    public List<Model_Post> getPost(int projectId) {
        List<Model_Post> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_POST);
            p.setInt(1, projectId);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int postId = r.getInt(1);
            	int proId = r.getInt(2);
            	String userName = r.getString(3);
            	String avatarPath = r.getString(4);
            	String timing = r.getString(5);
            	String content = r.getString(6);
            	Model_Post post = new Model_Post(postId, proId, userName, avatarPath, timing, content);
            	list.add(post);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
    }
    
    public List<Model_User_Account> getMember(int projectId) {
        List<Model_User_Account> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_MEMBER);
            p.setInt(1, projectId);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                int userID = r.getInt(1);
                String userName = r.getString(2);
                String fullName = r.getString(3);
                String email = r.getString(4);
                String phone = r.getString(5);
                String address = r.getString(6);
                String avatar_path = r.getString(7);
                list.add(new Model_User_Account(userID, userName, fullName, email, phone, address, avatar_path, true));
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
    }
    int userMemberID;
    public Model_User_Account addMember(String user, int projectId){
    	try {
                Model_User_Account account = null;
                PreparedStatement p = con.prepareStatement(SELECT_USER);
                p.setString(1, user);
                ResultSet r = p.executeQuery();
                
                if (r.next()) {
                	userMemberID = r.getInt(1);
                    String userName = r.getString(2);
                    String fullName = r.getString(3);
                    String email = r.getString(4);
                    String phone = r.getString(5);
                    String address = r.getString(6);
                    String avatar_path = r.getString(7);
                    account = new Model_User_Account(userMemberID, userName, fullName, email, phone, address, avatar_path, true);
                }
                r.close();
                p.close();
                
                p = con.prepareStatement(INSERT_COMMUNITY);
                p.setInt(1, projectId);
                p.setInt(2, userMemberID);
                p.execute();
                p.close();
                
                return account;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public Model_Meeting addMeeting(Model_Meeting meeting) {
    	try {
    		PreparedStatement p = con.prepareStatement(INSERT_MEETING, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setInt(1, meeting.getProjectId());
            p.setString(2, meeting.getTitle());
            p.setString(3, meeting.getTime());
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int meetingId = r.getInt(1);
            meeting.setMeetingId(meetingId);
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return meeting;
    }
    
    public List<Model_Meeting> getMeeting(int projectId) {
        List<Model_Meeting> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_MEETING);
            p.setInt(1, projectId);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	int meetingId = r.getInt(1);
            	int project_Id = r.getInt(2);
            	String title = r.getString(3);
            	String time = r.getString(4);
            	Model_Meeting meeting = new Model_Meeting(meetingId, project_Id, title, time);
            	list.add(meeting);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
    }
    
    
}
