package com.smoothstack.lms.service;

import com.smoothstack.lms.app.Menu;
import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.PublisherDao;
import com.smoothstack.lms.model.Author;
import com.smoothstack.lms.model.Book;
import com.smoothstack.lms.model.Publisher;
import com.smoothstack.lms.myutil.IdValidate;

import java.util.Map;
import java.util.Scanner;

public class BookService {


    public static void addBook() {
        Map<String, Book> bookMap = BookDao.createMap();
        Scanner scan = new Scanner(System.in);
        String isbn;
        String authorId;
        String publisherId;

        System.out.println("Please enter Isbn ");
        isbn = scan.nextLine();

        //if isbn already exists, book exists
        while (bookMap.containsKey(isbn) && !IdValidate.isValid(isbn)) {
            System.out.println("ISBN already exists or is not valid please try again");
            isbn = scan.nextLine();
        }
        int newIsbn = IdValidate.parser(isbn);
        //prompt for pid and check if exists

        System.out.println("Please enter the title name you would like to add");
        String title = scan.nextLine();

        Map<String, Publisher> publisherMap = PublisherDao.createMap();
        System.out.println("Please enter the publisher id");
        publisherId = scan.nextLine();

        while (!IdValidate.isValid(publisherId)) {
            System.out.println("Not a valid publisher Id, please try again");
            publisherId = scan.nextLine();
        }

        if (publisherMap.containsKey(publisherId)) {
            //prompt for aid and check if exists
            Map<String, Author> authorMap = AuthorDao.createMap();
            System.out.println("Please enter the author id");
            authorId = scan.nextLine();

            while (!IdValidate.isValid(authorId)) {
                System.out.println("Not a valid author Id, please try again");
                authorId = scan.nextLine();
            }


            if (authorMap.containsKey(authorId)) {
                //all passes create book
                int newPubId = IdValidate.parser(publisherId);
                int newAuthorId = IdValidate.parser(authorId);

                BookDao.add(new Book(title, newIsbn, newAuthorId, newPubId));
                System.out.println(title + " added!");

            } else {
                //if author does not exist, create author then create book
                int newPubId = IdValidate.parser(publisherId);
                int newAuthorId = IdValidate.parser(authorId);
                System.out.println("Author does not exist, creating author");
                AuthorService.addAuthor(newAuthorId);

                BookDao.add(new Book(title, newIsbn, newAuthorId, newPubId));
            }

        } else {
            //if pid doesn't exist make it exist
            System.out.println("Publisher does not exist...\n" +
                    "Creating publisher");
            int newPubId = IdValidate.parser(publisherId);
            PublisherService.addPublisher(newPubId);

            System.out.println("Lets continue!");

            Map<String, Author> authorMap = AuthorDao.createMap();
            System.out.println("Please enter the author id");
            authorId = scan.nextLine();

            while (!IdValidate.isValid(authorId)) {
                System.out.println("Not a valid author Id, please try again");
                authorId = scan.nextLine();
            }

            if (authorMap.containsKey(authorId)) {
                //all passes create book
                int newAuthorId = IdValidate.parser(authorId);

                BookDao.add(new Book(title, newIsbn, newAuthorId, newPubId));
                System.out.println(title + " added!");
            } else {
                //if author does not exist add the book through author handler
                int newAuthorId = IdValidate.parser(authorId);
                System.out.println("Author does not exist, creating author");
                AuthorService.addAuthor(newAuthorId);
                System.out.println("Lets continue!");
                BookDao.add(new Book(title, newIsbn, newAuthorId, newPubId));
                System.out.println(title + " added!");

            }
        }
    }


    public static void updateBook() {
        Scanner scan = new Scanner(System.in);
        Map<String, Book> bookMap = BookDao.createMap();
        Map<String, Author> authorMap = AuthorDao.createMap();

        String bookKey;
        String userChoice;

        System.out.println("Enter the ISBN that you would like to update");
        bookKey = scan.nextLine();
        while (!IdValidate.isValid(bookKey)) {
            System.out.println("ISBN not valid please try again");
            bookKey = scan.nextLine();
        }

        if (bookMap.containsKey(bookKey)) {
            //create a book based off books csv
            Book b = bookMap.get(bookKey);
            System.out.println("Book found! What would you like to change?\n" +
                    "(1)Book title\n" +
                    "(2)Book ISBN\n" +
                    "(3)Author id\n" +
                    "(4)Publisher id");
            userChoice = scan.nextLine();

            switch (userChoice) {
                case "1":
                    System.out.println("What would you like to change it to?");
                    String changeTitle = scan.nextLine();
                    bookMap.get(bookKey).setTitle(changeTitle);
                    BookDao.update(bookMap.get(bookKey));
                    break;

                case "2":
                    System.out.println("What would you like to change ISBN to?");
                    String changeIsbn = scan.nextLine();

                    //validate new book id
                    while (bookMap.containsKey(changeIsbn) && !IdValidate.isValid(changeIsbn)) {
                        System.out.println("ISBN already exists or is not valid please try again");
                        changeIsbn = scan.nextLine();
                    }

                    b.setIsbn(IdValidate.parser(changeIsbn));

                    BookDao.updateById(b, IdValidate.parser(bookKey));
                    break;

                case "3":
                    System.out.println("What would you like to change author id to?");
                    String changeAid = scan.nextLine();

                    while (!IdValidate.isValid(changeAid)) {
                        System.out.println("Not a valid author Id, please try again");
                        changeAid = scan.nextLine();
                    }

                    if (authorMap.containsKey(changeAid)) {

                        b.setAuthorId(IdValidate.parser(changeAid));
                        BookDao.update(b);

                    } else {
                        System.out.println("Cannot change to an author that does not exist");
                    }
                    break;
                case "4":
                    Map<String, Publisher> publisherMap = PublisherDao.createMap();
                    System.out.println("What would you like to change publisher id to?");
                    String changePid = scan.nextLine();

                    while (!IdValidate.isValid(changePid)) {
                        System.out.println("Not a valid publisher Id, please try again");
                        changePid = scan.nextLine();
                    }
                    if (publisherMap.containsKey(changePid)) {
                        b.setPublisherId(IdValidate.parser(changePid));
                        BookDao.update(b);
                    } else {
                        System.out.println("Publisher does not exist");
                    }
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } else {
            System.out.println("ISBN does not exist");
        }
    }

    public static void deleteBook() {
        Map<String, Book> bookMap = BookDao.createMap();
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the isbn you would like to delete");
        String deleteKey = scan.nextLine();
        while (!IdValidate.isValid(deleteKey)) {
            System.out.println("ISBN is not valid please try again");
            deleteKey = scan.nextLine();
        }
        if (bookMap.containsKey(deleteKey)) {
            BookDao.delete(bookMap.get(deleteKey));
            System.out.println("Book Deleted!");
        } else {
            System.out.println("Book does not exist");
        }
    }
}
