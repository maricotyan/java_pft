package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactDataGeneral;
import ru.stqa.pft.addressbook.model.ContactDataPhone;
import ru.stqa.pft.addressbook.model.ContactDataSecondary;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")) {
            return;
        }
        click(By.linkText("add new"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactFormGeneral(ContactDataGeneral contactDataGeneral, boolean creation) {
        type(By.name("firstname"), contactDataGeneral.getFirstName());
        type(By.name("middlename"), contactDataGeneral.getMiddleName());
        type(By.name("lastname"), contactDataGeneral.getLastName());
        type(By.name("nickname"), contactDataGeneral.getNickName());
        type(By.name("title"), contactDataGeneral.getTitle());
        type(By.name("company"), contactDataGeneral.getCompany());
        type(By.name("address"), contactDataGeneral.getAddress());
        type(By.name("email"), contactDataGeneral.getEmail());
        select(By.name("bday"), contactDataGeneral.getBday());
        select(By.name("bmonth"), contactDataGeneral.getBmonth());
        type(By.name("byear"), contactDataGeneral.getByear());

        if (creation){
            select(By.name("new_group"), contactDataGeneral.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void fillContactFormPhone(ContactDataPhone contactDataPhone) {
        type(By.name("home"), contactDataPhone.getHome());
        type(By.name("mobile"), contactDataPhone.getMobile());
        type(By.name("work"), contactDataPhone.getWork());
    }

    public void fillContactFormSecondary(ContactDataSecondary contactDataSecondary) {
        type(By.name("address2"), contactDataSecondary.getAddressSec());
        type(By.name("phone2"), contactDataSecondary.getHomeSec());
        type(By.name("notes"), contactDataSecondary.getNote());
    }

    public void editContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void chooseContact() {
        click(By.name("selected[]"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void liteContactCreate(ContactDataGeneral contactDataGeneral, boolean creation) {
        initContactCreation();
        type(By.name("firstname"), contactDataGeneral.getFirstName());
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
