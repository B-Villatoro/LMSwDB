import com.smoothstack.lms.dao.*;
import com.smoothstack.lms.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class BookCopiesDaoTests {

    @Test
    public void createData(){
        AuthorDao.add(new Author("TestAuthor",99));
        PublisherDao.add(new Publisher("TestPub","TestAddy",99,99));
        BookDao.add(new Book("TestAdd",99,99,99));

        LibraryDao.add(new Library(99,"Test Branch","Test Addy"));

        BorrowerDao.add(new Borrower(99,"Test name","Test Addy",111));



        AuthorDao.add(new Author("TestAuthor",101));
        PublisherDao.add(new Publisher("TestPub","TestAddy",101,101));
        BookDao.add(new Book("TestAdd",101,101,101));

        LibraryDao.add(new Library(101,"Test Branch","Test Addy"));

        BorrowerDao.add(new Borrower(101,"Test name","Test Addy",111));

    }

    @Test
    public void removeData(){

        BookDao.delete(new Book("TestAdd",101,101,101));
        AuthorDao.delete(new Author("TestAuthor",101));
        PublisherDao.delete(new Publisher("TestPub","TestAddy",101,101));
        LibraryDao.delete(new Library(101,"Test Branch","Test Addy"));
        BorrowerDao.delete(new Borrower(101,"Test name","Test Addy",111));

        BookDao.delete(new Book("TestAdd",99,101,101));
        AuthorDao.delete(new Author("TestAuthor",99));
        PublisherDao.delete(new Publisher("TestPub","TestAddy",101,99));
        LibraryDao.delete(new Library(99,"Test Branch","Test Addy"));
        BorrowerDao.delete(new Borrower(99,"Test name","Test Addy",111));

    }

    @Test
    public void daoShow() {
        BookCopiesDao.show();

    }

    @Test
    public void loadMap() {
        Map<String, BookCopies> bookCopiesMap;
        bookCopiesMap = BookCopiesDao.createMap();
        bookCopiesMap.forEach((key, bookCopy) -> {
            System.out.println("Key:"+key+";BookCopies:"+bookCopy.getIsbn() + ";" + bookCopy.getBranchId() + ";"
                    + bookCopy.getNoOfCopies() + ";");
        });
    }

    @Test
    public void BookCopiesDaoAdd(){

        BookCopiesDao.add(new BookCopies(99,99,99));
        BookCopiesDao.show();
    }

    @Test
    public void BookCopiesDaoUpdate(){
        BookCopiesDao.update(new BookCopies(99,99,101));
        BookCopiesDao.show();
    }
    @Test
    public void BookCopiesDaoUpdateId(){
        BookCopiesDao.updateById(new BookCopies(101,101,101),99,99);
        BookCopiesDao.show();
    }
    @Test
    public void BookCopiesDaoDelete(){
        BookCopiesDao.update(new BookCopies(101,101,101));
        BookCopiesDao.show();


    }



}
