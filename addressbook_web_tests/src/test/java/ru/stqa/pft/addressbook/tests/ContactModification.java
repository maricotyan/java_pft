package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDataGeneral;
import ru.stqa.pft.addressbook.model.ContactDataPhone;

public class ContactModification extends TestBase {

    @Test
    public void testGroupModification() {
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().liteContactCreate(new ContactDataGeneral("firstName", null, null, null, null, null, null, null, "18", "October", "1000", "testName1"), false);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactFormGeneral(new ContactDataGeneral("firstName", null, null, null, null, null, null, null, "18", "October", "1000", "testName1"), false);
        app.getContactHelper().fillContactFormPhone(new ContactDataPhone("777777", "+77777777777", "+70000000000"));;
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
