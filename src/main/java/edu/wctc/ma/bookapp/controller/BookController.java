/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.ma.bookapp.controller;

import edu.wctc.ma.bookapp.components.Author;
import edu.wctc.ma.bookapp.components.Book;
import edu.wctc.ma.bookapp.service.AbstractFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
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
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

    
    // NO MAGIC NUMBERS!
    private static final String NO_PARAM_ERR_MSG = "No request parameter identified";
    private static final String LIST_PAGE = "/listBooks.jsp";
    private static final String ADD_EDIT_BOOKS_PAGE = "/addEditBooks.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_EDIT_DELETE_ACTION = "addEditDelete";
    private static final String SUBMIT_ACTION = "submit";
    private static final String ADD_EDIT_ACTION = "addEdit";
    private static final String ACTION_PARAM = "action";
    private static final String DELETE_ACTION = "delete";

    @Inject
    private AbstractFacade<Book> bookService;
    @Inject
    private AbstractFacade<Author> authService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String destination = LIST_PAGE;
        String action = request.getParameter(ACTION_PARAM);
        Book book = null;

        try {
          
            switch (action) {
                case LIST_ACTION:
                    this.refreshBookList(request, bookService);
                    this.refreshAuthorList(request, authService);
                    destination = LIST_PAGE;
                    break;

                case DELETE_ACTION:
                    String deleteId =  request.getParameter("bookId");
                            book = bookService.find(new Integer(deleteId));
                            bookService.remove(book);
                        

                        this.refreshBookList(request, bookService);
                        this.refreshAuthorList(request, authService);
                        destination = LIST_PAGE;
                    
                    break;
                    
                case ADD_EDIT_ACTION:
                    String title = request.getParameter("inputTitle");
                    String isbn = request.getParameter("inputIsbn");
                    String authorId = request.getParameter("authorId");
                    String bookId = request.getParameter("bookId");
                    
                    if(bookId == null) {
                        book = new Book(0);
                        book.setTitle(title);
                        book.setIsbn(isbn);
                        Author author = null;
                        if(authorId != null) {
                            author = authService.find(new Integer(authorId));
                            
                            book.setAuthorId(author);
                        }
                    } else {
                         authorId = request.getParameter("inputAuthorId");
                        book = bookService.find(new Integer(bookId));
                        book.setTitle(title);
                        book.setIsbn(isbn);
                        Author author = null;
                        if(authorId != null) {
                            author = authService.find(new Integer(authorId));
                            book.setAuthorId(author);
                        }
                    }
                    bookService.edit(book);
                    this.refreshBookList(request, bookService);
                    this.refreshAuthorList(request, authService);
                    destination = LIST_PAGE;
                    break;
                default:
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

    // Avoid D-R-Y
    private void refreshBookList(HttpServletRequest request, AbstractFacade<Book> bookService) throws Exception {
        List<Book> books = bookService.findAll();
        request.setAttribute("books", books);
    }
    
    private void refreshAuthorList(HttpServletRequest request, AbstractFacade<Author> authService) throws Exception {
        List<Author> authors = authService.findAll();
        request.setAttribute("authors", authors);
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
