package com.smoothstack.lms.service;

import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.dao.BorrowerDao;
import com.smoothstack.lms.model.Author;
import com.smoothstack.lms.model.Borrower;
import com.smoothstack.lms.myutil.IdValidate;

import java.util.Map;
import java.util.Scanner;

public class BorrowerService {

    public static void addBorrower() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Please create a card number");
        String userCardNo = scan.nextLine();

        Map<String, Borrower> borrowerMap = BorrowerDao.createMap();
        while (!IdValidate.isValid(userCardNo) || borrowerMap.containsKey(userCardNo)) {
            if (borrowerMap.containsKey(userCardNo)) {
                System.out.println("Card number already exists please try again");
                userCardNo = scan.nextLine();
            } else {
                System.out.println("Invalid card number,please try again");
                userCardNo = scan.nextLine();
            }
        }

        System.out.println("Enter your name");
        String name = scan.nextLine();

        System.out.println("Enter your address");
        String address = scan.nextLine();

        System.out.println("Enter your phone number");
        String phone = scan.nextLine();
        while (!IdValidate.isValid(phone)) {
            System.out.println("Invalid phone number please try again");
            phone = scan.nextLine();
        }

        BorrowerDao.add(new Borrower(IdValidate.parser(userCardNo), name, address,
                IdValidate.parser(phone)));
        System.out.println("You have created a library card!");
    }


    public static void updateBorrower() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the card number you would like to update");
        String borrowerKey = scan.nextLine();

        while (IdValidate.isValid(borrowerKey)) {
            System.out.println("Enter valid card number");
            borrowerKey = scan.nextLine();
        }

        Map<String, Borrower> borrowerMap = BorrowerDao.createMap();
        if (borrowerMap.containsKey(borrowerKey)) {
            Borrower updateBorrower = borrowerMap.get(borrowerKey);
            System.out.println("What would you like to change?\n" +
                    "(1)Borrower name\n" +
                    "(2)Borrower address\n" +
                    "(3)Borrower phone number\n" +
                    "(3)Borrower card number\n");
            String userChoice = scan.nextLine();
            switch (userChoice) {
                case "1":
                    System.out.println("What would you like to change the name to?");
                    String changeName = scan.nextLine();
                    updateBorrower.setBorrowerName(changeName);
                    BorrowerDao.update(updateBorrower);
                    System.out.println("Borrower Added!");
                    break;


                case "2":
                    System.out.println("What would you like to change the address to?");
                    String changeAddress = scan.nextLine();

                    updateBorrower.setBorrowerAddress(changeAddress);
                    BorrowerDao.update(updateBorrower);

                    break;

                case "3":
                    System.out.println("What would you like to change the number to?");
                    String changePhone = scan.nextLine();

                    while (!IdValidate.isValid(changePhone)) {
                        System.out.println("Borrower id is not valid, please try again");
                        changePhone = scan.nextLine();
                    }
                    updateBorrower.setBorrowerPhone(IdValidate.parser(changePhone));
                    BorrowerDao.update(updateBorrower);
                    System.out.println("Phone number has been updated!");

                case "4":
                    System.out.println("What would you ike to change the card number to?");
                    String changeCardNo = scan.nextLine();

                    while (borrowerMap.containsKey(changeCardNo) || !IdValidate.isValid(changeCardNo)) {
                        if (borrowerMap.containsKey(changeCardNo)) {
                            System.out.println("Card number already exist, please try again");
                            changeCardNo = scan.nextLine();
                        } else {
                            System.out.println("Card number is not valid, please try again");
                            changeCardNo = scan.nextLine();
                        }
                    }

                    updateBorrower.setCardNo(IdValidate.parser(changeCardNo));
                    BorrowerDao.updateById(updateBorrower, IdValidate.parser(borrowerKey));
                    System.out.println("Card number has been updated to the new id!");
                    break;
            }

        } else {
            System.out.println("Card number does not exist");
        }

    }

    public static void deleteBorrower() {
        Map<String, Borrower> borrowerMap = BorrowerDao.createMap();
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the card number you would like to delete");
        String deleteKey = scan.nextLine();
        while(!IdValidate.isValid(deleteKey)){
            System.out.println("Not a valid card number, please try again");
            deleteKey = scan.nextLine();
        }

        if (borrowerMap.containsKey(deleteKey)) {
            BorrowerDao.delete(borrowerMap.get(deleteKey));
            System.out.println(borrowerMap.get(deleteKey).getCardNo()+ " is deleted!");
        } else {
            System.out.println("Card number does not exist");
        }

    }


}
