package com.smoothstack.lms.dao;

import com.smoothstack.lms.model.Publisher;
import com.smoothstack.lms.myutil.Env;

import java.sql.*;
import java.util.*;

public class PublisherDao {

    public static void show() {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_publisher");
            while (rs.next())
                System.out.println(rs.getInt(1) + " Name:" + rs.getString(2) + " Address:"
                        + rs.getString(3) + " Phone Number:" + rs.getString(4));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void add(Publisher publisher) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("INSERT INTO tbl_publisher VALUES(?,?,?,?)");
            stmt.setInt(1, publisher.getId());
            stmt.setString(2, publisher.getName());
            stmt.setString(3, publisher.getAddress());
            stmt.setInt(4, publisher.getPhone());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Map<String, Publisher> createMap() {

        Map<String, Publisher> publisherMap = new HashMap<>();
        //initiated buffer reader
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_publisher");
            while (rs.next()) {
                publisherMap.put(Integer.toString(rs.getInt(1)), new Publisher(rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getInt(1)));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }        //initiate map
        return publisherMap;
    }

    public static void delete(Publisher publisher) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("DELETE FROM tbl_publisher WHERE publisherId = (?)");
            stmt.setInt(1, publisher.getId());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //updates the csv by writing to it
    public static void update(Publisher publisher) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_publisher " +
                    "SET publisherId = (?),publisherName = (?), publisherAddress = (?),publisherPhone = (?)"
                    + "WHERE publisherId = (?)");
            stmt.setInt(1, publisher.getId());
            stmt.setString(2, publisher.getName());
            stmt.setString(3, publisher.getAddress());
            stmt.setInt(4, publisher.getPhone());
            stmt.setInt(5, publisher.getId());

            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateById(Publisher publisher, int oldId) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_publisher " +
                    "SET publisherId = (?),publisherName = (?), publisherAddress = (?),publisherPhone=(?)"
                    + "WHERE publisherId = (?)");
            stmt.setInt(1, publisher.getId());
            stmt.setString(2, publisher.getName());
            stmt.setString(3, publisher.getAddress());
            stmt.setInt(4, publisher.getPhone());
            stmt.setInt(5, oldId);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


