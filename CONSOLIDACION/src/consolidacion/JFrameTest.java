/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolidacion;

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
    
    public static void main(String[]args) {
        new JFrameTest();
    }
  
  
  
}
