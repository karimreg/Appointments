/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appointments;
import static java.lang.System.out;
import java.sql.*;  
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
  
public class LoginApp {  
public static boolean validate(String name,String pass){  
  
 try {
 Context ctx = new InitialContext();
 if (ctx == null) {
 throw new Exception("Error - No Context");
 }


DataSource ds = (DataSource)
ctx.lookup("java:/comp/env/jdbc/postgres");
 if (ds != null) { Connection conn = ds.getConnection();
if (conn != null) {
 out.println("<table>");
Statement stmt = conn.createStatement();
ResultSet rst = stmt.executeQuery("SELECT username,password from user_;");
 
 while (rst.next()) {
 if( name.equals(rst.getString(1)) &&
         pass.equals(rst.getString(2)) )return true;


 }
 conn.close();
 
 } else {
 out.println("Error: conn is null ");
 }
 }
 } catch (Exception e) {
 //e.printStackTrace();
 out.println("Exception caught");
 out.println(e.toString());
 }
return false;  
}  
}  
