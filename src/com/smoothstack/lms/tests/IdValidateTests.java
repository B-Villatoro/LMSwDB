package com.smoothstack.lms.tests;

import com.smoothstack.lms.myutil.IdValidate;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;


public class IdValidateTests {





    @Test
    public void idValidateBool() throws IOException {
        assertTrue("Returned false, is not valid", IdValidate.isValid("01234") == true);
    }

}


