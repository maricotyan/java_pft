package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModify extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("firstName").withBday("18").withBmonth("October").withByear("1000").withGroup("testName1"));
            app.goTo().HomePage();
        }
    }

    @Test
    public void testGroupModify() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("firstNameModified").withLastName("lastNameModified").withBday("18").withBmonth("October").withByear("1000").withGroup("testName1").withMobile("+70000000000");
        app.contact().modify(modifiedContact, contact);
        app.goTo().HomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withModified(modifiedContact, contact)));
    }
}
