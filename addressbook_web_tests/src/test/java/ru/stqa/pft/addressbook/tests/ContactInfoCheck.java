package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactInfoCheck extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().contacts().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstName("firstName").withBday("18").withBmonth("October").withByear("1000")
                    .withAddress("address,\n" + "building,\n" + "41-11")
                    .withEmail("test_test@test.test").withEmail2("123@test.test").withEmail3("")
                    .withHome("77-77-77").withMobile("+77777777777").withWork("8 000 000 0000"));
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactPhones() throws Exception {
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(getMergePhones(contactInfoFromEditForm)));
    }

    @Test
    public void testContactAddress() throws Exception {
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    @Test
    public void testContactEmails() throws Exception {
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(getMergeEmails(contactInfoFromEditForm)));
    }

    public String getMergePhones(ContactData contact) {
        return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactInfoCheck::cleaned)
                .collect(Collectors.joining(""));
    }

    public String getMergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining(""));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("-()", "");
    }
}
