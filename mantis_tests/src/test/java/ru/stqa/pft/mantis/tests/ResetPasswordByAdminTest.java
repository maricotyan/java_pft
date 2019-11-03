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
    protected Users allUsers;
    protected UserData adminUser;
    protected UserData userWithoutAdmin;

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @BeforeMethod
    public void ensurePreconditions() throws IOException, MessagingException {
        allUsers = app.db().users();
        adminUser = app.userHelper().chooseFromDbById(1, allUsers);
        userWithoutAdmin = app.userHelper().getSomeUser(allUsers, adminUser);

        if (userWithoutAdmin == null) {
            Long now = System.currentTimeMillis();
            user = String.format("user%s", now);
            password = "password";
            email = String.format("user%s@localhost.localdomain", now);
            app.registration().start(user, email);
            List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
            String confirmationLink = findConfirmationLinc(mailMessages, email);
            app.registration().finish(confirmationLink, password);
            userWithoutAdmin = app.userHelper().getSomeUser(allUsers, adminUser);
            assertTrue(app.newSession().login(user, password));
        }
    }

    @Test
    public void testResetPasswordByAdminTest() throws IOException, MessagingException {
        HttpSession session = app.newSession();
        app.userHelper().login("administrator", "root");
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));

        UserData userToResetPass = userWithoutAdmin;
        System.out.println("!!!!!!!!!!!!!!debug!!!!!!!!!!!!!! " + userToResetPass);
        app.adminHelper().resetUsersPass(userToResetPass.getName());
        app.userHelper().logout();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        email = userToResetPass.getEmail();
        System.out.println(email);
        String confirmationLink = findConfirmationLinc(mailMessages, email);
        String userName = userToResetPass.getName();
        String newPass = "pass";

        app.userHelper().setNewPassByLink(confirmationLink, userName, newPass);

        assertTrue(session.login(userName, newPass));
        assertTrue(session.isLoggedInAs(userName));
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
