/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.ma.bookapp.controller;

import edu.wctc.ma.bookapp.model.Author;
import edu.wctc.ma.bookapp.model.AuthorDao;
import edu.wctc.ma.bookapp.model.AuthorDaoStrategy;
import edu.wctc.ma.bookapp.model.AuthorService;
import edu.wctc.ma.bookapp.model.DBStrategy;
import edu.wctc.ma.bookapp.model.MySqlDbStrategy;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mark
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {
    
 private static final String NO_PARAM_ERR_MSG = "No request parameter identified";

    private static final String LIST_PAGE = "/listAuthors.jsp";
    private static final String ADD_PAGE = "/addAuthor.jsp";
    private static final String DELETE_PAGE = "/deleteAuthor.jsp";
    private static final String UPDATE_PAGE = "/updateAuthor.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "add";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";
    private static final String ACTION_PARAM = "action";
  
    private static final String ID_PARAM = "inputId";
    private static final String NAME_PARAM = "inputName";

    private int col_affected;

    

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String destination = LIST_PAGE;
        String action = request.getParameter(ACTION_PARAM);

        DBStrategy db = new MySqlDbStrategy();
        AuthorDaoStrategy authorDao
                = new AuthorDao(db, "com.mysql.jdbc.Driver",
                        "jdbc:mysql://localhost:3306/books", "root", "admin");
        AuthorService authorService = new AuthorService(authorDao);

        try {
          
//            Context ctx = new InitialContext();
//            DataSource ds = (DataSource)ctx.lookup("jdbc/book");
//            AuthorDaoStrategy authDao = new ConnPoolAuthorDao(ds, new MySqlDbStrategy());
//            AuthorService authService = new AuthorService(authDao);

           
            if (action.equals(LIST_ACTION)) {
                List<Author> authors = null;
                authors = authorService.getAllAuthors();
                System.out.println(authors.size() + " Records");
                request.setAttribute("authors", authors);
                request.setAttribute("count", authors.size());
                destination = LIST_PAGE;

            } else if (action.equals(ADD_ACTION)) {
               
              
                
                try{
                this.col_affected = authorService.insertAuthor( request.getParameter(NAME_PARAM));
                request.setAttribute("updated", col_affected + " Records Added");
                }catch(Exception e){
                    request.setAttribute("updated", "An error occured");
                }
                destination = ADD_PAGE;
            
            } else if (action.equals(DELETE_ACTION)) {
               try{
                   this.col_affected = authorService.deleteAuthor(request.getParameter(ID_PARAM));
                   request.setAttribute("updated", col_affected + " Records Deleted");
               }catch(Exception ex){
                    request.setAttribute("updated", "An error occured");
               }
               destination = DELETE_PAGE;
            } else if (action.equals(UPDATE_ACTION)) {
                try{
                    this.col_affected = authorService.updateAuthor(request.getParameter(ID_PARAM), request.getParameter(NAME_PARAM));
                    request.setAttribute("updated", col_affected + " Records Updated");
                }catch(Exception e){
                    request.setAttribute("updated", "An error occured " + e.getMessage());
                }
                destination = UPDATE_PAGE;
            } else {
                // no param identified in request, must be an error
                request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
                destination = LIST_PAGE;
            }
            
        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }

        // Forward to destination page
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
