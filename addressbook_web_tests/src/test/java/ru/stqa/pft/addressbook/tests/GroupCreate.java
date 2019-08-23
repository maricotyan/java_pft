package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreate extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("testName1", "testHeader1", "testFooter1"));
    app.submiteGroupCreation();
    app.returnToGroupPage();
  }

}
