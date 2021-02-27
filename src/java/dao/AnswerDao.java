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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;

/**
 *
 * @author Vu Ngoc Thinh
 */
public class AnswerDao extends DBContext {

    /**
     *
     * @param qid
     * @return Purpose: get all answer of corresponding question
     */
    public ArrayList<Answer> getAnswers(String qid) {

        ArrayList<Answer> list = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            list = new ArrayList<>();
            String sql = "SELECT * FROM Answer WHERE qid= ?";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, qid);
            rs = st.executeQuery();
            while (rs.next()) {
                //loop to each item of the result set
                list.add(new Answer(rs.getString("aid"),
                        rs.getString(3),
                        rs.getBoolean("isTrue")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null || st != null || rs != null) {
                //close connection before return 
                try {
                    rs.close();
                    st.close();
                    con.close();
                } catch (SQLException e) {
                    Logger.getLogger(AnswerDao.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return list;
    }

    /**
     *
     * @param qid
     */
    public void deleteAnswer(String qid) {

        Connection con = null;
        PreparedStatement st = null;

        try {
            String sql = "DELETE FROM Answer WHERE qid = ?";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, qid);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null || st != null) {
                //close connection before return 
                try {
                    st.close();
                    con.close();
                } catch (SQLException e) {
                    Logger.getLogger(AnswerDao.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

    }

    /**
     *
     * @param id
     * @return
     */
    public boolean checkAnswerId(String id) {

        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Answer WHERE aid = ?\n";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                //loop to each item of the result set
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null || st != null || rs != null) {
                //close connection before return 
                try {
                    rs.close();
                    st.close();
                    con.close();
                } catch (SQLException e) {
                    Logger.getLogger(AnswerDao.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return result;
    }

    /**
     *
     * @param a
     * @param qid
     */
    public void insertAnswer(Answer a, String qid) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO [dbo].[Answer]\n"
                    + "           ([aid]\n"
                    + "           ,[qid]\n"
                    + "           ,[content]\n"
                    + "           ,[isTrue])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, a.getId());
            st.setString(2, qid);
            st.setString(3, a.getContent());
            st.setBoolean(4, a.isIsTrue());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null || st != null) {
                //close connection before return 
                try {
                    st.close();
                    con.close();
                } catch (SQLException e) {
                    Logger.getLogger(AnswerDao.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    /**
     *
     * @param lengthOfId
     * @return answer id
     */
    public String[] generateRandomIDs(int lengthOfId) {
        Random rand = new Random();
        String str = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(lengthOfId);
        HashSet<String> aIds = new HashSet();
        //hashet for each element is difference
        String[] rs = new String[4];
        //init array string with 4 elements cause question has 4 answer
        while (aIds.size() != 4) {
            //loop to answer id list has all 4 ids
            for (int i = 0; i < lengthOfId; i++) {
                //loop from 0 to length of answer id
                sb.append(str.charAt(rand.nextInt(str.length())));
            }
            if (!checkAnswerId(sb.toString())) {
                //to check id is unique in database
                aIds.add(sb.toString());
                //add to answer id list 
                sb.setLength(0);
                //set string builder length is 0 for next turn
            }
        }
        return aIds.toArray(rs);
    }
}
