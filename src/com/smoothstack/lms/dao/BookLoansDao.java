package com.smoothstack.lms.dao;

import com.smoothstack.lms.model.Borrower;
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
            ResultSet rs = stmt.executeQuery("select * from tbl_borrower");
            while (rs.next())
                System.out.println("Card Number: " + rs.getInt(1) + " Name: " + rs.getString(2) +
                        " Phone: " + rs.getString(3) + " " + rs.getString(4));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Map<String, Borrower> createMap() {
        Map<String, Borrower> borrowerMap = new HashMap<>();
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_borrower");
            while (rs.next()) {
                borrowerMap.put(Integer.toString(rs.getInt(1)),new Borrower(rs.getInt(1),
                        rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }        //initiate map
        return borrowerMap;
    }
    public static void add(Borrower borrower) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("INSERT INTO tbl_borrower VALUES(?,?,?,?)");
            stmt.setInt(1, borrower.getCardNo());
            stmt.setString(2, borrower.getBorrowerName());
            stmt.setString(3, borrower.getBorrowerAddress());
            stmt.setInt(4, borrower.getBorrowerPhone());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void delete( Borrower borrower) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("DELETE FROM tbl_borrower WHERE bookId = (?)");
            stmt.setInt(1, borrower.getCardNo());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void update(Borrower borrower) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_borrower " +
                    "SET cardNo = (?), name=(?), address=(?), phone=(?)" +
                    "WHERE cardNo = (?)");
            stmt.setInt(1, borrower.getCardNo());
            stmt.setString(2, borrower.getBorrowerName());
            stmt.setString(3, borrower.getBorrowerAddress());
            stmt.setInt(4, borrower.getBorrowerPhone());
            stmt.setInt(5,borrower.getCardNo());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateById(Borrower borrower,int oldId) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_borrower " +
                    "SET cardNo = (?), name=(?), address=(?), phone=(?)" +
                    "WHERE cardNo = (?)");
            stmt.setInt(1, borrower.getCardNo());
            stmt.setString(2, borrower.getBorrowerName());
            stmt.setString(3, borrower.getBorrowerAddress());
            stmt.setInt(4, borrower.getBorrowerPhone());
            stmt.setInt(5,oldId);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
