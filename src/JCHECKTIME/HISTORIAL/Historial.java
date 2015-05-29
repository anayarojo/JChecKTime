
package JCHECKTIME.HISTORIAL;

import JCHECKTIME.DATABASE;
import JCHECKTIME.RESULTADO;
import JCHECKTIME.RESULTADO.enumResultados;
import JCHECKTIME.TABLAS;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Historial {
    
  /*---------------------------------------------------------------------------------*
   *  Ultima revision:             25 septiembre 2013                                *     
   *  Actualizado:                 Juan Manuel Esquer Esquer                         *        
   *  Ultima modificacion en:      Clase Historial                                   *
     --------------------------------------------------------------------------------*/
    
    DATABASE DB=new DATABASE();
    
    //region ESTRUCTURA
    public class strHistorial{
    
        public int Obj1_idHistorial;
        public int Obj2_idEmpleado;
        public String Obj3_Accion;
        public String Obj4_FechaHora;
        public boolean Obj5_DESISTEMA;
        public boolean Obj6_ELIMINADO;
        public String Obj7_FECHAHORACAMBIO;
        public String Obj8_IDUSUARIO;
        
    }
    //endRegion ESTRUCTURA
    
     //region COLUMNAS
    public static class ColumHistorial{
        
        public static String Colum1_idHistorial = "idHistorial";
        public static String Colum2_idEmpleado = "idEmpleado";
        public static String Colum3_Accion = "Accion";
        public static String Colum4_FechaHora = "FechaHora";
        public static String Colum5_DESISTEMA = "DESISTEMA";
        public static String Colum6_ELIMINADO = "ELIMINADO";
        public static String Colum7_FECHAHORACAMBIO= "FECHAHORACAMBIO";
        public static String Colum8_IDUSUARIO = "IDUSUARIO";
        
    }
    //endregion COLUMNAS
    
    //region LISTAR
    public RESULTADO.enumResultados ListarHistorial(String Filtro,Historial.strHistorial[] ARR){
        
        try
        {
            String Query;
            
            DB.SQLSelect="SELECT COUNT(*) AS count FROM " + JCHECKTIME.TABLAS.Historial;
            DB.SQLWheres=" WHERE ELIMINADO = '0' AND (Nombre + Turno) LIKE '%"+Filtro+"%'";
            
            Query= DB.SQLSelect + DB.SQLWheres;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            int Cuantos=0;
            
            while (DB.REG.next()) 
            {
                Cuantos=Integer.parseInt(DB.REG.getString("count"));
            }
            
            ARR=new Historial.strHistorial[Cuantos];
            
            DB.SQLSelect="SELECT * FROM " + JCHECKTIME.TABLAS.Historial;
            Query= DB.SQLSelect + DB.SQLWheres;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            int i=0;
            while (DB.REG.next()) 
            {
                ARR[i].Obj1_idHistorial = DB.REG.getInt(Historial.ColumHistorial.Colum1_idHistorial);
                ARR[i].Obj2_idEmpleado = DB.REG.getInt(Historial.ColumHistorial.Colum2_idEmpleado);
                ARR[i].Obj3_Accion = DB.REG.getString(Historial.ColumHistorial.Colum3_Accion);
                ARR[i].Obj4_FechaHora = DB.REG.getString(Historial.ColumHistorial.Colum4_FechaHora);
                ARR[i].Obj5_DESISTEMA = DB.REG.getBoolean(Historial.ColumHistorial.Colum5_DESISTEMA);
                ARR[i].Obj6_ELIMINADO = DB.REG.getBoolean(Historial.ColumHistorial.Colum6_ELIMINADO);
                ARR[i].Obj7_FECHAHORACAMBIO = DB.REG.getString(Historial.ColumHistorial.Colum7_FECHAHORACAMBIO);
                ARR[i].Obj8_IDUSUARIO = DB.REG.getString(Historial.ColumHistorial.Colum8_IDUSUARIO);
                
                i++;
            }
            
            DB.Close();
            return RESULTADO.enumResultados.OperacionCorrecta;
        }
        catch (SQLException e) 
        {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "SQLException" , 0);
            return RESULTADO.enumResultados.ErrorDeBaseDeDatos;
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);
            return  RESULTADO.enumResultados.ErrorDesconocido;
        }
    }
    //endregion LISTAR
    
    //region REGISTRAR
       public enumResultados RegistrarHistorial(strHistorial VAR){
        
        try
        {
           String Query;
           DB.SQLFields = ColumHistorial.Colum2_idEmpleado + ", " + 
                          ColumHistorial.Colum3_Accion + ", " + 
                          ColumHistorial.Colum4_FechaHora + ", " + 
                          ColumHistorial.Colum5_DESISTEMA + ", " + 
                          ColumHistorial.Colum6_ELIMINADO + ", " + 
                          ColumHistorial.Colum7_FECHAHORACAMBIO + ", " + 
                          ColumHistorial.Colum8_IDUSUARIO + " ";
                           
            DB.SQLValues = VAR.Obj2_idEmpleado+", "
                          +VAR.Obj3_Accion+", "
                          +VAR.Obj4_FechaHora+", "
                          +VAR.Obj5_DESISTEMA+", "
                          +VAR.Obj6_ELIMINADO+", "
                          +VAR.Obj7_FECHAHORACAMBIO+", "
                          +VAR.Obj8_IDUSUARIO;
                         
            
            DB.SQLInsert = "INSERT INTO " + TABLAS.Historial + " ("+DB.SQLFields+") " + " values("+DB.SQLValues+")";
            Query= DB.SQLInsert;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            DB.Close();
            return enumResultados.OperacionCorrecta;
        }
        catch (SQLException e) 
        {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion
            
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "SQLException" , 0);
            return enumResultados.ErrorDeBaseDeDatos;
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);
            return  enumResultados.ErrorDesconocido;
        }
    }
    //endregion REGISTRAR
       
    //region MODIFICAR
    public enumResultados ModificarHistorial(Historial.strHistorial VAR){
        
        try
        {
           String Query;
           
           DB.SQLSet=Historial.ColumHistorial.Colum2_idEmpleado+"="+VAR.Obj2_idEmpleado+", "+
                   Historial.ColumHistorial.Colum3_Accion+"="+VAR.Obj3_Accion+", "+
                   Historial.ColumHistorial.Colum4_FechaHora+"="+VAR.Obj4_FechaHora+", "+
                   Historial.ColumHistorial.Colum5_DESISTEMA+"="+VAR.Obj5_DESISTEMA+", "+
                   Historial.ColumHistorial.Colum6_ELIMINADO+"="+VAR.Obj6_ELIMINADO+", "+
                   Historial.ColumHistorial.Colum7_FECHAHORACAMBIO+"="+VAR.Obj7_FECHAHORACAMBIO+", "+
                   Historial.ColumHistorial.Colum8_IDUSUARIO+"="+VAR.Obj8_IDUSUARIO;
           
           DB.SQLWheres=Historial.ColumHistorial.Colum1_idHistorial+" = "+VAR.Obj1_idHistorial;
           DB.SQLUpdate="UPDATE "+TABLAS.Horarios+" set "+DB.SQLSet+" WHERE "+DB.SQLWheres;
           
            Query= DB.SQLUpdate;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            DB.Close();
            return enumResultados.OperacionCorrecta;
        }
        catch (SQLException e) 
        {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion
            
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "SQLException" , 0);
            return enumResultados.ErrorDeBaseDeDatos;
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);
            return  enumResultados.ErrorDesconocido;
        }
    }
    //endregion MODIFICAR
    
    //region ELIMINAR
    public enumResultados EliminarHistorial(int idHistorial){
        
        try
        {
           String Query;
           
           DB.SQLSet=Historial.ColumHistorial.Colum6_ELIMINADO+" = 1, "+
                     Historial.ColumHistorial.Colum8_IDUSUARIO+" = "+JCHECKTIME.SEGURIDAD.IDUSUARIO+
                     Historial.ColumHistorial.Colum7_FECHAHORACAMBIO+" = getdate()";
           
           DB.SQLWheres=Historial.ColumHistorial.Colum1_idHistorial+" = "+idHistorial;
           DB.SQLUpdate="UPDATE "+TABLAS.Historial+" set "+DB.SQLSet+" WHERE "+DB.SQLWheres;
           
            Query= DB.SQLUpdate;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            DB.Close();
            return enumResultados.OperacionCorrecta;
        }
        catch (SQLException e) 
        {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion
            
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "SQLException" , 0);
            return enumResultados.ErrorDeBaseDeDatos;
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);
            return  enumResultados.ErrorDesconocido;
        }
    }
    //endregion ELIMINAR
    
    //region CONSULTAR
    public enumResultados ConsultarHistorial(int idHistorial, Historial.strHistorial VAR){
        
        try
        {
            String Query;
            
            DB.SQLWheres = " WHERE "+Historial.ColumHistorial.Colum6_ELIMINADO+" = '0' AND "+Historial.ColumHistorial.Colum1_idHistorial+"="+idHistorial;
            DB.SQLSelect = "SELECT * FROM " + JCHECKTIME.TABLAS.Historial;
            Query = DB.SQLSelect + DB.SQLWheres;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            while (DB.REG.next()) 
            {
                VAR.Obj1_idHistorial = DB.REG.getInt(Historial.ColumHistorial.Colum1_idHistorial);
                VAR.Obj2_idEmpleado = DB.REG.getInt(Historial.ColumHistorial.Colum2_idEmpleado);
                VAR.Obj3_Accion = DB.REG.getString(Historial.ColumHistorial.Colum3_Accion);
                VAR.Obj4_FechaHora = DB.REG.getString(Historial.ColumHistorial.Colum4_FechaHora);
                VAR.Obj5_DESISTEMA = DB.REG.getBoolean(Historial.ColumHistorial.Colum5_DESISTEMA);
                VAR.Obj6_ELIMINADO = DB.REG.getBoolean(Historial.ColumHistorial.Colum6_ELIMINADO);
                VAR.Obj7_FECHAHORACAMBIO = DB.REG.getString(Historial.ColumHistorial.Colum7_FECHAHORACAMBIO);
                VAR.Obj8_IDUSUARIO = DB.REG.getString(Historial.ColumHistorial.Colum8_IDUSUARIO);
            }
            
            DB.Close();
            return enumResultados.OperacionCorrecta;
        }
        catch (SQLException e) 
        {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "SQLException" , 0);
            return enumResultados.ErrorDeBaseDeDatos;
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);
            return  enumResultados.ErrorDesconocido;
        }
    }
    //endregion CONSULTAR
    
    //region EXISTE
    public boolean ExisteHistorial(int idHistorial){
        
        try
        {
            String Query;
            
            DB.SQLWheres = " WHERE "+Historial.ColumHistorial.Colum6_ELIMINADO+" = '0' AND "+Historial.ColumHistorial.Colum1_idHistorial+"="+idHistorial;
            DB.SQLSelect = "SELECT isnull( COUNT(*), 0) AS num FROM " + JCHECKTIME.TABLAS.Historial;
            Query = DB.SQLSelect + DB.SQLWheres;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            int Cuantos=0;
            while (DB.REG.next()) 
            {
                Cuantos=Integer.parseInt(DB.REG.getString("num"));
            }
            
            DB.Close();
            
            if(Cuantos==0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (SQLException e) 
        {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "SQLException" , 0);
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);
        }
        
        return false;
    }
    //endregion EXISTE
    
    //region CERRAR
    public void CerrarBaseDeDatos(){
        DB.Close();
    }
    //region CERRAR


}
