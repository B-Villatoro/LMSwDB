package com.smoothstack.lms.service;

import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.BookLoansDao;
import com.smoothstack.lms.dao.BorrowerDao;
import com.smoothstack.lms.dao.LibraryDao;
import com.smoothstack.lms.model.Book;
import com.smoothstack.lms.model.BookLoans;
import com.smoothstack.lms.model.Borrower;
import com.smoothstack.lms.model.Library;
import com.smoothstack.lms.myutil.IdValidate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class BookLoansService {
    public static void checkOutBook(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your library card number");
        String cardNo = scan.nextLine();
        while(!IdValidate.isValid(cardNo)){
            System.out.println("Not a valid card number, please try again");
            cardNo = scan.nextLine();
        }
        Map<String , Borrower> borrowerMap = BorrowerDao.createMap();


        if(borrowerMap.containsKey(cardNo)){

            System.out.println("Now enter the branch id of where you are checking this book out");
            String branchId = scan.nextLine();
            while (!IdValidate.isValid(branchId)){
                System.out.println("Not valid branch id please try again");
                branchId = scan.nextLine();
            }
            Map<String , Library> libraryMap = LibraryDao.createMap();
            if(libraryMap.containsKey(branchId)){
                System.out.println("Now enter the ISBN of the book you are checking out");
                String isbn = scan.nextLine();
                while(!IdValidate.isValid(isbn)){
                    System.out.println("ISBN not valid please try again");
                    isbn = scan.nextLine();
                }
                Map<String, Book> bookMap = BookDao.createMap();
                if(bookMap.containsKey(isbn)){

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date());
                    String dateOut = sdf.format(cal.getTime());
                    cal.add(Calendar.DATE, 30);
                    String dateDue = sdf.format(cal.getTime());


                    BookLoansDao.add(new BookLoans(IdValidate.parser(isbn),IdValidate.parser(branchId),IdValidate.parser(cardNo),
                            dateOut,dateDue));
                    System.out.println("Your book has been checked out please return it by "+dateDue);

                }else{
                    System.out.println("book does not exist");
                }

            }else{
                System.out.println("Branch Id does not exist");
            }

        }else{
            System.out.println("Card number does not exist");
        }
    }

    public static void extendDueDate(){

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your library card number");
        String cardNo = scan.nextLine();
        while(!IdValidate.isValid(cardNo)){
            System.out.println("Not a valid card number, please try again");
            cardNo = scan.nextLine();
        }
        Map<String , Borrower> borrowerMap = BorrowerDao.createMap();


        if(borrowerMap.containsKey(cardNo)){

            System.out.println("Now enter the branch id of where you checked this book out");
            String branchId = scan.nextLine();
            while (!IdValidate.isValid(branchId)){
                System.out.println("Not valid branch id please try again");
                branchId = scan.nextLine();
            }
            Map<String , Library> libraryMap = LibraryDao.createMap();
            if(libraryMap.containsKey(branchId)){
                System.out.println("Now enter the ISBN of the book you checked out");
                String isbn = scan.nextLine();
                while(!IdValidate.isValid(isbn)){
                    System.out.println("ISBN not valid please try again");
                    isbn = scan.nextLine();
                }
                Map<String, Book> bookMap = BookDao.createMap();
                if(bookMap.containsKey(isbn)){

                    Map<String,BookLoans>  bookLoansMap = BookLoansDao.createMap();

                    BookLoans bl = bookLoansMap.get(isbn+","+branchId+","+cardNo);



                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date d = sdf.parse(bl.getDateOut());
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(d);
                        cal.add(Calendar.DATE, 14);
                        bl.setDateDue(sdf.format(cal.getTime()));

                    }catch(ParseException e){
                        e.printStackTrace();
                    }


                    BookLoansDao.update(bl);
                    System.out.println("Your book has been checked out please return it by "+bl.getDateDue());

                }else{
                    System.out.println("book does not exist");
                }

            }else{
                System.out.println("Branch Id does not exist");
            }

        }else{
            System.out.println("Card number does not exist");
        }

    }

    public static void returnBook(){

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your library card number");
        String cardNo = scan.nextLine();
        while(!IdValidate.isValid(cardNo)){
            System.out.println("Not a valid card number, please try again");
            cardNo = scan.nextLine();
        }
        Map<String , Borrower> borrowerMap = BorrowerDao.createMap();


        if(borrowerMap.containsKey(cardNo)){

            System.out.println("Now enter the branch id of where you returning this book");
            String branchId = scan.nextLine();
            while (!IdValidate.isValid(branchId)){
                System.out.println("Not valid branch id please try again");
                branchId = scan.nextLine();
            }
            Map<String , Library> libraryMap = LibraryDao.createMap();
            if(libraryMap.containsKey(branchId)){
                System.out.println("Now enter the ISBN of the book you are returning");
                String isbn = scan.nextLine();
                while(!IdValidate.isValid(isbn)){
                    System.out.println("ISBN not valid please try again");
                    isbn = scan.nextLine();
                }
                Map<String, Book> bookMap = BookDao.createMap();
                if(bookMap.containsKey(isbn)){

                    Map<String,BookLoans>  bookLoansMap = BookLoansDao.createMap();

                    BookLoans bl = bookLoansMap.get(isbn+","+branchId+","+cardNo);

                    BookLoansDao.delete(bl);

                    System.out.println("Your book has been returned!");

                }else{
                    System.out.println("book does not exist");
                }

            }else{
                System.out.println("Branch Id does not exist");
            }

        }else{
            System.out.println("Card number does not exist");
        }


    }
}
