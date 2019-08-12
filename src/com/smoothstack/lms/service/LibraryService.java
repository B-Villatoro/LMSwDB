package com.smoothstack.lms.service;

import com.smoothstack.lms.dao.LibraryDao;
import com.smoothstack.lms.dao.LibraryDao;
import com.smoothstack.lms.model.Library;
import com.smoothstack.lms.model.Library;
import com.smoothstack.lms.myutil.IdValidate;

import java.util.Map;
import java.util.Scanner;

public class LibraryService {


    public static void addLibrary() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the branch Id");
        String userBranchId = scan.nextLine();

        Map<String, Library> libraryMap = LibraryDao.createMap();
        while (!IdValidate.isValid(userBranchId) || libraryMap.containsKey(userBranchId)) {
            if (libraryMap.containsKey(userBranchId)) {
                System.out.println("Id already exists please try again");
                userBranchId = scan.nextLine();
            } else {
                System.out.println("Enter valid id");
                userBranchId = scan.nextLine();
            }
        }

        System.out.println("Enter the branch name");
        String branchName = scan.nextLine();

        System.out.println("Enter the branch address");
        String branchAddress = scan.nextLine();

        LibraryDao.add(new Library(IdValidate.parser(userBranchId), branchName, branchAddress));
        System.out.println("Library Branch added!");
    }


    public static void updateLibrary() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the branch Id you would like to update");
        String branchKey = scan.nextLine();

        while (IdValidate.isValid(branchKey)) {
            System.out.println("Enter valid id");
            branchKey = scan.nextLine();
        }

        Map<String, Library> libraryMap = LibraryDao.createMap();
        if (libraryMap.containsKey(branchKey)) {
            Library updateLibrary = libraryMap.get(branchKey);
            System.out.println("What would you like to change?\n" +
                    "(1)Library name\n" +
                    "(2)Library address\n" +
                    "(3)Library Id\n");
            String userChoice = scan.nextLine();
            switch (userChoice) {
                case "1":
                    System.out.println("What would you like to change the name to?");
                    String changeName = scan.nextLine();
                    updateLibrary.setBranchName(changeName);
                    LibraryDao.update(updateLibrary);
                    System.out.println("Library Added!");
                    break;


                case "2":
                    System.out.println("What would you like to change the address to?");
                    String changeAddress = scan.nextLine();

                    updateLibrary.setBranchAddress(changeAddress);
                    LibraryDao.update(updateLibrary);

                    break;
                case "3":
                    System.out.println("What would you ike to change the Id to?");
                    String changeBranchId = scan.nextLine();

                    while (libraryMap.containsKey(changeBranchId) || !IdValidate.isValid(changeBranchId)) {
                        if (libraryMap.containsKey(changeBranchId)) {
                            System.out.println("Library Id already exist, please try again");
                            changeBranchId = scan.nextLine();
                        } else {
                            System.out.println("Library id is not valid, please try again");
                            changeBranchId = scan.nextLine();
                        }
                    }
                    
                    updateLibrary.setBranchId(IdValidate.parser(changeBranchId));
                    LibraryDao.updateById(updateLibrary, IdValidate.parser(changeBranchId));
                    System.out.println("Library has been updated to the new id!");
                    break;
            }

        } else {
            System.out.println("Library Id does not exist");
        }

    }


}
