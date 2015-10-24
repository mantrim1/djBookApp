/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.ma.bookapp.controller;


import edu.wctc.ma.bookapp.components.Author;
import edu.wctc.ma.bookapp.service.AuthorFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mark
 */

@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {
    
 private static final String NO_PARAM_ERR_MSG = "No request parameter identified";

    private static final String LIST_PAGE = "/listAuthors.jsp";
    private static final String LIST_CONTROL = "AuthorController?action=list";
    private static final String ADD_PAGE = "/addAuthor.jsp";
    private static final String DELETE_PAGE = "/deleteAuthor.jsp";
    private static final String UPDATE_PAGE = "/updateAuthor.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "add";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";
    private static final String ACTION_PARAM = "action";
    private static final String REDIRECT_PARAM ="nav";
    private static final Date TODAY = Calendar.getInstance().getTime();
    private static final Format MYSQL_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private int count;
    private static final String ID_PARAM = "inputId";
    private static final String NAME_PARAM = "inputName";
    private static final String HOME_PARAM = "home";
    private static final String HOME_PAGE ="/bookApp";
    @Inject
    private AuthorFacade authorService;

    private int col_affected;

    

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
       
        ServletContext ctx = request.getServletContext();
        
        if(null == session.getAttribute("sessionRows")){
            count=0;
        }else{
            try{
            count=(int) session.getAttribute("sessionRows");
            }catch(Exception ex){
                count=0;
            }
        }
        
        
       
       
        String destination = LIST_PAGE;
        String action = request.getParameter(ACTION_PARAM);  
       
        Author auth;

        try {
          
//            Context ctx = new InitialContext();
//            DataSource ds = (DataSource)ctx.lookup("jdbc/book");
//            AuthorDaoStrategy authDao = new ConnPoolAuthorDao(ds, new MySqlDbStrategy());
//            AuthorService authService = new AuthorService(authDao);
            switch (action) {
                case LIST_ACTION:
                    List<Author> authors = authorService.findAll();
                    System.out.println(authors.size() + " Records");
                    request.setAttribute("authors", authors);
                    request.setAttribute("count", authors.size());
                    destination = LIST_PAGE;
                    
                    break;
                case ADD_ACTION:
                    try{
                        auth = new Author();
                        
                        auth.setAuthorName(request.getParameter(NAME_PARAM));
                      auth.setCreateDate(TODAY);
                        
                        authorService.edit(auth);
                        request.setAttribute("updated", col_affected + " Records Added");
                                            count+=this.col_affected;

                    }catch(Exception e){
                        request.setAttribute("updated", "An error occured");
                        
                    }   destination = ADD_PAGE;
                    session.setAttribute("sessionRows", count);
                    break;
                case DELETE_ACTION:
                    try{
                       auth = authorService.find(new Integer(request.getParameter(ID_PARAM)));
                        authorService.remove(auth);
                        request.setAttribute("updated", col_affected + " Records Deleted");
                         count+=this.col_affected;
                    }catch(Exception ex){
                        request.setAttribute("updated", "An error occured");
               }    destination = DELETE_PAGE;
               session.setAttribute("sessionRows", count);
                    break;
                case UPDATE_ACTION:
                    try{
                        auth = authorService.find(new Integer(request.getParameter(ID_PARAM)));
                        authorService.edit(auth);
                        request.setAttribute("updated", col_affected + " Records Updated");
                         count+=this.col_affected;
                    }catch(Exception e){
                        request.setAttribute("updated", "An error occured " + e.getMessage());
                }   destination = UPDATE_PAGE;
                session.setAttribute("sessionRows", count);
                break;
                case REDIRECT_PARAM:
                    
                        String goTo = request.getParameter("dest");
                        switch (goTo){
                            case HOME_PARAM:
                                response.sendRedirect(HOME_PAGE);
                                return;
                           
                                
                        }
                        
                break;
                default:
                    // no param identified in request, must be an error
                    request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
                    destination = LIST_PAGE;
                    break;
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
