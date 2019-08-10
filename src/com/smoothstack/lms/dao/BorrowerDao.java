package com.smoothstack.lms.dao;

import com.smoothstack.lms.myutil.Env;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BorrowerDao {


    public static void show(){
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + Env.port() + "/" + Env.db(), Env.user(), Env.p());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tbl_borrower");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
