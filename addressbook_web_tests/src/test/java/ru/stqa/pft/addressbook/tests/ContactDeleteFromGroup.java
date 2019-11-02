package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroup extends TestBase {

    private Contacts allContacts;
    private ContactData contact = null;
    private Groups allGroups;
    private GroupData group = null;

    @BeforeMethod

    public void ensurePreconditions() {
        allContacts = app.db().contacts();
        allGroups = app.db().groups();

        if (allGroups.size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("testName1").withHeader("testHeader1"));
        }

        if (allContacts.size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("firstName").withBday("18").withBmonth("October").withByear("1000").inGroup(allGroups.iterator().next()));
            app.goTo().HomePage();
        }

        for (ContactData c: allContacts) {
            if (c.getGroups().size() != 0) {
                contact = c;
                group = c.getGroups().iterator().next();
            }
        }

        if (contact == null) {
            contact = allContacts.iterator().next();
            group = allGroups.iterator().next();
            app.contact().addToGroup(allContacts.iterator().next(), allGroups.iterator().next());
        }
    }

    @Test
    public void testDeleteContactFromGroup() {
        Groups groupsBefore = contact.getGroups();
        app.goTo().HomePage();
        app.contact().deleteFromGroup(contact, group);
        app.goTo().HomePage();

        Contacts contactsAfter = app.db().contacts();
        ContactData contactAfter =  app.contact().chooseFromDbById(contact.getId(), contactsAfter);
        Groups groupsAfter = contactAfter.getGroups();
        assertThat(groupsAfter, equalTo(groupsBefore.without(group)));
    }
}
