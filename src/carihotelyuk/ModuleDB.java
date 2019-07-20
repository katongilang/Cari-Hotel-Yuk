/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carihotelyuk;

import java.sql.*;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Katon Gilang
 */
public class ModuleDB {
    public static int id_user;
    public static String username;
    public static String password;
    public static String nama_lengkap;
    public static String email;
    public static String level;
    public static int id_hotel;
    
    public static Connection connectDB(){
        //String path="jdbc:sqlite:E://projek RPL/CariHotelYuk/dbHotel.db";
        String path="jdbc:sqlite:D://Doc.Gilang/SEMESTER 5/RPL/Projek Jadi Kuliah/CariHotelYuk/dbHotel.db";
        Connection con=null;
        try{
            con=DriverManager.getConnection(path);
        }
        catch(SQLException e){
            showMessageDialog(null,"Gagal terkoneksi database!","Error",JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
    
}
