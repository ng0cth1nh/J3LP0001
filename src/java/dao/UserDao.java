/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Vu Ngoc Thinh
 */
public class UserDao extends DBContext {

    /**
     *
     * @param userName
     * @param password
     * @return user
     */
    public User getUser(String userName, String password) {

        User user = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "select * from [User] where [userName]= ? and [password]= ?";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, password);
            rs = st.executeQuery();
            while (rs.next()) {
                //loop to each item of the result set
                user = new User(rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getBoolean("isTeacher"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null || st != null || rs != null) {
                //close connection before return 
                try {
                    rs.close();
                    st.close();
                    con.close();
                } catch (SQLException e) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return user;
    }

    /**
     *
     * @param userName
     * @return userName is existed or not
     */
    public boolean checkExist(String userName) {

        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "select * from [User] where [userName]= ?";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, userName);
            rs = st.executeQuery();
            while (rs.next()) {
                //loop to each item of the result set
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null || st != null || rs != null) {
                //close connection before return 
                try {
                    rs.close();
                    st.close();
                    con.close();
                } catch (SQLException e) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        return result;
    }

    /**
     * 
     * @param u 
     */
    public void insertUser(User u) {

        Connection con = null;
        PreparedStatement st = null;

        try {
            String sql = "INSERT INTO [dbo].[User]\n"
                    + "           ([userName]\n"
                    + "           ,[password]\n"
                    + "           ,[email]\n"
                    + "           ,[isTeacher])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, u.getUserName());
            st.setString(2, u.getPassword());
            st.setString(3, u.getEmail());
            st.setBoolean(4, u.isIsTeacher());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null || st != null) {
                 //close connection before return 
                try {
                    st.close();
                    con.close();
                } catch (SQLException e) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

    }

    /**
     * 
     * @param username
     * @param password
     * @return check username and password
     */
    public String checkUserAccount(String username, String password) {
        if (!checkExist(username)) {
            //username is incorrect then
            return "Account is incorrect!";
        } else if (getUser(username, password) == null) {
            //password is incorrext
            return "Wrong password";
        }
        //username and password is correct
        return "";
    }

}
