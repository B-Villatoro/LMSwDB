package com.smoothstack.lms.dao;

import java.io.*;
import java.util.*;

import com.smoothstack.lms.model.Author;

public class AuthorDao {

    public static void add(Author author) {

        try {
            FileWriter fr = new FileWriter("./resources/authors.csv", true);
            BufferedWriter writer = new BufferedWriter(fr);
            writer.newLine();
            writer.append(author.getName() + ";");
            writer.append(author.getId() + ";");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void show() {
        File fileName = new File("./resources/authors.csv");
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            br.lines().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Author> createMap() {

        Map<String, Author> authorBookMap = new HashMap<>();
        //initiated buffer reader
        try {
            FileInputStream fin = new FileInputStream("./resources/authors.csv");
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(fin));
            String authorLine;

            while ((authorLine = buffReader.readLine()) != null) {
                String[] splitArray = authorLine.split(";");
                //creating the author object to pass into the map
                Author author = new Author();
                author.setName(splitArray[0]);
                author.setId(splitArray[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //initiate map
        return authorBookMap;
    }

    public static void delete(String key, Map<String, Author> map) {
        if (map.containsKey(key)) {
            try {
                FileWriter fr = new FileWriter("./resources/authors.csv");
                BufferedWriter writer = new BufferedWriter(fr);
                map.remove(key);
                map.forEach((mapKey, author) -> {
                    try {
                        String stringBuild = author.getName()+ ";" + author.getId() +";";
                        writer.append(stringBuild);
                        writer.newLine();
                    } catch (IOException exc0) {
                        exc0.printStackTrace();
                    }
                });
                writer.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } else {
            System.out.println("Author does not exist");
        }
    }

    public static void update(Map<String, Author> map) {
        try {
            FileWriter fr = new FileWriter("./resources/authors.csv");
            BufferedWriter writer = new BufferedWriter(fr);
            map.forEach((key, author) -> {
                try {
                    writer.append(author.getName() + ";");
                    writer.append(author.getId() + ";");
                    writer.newLine();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            });
            writer.close();
        } catch (IOException exc1) {
            exc1.printStackTrace();
        }
    }
}