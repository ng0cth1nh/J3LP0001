/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AnswerDao;
import dao.QuestionDao;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Answer;
import model.Question;
import model.User;

/**
 *
 * @author Vu Ngoc Thinh
 */
public class MakeQuizServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.getRequestDispatcher("view/makeQuiz.jsp").forward(request, response);
        } catch (IOException e) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            QuestionDao qDao = new QuestionDao();
            AnswerDao aDao = new AnswerDao();

            String question = request.getParameter("question");
            String option1 = request.getParameter("option1");
            String option2 = request.getParameter("option2");
            String option3 = request.getParameter("option3");
            String option4 = request.getParameter("option4");

            String qId = qDao.generateRandomID(10);
            //generate random and unique id for question
            String[] aIds = aDao.generateRandomIDs(10);
            //generate 4 random and unique ids for each answer

            ArrayList<Answer> answers = new ArrayList<>();
            //initialize array list answer
            
            answers.add(new Answer(aIds[0], option1, checkBoxValue(request.getParameter("op1"))));
            answers.add(new Answer(aIds[1], option2, checkBoxValue(request.getParameter("op2"))));
            answers.add(new Answer(aIds[2], option3, checkBoxValue(request.getParameter("op3"))));
            answers.add(new Answer(aIds[3], option4, checkBoxValue(request.getParameter("op4"))));
            

            long millis = System.currentTimeMillis();
            //get current time measured in milliseconds, between the current time and midnight, January 1, 1970 UTC
            Date date = new Date(millis);
            //init current date
            Question q = new Question(qId, question, date, answers);

            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");

            qDao.insertQuestion(q, user.getUserName());
            
            response.sendRedirect("manage-quiz");
            
        } catch (IOException e) {
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
        }

    }

    /**
     * 
     * @param value
     * @return boolean
     */
    public boolean checkBoxValue(String value) {
        if (value == null) {
            //value of checkbox is null checked then return false
            return false;
        } else {
            //value of checkbox is not null checked then return true
            return value.equals("true");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
