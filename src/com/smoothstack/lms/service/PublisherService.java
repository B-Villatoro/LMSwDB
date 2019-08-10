package com.smoothstack.lms.service;

import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.PublisherDao;
import com.smoothstack.lms.model.Author;
import com.smoothstack.lms.model.Book;
import com.smoothstack.lms.model.Publisher;
import com.smoothstack.lms.myutil.IdValidate;

import java.util.Map;
import java.util.Scanner;

public class PublisherService {

    public static void addPublisher() {
        Map<String, Publisher> publisherMap = PublisherDao.createMap();
        Scanner scan = new Scanner(System.in);
        String address;
        String publisherName;
        String publisherId;

        System.out.println("Please create the publisher Id");
        publisherId = scan.nextLine();

        while (!IdValidate.isValid(publisherId) && publisherMap.containsKey(publisherId)) {
            if (publisherMap.containsKey(publisherId)) {
                System.out.println("Publisher id already exists");
                publisherId = scan.nextLine();
            } else {
                System.out.println("Publisher id is not valid please try again");
                publisherId = scan.nextLine();
            }
        }

        System.out.println("Please enter the publisher name you would like to add");
        publisherName = scan.nextLine();

        System.out.println("Please enter the publisher address");
        address = scan.nextLine();

        PublisherDao.add(new Publisher(publisherName, address, IdValidate.parser(publisherId)));
        System.out.println(publisherName + " is added!");
    }

    public static void addPublisher(int publisherId) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter the publisher name you would like to add");
        String publisherName = scan.nextLine();

        System.out.println("Please enter the publisher address");
        String address = scan.nextLine();

        PublisherDao.add(new Publisher(publisherName, address, publisherId));
        System.out.println(publisherName + " is added!");
    }

    public static void updatePublisher() {
        Scanner scan = new Scanner(System.in);
        Map<String, Publisher> publisherMap = PublisherDao.createMap();
        String publisherKey;
        String userChoice;

        System.out.println("Enter the publisher id that you would like to update");
        publisherKey = scan.nextLine();
        while(!IdValidate.isValid(publisherKey)){
            System.out.println("Id not valid please try again");
            publisherKey = scan.nextLine();
        }

        if (publisherMap.containsKey(publisherKey)) {
            Publisher p = publisherMap.get(publisherKey);
            System.out.println("What would you like to change?\n" +
                    "(1)Publisher name\n" +
                    "(2)Publisher address\n" +
                    "(3)Publisher id");
            userChoice = scan.nextLine();

            switch (userChoice) {
                case "1":
                    System.out.println("What would you like to change the name to?");
                    String changeName = scan.nextLine();
                    p.setName(changeName);

                    PublisherDao.update(p);
                    System.out.println("The publisher name has been changed!");
                    break;

                case "2":
                    System.out.println("What would you like to change the address to?");
                    String changeAddress = scan.nextLine();
                    p.setAddress(changeAddress);
                    PublisherDao.update(p);
                    System.out.println("The publisher address has been changed!");
                    break;

                case "3":


                    System.out.println("What would you like to change the publisher id to?");
                    String changeId = scan.nextLine();
                    changeId = "pid-" + changeId;

                    while (publisherMap.containsKey(changeId) && !IdValidate.isValid(changeId)) {
                        if(publisherMap.containsKey(changeId)){
                            System.out.println("Id already exists, please try again.");
                            changeId = scan.nextLine();
                        } else{
                            System.out.println("Id is not valid, please try again");
                            changeId = scan.nextLine();
                        }

                    }
                    p.setId(IdValidate.parser(changeId));
                    PublisherDao.updateById(p,IdValidate.parser(publisherKey));
                    System.out.println("Publisher id has been changed!");
                    break;
            }
        } else {
            System.out.println("Publisher does not exist");
        }

    }

    public static void deletePublisher() {
        Map<String, Publisher> publisherMap = PublisherDao.createMap();
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the publisher id you would like to delete");
        String deleteKey = scan.nextLine();

        while(!publisherMap.containsKey(deleteKey) && !IdValidate.isValid(deleteKey)){
            if (!publisherMap.containsKey(deleteKey)) {
                System.out.println("Publisher Id does not exist, please try again.");
                deleteKey = scan.nextLine();
            } else {
                System.out.println("Id is not valid, please try again");
                deleteKey = scan.nextLine();
            }
        }

        PublisherDao.delete(publisherMap.get(deleteKey));
        System.out.println("Publisher deleted!");
    }
}
