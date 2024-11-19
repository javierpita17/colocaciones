package com.colocaciones;


import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.colocaciones.gui.PantallaPrincipal;

public class ColocacionesGUI {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		//UIManager.put("nimbusOrange", new Color(0, 170, 255));
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
		try {

			PantallaPrincipal aplicacion = new PantallaPrincipal();
			aplicacion.setVisible(true);
		}catch(ExceptionInInitializerError e){
			JOptionPane.showMessageDialog(null, "No se han iniciado servicios de MySQl : " + e.getMessage(), "Error al iniciar", JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error al iniciar el programa: : " + e.getLocalizedMessage(), "Error al iniciar", JOptionPane.ERROR_MESSAGE);

		}
	}
}	
