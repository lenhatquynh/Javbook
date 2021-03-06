package com.group1.model.dao;

import com.group1.model.Friend;
import com.group1.model.ProfileUserAbout;
import static com.group1.model.db.SQLConnector.SQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dang Minh Canh
 */
public class FriendDAO {
    
    // Canh
    public static boolean isFriend(int accountID, int accountID2){
        try {
            String query = "SELECT * "
                    + "FROM Friend "
                    + "WHERE A = ? "
                    + "AND B = ?";
            
            if (accountID > accountID2){
                int tmp = accountID;
                accountID = accountID2;
                accountID2 = tmp;
            }
            PreparedStatement ps = SQL.prepareStatement(query); // nem cau lenh query tu netbeans sang sql server
            ps.setInt(1, accountID);
            ps.setInt(2, accountID2);
            
            ResultSet rs = ps.executeQuery(); // execute query va nhan ket qua tra ve
            
            return rs.isBeforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
       
    public static void deleteFriend(int accountID, int accountID2) {
        try {
            String query = "DELETE FROM Friend " 
                         +  "WHERE A = ? "
                         +  "AND B = ?";
            
            if (accountID > accountID2){
                int tmp = accountID;
                accountID = accountID2;
                accountID2 = tmp;
            }
            
            PreparedStatement ps = SQL.prepareStatement(query);
            
            ps.setInt(1, accountID);
            ps.setInt(2, accountID2);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FriendDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    

    public static void requestFriend(int receiverID, int senderID) {
  
        try {
            String query = "INSERT INTO NotificationFriend "
                    + "VALUES (?, ? , ?)";
            
            PreparedStatement ps = SQL.prepareStatement(query);
            ps.setInt(1, receiverID);
            ps.setInt(2, senderID);

            Timestamp time = new Timestamp(System.currentTimeMillis());
            ps.setTimestamp(3, time);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FriendDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public static boolean isRequestedFriend(int receiverID, int senderID) {
        try {
            String query = "SELECT * "
                    + "FROM NotificationFriend "
                    + "WHERE ReceiverID = ? "
                    + "AND SenderID = ?";
           
            PreparedStatement ps = SQL.prepareStatement(query); // nem cau lenh query tu netbeans sang sql server
            ps.setInt(1, receiverID);
            ps.setInt(2, senderID);

            ResultSet rs = ps.executeQuery(); // execute query va nhan ket qua tra ve
 
            return rs.isBeforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
 
    // Quynh
    public static void insertFriend(int A, int B) {
        try {
            String sql = "insert into Friend values(?,?,?)";
            PreparedStatement ps = SQL.prepareStatement(sql);
            if (A < B) {
                ps.setInt(1, A);
                ps.setInt(2, B);
            } else {
                ps.setInt(1, B);
                ps.setInt(2, A);
            }
            Timestamp time = new Timestamp(System.currentTimeMillis());
            ps.setTimestamp(3, time);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Can not insert friend to database!");
        }
    }

    public static List<ProfileUserAbout> getListFriend(int userid) {
        try {
            List<ProfileUserAbout> list = new ArrayList<>();
            String sql = "select *\n"
                    + "from friend f\n"
                    + "where f.A = ? or f.B =?";
            PreparedStatement ps = SQL.prepareStatement(sql);
            ps.setInt(1, userid);
            ps.setInt(2, userid);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                return null;

            } else {
                while (rs.next()) {

                    if (rs.getInt(1) == userid) {
                        list.add(ProfileUserAboutDAO.getProfileUser(rs.getInt(2)));
                    } else if (rs.getInt(2) == userid) {
                        list.add(ProfileUserAboutDAO.getProfileUser(rs.getInt(1)));
                    }
                }
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Can not get list friend!");
        }
        return null;
    }
}
