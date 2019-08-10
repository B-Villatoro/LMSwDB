import java.io.IOException;
import java.util.Map;

import com.smoothstack.lms.model.Author;
import org.junit.Before;
import org.junit.Test;
import com.smoothstack.lms.dao.AuthorDao;

import static junit.framework.TestCase.assertTrue;

public class AuthorDaoTests {


    @Test
    public void loadMap() throws IOException {
        Map<String, Author> authorMap;
        authorMap = AuthorDao.createMap();
        authorMap.forEach((key,author)->{
            System.out.println(author.getName()+";"+author.getId()+";");
        });
    }

    @Test
    public void daoShow(){
        AuthorDao.show();

    }

    @Test
    public void MapHasData() throws IOException {
        Map<String, Author> authorMap = AuthorDao.createMap();
        assertTrue("Map has no data", authorMap.size() > 0);
    }

    @Test
    public void AuthorDaoAdd(){
        AuthorDao.add(new Author("TestAdd",99));
        AuthorDao.show();
    }

    @Test
    public void AuthorDaoUpdate(){
        AuthorDao.update(new Author("TestUpdate",99));
        AuthorDao.show();
    }
    @Test
    public void AuthorDaoUpdateId(){
        AuthorDao.updateById(new Author("TestUpdate",101),99);
        AuthorDao.show();
    }
    @Test
    public void AuthorDaoDelete(){
        AuthorDao.delete(new Author("TestUpdate",101));
        AuthorDao.show();
    }

}
