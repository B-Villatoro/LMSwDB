package com.smoothstack.lms.model;

public class BookLoans {

    private int isbn;
    private int branchId;
    private int cardNo;
    private String dateOut;
    private String dateDue;

    public BookLoans(int isbn, int branchId, int cardNo, String dateOut, String dateDue) {
        this.isbn = isbn;
        this.branchId = branchId;
        this.cardNo = cardNo;
        this.dateOut = dateOut;
        this.dateDue = dateDue;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }
}
