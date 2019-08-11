import com.smoothstack.lms.dao.LibraryDao;
import com.smoothstack.lms.model.Library;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class LibraryDaoTests {
    @Test
    public void loadMap() {
        Map<String, Library> libraryMap;
        libraryMap = LibraryDao.createMap();
        libraryMap.forEach((key,library)->{
            System.out.println(library.getBranchId()+";"+library.getBranchName()+";"+library.getBranchAddress());
        });
    }

    @Test
    public void daoShow(){
        LibraryDao.show();
    }

    @Test
    public void MapHasData() {
        Map<String, Library> libraryMap = LibraryDao.createMap();
        assertTrue("Map has no data", libraryMap.size() > 0);
    }

    @Test
    public void LibraryDaoAdd(){
        LibraryDao.add(new Library(99,"Test Branch","Test Addy"));
        LibraryDao.show();
    }
//
    @Test
    public void LibraryDaoUpdate(){
        LibraryDao.update(new Library(99,"TestUpdate","TestUpdate"));
        LibraryDao.show();
    }
    @Test
    public void LibraryDaoUpdateId(){
        LibraryDao.updateById(new Library(101,"TestUpdate","TestUpdate"),99);
        LibraryDao.show();
    }
    @Test
    public void LibraryDaoDelete(){
        LibraryDao.delete(new Library(101,"TestUpdate","TestUpdate"));
        LibraryDao.show();
    }
}
