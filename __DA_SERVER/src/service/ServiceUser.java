package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.DatabaseConnection;
import model.Model_Login;
import model.Model_Message;
import model.Model_Register;
import model.Model_User_Account;

public class ServiceUser {
    private final String SELECT_USER_ACCOUNT = "select User_ID, UserName,fullName, Email, Phone, Address, Avatar_path from user_account LIMIT 3";
    private final String INSERT_USER = "insert into user (UserName, `Password`) values (?,?)";
    private final String INSERT_USER_ACCOUNT = "insert into user_account (User_ID, UserName, fullName, Email, Phone, Address, Avatar_path, status) values (? ,? , ?, ?, ?, ?, ?, 1)";
    private final String CHECK_USER = "select User_ID from user where UserName =? limit 1";
    private final String LOGIN = "select User_ID, UserName,fullName, Email, Phone, Address, Avatar_path from user_account where user_Id=?";
    private final String CHECK_LOGIN = "select User_ID from user where userName=? and password=?";
    private final String UPDATE_USER_INFO = "update user_account set fullname=?, email=?, phone=?, address=?, avatar_path=? where user_Id=?" ;
    private final String SELECT_CHECK_CONTACT = "SELECT history FROM history_message WHERE (user1=? and user2=?) or (user1=? and user2=?)";

    //  Instance
    private final Connection con;
    
    private int user_Id;
    
    public ServiceUser() {
        this.con = DatabaseConnection.getInstance().getConnection();
        user_Id = 0;
    }

    public Model_Message register(Model_Register data) {
        //  Check user exit
        Model_Message message = new Model_Message();
        try {
            PreparedStatement p = con.prepareStatement(CHECK_USER, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            p.setString(1, data.getUserName());
            ResultSet r = p.executeQuery();
            if (r.first()) {
                message.setAction(false);
                message.setMessage("User Already Exit");
            } else {
                message.setAction(true);
            }
            r.close();
            p.close();
            if (message.isAction()) {
                //  Insert User Register
//                con.setAutoCommit(false);
                p = con.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                p.setString(1, data.getUserName());
                p.setString(2, data.getPassword());
                p.execute();
                r = p.getGeneratedKeys();
                r.first();
                user_Id = r.getInt(1);
                r.close();
                p.close();
                //  Create user account
//                p = con.prepareStatement(INSERT_USER_ACCOUNT);
//                p.setInt(1, userID);
//                p.setString(2, data.getUserName());
//                p.execute();
//                p.close();
                
//                con.commit();
//                con.setAutoCommit(true);
                message.setAction(true);
                
                message.setMessage("Successful account registration");
            }
        } catch (SQLException e) {
            message.setAction(false);
            message.setMessage("Server Error");
            e.printStackTrace();
            try {
                if (con.getAutoCommit() == false) {
                    con.rollback();
                    con.setAutoCommit(true);
                }
            } catch (SQLException e1) {
            }
        }
        return message;
    }
    
    public void registerInfo(Model_User_Account data) {
        try {
          PreparedStatement p = con.prepareStatement(INSERT_USER_ACCOUNT);
          p.setInt(1, user_Id);
          p.setString(2, data.getUserName());
          p.setString(3, data.getFullName());
          p.setString(4, data.getEmail());
          p.setString(5, data.getPhone());
          p.setString(6, data.getAddress());
          p.setString(7, data.getUserName());
          p.execute();
          p.close();
          writeFile(data.getUserName() + "|" + data.getAvatar_path(), true);
//          writeFile2(data.getUserName(), data.getAvatar_path());
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    public void updateInfo(Model_User_Account data) {
        try {
          PreparedStatement p = con.prepareStatement(UPDATE_USER_INFO);
          p.setString(1, data.getFullName());
          p.setString(2, data.getEmail());
          p.setString(3, data.getPhone());
          p.setString(4, data.getAddress());
          p.setString(5, data.getUserName());
          p.setInt(6, user_Id);
          p.execute();
          p.close();
        	
          StringBuilder ans = new StringBuilder("");
            try {
                FileReader fr = new FileReader("src/connection/DataImage");
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                	String[] str = line.split("\\|");
                	String userName = str[0];
                	String pathImage = str[1];
                    if(!userName.equals(data.getUserName())) {
                    	ans.append(line + "\n");
                    }
                }
                br.close();
            } catch (Exception e) {
                System.err.println("Đã xảy ra lỗi khi đọc tập tin: " + e.getMessage());
            }
            ans.append(data.getUserName() + "|" + data.getAvatar_path() + "\n");
        	writeFile(ans.toString(), false);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public Model_Message login(Model_Login login){
    	Model_Message message = new Model_Message();
    	try {
            Model_User_Account data = null;
            PreparedStatement p = con.prepareStatement(CHECK_LOGIN, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            p.setString(1, login.getUserName());
            p.setString(2, login.getPassword());
            ResultSet r = p.executeQuery();
            if (r.first()) {
                message.setAction(true);
                
                message.setMessage("Logged in successfully");
            }
            else {
            	message.setAction(false);
            	message.setMessage("Account or password is incorrect");
            }
            if(message.isAction()) {
            	user_Id = r.getInt(1);
            }
            r.close();
            p.close();
            
            return message;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loi o day ha");
			return null;
		}
    }
    
    public Model_User_Account loadLogin(){
    	try {
    		if(user_Id != 0) {
                Model_User_Account data = null;
                PreparedStatement p = con.prepareStatement(LOGIN);
                p.setInt(1, user_Id);
                ResultSet r = p.executeQuery();
                if (r.next()) {
                    int userID = r.getInt(1);
                    String userName = r.getString(2);
                    String fullName = r.getString(3);
                    String email = r.getString(4);
                    String phone = r.getString(5);
                    String address = r.getString(6);
                    String avatar_path = readFile(r.getString(7));
//                    String avatar_path = readFile2(r.getString(7));
                    data = new Model_User_Account(userID, userName, fullName, email, phone, address, avatar_path, true);
                    return data;
                }
                r.close();
                p.close();
                return data;
    		}
    		else {
    			return null;
    		}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public List<Model_User_Account> getUser() {
        List<Model_User_Account> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_USER_ACCOUNT);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                int userID = r.getInt(1);
                String userName = r.getString(2);
                String fullName = r.getString(3);
                String email = r.getString(4);
                String phone = r.getString(5);
                String address = r.getString(6);
                String avatar_path = readFile(r.getString(7));
//                String avatar_path = readFile2(r.getString(7));
                list.add(new Model_User_Account(userID, userName, fullName, email, phone, address, avatar_path, true));
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
    }
    
    public boolean checkContact(int user_Id2) {
    	boolean check = false;
    	try {
            PreparedStatement p = con.prepareStatement(SELECT_CHECK_CONTACT);
            p.setInt(1, user_Id);
            p.setInt(2, user_Id2);
            p.setInt(3, user_Id2);
            p.setInt(4, user_Id);
            ResultSet r = p.executeQuery();
            if (r.next()) {
            	check = true;
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return check;
    }
    
	public void writeFile(String data, boolean append) {
        try {
            FileWriter fw = new FileWriter("src/connection/DataImage", append);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data +"\n");
            bw.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void writeFile2(String user, String data) {
        try {
            FileWriter fw = new FileWriter("src/imageUser/" + user);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public String readFile(String user) {
        try {
            FileReader fr = new FileReader("src/connection/DataImage");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
            	String[] str = line.split("\\|");
            	String userName = str[0];
            	String pathImage = str[1];
                if(userName.equals(user)) {
                	return pathImage;
                }
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi đọc tập tin: " + e.getMessage());
        }
        return null;
	}
	
    public String readFile2(String user) {
        String data = "";
        try {
            FileReader fr = new FileReader("src/imageUser/" + user);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                data = line;
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi đọc file: " + e.getMessage());
        }
        return data;
    }
}
