package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModify extends TestBase {

    @Test
    public void testGroupModify() {
        ContactData contact = new ContactData("firstName", null, null, null, null, null, null, null, "18", "October", "1000", "testName1",null, null, null,null, null, null);
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().lightContactCreate(contact, false);
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size() - 1);
        ContactData contactModified = new ContactData("firstNameModified", null, "lastNameModified", null, null, null, null, null, "18", "October", "1000", "testName1", "777777", "+77777777777", "+70000000000",null, null, null);
        app.getContactHelper().fillContactForm(contactModified, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        before.remove(before.size() - 1);
        before.add(contactModified);
        List<ContactData> after = app.getContactHelper().getContactList();
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
