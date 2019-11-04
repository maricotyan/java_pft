package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

public class UserHelper extends HelperBase{

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public Users userCache = null;

    public UserData chooseFromDbById(Integer id, Users allUsers) {
        UserData thisUser = new UserData();
        for (UserData user: allUsers) {
            if (user.getId() == id) {
                thisUser = user;
                return thisUser;
            }
        }
        return null;
    }

    public UserData getSomeUser (Users allUsers, UserData adminUser) {
        UserData someUser = null;
        for (UserData user: allUsers) {
            if (user != adminUser) {
                System.out.println(user);
                someUser = user;
            }
        }
        return someUser;
    }

    public void setNewPassByLink (String confirmationLink, String name, String password) {
        wd.get(confirmationLink);
        type(By.name("realname"),name);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

    public void login(String username, String password) {
        type(By.name("username"), username);
        wd.findElement(By.xpath("//input[2]")).click();
        type(By.name("password"), password);
        wd.findElement(By.xpath("//input[3]")).click();
    }
    public void logout() {
        wd.get(app.getProperty("web.baseUrl") + "/logout_page.php");
    }
}