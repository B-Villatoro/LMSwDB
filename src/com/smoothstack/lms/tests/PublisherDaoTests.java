import java.io.IOException;
import java.util.Map;

import com.smoothstack.lms.dao.PublisherDao;
import com.smoothstack.lms.model.Publisher;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class PublisherDaoTests {

    @Test
    public void loadMap() throws IOException {
        Map<String, Publisher> publisherMap;
        publisherMap = PublisherDao.createMap();
        publisherMap.forEach((key,publisher)->{
            System.out.println(publisher.getName()+";"+publisher.getId()+";");
        });
    }

    @Test
    public void daoShow(){
        PublisherDao.show();

    }

    @Test
    public void MapHasData() throws IOException {
        Map<String, Publisher> publisherMap = PublisherDao.createMap();
        assertTrue("Map has no data", publisherMap.size() > 0);
    }

    @Test
    public void PublisherDaoAdd(){
        PublisherDao.add(new Publisher("TestAdd","TestAddy",999,99));
        PublisherDao.show();
    }

    @Test
    public void PublisherDaoUpdate(){
        PublisherDao.update(new Publisher("TestUpdate","99",999,99));
        PublisherDao.show();
    }
    @Test
    public void PublisherDaoUpdateId(){
        PublisherDao.updateById(new Publisher("TestUpdate","TestAddy",999,101),99);
        PublisherDao.show();
    }
    @Test
    public void PublisherDaoDelete(){
        PublisherDao.delete(new Publisher("TestUpdate","TestAddy",999,101));
        PublisherDao.show();
    }

}
