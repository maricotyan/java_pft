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
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData()
                            .withFirstName("firstName").withBday("18").withBmonth("October").withByear("1000").withGroup("testName1"));
            app.goTo().HomePage();
        }
    }

    @Test
    public void testGroupModify() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        app.contact().modify(modifiedContact.getId());
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("firstNameModified").withLastName("lastNameModified").withBday("18").withBmonth("October").withByear("1000").withGroup("testName1").withMobile("+70000000000");
        app.contact().fillForm(contact, false);
        app.contact().submitModification();
        app.goTo().HomePage();
       Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.withModified(modifiedContact, contact)));
    }
}
