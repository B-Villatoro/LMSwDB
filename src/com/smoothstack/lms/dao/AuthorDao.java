package com.smoothstack.lms.dao;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.smoothstack.lms.model.Author;
import com.smoothstack.lms.myutil.Env;

public class AuthorDao {

    public static void show() {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_author");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void add(Author author) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("INSERT INTO tbl_author VALUES(?,?)");
            stmt.setInt(1, author.getId());
            stmt.setString(2, author.getName());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static Map<String, Author> createMap() {
        Map<String, Author> authorMap = new HashMap<>();
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_author");
            while (rs.next()){
                authorMap.put(Integer.toString(rs.getInt(1)), new Author(rs.getString(2), rs.getInt(1)));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }        //initiate map
        return authorMap;
    }

    public static void delete(Author author) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("DELETE FROM tbl_author WHERE authorId = (?)");
            stmt.setInt(1, author.getId());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void update(Author author) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_author SET authorId = (?),authorName = (?) "+
                    "WHERE authorId = (?)");
            stmt.setInt(1, author.getId());
            stmt.setString(2, author.getName());
            stmt.setInt(3, author.getId());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateById(Author author,int oldId) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_author SET authorId = (?),authorName = (?) "+
                    "WHERE authorId = (?)");
            stmt.setInt(1, author.getId());
            stmt.setString(2, author.getName());
            stmt.setInt(3, oldId);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
