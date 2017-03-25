/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolidacion;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author USUARIO
 */
public class contacto extends javax.swing.JPanel {

  private JTable table;
  private DefaultTableModel modelotabla;
  private Connection connection = null;
  private ResultSet rs = null;
  private ResultSet rs2 = null;
  private ResultSet rs3 = null;
  private ResultSet rs4 = null;
  private Statement s = null;
  private Statement s2 = null;
  private Statement s3 = null;
  private Statement s4 = null;
  TableRowSorter trsfiltro;

  /**
   * Creates new form contacto
   */
  public contacto() {
    modelotabla = new DefaultTableModel(null, getColumnas());

    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")

  public void filtro() {

    //Obtenemos el valor del JTextField para el filtro
    String filtroo = filtro.getText().toUpperCase();
    // Identificamos cual es el JRadioButton seleccionado para filtrar el
    // resultado de acuerdo a los datos de la columna elegida
    // Inicializamos el objeto trsfiltro de la clase TableRowSorter con
    // el modelo de la tabla, que para nuestro caso es tabladatos
    trsfiltro = new TableRowSorter(modelotabla);
    // Añadimos al Jtable el filtro trsfiltro
    tabla.setModel(modelotabla);
    tabla.setRowSorter(trsfiltro);
    trsfiltro.setRowFilter(RowFilter.regexFilter(filtroo, 0));

  }

  public long calcular(String añ, String me, String di) {

    final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
    java.util.Date hoy = new Date(); //Fecha de hoy 

    int año = Integer.parseInt(añ);
    int mes = Integer.parseInt(me);
    int dia = Integer.parseInt(di);

    Calendar calendar = new GregorianCalendar(año, mes - 1, dia);
    java.sql.Date fecha = new java.sql.Date(calendar.getTimeInMillis());
    long diferencia = (hoy.getTime() - fecha.getTime()) / MILLSECS_PER_DAY;
    System.out.println(diferencia);

    return diferencia;
  }

  private String[] getColumnas() {

    String columna[] = new String[]{"NUEVO", "DIAS", "CELULAR ", "PADRE ESPIRITUAL", "CELULAR", "AUX SUPERVISOR", "CELULAR", "SUPERVISOR", "CELULAR", "CONSOLIDADOR", "CELULAR"};

    return columna;
  }

  private void setFilas() {

    conexion();

    try {
      s = connection.createStatement();
      rs = s.executeQuery("SELECT * FROM nuevo");
      Object datos[] = new Object[11];
      String string = "";
      while (rs.next()) {
	string += rs.getString(1) + "\n";
	long numero = calcular(rs.getString(13).substring(0, 4), rs.getString(13).substring(5, 7), rs.getString(13).substring(8, 10));

	//
	string = "";
	if (numero > 8) {
	  datos[0] = "";
	  datos[1] = "";
	  datos[2] = "";
	  datos[3] = "";
	  datos[4] = "";
	  datos[5] = "";
	  datos[6] = "";
	  datos[7] = "";
	  datos[8] = "";
	  datos[9] = "";
	  datos[10] = "";
	  datos[0] = rs.getObject(2);
	  datos[2] = rs.getObject(1);
	  datos[1] = numero;

	  String d = "";
	  String d1 = "";
	  String d2 = "";
	  String pe = (String) rs.getObject(12);
	  if (rs.getObject(12).equals("P. ESPIRITUAL")) {
	    d = "lider";
	  }
	  if (rs.getObject(12).equals("AUX SUPERVISOR")) {
	    d = "aux_super";
	  }
	  if (rs.getObject(12).equals("SUPERVISOR")) {
	    d = "super";
	  }
	  if (rs.getObject(12).equals("CONSOLIDADOR")) {
	    d = "consolidador";
	  }
	  System.out.println(d + "  CEDULA  " + rs.getObject(11));
	  try {
	    s2 = connection.createStatement();
	    int p = 3;
	    int p2 = 4;
	    rs2 = s2.executeQuery("SELECT * FROM " + d + " WHERE cedula='" + rs.getObject(11) + "'");
	    while (rs2.next()) {

	      if (d == "aux_super") {
		p = 5;
		p2 = 6;
	      }
	      if (d == "super") {
		p = 7;
		p2 = 8;
	      }
	      if (d == "consolidador") {
		p = 9;
		p2 = 10;
	      }

	      datos[p] = rs2.getObject(2);
	      datos[p2] = rs2.getObject(10);
		//System.out.println(rs2.getObject(12)+"prueba");
	      int y = 10;
	      if (y == 10) {
		if (!d.equals("consolidador")) {
		  System.out.println("ingreos al for diferente de consolidador");
		  System.out.println("datos"+rs2.getObject(12)+rs2.getObject(11));
		  if (!rs2.getObject(12).equals("") && !rs2.getObject(11).equals("")) {
		    System.out.println("ingreso");

		    if (rs2.getObject(12).equals("AUX SUPERVISOR")) {
		      d1 = "aux_super";
		    }
		    if (rs2.getObject(12).equals("SUPERVISOR")) {
		      d1 = "super";
		    }
		    if (rs2.getObject(12).equals("CONSOLIDADOR")) {
		      d1 = "consolidador";
		    }
		    System.out.println(d1 + "  CEDULA  ");
		    
		    s3 = connection.createStatement();
		    rs3 = s3.executeQuery("SELECT * FROM " + d1 + " WHERE cedula='" + rs2.getObject(11) + "'");
		    while (rs3.next()) {

		      p = 5;
		      p2 = 6;
		      if (d1 == "super") {
			p = 7;
			p2 = 8;
		      }
		      if (d1 == "consolidador") {
			p = 9;
			p2 = 10;
		      }
			datos[p] = rs3.getObject(2);
			datos[p2] = rs3.getObject(10);
			
			
			int y2 = 10;
	      if (y2 == 10) {
		if (!d1.equals("consolidador")) {
		  System.out.println("ingreos al for diferente de consolidador");
		  System.out.println("datos"+rs3.getObject(12)+rs3.getObject(11));
		  if (!rs3.getObject(12).equals("") && !rs3.getObject(11).equals("")) {
		    System.out.println("ingreso");

		    
		    if (rs3.getObject(12).equals("SUPERVISOR")) {
		      d2 = "super";
		    }
		    if (rs3.getObject(12).equals("CONSOLIDADOR")) {
		      d2 = "consolidador";
		    }
		    System.out.println(d1 + "  CEDULA  ");
		    
		    s4 = connection.createStatement();
		    rs4 = s3.executeQuery("SELECT * FROM " + d2 + " WHERE cedula='" + rs2.getObject(11) + "'");
		    while (rs3.next()) {

		      p = 7;
		      p2 = 8;
		      
		      if (d1 == "consolidador") {
			p = 9;
			p2 = 10;
		      }
			datos[p] = rs3.getObject(2);
			datos[p2] = rs3.getObject(10);
			
			
			
			
			
		    }
		  }

		}

	     //--->
	      }
			
			
		    }
		  }

		}

	     //--->
	      }
	    }

	    modelotabla.addRow(datos);

	  } catch (Exception e) {
	    System.out.println("Problema Buscando la Base de Datos222.....");
	  }

	}

      }

    } catch (Exception e) {
      System.out.println("Problema Buscando la Base de Datosssssss11111");

    }

  }
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    tabla = new javax.swing.JTable();
    filtro = new javax.swing.JTextField();

    jLabel1.setText("NUEVOS SIN CONTACTO");

    jButton1.setText("BUSCAR");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    tabla.setModel(modelotabla);
    tabla.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        tablaKeyTyped(evt);
      }
    });
    jScrollPane1.setViewportView(tabla);

    filtro.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        filtroActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(182, 182, 182)
            .addComponent(jButton1)
            .addGap(179, 179, 179)
            .addComponent(jLabel1))
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addGap(459, 459, 459)
            .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(17, 17, 17)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(jButton1))
        .addGap(18, 18, 18)
        .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(44, 44, 44))
    );
  }// </editor-fold>//GEN-END:initComponents
public void conexion() {
    if (connection != null) {
      return;
    }

    String url = "jdbc:postgresql://localhost:5433/CONSOLIDACION";
    String password = "ideafix";
    try {
      Class.forName("org.postgresql.Driver");

      connection = DriverManager.getConnection(url, "postgres", password);

      if (connection != null) {
	System.out.println("Conectando a Base de Datos...");
      }
    } catch (Exception e) {
      System.out.println("Problemas de Conexion");
    }
  }
  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    setFilas();
    // TODO add your handling code here:
  }//GEN-LAST:event_jButton1ActionPerformed

  private void tablaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyTyped
// Comprobamos que el ButtonGroup que nosotros llamamos bGfiltar tenga
    // seleccionado alguno de los tres JRadioButtons que le hemos agregado

    // Añadimos al JTextField un KeyListener con un KeyAdapter. De esta
    // forma es como si dieramos enter cada vez que digitamos una techa
//        if (!filtro_placa.equals("")) {
//
//            filtro_placa.addKeyListener(new KeyAdapter() {
//                @Override
//                public void keyTyped(final KeyEvent e) {
//                    // Llamamos al método encargado de realizar el filtro
//                    filtro();
//                }
//            });
    System.out.print("entro");
    if (!filtro.equals("")) {
      filtro.addKeyListener(new KeyAdapter() {
	@Override
	public void keyReleased(final KeyEvent e) {
	  // Llamamos al método encargado de realizar el filtro

	  filtro();

	}
      });

    }

    // Inicializamos el objeto trsfiltro de la clase TableRowSorter con
    // el modelo de la tabla, que para nuestro caso es tabladatos
    trsfiltro = new TableRowSorter(modelotabla);
    // Añadimos al Jtable el filtro trsfiltro
    tabla.setRowSorter(trsfiltro);    // TODO add your handling code here:
  }//GEN-LAST:event_tablaKeyTyped

  private void filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_filtroActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextField filtro;
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable tabla;
  // End of variables declaration//GEN-END:variables
}
