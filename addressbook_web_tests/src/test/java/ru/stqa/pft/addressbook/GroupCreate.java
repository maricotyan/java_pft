package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreate extends TestBase{

  @Test
  public void testGroupCreate() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("testName1", "testHeader1", "testFooter1"));
    submiteGroupCreation();
    returnToGroupPage();
  }

}
