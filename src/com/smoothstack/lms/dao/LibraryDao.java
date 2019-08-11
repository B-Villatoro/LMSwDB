package com.smoothstack.lms.dao;

import com.smoothstack.lms.model.Library;
import com.smoothstack.lms.model.Library;
import com.smoothstack.lms.myutil.Env;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class LibraryDao {

    public static void show(){

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_library_branch");
            while (rs.next())
                System.out.println("Branch ID: "+rs.getInt(1) + " Branch Name: " + rs.getString(2)+
                        " Address: " +rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void add(Library library){
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("INSERT INTO tbl_library_branch VALUES(?,?,?)");
            stmt.setInt(1, library.getBranchId());
            stmt.setString(2, library.getBranchName());
            stmt.setString(3,library.getBranchAddress());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static Map<String,Library> createMap(){
        Map<String, Library> libraryMap = new HashMap<>();
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_library_branch");
            while (rs.next()){
                libraryMap.put(Integer.toString(rs.getInt(1)), new Library(rs.getInt(1),
                        rs.getString(2),rs.getString(3)));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }        //initiate map
        return libraryMap;


    }

    public static void update(Library library){
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_library_branch SET branchId = (?)," +
                    "branchName = (?), branchAddress = (?) "+
                    "WHERE branchId = (?)");
            stmt.setInt(1, library.getBranchId());
            stmt.setString(2, library.getBranchName());
            stmt.setString(3, library.getBranchAddress());
            stmt.setInt(4, library.getBranchId());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void updateById(Library library,int oldId){
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("UPDATE tbl_library_branch SET branchId = (?)," +
                    "branchName = (?), branchAddress = (?) "+
                    "WHERE branchId = (?)");
            stmt.setInt(1, library.getBranchId());
            stmt.setString(2, library.getBranchName());
            stmt.setString(3, library.getBranchAddress());
            stmt.setInt(4, oldId);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void delete(Library library){
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            PreparedStatement stmt = con.prepareStatement("DELETE FROM tbl_library_branch WHERE branchId = (?)");
            stmt.setInt(1, library.getBranchId());
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
