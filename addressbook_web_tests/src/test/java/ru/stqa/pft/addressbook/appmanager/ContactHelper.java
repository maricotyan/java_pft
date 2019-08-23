package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactDataGeneral;
import ru.stqa.pft.addressbook.model.ContactDataPhone;
import ru.stqa.pft.addressbook.model.ContactDataSecondary;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactFormGeneral(ContactDataGeneral contactDataGeneral) {
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
        select(By.name("new_group"), contactDataGeneral.getGroup());
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
        click(By.xpath("//tr[3]/td/input"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }
}