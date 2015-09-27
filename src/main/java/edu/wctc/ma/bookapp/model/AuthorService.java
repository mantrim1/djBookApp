package edu.wctc.ma.bookapp.model;

import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jlombardo
 */
public class AuthorService {
    private AuthorDaoStrategy dao;
    private final List<String> columns = new ArrayList<> ();
    private final List<String> values = new ArrayList<>();
    private static final String TABLE_NAME = "author";
    private static final Date TODAY = Calendar.getInstance().getTime();
    private static final Format MYSQL_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
      private static final String AUTHOR_NAME = "author_name";
    private static final String ADDED_DATE = "create_date";
    private static final String ID = "author_id";
    // purpose is encapuslation of methods
    public AuthorService(AuthorDaoStrategy dao) {
        this.dao = dao;

    }
    
    public List<Author> getAllAuthors() throws Exception {
        return dao.getAllAuthors();
    }
    public int insertAuthor(String authorName)throws Exception{
         values.clear();
         columns.clear();
        columns.add(AUTHOR_NAME);
        values.add(authorName);
       
        columns.add(ADDED_DATE);
         values.add(MYSQL_FORMAT.format(TODAY));
        return dao.insertAuthors(TABLE_NAME, columns, values);
    }
    public int deleteAuthor(String id) throws Exception{
        int idInt = Integer.parseInt(id);
        return dao.deleteAuthor(TABLE_NAME, ID, id);
    }
    public int updateAuthor(String id, String authorName) throws Exception{
        int idInt = Integer.parseInt(id);
        values.clear();
        columns.clear();
        columns.add(AUTHOR_NAME);
        values.add(authorName);
        return dao.updateRecords(TABLE_NAME, ID, idInt, columns, values);
    }
    public static void main(String[] args) {
        
    }
}
