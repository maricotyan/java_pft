package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDelete extends TestBase {

  @Test
  public void testGroupDelete() throws Exception {
    app.gotoGroupPage();
    app.selectGroup();
    app.deleteSelectedGroups();
    app.returnToGroupPage();
  }
}