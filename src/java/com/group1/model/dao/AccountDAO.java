package com.group1.model.dao;

import com.group1.model.Account;
import static com.group1.model.db.SQLConnector.SQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dang Minh Canh
 */
public class AccountDAO {
 
    public static boolean isAccountExists(String username, String password){
        try {
            String query = "SELECT * "
                    + "FROM AccountUser "
                    + "WHERE Username = ? "
                    + "AND Password = ?";
            PreparedStatement ps = SQL.prepareStatement(query); // nem cau lenh query tu netbeans sang sql server
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery(); // execute query va nhan ket qua tra ve
            
            return rs.isBeforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static void createNewAccount(String username, String password) {
        try {
            String query = "INSERT INTO AccountUser(Username, Password, Phone, Email) "
                    + "VALUES (?, ?, '0123456789', 'email@gmail.com');";
            
            PreparedStatement ps = SQL.prepareStatement(query); // nem cau lenh query tu netbeans sang sql server
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean isAvailableUsername(String username) {
        try {
            String query = "SELECT * "
                    + "FROM AccountUser "
                    + "WHERE Username = ?;";
            PreparedStatement ps = SQL.prepareStatement(query); // nem cau lenh query tu netbeans sang sql server
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery(); // execute query va nhan ket qua tra ve

            return !rs.isBeforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static Account getAccountByUsername(String username) {
        try {
            String query = "SELECT * "
                    + "FROM AccountUser "
                    + "WHERE Username = ?;";
            PreparedStatement ps = SQL.prepareStatement(query); // nem cau lenh query tu netbeans sang sql server
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery(); // execute query va nhan ket qua tra ve
            
            // if do not have account
            if (!rs.isBeforeFirst()){
                return null;
            } else {
                // if have account
                rs.next();
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getBoolean(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Account getAccount(int accountID) {
        try {
            String query = "SELECT * "
                    + "FROM AccountUser "
                    + "WHERE AccountUserID = ?;";
            PreparedStatement ps = SQL.prepareStatement(query); // nem cau lenh query tu netbeans sang sql server
            ps.setInt(1, accountID);
            
            ResultSet rs = ps.executeQuery(); // execute query va nhan ket qua tra ve
            
            // if do not have account
            if (!rs.isBeforeFirst()){
                return null;
            } else {
                // if have account
                rs.next();
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getBoolean(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void markFirstLogin(int accountId) {
        String query = "UPDATE AccountUser SET firstLogin=1 WHERE accountUserId=?";
        try {
            PreparedStatement ps;
            ps = SQL.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static boolean updatePhone(int accountID, String phone) {
        try {
            String query = "UPDATE AccountUser "
                    +  "SET Phone = ? "
                    +  "WHERE AccountUserID = ?";
            
            PreparedStatement ps = SQL.prepareStatement(query); // nem cau lenh query tu netbeans sang sql server
            ps.setString(1, phone);
            ps.setInt(2, accountID);
            
            int affectedRows = ps.executeUpdate(); // execute query va nhan ket qua tra ve
            return (affectedRows != 0);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileUserAboutDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }
    
    public static boolean updateEmail(int accountID, String email) {
        try {
            String query = "UPDATE AccountUser "
                    +  "SET Email = ? "
                    +  "WHERE AccountUserID = ?";
            
            PreparedStatement ps = SQL.prepareStatement(query); // nem cau lenh query tu netbeans sang sql server
            ps.setString(1, email);
            ps.setInt(2, accountID);
            
            int affectedRows = ps.executeUpdate(); // execute query va nhan ket qua tra ve
            return (affectedRows != 0);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileUserAboutDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean changePassword(int accountID, String password) {
        try {
            String query = "UPDATE AccountUser "
                    +  "SET Password = ? "
                    +  "WHERE AccountUserID = ?";
            
            PreparedStatement ps = SQL.prepareStatement(query); // nem cau lenh query tu netbeans sang sql server
            ps.setString(1, password);
            ps.setInt(2, accountID);
            
            int affectedRows = ps.executeUpdate(); // execute query va nhan ket qua tra ve
            return (affectedRows != 0);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileUserAboutDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
