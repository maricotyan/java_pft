package ru.stqa.pft.addressbook.model;

public class ContactData {
    protected final String home;
    protected final String mobile;
    protected final String work;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String title;
    private final String company;
    private final String address;
    private final String email;
    private final String bday;
    private final String bmonth;
    private final String byear;
    private final String group;
    private final String addressSec;
    private final String homeSec;
    private final String note;

    public ContactData(String firstName, String middleName, String lastName, String nickName, String title, String company, String address, String email, String bday, String bmonth, String byear, String group, String home, String mobile, String work, String addressSec, String homeSec, String note) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.title = title;
        this.company = company;
        this.address = address;
        this.email = email;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
        this.group = group;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.addressSec = addressSec;
        this.homeSec = homeSec;
        this.note = note;
    }

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

    public String getEmail() {
        return email;
    }

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
}
