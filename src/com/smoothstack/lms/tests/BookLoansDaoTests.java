import com.smoothstack.lms.dao.*;
import com.smoothstack.lms.dao.BookLoansDao;
import com.smoothstack.lms.model.*;
import com.smoothstack.lms.model.BookLoans;
import org.junit.Test;

import java.util.Map;

public class BookLoansDaoTests {


    @Test
    public void daoShow() {
        BookLoansDao.show();

    }

    @Test
    public void loadMap() {
        Map<String, BookLoans> bookLoansMap;
        bookLoansMap = BookLoansDao.createMap();
        bookLoansMap.forEach((key, bookLoan) -> {
            System.out.println("Key:"+key+";BookLoan:"+bookLoan.getIsbn() + ";" + bookLoan.getBranchId() + ";" + bookLoan.getCardNo()
                    + ";" + bookLoan.getDateOut() + ";"+bookLoan.getDateDue()+";");
        });
    }

    @Test
    public void BookLoansDaoAdd(){
        AuthorDao.add(new Author("TestAuthor",99));
        PublisherDao.add(new Publisher("TestPub","TestAddy",99,99));
        BookDao.add(new Book("TestAdd",99,99,99));

        LibraryDao.add(new Library(99,"Test Branch","Test Addy"));

        BorrowerDao.add(new Borrower(99,"Test name","Test Addy",111));

        BookLoansDao.add(new BookLoans(99,99,99,"2019-08-09","2019-09-09"));
        BookLoansDao.show();
    }

    @Test
    public void BookLoansDaoUpdate(){
        BookLoansDao.update(new BookLoans(99,99,99,"2019-08-09","2019-10-09"));
        BookLoansDao.show();
    }
    @Test
    public void BookLoansDaoUpdateId(){
        AuthorDao.add(new Author("TestAuthor",101));
        PublisherDao.add(new Publisher("TestPub","TestAddy",101,101));
        BookDao.add(new Book("TestAdd",101,101,101));

        LibraryDao.add(new Library(101,"Test Branch","Test Addy"));

        BorrowerDao.add(new Borrower(101,"Test name","Test Addy",111));



        BookLoansDao.updateById(new BookLoans(101,101,101,"2019-08-09",
                "2019-09-09"),99,99,99);
        BookLoansDao.show();
    }
    @Test
    public void BookLoansDaoDelete(){
        BookLoansDao.delete(new BookLoans(101,101,101,"2019-08-09","2019-09-09"));
        BookLoansDao.show();

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

}
