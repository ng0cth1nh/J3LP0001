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
import java.util.ArrayList;
import java.util.Random;
import model.Answer;
import model.Question;

/**
 *
 * @author Vu Ngoc Thinh
 */
public class QuestionDao extends DBContext {

    AnswerDao aDao = new AnswerDao();

    /**
     *
     * @param pageIndex
     * @param size
     * @return questions of corresponding page
     * @throws java.lang.Exception
     */
    public ArrayList<Question> getQuestions(int pageIndex, int size) throws Exception {

        ArrayList<Question> list = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            list = new ArrayList<>();
            String sql = "DECLARE @PageIndex int;\n"
                    + "DECLARE @PageSize int;\n"
                    + "SET @PageIndex = ?;\n"
                    + "SET @PageSize = ?;\n"
                    + "With ranked AS\n"
                    + "(\n"
                    + "   SELECT ROW_NUMBER() OVER(ORDER BY qid) AS RowNum,  *\n"
                    + "   FROM Question\n"
                    + ")\n"
                    + "SELECT *\n"
                    + "FROM Ranked\n"
                    + "WHERE RowNum BETWEEN ((@PageIndex - 1) * @PageSize + 1) AND (@PageIndex * @PageSize)";

            con = getConnection();
            st = con.prepareStatement(sql);
            st.setInt(1, pageIndex);
            st.setInt(2, size);
            rs = st.executeQuery();
            while (rs.next()) {
                //loop to each item of the result set
                list.add(new Question(rs.getString("qid"),
                        rs.getString("content"),
                        rs.getDate("created")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(con, st, rs);
        }
        return list;
    }

    /**
     *
     * @return quantity of all questions
     * @throws java.lang.Exception
     */
    public int countQuestion() throws Exception {
        int num = 0;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT COUNT(qid) AS NumberOfQues FROM Question";
            con = getConnection();
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                //loop to each item of the result set
                num = rs.getInt(1);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(con, st, rs);
        }
        return num;
    }

    /**
     *
     * @param pageSize
     * @return quantity of all page with corresponding page size
     * @throws java.lang.Exception
     */
    public int getTotalPage(int pageSize) throws Exception {
        int total = countQuestion();
        if (total % pageSize == 0) {
            //return number of page when total % pageSize
            return total / pageSize;
        } else {
            //return number of page when total % pageSize then add to 1
            return (total / pageSize) + 1;
        }
    }

    /**
     *
     * @param id
     * @return whether id is duplicate in database
     * @throws java.lang.Exception
     */
    public boolean checkQuestionId(String id) throws Exception {

        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Question WHERE qid = ?\n";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                //loop to each item of the result set
                result = true;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(con, st, rs);
        }
        return result;
    }

    /**
     *
     * @param number
     * @return random answer with corresponding number
     * @throws java.lang.Exception
     */
    public ArrayList<Question> getQuestions(int number) throws Exception {

        ArrayList<Question> list = null;
        ArrayList<Answer> answerList = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            list = new ArrayList<>();
            String sql = "SELECT TOP (?) NEWID() as rowId, * FROM Question\n"
                    + "ORDER BY rowId";

            con = getConnection();
            st = con.prepareStatement(sql);
            st.setInt(1, number);
            rs = st.executeQuery();
            while (rs.next()) {
                //loop to each item of the result set
                String qid = rs.getString("qid");
                Question q = new Question(qid, rs.getString("content"), rs.getDate("created"));
                answerList = aDao.getAnswers(qid);
                //get all answers of that question follow qid
                q.setAnswers(answerList);
                list.add(q);
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(con, st, rs);
        }
        return list;
    }

    /**
     *
     * @param qid
     * @throws java.lang.Exception
     */
    public void deleteQuestion(String qid) throws Exception {

        Connection con = null;
        PreparedStatement st = null;

        try {
            con = getConnection();
            String sql = "DELETE FROM Question WHERE qid = ?";
            aDao.deleteAnswer(qid);
            st = con.prepareStatement(sql);
            st.setString(1, qid);
            st.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(con, st);
        }
    }

    /**
     *
     * @param q
     * @param userName
     * @throws java.lang.Exception
     */
    public void insertQuestion(Question q, String userName) throws Exception {

        Connection con = null;
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO [dbo].[Question]\n"
                    + "           ([qid]\n"
                    + "           ,[userName]\n"
                    + "           ,[content]\n"
                    + "           ,[created])\n"
                    + "     VALUES\n"
                    + "             (?,\n"
                    + "              ?,\n"
                    + "              ?,\n"
                    + "              ?)";
            con = getConnection();
            st = con.prepareStatement(sql);
            st.setString(1, q.getId());
            st.setString(2, userName);
            st.setString(3, q.getContent());
            st.setDate(4, q.getCreated());
            st.executeUpdate();
            for (Answer answer : q.getAnswers()) {
                //insert each answer of that question to database
                aDao.insertAnswer(answer, q.getId());
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closeConnection(con, st);
        }
    }

    /**
     *
     * @param length
     * @return random question id
     * @throws java.lang.Exception
     */
    public String generateRandomID(int length) throws Exception {
        Random rand = new Random();
        String str = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        String rs = "";
        while (true) {
            for (int i = 0; i < length; i++) {
                //loop from 0 to length of question id
                sb.append(str.charAt(rand.nextInt(str.length())));
            }
            rs = sb.toString();
            try {
                if (!checkQuestionId(rs)) {
                    //to check id is unique in database
                    break;
                }
            } catch (Exception e) {
                throw e;
            }

        }
        return rs;
    }
}
