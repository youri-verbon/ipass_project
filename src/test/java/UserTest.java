import model.User;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static org.testng.AssertJUnit.*;

public class UserTest {

    User henk = new User("Deze naam is langer dan 32 karakters!!!!!!", "henk@henk.nl");
    User piet = new User("Piet", "Piet");
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    @Test
    void checkEmailPatternTrue(){
        Pattern pat = Pattern.compile(emailRegex);
        assertTrue(pat.matcher(henk.getEmail()).matches());
    }

    @Test
    void checkEmailPatternFalse(){
        Pattern pat = Pattern.compile(emailRegex);
        assertFalse(pat.matcher(piet.getEmail()).matches());
    }


}
