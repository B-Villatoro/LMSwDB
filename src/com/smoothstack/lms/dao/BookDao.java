package com.smoothstack.lms.dao;

import com.smoothstack.lms.model.Author;
import com.smoothstack.lms.model.Book;
import com.smoothstack.lms.myutil.Env;

import java.io.*;
import java.sql.*;
import java.util.*;

public class BookDao {
    public static void show() {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_book");
            while (rs.next())
                System.out.println("ISBN: "+rs.getInt(1) + "  Title: " + rs.getString(2)+
                        " Author ID: "+rs.getString(3)+" Publisher ID: "+rs.getString(4));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void add(Book book) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("INSERT INTO tbl_book VALUES(?,?,?,?)");
            stmt.setInt(1, book.getIsbn());
            stmt.setString(2, book.getTitle());
            stmt.setInt(3, book.getAuthorId());
            stmt.setInt(4, book.getPublisherId());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }



    public static Map<String, Book> createMap() {

        Map<String, Book> bookMap = new HashMap<>();
        //initiated buffer reader
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_book");
            while (rs.next());
            bookMap.put(Integer.toString(rs.getInt(1)),new Book(rs.getString(2),
                    rs.getInt(1),rs.getInt(3),rs.getInt(4)));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }        //initiate map

        //initiate map
        return bookMap;
    }

    public static void delete( Book book) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("DELETE FROM tbl_book WHERE bookId = (?)");
            stmt.setInt(1, book.getIsbn());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void update(Book book) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_book " +
                    "SET bookId = (?), title=(?), authorId=(?), pubId=(?)" +
                    "WHERE bookId = (?)");
            stmt.setInt(1, book.getIsbn());
            stmt.setString(2, book.getTitle());
            stmt.setInt(3, book.getAuthorId());
            stmt.setInt(4, book.getPublisherId());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

