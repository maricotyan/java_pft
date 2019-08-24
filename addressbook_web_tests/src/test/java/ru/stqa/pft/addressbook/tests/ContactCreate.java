package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDataGeneral;
import ru.stqa.pft.addressbook.model.ContactDataPhone;
import ru.stqa.pft.addressbook.model.ContactDataSecondary;

public class ContactCreate extends TestBase {

  @Test
  public void testContactCreate() throws Exception {
    app.getNavigationHelper().gotoContactCreationPage();
    app.getContactHelper().fillContactFormGeneral(new ContactDataGeneral("firstName", "middleName", "LastName", "nickName", "title", "company", "address", "test@test.test", "18", "October", "1000", "testName1"), true);
    app.getContactHelper().fillContactFormPhone(new ContactDataPhone("777777", "+77777777777", "+70000000000"));
    app.getContactHelper().fillContactFormSecondary(new ContactDataSecondary("addressSec", "homeSec", "note"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomePage();
  }
}
