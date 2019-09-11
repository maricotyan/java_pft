package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreate extends TestBase {

  @Test
  public void testContactCreate() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("firstName", "middleName", "LastName", "nickName", "title", "company", "address", "test@test.test", "18", "October", "1000", "testName1", "777777", "+77777777777", "+70000000000", "addressSec", "homeSec", "note");
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(contact, true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
