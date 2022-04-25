//Muhammad Raehan Parikesit (123200149)

package pbo.tugas.jdbc;

import java.sql.*;
import javax.swing.JOptionPane;

public class Koneksi {
    String DBurl = "jdbc:mysql://localhost:3306/tugasjdbc";
    String DBusername = "root";
    String DBpassword = "";
    //
    String data[] = new String[2];
    Connection conn;
    Statement statement;
    static String[] username;
    
    public Koneksi() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
            System.out.println("Koneksi ke database Berhasil");
        }catch(Exception ex){
            System.out.println("Koneksi Gagal " + ex.getMessage());
        }
    }
    
    void insertData(String username, String password){
        try {
            if(!cekUsername(username)){
                String query = "INSERT INTO `users`(`username`,`password`) "
                    + "VALUES('" + username + "','" + password + "')";

                statement = conn.createStatement();
                statement.executeUpdate(query);

                System.out.println("Input Berhasil!");
                JOptionPane.showMessageDialog(null, "Register Berhasil!");
            }else{
                JOptionPane.showMessageDialog(null, "Username Sudah Tersedia!");
            }
            
        } catch (Exception ex) {
            System.out.println("Input Gagal!");
        }
    }
    
     String[] readData(){
        try {
            String query = "SELECT * FROM `users`";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){ //konversi tabel ke string
                data[0] = resultSet.getString("username"); 
//                data[1] = resultSet.getString("password"); 
            }
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        } finally {
            return data;
        }
    }
     
     boolean cekUsername(String username){
         try {
             String query = "SELECT * FROM `users` WHERE username='"+username+"'";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            
            while(resultSet.next()){ //konversi tabel ke string
                data[0] = resultSet.getString("username"); 
            }
            statement.close();
            data[0].toString();
            return true;
         } catch (Exception e) {
            return false;
         }
         
            
         
     }
     
     boolean cekLogin(String username, String password){
         try {
             String query = "SELECT * FROM `users` WHERE username='"+username+"'";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            
            while(resultSet.next()){ //konversi tabel ke string
                data[0] = resultSet.getString("username"); 
                data[1] = resultSet.getString("password");
            }
            statement.close();
             System.out.println(data[1].toString());
             System.out.println(password);
            if(data[1].toString().equals(password)){
                return true;
            }else{
                return false;
            }
            
         } catch (Exception e) {
            return false;
         }
       
     }
}