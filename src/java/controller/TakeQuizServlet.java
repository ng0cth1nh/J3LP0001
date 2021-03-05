/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDao;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TakeQuizServlet extends HttpServlet {

    private long duration, startTime;
    private ArrayList<Question> questions;
    private static long DELAYTIME = 1000;
    //suppose that delaytime of server response to client and the client
    //response to server when taking quiz is 1000 milliseconds

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
        response.setContentType("text/html;charset=UTF-8");
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
            QuestionDao qDao = new QuestionDao();
            if (request.getParameter("number") == null) {
                //the number parameter is not requested then return to entering number quiz page
                request.setAttribute("maxQuiz", qDao.countQuestion());
                request.getRequestDispatcher("view/enterNumQuiz.jsp").forward(request, response);
            } else {
                //the number parameter is requested then return to taking quiz page
                int number = Integer.parseInt(request.getParameter("number"));
                

                duration = number * 60 * 1000 + DELAYTIME;
                //set duration time measured in milliseconds is the limit time to take all quizzes for user
                //with that number of question
                this.questions = qDao.getQuestions(number);
                //assign questions to calculate result after

                request.setAttribute("questions", this.questions);
                request.setAttribute("quantityOfQues", String.format("%02d", number));
                request.getRequestDispatcher("view/takeQuiz.jsp").forward(request, response);

                startTime = System.currentTimeMillis();
                //start time when user taking the quiz is ticked
            }
        } catch (Exception e) {
            request.setAttribute("error", e);
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
            String result = request.getParameter("result");
            System.out.println("result=" + result);
            long currentTime = System.currentTimeMillis();
            //get current time when user sent result of quiz to server
            long takingTime = (currentTime - startTime);
            //takingTime is the time of the user taking all of the quizzes
            if (takingTime > duration) {
                //takingTime is greater than duration so that result is not accepted.
                request.setAttribute("mess", "Your result has been rejected!");
                request.getRequestDispatcher("view/result.jsp").forward(request, response);
            } else {
                float score = (float) (calculateScore(result.split(" ")) * 10.0);
                //calculate the score
                DecimalFormat df = new DecimalFormat("#.#");

                String formatted = df.format(score);
                request.setAttribute("mark", Float.parseFloat(formatted));

                request.setAttribute("percent", Float.parseFloat(formatted) * 10);
                //calculated the percent of the result
                String isPass = score < 5.0 ? "Not Pass" : "Pass";
                //is pass or not
                request.setAttribute("isPass", isPass);
                request.getRequestDispatcher("view/result.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("view/error.jsp").forward(request, response);
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

    /**
     * 
     * @param results
     * @return score
     * Purpose: calculate the score for user
     */
    public float calculateScore(String[] results) {
        int num = 0;
        ArrayList<String> trueAnswerString = new ArrayList<>();
        ArrayList<String> falseAnswerString = new ArrayList<>();
        for (Question question : questions) {
            //loop each question to check correct or not
            for (Answer answer : question.getAnswers()) {
                //loop each answer of each question to get each false and true answer
                if (answer.isIsTrue() == true) {
                    //get each true answer then push to the true answer arraylist
                    trueAnswerString.add(answer.getId());
                } else {
                    //get each true answer then push to the false answer arraylist
                    falseAnswerString.add(answer.getId());
                }
            }
            if (checkAnswer(results, trueAnswerString, falseAnswerString)) {
                //then check that answer whether correct or not in each corresponding question
                num++;
            }
            trueAnswerString.removeAll(trueAnswerString);
            //remove all true and false answer array list for next question
            falseAnswerString.removeAll(falseAnswerString);
        }

        return (float) num / questions.size();
    }

    /**
     * 
     * @param results
     * @param trueAnswerString
     * @param falseAnswerString
     * @return boolean
     * Purpose: to check each answer is correct or not in each question
     */
    public boolean checkAnswer(String[] results, ArrayList<String> trueAnswerString, ArrayList<String> falseAnswerString) {
        
        int numOfTrueAnswer = 0;
        
        for (String falseA : falseAnswerString) {
            //loop each false answer of each question
            for (String result : results) {
                //loop each result string of all answer that client sent to server
                if (falseA.equals(result)) {
                    //if one of all answer is in false answer of that question
                    // then return that answer of question is false
                    return false;
                }
            }
        }

        for (String trueA : trueAnswerString) {
            //loop each true answer of each question
            for (String result : results) {
                //loop each result string of all answer that client sent to server
                if (trueA.equals(result)) {
                    //if one of all answer is in false answer of that question
                    // then return numOfTrueAnswer increase to 1
                    numOfTrueAnswer++;
                }
            }
        }

        return numOfTrueAnswer == trueAnswerString.size();
        //return whether quantity of true answer that question is enough or not
    }

}
