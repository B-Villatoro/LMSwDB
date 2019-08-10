package com.smoothstack.lms.model;

public class BookCopies {

    private int isbn;
    private int branchId;
    private int noOfCopies;

    public BookCopies(int isbn, int branchId, int noOfCopies) {
        this.isbn = isbn;
        this.branchId = branchId;
        this.noOfCopies = noOfCopies;
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

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }
}
