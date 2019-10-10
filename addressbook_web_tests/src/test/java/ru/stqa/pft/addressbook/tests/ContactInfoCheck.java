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
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstName("firstName").withBday("18").withBmonth("October").withByear("1000").withGroup("testName1")
                    .withAddress("address,\n" + "building,\n" + "41-11").withEmail("test_test@test.test")
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
    public void testContactEmail() throws Exception {
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
    }

    public String getMergePhones(ContactData contact) {
        return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactInfoCheck::cleaned)
                .collect(Collectors.joining(""));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("-()", "");
    }
}
