import java.io.IOException;
import java.util.Map;

import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.PublisherDao;
import com.smoothstack.lms.model.Author;
import com.smoothstack.lms.model.Book;
import com.smoothstack.lms.model.Publisher;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BookDaoTests {

    @Test
    public void loadMap() throws IOException {
        Map<String, Book> bookMap;
        bookMap = BookDao.createMap();
        bookMap.forEach((key,book)->{
            System.out.println(book.getTitle()+";"+book.getIsbn()+";");
        });
    }

    @Test
    public void daoShow(){
        BookDao.show();

    }

    @Test
    public void MapHasData() throws IOException {
        Map<String, Book> bookMap = BookDao.createMap();
        assertTrue("Map has no data", bookMap.size() > 0);
    }

    @Test
    public void BookDaoAdd(){
        AuthorDao.add(new Author("TestAuthor",99));
        PublisherDao.add(new Publisher("TestPub","TestAddy",99,99));
        BookDao.add(new Book("TestAdd",99,99,99));
        BookDao.show();
    }

    @Test
    public void BookDaoUpdate(){
        BookDao.update(new Book("TestUpdate",99,99,99));
        BookDao.show();
    }
    @Test
    public void BookDaoUpdateId(){
        BookDao.updateById(new Book("TestUpdate",101,99,99),99);
        BookDao.show();
    }
    @Test
    public void BookDaoDelete(){
        BookDao.delete(new Book("TestUpdate",101,99,99));
        BookDao.show();
    }

}
