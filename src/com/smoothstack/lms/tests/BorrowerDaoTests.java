import com.smoothstack.lms.dao.BorrowerDao;
import com.smoothstack.lms.model.Borrower;
import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.assertTrue;


public class BorrowerDaoTests {

    @Test
    public void loadMap() {
        Map<String, Borrower> borrowerMap;
        borrowerMap = BorrowerDao.createMap();
        borrowerMap.forEach((key,borrower)->{
            System.out.println(borrower.getCardNo()+";"+borrower.getBorrowerName()+";"+borrower.getBorrowerAddress()
                    +";"+borrower.getBorrowerPhone()+";");
        });
    }

    @Test
    public void daoShow(){
        BorrowerDao.show();
    }

    @Test
    public void MapHasData() {
        Map<String, Borrower> borrowerMap = BorrowerDao.createMap();
        assertTrue("Map has no data", borrowerMap.size() > 0);
    }

    @Test
    public void BorrowerDaoAdd(){
        BorrowerDao.add(new Borrower(99,"Test name","Test Addy",111));
        BorrowerDao.show();
    }
    //
    @Test
    public void BorrowerDaoUpdate(){
        BorrowerDao.update(new Borrower(99,"TestUpdate","TestUpdate",111));
        BorrowerDao.show();
    }
    @Test
    public void BorrowerDaoUpdateId(){
        BorrowerDao.updateById(new Borrower(101,"TestUpdate","TestUpdate",111),
                99);
        BorrowerDao.show();
    }
    @Test
    public void BorrowerDaoDelete(){
        BorrowerDao.delete(new Borrower(101,"TestUpdate","TestUpdate",111));
        BorrowerDao.show();
    }
}
