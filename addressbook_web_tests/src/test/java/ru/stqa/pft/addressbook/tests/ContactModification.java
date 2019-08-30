package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModification extends TestBase {

    @Test
    public void testGroupModification() {
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().lightContactCreate(new ContactData("firstName", null, null, null, null, null, null, null, "18", "October", "1000", "testName1",null, null, null,null, null, null), false);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("firstName", null, null, null, null, null, null, null, "18", "October", "1000", "testName1", "777777", "+77777777777", "+70000000000",null, null, null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
