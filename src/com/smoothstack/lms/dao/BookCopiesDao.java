package com.smoothstack.lms.dao;

import com.smoothstack.lms.model.BookCopies;
import com.smoothstack.lms.model.BookCopies;
import com.smoothstack.lms.model.BookCopies;
import com.smoothstack.lms.myutil.Env;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BookCopiesDao {

    public static void show() {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_book_copies");
            while (rs.next())
                System.out.println("ISBN: " + rs.getInt(1) + " Branch ID: " + rs.getString(2) +
                        " Number of Copies: "+rs.getInt(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void add(BookCopies bookCopies) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("INSERT INTO tbl_book_copies VALUES(?,?,?)");
            stmt.setInt(1, bookCopies.getIsbn());
            stmt.setInt(2, bookCopies.getBranchId());
            stmt.setInt(3, bookCopies.getNoOfCopies());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Map<String, BookCopies> createMap() {
        Map<String, BookCopies> bookCopiesMap = new HashMap<>();
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_book_copies");
            while (rs.next()) {
                bookCopiesMap.put(rs.getString(1) + "," + rs.getString(2),
                        new BookCopies(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }        //initiate map
        return bookCopiesMap;
    }

    public static void delete(BookCopies bookCopies) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("DELETE FROM tbl_book_copies " +
                    "WHERE bookId = (?) AND branchId = (?);" );
            stmt.setInt(1, bookCopies.getIsbn());
            stmt.setInt(2, bookCopies.getBranchId());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void update(BookCopies bookCopies) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_book_copies "+
                    "SET bookId = (?), branchId = (?), noOfCopies = (?)"+
                    "WHERE bookId = (?) AND branchId = (?);" );
            stmt.setInt(1, bookCopies.getIsbn());
            stmt.setInt(2, bookCopies.getBranchId());
            stmt.setInt(3, bookCopies.getNoOfCopies());
            stmt.setInt(4, bookCopies.getIsbn());
            stmt.setInt(5, bookCopies.getBranchId());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateById(BookCopies bookCopies, int oldIsbn,int oldBranchId) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_book_copies "+
                    "SET bookId = (?), branchId = (?), noOfCopies = (?)"+
                    "WHERE bookId = (?) AND branchId = (?);" );
            stmt.setInt(1, bookCopies.getIsbn());
            stmt.setInt(2, bookCopies.getBranchId());
            stmt.setInt(3, bookCopies.getNoOfCopies());
            stmt.setInt(4, oldIsbn);
            stmt.setInt(5, oldBranchId);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
