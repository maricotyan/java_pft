package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase {

    private Contacts allContacts;
    private ContactData contact = null;
    private Groups allGroups;
    private GroupData group = null;
    private Groups contactGroups = null;

    @BeforeMethod
    public void ensurePreconditions() {
        allContacts = app.db().contacts();
        allGroups = app.db().groups();

        if (allGroups.size() == 0) {
            groupCreate();
        }

        if (allContacts.size() == 0) {
            contactCreate();
        }

        for (ContactData someContact:allContacts) {
            contactGroups = someContact.getGroups();
            if (contactGroups.size() < allGroups.size()) {
                allGroups.removeAll(contactGroups);
                contact = someContact;
                break;
            }
        }

        if (contact == null) {
            contactCreate();
        }
    }

    private void contactCreate() {
        contact = new ContactData()
                .withFirstName("firstName").withBday("18").withBmonth("October").withByear("1000");
        app.contact().create(contact);
        app.goTo().HomePage();
    }

    private void groupCreate() {
        app.goTo().GroupPage();
        group = new GroupData().withName("testName1").withHeader("testHeader1");
        app.group().create(group);
    }

    @Test
    public void testAddContactToGroup() {
        Contacts contactsBefore = app.db().contacts();
        GroupData newGroup = allGroups.iterator().next();
        ContactData contactBefore = contact;
        Groups groupsBefore = contactBefore.getGroups();
        app.contact().addToGroup(contactBefore, newGroup);
        app.goTo().HomePage();

        assertThat(app.contact().count(), equalTo(contactsBefore.size()));
        Contacts contactsAfter = app.db().contacts();
        ContactData contactAfter =  app.contact().chooseFromDbById(contactBefore.getId(), contactsAfter);
        Groups groupsAfter = contactAfter.getGroups();
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(newGroup)));
    }
}
