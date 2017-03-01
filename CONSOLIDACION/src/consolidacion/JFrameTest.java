/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolidacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Container;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO
 */
public class JFrameTest extends JFrame {
  private Connection connection=null;
private ResultSet rs= null;
private Statement s=null;
    MENU menu;
  private Container cp;
  
    public JFrameTest() {
      
	setLayout(new BorderLayout());
	
	JPanel centro = new JPanel();
	  JLabel test = new JLabel("Test");
	  centro.add(test);
	add(centro, BorderLayout.CENTER);
	

	centro.removeAll();
        centro.updateUI();
        centro.repaint();
        MENU menu = new MENU();
        centro.add(menu, BorderLayout.CENTER);
        this.pack();
 
	
	setVisible(true);
	setSize(1200,1200);
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	
    }
    public void realizaConexion(){
            if(connection !=null){
		return;
	}
	
	String url ="jdbc:postgresql://localhost:5433/CONSOLIDACION";
	String password ="ideafix";
	try{
	  
	   Class.forName("org.postgresql.Driver");
	   
	   connection=DriverManager.getConnection(url,"postgres",password);
	   
	   if(connection!=null){
		   System.out.println("Conectando a Base de Datos...");
	   }
	} catch (Exception e){
		System.out.println("Problemas de Conexion"+e.getMessage());
	}
}
    public static void main(String[]args) {
            new JFrameTest();
	  
     // new JFrameTest();
      
    }
  
  
  
}
