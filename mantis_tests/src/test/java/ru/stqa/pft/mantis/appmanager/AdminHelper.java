package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.IOException;

public class AdminHelper extends HelperBase{
    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    public void resetUsersPass (String username) throws IOException {
//        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        wd.findElement(By.linkText("Управление")).click();
        wd.findElement(By.linkText("Управление пользователями")).click();
        wd.findElement(By.linkText(username)).click();
        wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
    }

}
