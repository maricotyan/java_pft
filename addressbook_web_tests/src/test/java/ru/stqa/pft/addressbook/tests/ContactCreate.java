package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreate extends TestBase {

  @Test
  public void testContactCreate() throws Exception {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData()
            .withFirstName("firstName").withMiddleName("middleName").withLastName("LastName").withNickName("nickName").withTitle("title").withCompany("company").withAddress("address").withEmail("test@test.test")
            .withBday("18").withBmonth("October").withByear("1000").withGroup("testName1").withHome("777777").withMobile("+77777777777").withWork("+70000000000").withAddressSec("addressSec").withHomeSec("homeSec").withNote("note");
    app.contact().create(contact);
    app.goTo().HomePage();

    List<ContactData> after = app.contact().list();
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
