package com.smoothstack.lms.model;

public class Borrower {
    int cardNo;
    String borrowerName;
    String borrowerAddress;
    int borrowerPhone;

    public Borrower(int cardNo, String borrowerName, String borrowerAddress, int borrowerPhone) {
        this.cardNo = cardNo;
        this.borrowerName = borrowerName;
        this.borrowerAddress = borrowerAddress;
        this.borrowerPhone = borrowerPhone;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
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

    public int getBorrowerPhone() {
        return borrowerPhone;
    }

    public void setBorrowerPhone(int borrowerPhone) {
        this.borrowerPhone = borrowerPhone;
    }
}
