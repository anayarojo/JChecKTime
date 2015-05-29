/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TEST;

import JCHECKTIME.EMPLEADOS.Empleados;
import javax.swing.JOptionPane;

/**
 *
 * @author Raul
 */
public class Mensaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //JOptionPane.showMessageDialog(null, "Registra la huella del empleado en la lectora \n  addasdsadsa", "Notificacion" , 1);
        
        Empleados E=new Empleados();
        E.ListarVisEmpleados("");
    }
}
