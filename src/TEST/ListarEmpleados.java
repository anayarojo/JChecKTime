
package TEST;

import JCHECKTIME.DATABASE;
import java.sql.SQLException;

public class ListarEmpleados {

    static DATABASE DB=new DATABASE();
    
    public static void main(String[] args) throws SQLException {
        
        String Query;
        
        DB.SQLWheres=" WHERE ELIMINADO = '0' AND (NombreEmpleado + Apellidos) LIKE '%"+""+"%'";
        
        DB.SQLSelect="SELECT * FROM " + JCHECKTIME.VISTAS.visEmpleados;
            Query= DB.SQLSelect + DB.SQLWheres;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            int i=0;
            while (DB.REG.next()) 
            {
                
                System.out.println(DB.REG.getInt("idEmpleado"));
                System.out.println(DB.REG.getString("NombreEmpleado"));
                System.out.println(DB.REG.getString("Apellidos"));
                System.out.println(DB.REG.getString("Celular"));
                System.out.println(DB.REG.getString("Telefono"));
                System.out.println(DB.REG.getInt("idHorario"));
                System.out.println(DB.REG.getString("NombreHorario"));
                i++;
            }
            
            DB.Close();
    }
}
