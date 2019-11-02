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

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("testName1").withHeader("testHeader1"));
        }

        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("firstName").withBday("18").withBmonth("October").withByear("1000"));
            app.goTo().HomePage();
        }
    }

    @Test
    public void testAddContactToGroup() {
        Contacts contactsBefore = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData newGroup = groups.iterator().next();
        ContactData contactBefore = contactsBefore.iterator().next();
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
