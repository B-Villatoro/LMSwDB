package com.smoothstack.lms.service;

import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.model.Author;
import com.smoothstack.lms.myutil.IdValidate;
import java.util.Map;
import java.util.Scanner;

public class AuthorService {


    //Prompt users to fulfill requirements for the
    public static void addAuthor(int authorId) {
        System.out.println("Please enter the author name you would like to add");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();

        AuthorDao.add(new Author(name, authorId));
        System.out.println("Author Added! Let's continue.");
    }


    public static void addAuthor() {

        Scanner scan = new Scanner(System.in);
        String name;
        String userAuthorId;
        int authorId;

        System.out.println("Please enter author Id");
        userAuthorId = scan.nextLine();
        while(IdValidate.isValid(userAuthorId)){
            System.out.println("Enter vaild id");
            userAuthorId = scan.nextLine();
        }

        Map<String, Author> authorMap = AuthorDao.createMap();
        if (authorMap.containsKey(userAuthorId)) {
            System.out.println("Author id already exists");
        } else {
            System.out.println("Please enter the author name you would like to add");
            name = scan.nextLine();

            authorId = IdValidate.parser(userAuthorId);
            AuthorDao.add(new Author(name, authorId));
            System.out.println(name + " is added!");
        }
    }

    public static void updateAuthor() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the Author Id you would like to update");
        String authorKey = scan.nextLine();

        Map<String, Author> authorMap = AuthorDao.createMap();
        if (authorMap.containsKey(authorKey)) {
            Author updateAuthor = authorMap.get(authorKey);
            System.out.println("What would you like to change?\n" +
                    "(1)Author name\n" +
                    "(2)Author Id\n");
            String userChoice = scan.nextLine();
            switch (userChoice) {
                case "1":
                    System.out.println("What would you like to change it to?");
                    String changeName = scan.nextLine();
                    updateAuthor.setName(changeName);
                    AuthorDao.update(updateAuthor);
                    break;

                case "2":
                    System.out.println("What would you like to change it to?");
                    String changeAid = scan.nextLine();

                    while (authorMap.containsKey(changeAid) && !IdValidate.isValid(changeAid)) {
                        System.out.println("Author id already exists or is not valid, please try again");
                        changeAid = scan.nextLine();
                    }
                    updateAuthor.setId(IdValidate.parser(changeAid));
                    AuthorDao.updateById(updateAuthor,IdValidate.parser(changeAid));

                    System.out.println("Author and all books have been updated to the new id!");
                    break;
            }

        } else {
            System.out.println("Author Id does not exist");
        }
    }

    public static void deleteAuthor() {
        Map<String, Author> authorMap = AuthorDao.createMap();
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the author id you would like to delete");
        String deleteKey = scan.nextLine();

        if (authorMap.containsKey(deleteKey)) {
            AuthorDao.delete(authorMap.get(deleteKey));
            System.out.println(authorMap.get(deleteKey).getName()+ " is deleted!");
        } else {
            System.out.println("Author does not exist");
        }

    }
}