package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDelete extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.contact().list().size() == 0){
            app.contact().lightCreate(new ContactData("firstName", null, null, null, null, null, null, null, "18", "October", "1000", "testName1", null, null, null, null, null, null), false);
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactDelete() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().HomePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
