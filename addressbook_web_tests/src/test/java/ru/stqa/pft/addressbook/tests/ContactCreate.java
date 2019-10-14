package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreate extends TestBase {

  @Test
  public void testContactCreate() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/data/maxresdefault.jpg");
    ContactData contact = new ContactData()
            .withFirstName("firstName").withMiddleName("middleName").withLastName("LastName").withNickName("nickName").withTitle("title")
            .withCompany("company").withAddress("address").withEmail("test@test.test").withEmail2("123@test.test").withEmail3("")
            .withBday("18").withBmonth("October").withByear("1000").withGroup("testName1").withPhoto(photo)
            .withHome("777777").withMobile("+77777777777").withWork("+70000000000").withAddressSec("addressSec").withHomeSec("homeSec").withNote("note");
    app.contact().create(contact);
    app.goTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
