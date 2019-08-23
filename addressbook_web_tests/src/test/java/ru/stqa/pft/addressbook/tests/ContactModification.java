package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDataPhone;

public class ContactModification extends TestBase {

    @Test
    public void testGroupModification() {
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactFormPhone(new ContactDataPhone("777777", "+77777777777", "+70000000000"));;
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
