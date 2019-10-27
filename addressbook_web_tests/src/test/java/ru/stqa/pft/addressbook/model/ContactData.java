package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {

    @Id
    @Column(name = "id")
    private Integer id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "middleName")
    private String middleName;

    @Expose
    @Column(name = "lastName")
    private String lastName;

    @Transient
    private String nickName;

    @Transient
    private String title;

    @Transient
    private String company;

    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Transient
    private String bday;

    @Transient
    private String bmonth;

    @Transient
    private String byear;

    @Expose
    @Transient
    private String group;

    @Column(name = "home")
    @Type(type = "text")
    protected String home;

    @Column(name = "mobile")
    @Type(type = "text")
    protected String mobile;

    @Column(name = "work")
    @Type(type = "text")
    protected String work;

    @Transient
    private String addressSec;

    @Transient
    private String homeSec;

    @Transient
    private String note;

    @Transient
    private String allPhones;

    @Transient
    private String allEmails;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public Integer getId() { return id; }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() { return email; }

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }

    public String getGroup() {
        return group;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getAddressSec() {
        return addressSec;
    }

    public String getHomeSec() {
        return homeSec;
    }

    public String getNote() {
        return note;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail2() { return email2; }

    public String getEmail3() { return email3; }

    public String getAllEmails() { return allEmails; }

    public File getPhoto() {
        if (photo != null) {
            return new File(photo);
        }
        return null;
}

    public ContactData withId(Integer id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withBday(String bday) {
        this.bday = bday;
        return this;
    }

    public ContactData withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }

    public ContactData withAddressSec(String addressSec) {
        this.addressSec = addressSec;
        return this;
    }

    public ContactData withHomeSec(String homeSec) {
        this.homeSec = homeSec;
        return this;
    }

    public ContactData withNote(String note) {
        this.note = note;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmail(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
