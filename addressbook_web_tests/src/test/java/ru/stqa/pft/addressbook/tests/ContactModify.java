package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModify extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        ContactData contact = new ContactData("firstName", null, null, null, null, null, null, null, "18", "October", "1000", "testName1",null, null, null,null, null, null);
        if (app.contact().list().size() == 0){
            app.contact().lightCreate(contact, false);
            app.goTo().HomePage();
        }
    }

    @Test
    public void testGroupModify() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().edit(index);
        ContactData contactModified = new ContactData("firstNameModified", null, "lastNameModified", null, null, null, null, null, "18", "October", "1000", "testName1", "777777", "+77777777777", "+70000000000",null, null, null);
        app.contact().fillForm(contactModified, false);
        app.contact().submitModification();
        app.goTo().HomePage();

        before.remove(index);
        before.add(contactModified);
        List<ContactData> after = app.contact().list();
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
