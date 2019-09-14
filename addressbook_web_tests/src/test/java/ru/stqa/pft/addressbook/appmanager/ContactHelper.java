package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initCreation() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")) {
            return;
        }
        click(By.linkText("add new"));
    }

    public void submitCreation() {
        click(By.name("submit"));
    }

    public void fillForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        select(By.name("bday"), contactData.getBday());
        select(By.name("bmonth"), contactData.getBmonth());
        type(By.name("byear"), contactData.getByear());

        if (creation){
            select(By.name("new_group"), contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("address2"), contactData.getAddressSec());
        type(By.name("phone2"), contactData.getHomeSec());
        type(By.name("notes"), contactData.getNote());
    }

    public void edit(int index) {
        wd.findElements(By.xpath(".//img[@alt='Edit']")).get(index).click();
    }

    public void submitModification() {
        click(By.name("update"));
    }

    public void choose(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void delete() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void create(ContactData contact) {
        initCreation();
        fillForm(contact, true);
        submitCreation();
    }

    public void lightCreate(ContactData contactData, boolean creation) {
        initCreation();
        type(By.name("firstname"), contactData.getFirstName());
        submitCreation();
    }

    public void delete(int index) {
        choose(index);
        delete();
        closeAlert();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstName = element.findElement(By.xpath(".//td[3]")).getAttribute("textContent");
            String lastName = element.findElement(By.xpath(".//td[2]")).getAttribute("textContent");
            Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstName, null, lastName, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
