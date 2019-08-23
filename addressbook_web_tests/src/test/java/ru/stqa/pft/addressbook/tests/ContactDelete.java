package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDelete extends TestBase {

    @Test
    public void testContactDelete() {
        app.getContactHelper().chooseContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAlert();
        app.getNavigationHelper().gotoHomePage();
    }
}
