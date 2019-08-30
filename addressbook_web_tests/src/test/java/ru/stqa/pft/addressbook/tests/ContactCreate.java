package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreate extends TestBase {

  @Test
  public void testContactCreate() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("firstName", "middleName", "LastName", "nickName", "title", "company", "address", "test@test.test", "18", "October", "1000", "testName1", "777777", "+77777777777", "+70000000000", "addressSec", "homeSec", "note"), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomePage();
  }
}
