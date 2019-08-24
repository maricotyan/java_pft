package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDataGeneral;

public class ContactDelete extends TestBase {

    @Test
    public void testContactDelete() {
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().liteContactCreate(new ContactDataGeneral("firstName", null, null, null, null, null, null, null, "18", "October", "1000", "testName1"), false);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().chooseContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAlert();
        app.getNavigationHelper().gotoHomePage();
    }
}
