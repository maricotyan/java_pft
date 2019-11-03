package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordByAdminTest extends TestBase {
    protected String user;
    protected String password;
    protected String email;

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @BeforeMethod
    public void ensurePreconditions() throws IOException, MessagingException {
        Users allUsers = app.db().users();
        UserData adminUser = app.userHelper().chooseFromDbById(1, allUsers);
        Users usersWithoutAdmin = app.userHelper().getUsersWithoutAdmin(allUsers, adminUser);

        if (usersWithoutAdmin.size() == 0) {
            Long now = System.currentTimeMillis();
            user = String.format("user%s", now);
            password = "password";
            email = String.format("user%s@localhost.localdomain", now);
            app.registration().start(user, email);
            List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
            String confirmationLink = findConfirmationLinc(mailMessages, email);
            app.registration().finish(confirmationLink, password);
            assertTrue(app.newSession().login(user, password));
        }
    }

    @Test
    public void testResetPasswordByAdminTest() throws IOException, MessagingException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));

        Users allUsers = app.db().users();
        UserData adminUser = app.userHelper().chooseFromDbById(1, allUsers);
        Users usersWithoutAdmin = app.userHelper().getUsersWithoutAdmin(allUsers, adminUser);
        UserData userToResetPass = usersWithoutAdmin.iterator().next();
        System.out.println(userToResetPass);
    }

    private String findConfirmationLinc(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
            app.mail().stop();
    }
}
