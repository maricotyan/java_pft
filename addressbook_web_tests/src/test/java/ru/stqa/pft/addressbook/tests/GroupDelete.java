package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDelete extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    app.goTo().GroupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("testName1").withHeader("testHeader1"));
    }
  }

  @Test
  public void testGroupDelete() throws Exception {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().select(index);
    app.group().delete();
    app.group().returnToGroupPage();
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }
}