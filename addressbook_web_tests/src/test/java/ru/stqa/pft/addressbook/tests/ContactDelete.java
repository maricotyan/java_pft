package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDelete extends TestBase {

    @Test
    public void testContactDelete() {
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().lightContactCreate(new ContactData("firstName", null, null, null, null, null, null, null, "18", "October", "1000", "testName1", null, null, null, null, null, null), false);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().chooseContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAlert();
        app.getNavigationHelper().gotoHomePage();
    }
}
