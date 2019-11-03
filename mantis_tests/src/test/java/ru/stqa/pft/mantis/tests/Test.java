package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class Test extends TestBase {

    protected String user;
    protected String password;
    protected String email;

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Mail start!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @org.testng.annotations.Test
    public void testResetPasswordByAdminTest() throws IOException, MessagingException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
