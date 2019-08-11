package com.smoothstack.lms.dao;

import com.smoothstack.lms.model.Book;
import com.smoothstack.lms.model.BookLoans;
import com.smoothstack.lms.model.BookLoans;
import com.smoothstack.lms.myutil.Env;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BookLoansDao {
    public static void show() {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_book_loans");
            while (rs.next())
                System.out.println(" ISBN: " + rs.getString(1) + " Branch ID: " + rs.getString(2) +
                        " Card Number: " + rs.getInt(3) +
                        " Date Out: " + rs.getString(4) +
                        " Date Due: " + rs.getString(5));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Map<String, BookLoans> createMap() {
        Map<String, BookLoans> bookLoansMap = new HashMap<>();
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_book_loans");
            while (rs.next()) {
                bookLoansMap.put(rs.getString(1) + "," + rs.getString(2) + "," +
                        rs.getString(3), new BookLoans(rs.getInt(1), rs.getInt(2),
                        rs.getInt(3), rs.getString(4), rs.getString(5)));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }        //initiate map
        return bookLoansMap;
    }

    public static void add(BookLoans bookLoans) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("INSERT INTO tbl_book_loans VALUES(?,?,?,?,?)");
            stmt.setInt(1, bookLoans.getIsbn());
            stmt.setInt(2, bookLoans.getBranchId());
            stmt.setInt(3, bookLoans.getCardNo());
            stmt.setString(4, bookLoans.getDateOut());
            stmt.setString(5, bookLoans.getDateDue());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void delete(BookLoans bookLoans) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("DELETE FROM tbl_book_loans " +
                    "WHERE bookId = (?) AND branchId = (?) AND cardNo = (?);" );
            stmt.setInt(1, bookLoans.getIsbn());
            stmt.setInt(2, bookLoans.getBranchId());
            stmt.setInt(3, bookLoans.getCardNo());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void update(BookLoans bookLoans) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_book_loans "+
                    "SET bookId = (?), branchId = (?), cardNo = (?) ,dateOut=(?), dueDate = (?)"+
                    "WHERE bookId = (?) AND branchId = (?) AND cardNo = (?);" );
            stmt.setInt(1, bookLoans.getIsbn());
            stmt.setInt(2, bookLoans.getBranchId());
            stmt.setInt(3, bookLoans.getCardNo());
            stmt.setString(4, bookLoans.getDateOut());
            stmt.setString(5, bookLoans.getDateDue());
            stmt.setInt(6, bookLoans.getIsbn());
            stmt.setInt(7, bookLoans.getBranchId());
            stmt.setInt(8, bookLoans.getCardNo());

            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateById(BookLoans bookLoans, int oldIsbn,int oldBranchId,int oldCardNo) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_book_loans "+
                    "SET bookId = (?), branchId = (?), cardNo = (?),dateOut=(?), dueDate = (?)"+
                    "WHERE bookId = (?) AND branchId = (?) AND cardNo = (?) " );
            stmt.setInt(1, bookLoans.getIsbn());
            stmt.setInt(2, bookLoans.getBranchId());
            stmt.setInt(3, bookLoans.getCardNo());
            stmt.setString(4, bookLoans.getDateOut());
            stmt.setString(5, bookLoans.getDateDue());
            stmt.setInt(6, oldIsbn);
            stmt.setInt(7, oldBranchId);
            stmt.setInt(8, oldCardNo);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
