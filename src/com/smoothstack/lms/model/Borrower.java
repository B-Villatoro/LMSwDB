package com.smoothstack.lms.model;

public class Borrower {
    String cardNo;
    String borrowerName;
    String borrowerAddress;
    String borrowerPhone;

    public Borrower(String cardNo, String borrowerName, String borrowerAddress, String borrowerPhone) {
        this.cardNo = cardNo;
        this.borrowerName = borrowerName;
        this.borrowerAddress = borrowerAddress;
        this.borrowerPhone = borrowerPhone;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerAddress() {
        return borrowerAddress;
    }

    public void setBorrowerAddress(String borrowerAddress) {
        this.borrowerAddress = borrowerAddress;
    }

    public String getBorrowerPhone() {
        return borrowerPhone;
    }

    public void setBorrowerPhone(String borrowerPhone) {
        this.borrowerPhone = borrowerPhone;
    }
}
