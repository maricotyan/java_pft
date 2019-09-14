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
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData()
                            .withFirstName("firstName").withBday("18").withBmonth("October").withByear("1000").withGroup("testName1"));
            app.goTo().HomePage();
        }
    }

    @Test
    public void testGroupModify() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().edit(index);
        ContactData contactModified = new ContactData()
                .withFirstName("firstNameModified").withLastName("lastNameModified").withBday("18").withBmonth("October").withByear("1000").withGroup("testName1").withMobile("+70000000000");
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
