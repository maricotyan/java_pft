package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModify extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().GroupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("testName1").withHeader("testHeader1"));
        }
    }

    @Test
    public void testGroupModify() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData().withId(before.get(index).getId()).withName("testName2").withHeader("testHeader2").withFooter("testFooter2");
        app.group().modify(index, group);

        before.remove(index);
        before.add(group);
        List<GroupData> after = app.group().list();
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
