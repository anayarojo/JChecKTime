
package TEST;

import JCHECKTIME.DATABASE;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Pruebas {

    static DATABASE DB=new DATABASE();
    
    public static void main(String[] args) {
        
        try
        {
            DB.SQLSelect="SELECT COUNT(*) AS count FROM " + JCHECKTIME.VISTAS.visEmpleados;
            DB.SQLWheres=" WHERE ELIMINADO = '0' AND (NombreEmpleado + Apellidos) LIKE '%"+""+"%'";
            
            String Query= DB.SQLSelect + DB.SQLWheres;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            int Cuantos=45;
            
            while (DB.REG.next()) 
            {
                Cuantos=Integer.parseInt(DB.REG.getString("count"));
            }
            
            System.out.println(Cuantos);
           
        }
        catch (SQLException e) 
        {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            JOptionPane.showMessageDialog(null, e.toString(), "SQLException" , 0);
        }
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);
        }
    }
}
