package ru.stqa.pft.addressbook.model;

public class ContactDataSecondary {
    private final String addressSec;
    private final String homeSec;
    private final String note;

    public ContactDataSecondary(String addressSec, String homeSec, String note) {
        this.addressSec = addressSec;
        this.homeSec = homeSec;
        this.note = note;
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
