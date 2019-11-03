package ru.stqa.pft.mantis.appmanager;

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

    public Users getUsersWithoutAdmin(Users allUsers, UserData adminUser) {
        Users users = null;
        for (UserData user: allUsers) {
            if (user != adminUser) {
                users.add(user);
            }
        }
        return users;
    }
}