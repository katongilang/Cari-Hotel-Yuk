/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carihotelyuk;

/**
 *
 * @author Katon Gilang
 */
import java.sql.*;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.UIManager;

public class CariHotelYuk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Login formLogin=new Login();
        //formLogin.setVisible(true);
        
        //Create formCreate=new Create();
        //formCreate.setVisible(true);
        
        //DaftarHotel a=new DaftarHotel();
        //a.setVisible(true);
        try{
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        Home h=new Home();
        h.setVisible(true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
//        
        
//        LihatDetail h=new LihatDetail();
//        h.setVisible(true);
        
        //HasilPencarian h = new HasilPencarian();
        //h.setVisible(true);
        
        //ModerasiNoTlp m = new ModerasiNoTlp();
        //m.setVisible(true);
    }
    
}
