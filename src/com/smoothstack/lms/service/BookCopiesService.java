package com.smoothstack.lms.service;

import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.dao.BookCopiesDao;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.LibraryDao;
import com.smoothstack.lms.model.Author;
import com.smoothstack.lms.model.Book;
import com.smoothstack.lms.model.BookCopies;
import com.smoothstack.lms.model.Library;
import com.smoothstack.lms.myutil.IdValidate;

import java.util.Map;
import java.util.Scanner;

public class BookCopiesService {

    public static void addBookCopies() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter ISBN of the book you are adding to the library");
        String isbn = scan.nextLine();
        while (!IdValidate.isValid(isbn)) {
            System.out.println("ISBN is not valid please try again");
            isbn = scan.nextLine();
        }
        Map<String, Book> bookMap = BookDao.createMap();
        if (bookMap.containsKey(isbn)) {
            System.out.println("Enter the branch id you are adding to the library");
            String branchId = scan.nextLine();
            while (!IdValidate.isValid(branchId)) {
                System.out.println("Branch Id is not valid please try again");
                branchId = scan.nextLine();
            }
            Map<String, Library> libraryMap = LibraryDao.createMap();
            Map<String, BookCopies> bookCopiesMap = BookCopiesDao.createMap();
            if (libraryMap.containsKey(branchId) && !bookCopiesMap.containsKey(isbn + "," + branchId)) {
                System.out.println("Enter the number of copies for this branch");
                String noOfCopies = scan.nextLine();
                while (!IdValidate.isValid(noOfCopies)) {
                    System.out.println("Number of copies is not valid please try again");
                    noOfCopies = scan.nextLine();
                }
                BookCopiesDao.add(new BookCopies(IdValidate.parser(isbn), IdValidate.parser(branchId),
                        IdValidate.parser(noOfCopies)));

                System.out.println("Book copies have been added!");

            } else {
                System.out.println("This branch already has this book registered");
            }
        } else {
            System.out.println("Book does not exist");
        }
    }

    public static void updateBookCopies() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the branch where you are trying to update the number of copies");
        String branchKey = scan.nextLine();
        Map<String, Library> libraryMap = LibraryDao.createMap();
        while (!IdValidate.isValid(branchKey)) {
            System.out.println("Branch Id is not valid please try again");
            branchKey = scan.nextLine();
        }

        if (libraryMap.containsKey(branchKey)) {
            System.out.println("Now enter the ISBN of the book you are updating");
            String isbnKey = scan.nextLine();
            while (!IdValidate.isValid(isbnKey)) {
                System.out.println("ISBN is not valid please try again");
                isbnKey = scan.nextLine();
            }
            Map<String, Book> bookMap = BookDao.createMap();

            if (bookMap.containsKey(isbnKey)) {
                Map<String, BookCopies> bookCopiesMap = BookCopiesDao.createMap();
                if (bookCopiesMap.containsKey(isbnKey + "," + branchKey)) {

                    System.out.println("What would you like to update?");
                    System.out.println("(1)Number of copies\n" +
                            "(2)Different ISBN\n" +
                            "(3)Different Branch ID");
                    String userInput = scan.nextLine();
                    switch (userInput) {

                        case "1":
                            System.out.println("Please enter the number of you would like to change to");
                            String changeCopies = scan.nextLine();
                            while (!IdValidate.isValid(changeCopies)) {
                                System.out.println("Number is not valid please try again");
                                changeCopies = scan.nextLine();
                            }
                            bookCopiesMap.get(branchKey + "," + isbnKey).setNoOfCopies(IdValidate.parser(changeCopies));

                            BookCopiesDao.update(bookCopiesMap.get(branchKey + "," + isbnKey));

                            break;

                        case "2":
                            System.out.println("Please enter the number of you would like to change to");
                            String changeIsbn = scan.nextLine();
                            while (!IdValidate.isValid(changeIsbn)) {
                                System.out.println("ISBN is not valid please try again");
                                changeIsbn = scan.nextLine();
                            }
                            bookCopiesMap.get(branchKey + "," + isbnKey).setIsbn(IdValidate.parser(changeIsbn));
                            ;
                            BookCopiesDao.updateById(bookCopiesMap.get(branchKey + "," + isbnKey),
                                    IdValidate.parser(branchKey), IdValidate.parser(isbnKey));
                            break;

                        case "3":
                            System.out.println("Please enter the number of you would like to change to");
                            String changeBranch = scan.nextLine();
                            while (!IdValidate.isValid(changeBranch)) {
                                System.out.println("Branch Id is not valid please try again");
                                changeBranch = scan.nextLine();
                            }
                            bookCopiesMap.get(branchKey + "," + isbnKey).setBranchId(IdValidate.parser(changeBranch));

                            BookCopiesDao.updateById(bookCopiesMap.get(branchKey + "," + isbnKey),
                                    IdValidate.parser(branchKey), IdValidate.parser(isbnKey));
                            break;

                        default:
                            System.out.println("Not a valid option");
                            break;
                    }
                } else {
                    System.out.println("This entry does not exist");
                }


            } else {
                System.out.println("Book does not exist");
            }


        } else {
            System.out.println("Library branch does not exist");
        }
    }

    public static void deleteBookCopies() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the branch where you are trying to delete the entry");
        String branchKey = scan.nextLine();
        Map<String, Library> libraryMap = LibraryDao.createMap();
        while (!IdValidate.isValid(branchKey)) {
            System.out.println("Branch Id is not valid please try again");
            branchKey = scan.nextLine();
        }

        if (libraryMap.containsKey(branchKey)) {
            System.out.println("Now enter the ISBN of the book you are updating");
            String isbnKey = scan.nextLine();
            while (!IdValidate.isValid(isbnKey)) {
                System.out.println("ISBN is not valid please try again");
                isbnKey = scan.nextLine();
            }
            Map<String ,BookCopies>bookCopiesMap = BookCopiesDao.createMap();
            if (bookCopiesMap.containsKey(isbnKey+","+branchKey)){
                BookCopiesDao.delete(bookCopiesMap.get(isbnKey+","+branchKey));
                System.out.println("Book has been deleted");
            }else {
                System.out.println("This entry does not exist");
            }

        }else {
            System.out.println("Branch does not exist");
        }
    }
}