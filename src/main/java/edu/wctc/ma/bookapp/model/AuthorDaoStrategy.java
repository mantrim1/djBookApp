package edu.wctc.ma.bookapp.model;

import java.util.List;

/**
 *
 * @author jlombardo
 */
public interface AuthorDaoStrategy {

    List<Author> getAllAuthors() throws Exception;
    
}
