
import com.smoothstack.lms.myutil.IdValidate;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;


public class IdValidateTests {





    @Test
    public void idValidateIsValid() {
        assertTrue("Returned false, 01234 is a valid response", IdValidate.isValid("01234") == true);
    }

    @Test
    public void idValidateParser() {
        assertTrue("Returned as incorrect int",IdValidate.parser("123") ==123);
    }
}


