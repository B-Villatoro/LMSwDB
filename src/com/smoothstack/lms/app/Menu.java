package com.smoothstack.lms.app;

import com.smoothstack.lms.dao.*;
import com.smoothstack.lms.model.BookCopies;
import com.smoothstack.lms.model.BookLoans;
import com.smoothstack.lms.model.Borrower;
import com.smoothstack.lms.model.Library;
import com.smoothstack.lms.service.*;

import java.util.Scanner;

public class Menu {

    public Menu() {
        System.out.println("Hello welcome to BookBook!\nHere you will be able to organize your books!");
        mainMenu();
    }

    public static void mainMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What would you like to do today?\n" +
                "(1)View the library\n" +
                "(2)Add to the library\n" +
                "(3)Update an existing item\n" +
                "(4)Remove from the library\n" +
                "(5)Sign Up for a library card\n"+
                "(6)Check out a book today\n" +
                "(7)Extend the due date\n" +
                "(8)Return a book\n"+
                "(0)Close Program");

        String optionSelect = scan.nextLine();
        switch (optionSelect) {
            case "1":
                handleShow();
                break;
            case "2":
                handleAdd();
                break;
            case "3":
                handleUpdate();
                break;
            case "4":
                handleDelete();
                break;
            case "5":
                BorrowerService.addBorrower();
                break;
            case "6":
                BookLoansService.checkOutBook();
                break;
            case "7":
                BookLoansService.extendDueDate();
                break;
            case "8":
                BookLoansService.returnBook();
                break;
            case "0":
                System.out.println("GoodBye!");
                System.exit(0);
                break;
            default:
                System.out.println("Please enter valid option");
                mainMenu();
        }
    }

    //prompts back to main menu
    private static void backToMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to go back to main menu? y/n");
        String yn = scan.nextLine();
        yn = yn.toLowerCase();
        if (yn.equals("y") || yn.equals("yes")) {
            mainMenu();
        } else System.exit(0);
    }

    private static void handleAdd() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What item would you like to add?\n" +
                "(1)Author\n" +
                "(2)Book\n" +
                "(3)Publisher\n" +
                "(4)Library Branch");
        String opt = scan.nextLine();
        opt = opt.toLowerCase();
        switch (opt) {
            case "1":
            case "author":
                AuthorService.addAuthor();
                backToMenu();
                break;
            case "2":
            case "book":
                BookService.addBook();
                backToMenu();
                break;

            case "3":
            case "publisher":
                PublisherService.addPublisher();
                backToMenu();
                break;

            case"4":
                LibraryService.addLibrary();
                backToMenu();
                break;
            default:
                System.out.println("Wrong Input");
                handleAdd();
                break;
        }

    }

    private static void handleShow() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What item would you like to see?\n" +
                "(1)Author\n" +
                "(2)Book\n" +
                "(3)Publisher\n" +
                "(4)Library Branches\n" +
                "(5)Book Copies\n" +
                "(6)Book Loans");
        String opt = scan.nextLine();
        opt = opt.toLowerCase();
        switch (opt) {
            case "1":
            case "author":
                AuthorDao.show();
                backToMenu();
                break;
            case "2":
            case "book":
                BookDao.show();
                backToMenu();
                break;

            case "3":
            case "publisher":
                PublisherDao.show();
                backToMenu();
                break;
            case "4":
                LibraryDao.show();
                backToMenu();
                break;
            case "5":
                BookCopiesDao.show();
                backToMenu();
                break;
            case "6":
                BookLoansDao.show();
                backToMenu();
                break;

            default:
                System.out.println("Wrong Input");
                handleShow();
                break;
        }
    }
    private static void handleUpdate() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What item would you like to update?\n" +
                "(1)Author\n" +
                "(2)Book\n" +
                "(3)Publisher");
        String opt = scan.nextLine();
        opt = opt.toLowerCase();
        switch (opt) {
            case "1":
            case "author":
                AuthorService.updateAuthor();
                backToMenu();
                break;

            case "2":
            case "book":
                BookService.updateBook();
                backToMenu();
                break;

            case "3":
            case "publisher":
                PublisherService.updatePublisher();
                backToMenu();
                break;

            default:
                System.out.println("Wrong Input");
                handleUpdate();
                break;
        }
    }
    private static void handleDelete() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What item would you like to delete?\n" +
                "(1)Author\n" +
                "(2)Book\n" +
                "(3)Publisher");
        String opt = scan.nextLine();
        opt = opt.toLowerCase();
        switch (opt) {
            case "1":
            case "author":
                AuthorService.deleteAuthor();
                backToMenu();
                break;
            case "2":
            case "book":
                BookService.deleteBook();
                backToMenu();
                break;

            case "3":
            case "publisher":
                PublisherService.deletePublisher();
                backToMenu();
                break;

            default:
                System.out.println("Wrong Input");
                backToMenu();
                break;
        }
    }
}