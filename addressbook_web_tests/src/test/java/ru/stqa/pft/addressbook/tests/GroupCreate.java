package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreate extends TestBase {

  @Test
  public void testGroupCreate() throws Exception {
    app.goTo().GroupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("testName1").withHeader("testHeader1");
    app.group().create(group);

    List<GroupData> after = app.group().list();
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
