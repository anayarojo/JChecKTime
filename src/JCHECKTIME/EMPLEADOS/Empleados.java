
package JCHECKTIME.EMPLEADOS;

import JCHECKTIME.DATABASE;
import JCHECKTIME.RESULTADO.enumResultados;
import JCHECKTIME.SEGURIDAD;
import JCHECKTIME.TABLAS;
import JCHECKTIME.TOOLS.METODOS.Fecha;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Empleados {
    
  /*---------------------------------------------------------------------------------*
   *  Ultima revision:             23 septiembre 2013                                *     
   *  Actualizado:                 Raul Anaya Rojo                                   *        
   *  Ultima modificacion en:      Modificar, Eliminar, Consultar, Existe, Cerrar    *
   *---------------------------------------------------------------------------------*/
    
    DATABASE DB=new DATABASE();
    Fecha F=new Fecha();
    
    //region ESTRUCTURA
    public static class strEmpleados{
    
        public int Obj1_idEmpleado;
        public String Obj2_NombreEmpleado;
        public String Obj3_Apellidos;
        public String Obj4_FechaNacimiento;
        public String Obj5_Celular;
        public String Obj6_Telefono;
        public String Obj7_CorreoElectronico;
        public String Obj8_Ciudad;
        public String Obj9_Colonia;
        public String Obj10_NumeroSeguro;
        public String Obj11_CURP;
        public String Obj12_EstadoCivil;
        public String Obj13_Localidad;
        public String Obj14_Direccion;
        public String Obj15_Alergias;
        public String Obj16_EmergInfo;
        public String Obj17_EmergTel;
        public String Obj18_TipoDeSangre;
        public String Obj19_Departamento;
        public String Obj20_Puesto;
        public String Obj21_JefeInmediato;
        public String Obj22_FechaContrato;
        public int Obj23_Salario;
        public int Obj24_idHorario;
        public String Obj25_HuellaDig;
        public String Obj26_RFC;
        public boolean Obj27_DESISTEMA;
        public boolean Obj28_ELIMINADO;
        public String Obj29_FECHAHORACAMBIO;
        public String Obj30_IDUSUARIO;
    }
    
    public static class strVisEmpleados{
    
        public int Obj1_idEmpleado;
        public String Obj2_NombreEmpleado;
        public String Obj3_Apellidos;
        public String Obj4_Celular;
        public String Obj5_Telefono;
        public String Obj6_Departamento;
        public String Obj7_Puesto;
        public int Obj8_idHorario;
        public String Obj9_NombreHorario;
        public String Obj10_Turno;
        public boolean Obj11_DESISTEMA;
        public boolean Obj12_ELIMINADO;
        public String Obj13_FECHAHORACAMBIO;
        public String Obj14_IDUSUARIO;
    }
    //endregion ESTRUCTURA
    
    //region COLUMNAS
    public static class ColumEmpleados{
        
        public static String Colum1_idEmpleado = "idEmpleado";
        public static String Colum2_NombreEmpleado = "NombreEmpleado";
        public static String Colum3_Apellidos = "Apellidos";
        public static String Colum4_FechaNacimiento = "FechaNacimiento";
        public static String Colum5_Celular = "Celular";
        public static String Colum6_Telefono = "Telefono";
        public static String Colum7_CorreoElectronico = "CorreoElectronico";
        public static String Colum8_Ciudad = "Ciudad";
        public static String Colum9_Colonia = "Colonia";
        public static String Colum10_NumeroSeguro = "NumeroSeguro";
        public static String Colum11_CURP = "CURP";
        public static String Colum12_EstadoCivil = "EstadoCivil";
        public static String Colum13_Localidad = "Localidad";
        public static String Colum14_Direccion = "Direccion";
        public static String Colum15_Alergias = "Alergias";
        public static String Colum16_EmergInfo = "EmergInfo";
        public static String Colum17_EmergTel = "EmergTel";
        public static String Colum18_TipoDeSangre = "TipoDeSangre";
        public static String Colum19_Departamento = "Departamento";
        public static String Colum20_Puesto = "Puesto";
        public static String Colum21_JefeInmediato = "JefeInmediato";
        public static String Colum22_FechaContrato = "FechaContrato";
        public static String Colum23_Salario = "Salario";
        public static String Colum24_idHorario = "idHorario";
        public static String Colum25_HuellaDig = "HuellaDig";
        public static String Colum26_RFC = "RFC";
        public static String Colum27_DESISTEMA = "DESISTEMA";
        public static String Colum28_ELIMINADO = "ELIMINADO";
        public static String Colum29_FECHAHORACAMBIO = "FECHAHORACAMBIO";
        public static String Colum30_IDUSUARIO = "IDUSUARIO";
    }
    
    public static class ColumVisEmpleados{
        
        public static String Colum1_idEmpleado = "idEmpleado";
        public static String Colum2_NombreEmpleado = "NombreEmpleado";
        public static String Colum3_Apellidos = "Apellidos";
        public static String Colum4_Celular = "Celular";
        public static String Colum5_Telefono = "Telefono";
        public static String Colum6_Departamento = "Departamento";
        public static String Colum7_Puesto = "Puesto";
        public static String Colum8_idHorario = "idHorario";
        public static String Colum9_NombreHorario = "NombreHorario";
        public static String Colum10_Turno = "Turno";
        public static String Colum11_DESISTEMA = "DESISTEMA";
        public static String Colum12_ELIMINADO = "ELIMINADO";
        public static String Colum13_FECHAHORACAMBIO = "FECHAHORACAMBIO";
        public static String Colum14_IDUSUARIO = "IDUSUARIO";
    }
    //endregion COLUMNAS
    
    //region LISTAR
    public strEmpleados[] ListarEmpleados(String Filtro){
        
        strEmpleados ARR[];
        
        try
        {
            String Query;
            
            DB.SQLSelect="SELECT COUNT(*) AS num FROM " + JCHECKTIME.TABLAS.Empleados;
            DB.SQLWheres=" WHERE ELIMINADO = '0' AND (NombreEmpleado + Apellidos) LIKE '%"+Filtro+"%'";
            
            Query= DB.SQLSelect + DB.SQLWheres;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            int Cuantos=0;
            
            while (DB.REG.next()) 
            {
                Cuantos=Integer.parseInt(DB.REG.getString("num"));
            }
            
            ARR=new strEmpleados[Cuantos];
            
            for (int i = 0; i < Cuantos; i++) 
            {
                ARR[i]=new strEmpleados();
            }
            
            DB.SQLSelect="SELECT * FROM " + JCHECKTIME.TABLAS.Empleados;
            Query= DB.SQLSelect + DB.SQLWheres;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            int i=0;
            while (DB.REG.next()) 
            {
                ARR[i].Obj1_idEmpleado = DB.REG.getInt(ColumEmpleados.Colum1_idEmpleado);
                System.out.println(DB.REG.getString(ColumEmpleados.Colum2_NombreEmpleado));
                ARR[i].Obj2_NombreEmpleado = DB.REG.getString(ColumEmpleados.Colum2_NombreEmpleado);
                ARR[i].Obj3_Apellidos = DB.REG.getString(ColumEmpleados.Colum3_Apellidos);
                ARR[i].Obj4_FechaNacimiento = DB.REG.getString(ColumEmpleados.Colum4_FechaNacimiento);
                ARR[i].Obj5_Celular = DB.REG.getString(ColumEmpleados.Colum5_Celular);
                ARR[i].Obj6_Telefono = DB.REG.getString(ColumEmpleados.Colum6_Telefono);
                ARR[i].Obj7_CorreoElectronico = DB.REG.getString(ColumEmpleados.Colum7_CorreoElectronico);
                ARR[i].Obj8_Ciudad = DB.REG.getString(ColumEmpleados.Colum8_Ciudad);
                ARR[i].Obj9_Colonia = DB.REG.getString(ColumEmpleados.Colum9_Colonia);
                ARR[i].Obj10_NumeroSeguro = DB.REG.getString(ColumEmpleados.Colum10_NumeroSeguro);
                ARR[i].Obj11_CURP = DB.REG.getString(ColumEmpleados.Colum11_CURP);
                ARR[i].Obj12_EstadoCivil = DB.REG.getString(ColumEmpleados.Colum12_EstadoCivil);
                ARR[i].Obj13_Localidad = DB.REG.getString(ColumEmpleados.Colum13_Localidad);
                ARR[i].Obj14_Direccion = DB.REG.getString(ColumEmpleados.Colum14_Direccion);
                ARR[i].Obj15_Alergias = DB.REG.getString(ColumEmpleados.Colum15_Alergias);
                ARR[i].Obj16_EmergInfo = DB.REG.getString(ColumEmpleados.Colum16_EmergInfo);
                ARR[i].Obj17_EmergTel = DB.REG.getString(ColumEmpleados.Colum17_EmergTel);
                ARR[i].Obj18_TipoDeSangre = DB.REG.getString(ColumEmpleados.Colum18_TipoDeSangre);
                ARR[i].Obj19_Departamento = DB.REG.getString(ColumEmpleados.Colum19_Departamento);
                ARR[i].Obj20_Puesto = DB.REG.getString(ColumEmpleados.Colum20_Puesto);
                ARR[i].Obj21_JefeInmediato = DB.REG.getString(ColumEmpleados.Colum21_JefeInmediato);
                ARR[i].Obj22_FechaContrato = DB.REG.getString(ColumEmpleados.Colum22_FechaContrato);
                ARR[i].Obj23_Salario = DB.REG.getInt(ColumEmpleados.Colum23_Salario);
                ARR[i].Obj24_idHorario = DB.REG.getInt(ColumEmpleados.Colum24_idHorario);
                ARR[i].Obj25_HuellaDig = DB.REG.getString(ColumEmpleados.Colum25_HuellaDig);
                ARR[i].Obj26_RFC = DB.REG.getString(ColumEmpleados.Colum26_RFC);
                ARR[i].Obj27_DESISTEMA = DB.REG.getBoolean(ColumEmpleados.Colum27_DESISTEMA);
                ARR[i].Obj28_ELIMINADO = DB.REG.getBoolean(ColumEmpleados.Colum28_ELIMINADO);
                ARR[i].Obj29_FECHAHORACAMBIO = DB.REG.getString(ColumEmpleados.Colum29_FECHAHORACAMBIO);
                ARR[i].Obj30_IDUSUARIO = DB.REG.getString(ColumEmpleados.Colum30_IDUSUARIO);
                i++;
            }
            
            DB.Close();
            return ARR;
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
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - ListarStrEmpleados - SQLException" , 0);
            return null;
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - ListarStrEmpleado - Exception" , 0);
            return  null;
        }
    }
    
    public strVisEmpleados[] ListarVisEmpleados(String Filtro){
        
        strVisEmpleados[] ARR;
        
        try
        {
            String Query;
            
            DB.SQLSelect="SELECT COUNT(*) AS num FROM " + JCHECKTIME.VISTAS.visEmpleados;
            DB.SQLWheres=" WHERE ELIMINADO = '0' AND (NombreEmpleado + Apellidos) LIKE '%"+Filtro+"%'";
            
            Query= DB.SQLSelect + DB.SQLWheres;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            int Cuantos=0;
            
            while (DB.REG.next()) 
            {
                Cuantos=Integer.parseInt(DB.REG.getString("num"));
            }
            
            ARR=new strVisEmpleados[Cuantos];
            
            for (int i = 0; i < ARR.length; i++) 
            {
                ARR[i]=new strVisEmpleados();
            }
            
            DB.SQLSelect="SELECT * FROM " + JCHECKTIME.VISTAS.visEmpleados;
            Query= DB.SQLSelect + DB.SQLWheres;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            int i=0;
            while (DB.REG.next()) 
            {
                ARR[i].Obj1_idEmpleado = DB.REG.getInt(ColumVisEmpleados.Colum1_idEmpleado);
                ARR[i].Obj2_NombreEmpleado = DB.REG.getString(ColumVisEmpleados.Colum2_NombreEmpleado);
                ARR[i].Obj3_Apellidos = DB.REG.getString(ColumVisEmpleados.Colum3_Apellidos);
                ARR[i].Obj4_Celular = DB.REG.getString(ColumVisEmpleados.Colum4_Celular);
                ARR[i].Obj5_Telefono = DB.REG.getString(ColumVisEmpleados.Colum5_Telefono);
                ARR[i].Obj6_Departamento = DB.REG.getString(ColumVisEmpleados.Colum6_Departamento);
                ARR[i].Obj7_Puesto = DB.REG.getString(ColumVisEmpleados.Colum7_Puesto);
                ARR[i].Obj8_idHorario = DB.REG.getInt(ColumVisEmpleados.Colum8_idHorario);
                ARR[i].Obj9_NombreHorario = DB.REG.getString(ColumVisEmpleados.Colum9_NombreHorario);
                ARR[i].Obj10_Turno = DB.REG.getString(ColumVisEmpleados.Colum10_Turno);
                ARR[i].Obj11_DESISTEMA = DB.REG.getBoolean(ColumVisEmpleados.Colum11_DESISTEMA);
                ARR[i].Obj12_ELIMINADO = DB.REG.getBoolean(ColumVisEmpleados.Colum12_ELIMINADO);
                ARR[i].Obj13_FECHAHORACAMBIO = DB.REG.getString(ColumVisEmpleados.Colum13_FECHAHORACAMBIO);
                ARR[i].Obj14_IDUSUARIO = DB.REG.getString(ColumVisEmpleados.Colum14_IDUSUARIO);
                i++;
            }
            
            DB.Close();
            return ARR;
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
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - ListarVisEmpleado - SQLException" , 0);
            return null;
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - ListarVisEmpleado - Exception" , 0);
            return  null;
        }
    }
    //endregion LISTAR
    
    //region REGISTRAR
    public enumResultados RegistrarEmpleado(strEmpleados VAR){
        
        try
        {
           String Query;
           DB.SQLFields = ColumEmpleados.Colum2_NombreEmpleado + ", " + 
                          ColumEmpleados.Colum3_Apellidos + ", " + 
                          ColumEmpleados.Colum4_FechaNacimiento + ", " + 
                          ColumEmpleados.Colum5_Celular + ", " + 
                          ColumEmpleados.Colum6_Telefono + ", " + 
                          ColumEmpleados.Colum7_CorreoElectronico + ", " + 
                          ColumEmpleados.Colum8_Ciudad + ", " + 
                          ColumEmpleados.Colum9_Colonia + ", " + 
                          ColumEmpleados.Colum10_NumeroSeguro + ", " + 
                          ColumEmpleados.Colum11_CURP + ", " + 
                          ColumEmpleados.Colum12_EstadoCivil + ", " + 
                          ColumEmpleados.Colum13_Localidad + ", " + 
                          ColumEmpleados.Colum14_Direccion + ", " + 
                          ColumEmpleados.Colum15_Alergias + ", " + 
                          ColumEmpleados.Colum16_EmergInfo + ", " + 
                          ColumEmpleados.Colum17_EmergTel + ", " + 
                          ColumEmpleados.Colum18_TipoDeSangre + ", " + 
                          ColumEmpleados.Colum19_Departamento + ", " + 
                          ColumEmpleados.Colum20_Puesto + ", " + 
                          ColumEmpleados.Colum21_JefeInmediato + ", " + 
                          ColumEmpleados.Colum22_FechaContrato + ", " + 
                          ColumEmpleados.Colum23_Salario + ", " + 
                          ColumEmpleados.Colum24_idHorario + ", " + 
                          ColumEmpleados.Colum25_HuellaDig + ", " + 
                          ColumEmpleados.Colum26_RFC + ", " + 
                          ColumEmpleados.Colum27_DESISTEMA + ", " + 
                          ColumEmpleados.Colum28_ELIMINADO + ", " + 
                          ColumEmpleados.Colum30_IDUSUARIO  + " ";
           
            DB.SQLValues = "'"+VAR.Obj2_NombreEmpleado+"', '"
                          +VAR.Obj3_Apellidos+"', '"
                          +VAR.Obj4_FechaNacimiento+"', '"
                          +VAR.Obj5_Celular+"', '"
                          +VAR.Obj6_Telefono+"', '"
                          +VAR.Obj7_CorreoElectronico+"', '"
                          +VAR.Obj8_Ciudad+"', '"
                          +VAR.Obj9_Colonia+"', '"
                          +VAR.Obj10_NumeroSeguro+"', '"
                          +VAR.Obj11_CURP+"', '"
                          +VAR.Obj12_EstadoCivil+"', '"
                          +VAR.Obj13_Localidad+"', '"
                          +VAR.Obj14_Direccion+"', '"
                          +VAR.Obj15_Alergias+"', '"
                          +VAR.Obj16_EmergInfo+"', '"
                          +VAR.Obj17_EmergTel+"', '"
                          +VAR.Obj18_TipoDeSangre+"', '"
                          +VAR.Obj19_Departamento+"', '"
                          +VAR.Obj20_Puesto+"', '"
                          +VAR.Obj21_JefeInmediato+"', '"
                          +VAR.Obj22_FechaContrato+"', '"
                          +VAR.Obj23_Salario+"', '"
                          +VAR.Obj24_idHorario+"', '"
                          +VAR.Obj25_HuellaDig+"', '"
                          +VAR.Obj26_RFC+"', '"
                          +VAR.Obj27_DESISTEMA+"', '"
                          +VAR.Obj28_ELIMINADO+"', '"
                          +SEGURIDAD.IDUSUARIO+"'";
            
            DB.SQLInsert = "INSERT INTO " + TABLAS.Empleados + " ("+DB.SQLFields+") " + " values( "+DB.SQLValues+" ) ";
            Query= DB.SQLInsert;
            
            DB.COM=DB.CON.createStatement();
            DB.COM.executeUpdate(Query);
            
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
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - RegistrarEmpleado - SQLException" , 0);
            return enumResultados.ErrorDeBaseDeDatos;
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - RegistrarEmpleado - Exception" , 0);
            return  enumResultados.ErrorDesconocido;
        }
    }
    //endregion REGISTRAR
    
    //region MODIFICAR
    public enumResultados ModificarEmpleado(strEmpleados VAR){
        
        try
        {
           String Query;
           
           DB.SQLSet=ColumEmpleados.Colum2_NombreEmpleado+"='"+VAR.Obj2_NombreEmpleado+"', "+
                     ColumEmpleados.Colum3_Apellidos+"='"+VAR.Obj3_Apellidos+"', "+
                     ColumEmpleados.Colum4_FechaNacimiento+"='"+VAR.Obj4_FechaNacimiento+"', "+
                     ColumEmpleados.Colum5_Celular+"='"+VAR.Obj5_Celular+"', "+
                     ColumEmpleados.Colum6_Telefono+"='"+VAR.Obj6_Telefono+"', "+
                     ColumEmpleados.Colum7_CorreoElectronico+"='"+VAR.Obj7_CorreoElectronico+"', "+
                     ColumEmpleados.Colum8_Ciudad+"='"+VAR.Obj8_Ciudad+"', "+
                     ColumEmpleados.Colum9_Colonia+"='"+VAR.Obj9_Colonia+"', "+
                     ColumEmpleados.Colum10_NumeroSeguro+"='"+VAR.Obj10_NumeroSeguro+"', "+
                     ColumEmpleados.Colum11_CURP+"='"+VAR.Obj11_CURP+"', "+
                     ColumEmpleados.Colum12_EstadoCivil+"='"+VAR.Obj12_EstadoCivil+"', "+
                     ColumEmpleados.Colum13_Localidad+"='"+VAR.Obj13_Localidad+"', "+
                     ColumEmpleados.Colum14_Direccion+"='"+VAR.Obj14_Direccion+"', "+
                     ColumEmpleados.Colum15_Alergias+"='"+VAR.Obj15_Alergias+"', "+
                     ColumEmpleados.Colum16_EmergInfo+"='"+VAR.Obj16_EmergInfo+"', "+
                     ColumEmpleados.Colum17_EmergTel+"='"+VAR.Obj17_EmergTel+"', "+
                     ColumEmpleados.Colum18_TipoDeSangre+"='"+VAR.Obj18_TipoDeSangre+"', "+
                     ColumEmpleados.Colum19_Departamento+"='"+VAR.Obj19_Departamento+"', "+
                     ColumEmpleados.Colum20_Puesto+"='"+VAR.Obj20_Puesto+"', "+
                     ColumEmpleados.Colum21_JefeInmediato+"='"+VAR.Obj21_JefeInmediato+"', "+
                     ColumEmpleados.Colum22_FechaContrato+"='"+VAR.Obj22_FechaContrato+"', "+
                     ColumEmpleados.Colum23_Salario+"='"+VAR.Obj23_Salario+"', "+
                     ColumEmpleados.Colum24_idHorario+"='"+VAR.Obj24_idHorario+"', "+
                     ColumEmpleados.Colum25_HuellaDig+"='"+VAR.Obj25_HuellaDig+"', "+
                     ColumEmpleados.Colum26_RFC+"='"+VAR.Obj26_RFC+"', "+
                     ColumEmpleados.Colum27_DESISTEMA+"='"+VAR.Obj27_DESISTEMA+"', "+
                     ColumEmpleados.Colum28_ELIMINADO+"='"+VAR.Obj28_ELIMINADO+"', "+
                     ColumEmpleados.Colum30_IDUSUARIO+"='"+SEGURIDAD.IDUSUARIO+"'";
           
           DB.SQLWheres=ColumEmpleados.Colum1_idEmpleado+" = "+VAR.Obj1_idEmpleado;
           DB.SQLUpdate="UPDATE "+TABLAS.Empleados+" set "+DB.SQLSet+" WHERE "+DB.SQLWheres;
           
            Query= DB.SQLUpdate;
            
            DB.COM=DB.CON.createStatement();
            DB.COM.executeUpdate(Query);
            
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
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - ModificarEmpleado - SQLException" , 0);
            return enumResultados.ErrorDeBaseDeDatos;
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - ModificarEmpleado - Exception" , 0);
            return  enumResultados.ErrorDesconocido;
        }
    }
    //endregion MODIFICAR
    
    //region ELIMINAR
    public enumResultados EliminarEmpleado(int idEmpleado){
        
        try
        {
           String Query;
           
           DB.SQLSet=ColumEmpleados.Colum28_ELIMINADO+" = '1', "+
                     ColumEmpleados.Colum30_IDUSUARIO+" = '"+JCHECKTIME.SEGURIDAD.IDUSUARIO+"'";
           
           DB.SQLWheres=ColumEmpleados.Colum1_idEmpleado+" = "+idEmpleado;
           DB.SQLUpdate="UPDATE "+TABLAS.Empleados+" set "+DB.SQLSet+" WHERE "+DB.SQLWheres;
           
            Query= DB.SQLUpdate;
            
            DB.COM=DB.CON.createStatement();
            DB.COM.executeUpdate(Query);
            
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
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - EliminarEmpleado - SQLException" , 0);
            return enumResultados.ErrorDeBaseDeDatos;
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - EliminarEmpleado - Exception" , 0);
            return  enumResultados.ErrorDesconocido;
        }
    }
    //endregion ELIMINAR
    
    //region CONSULTAR
    public strEmpleados ConsultarEmpleado(int idEmpleado){
        
        strEmpleados VAR=new strEmpleados();
        
        try
        {
            String Query;
            
            DB.SQLWheres = " WHERE "+ColumEmpleados.Colum28_ELIMINADO+" = '0' AND "+ColumEmpleados.Colum1_idEmpleado+"="+idEmpleado;
            DB.SQLSelect = "SELECT * FROM " + JCHECKTIME.TABLAS.Empleados;
            Query = DB.SQLSelect + DB.SQLWheres;
            
            DB.COM=DB.CON.createStatement();
            DB.REG=DB.COM.executeQuery(Query);
            
            while (DB.REG.next()) 
            {
                VAR.Obj1_idEmpleado = DB.REG.getInt(ColumEmpleados.Colum1_idEmpleado);
                VAR.Obj2_NombreEmpleado = DB.REG.getString(ColumEmpleados.Colum2_NombreEmpleado);
                VAR.Obj3_Apellidos = DB.REG.getString(ColumEmpleados.Colum3_Apellidos);
                VAR.Obj4_FechaNacimiento = DB.REG.getString(ColumEmpleados.Colum4_FechaNacimiento);
                VAR.Obj5_Celular = DB.REG.getString(ColumEmpleados.Colum5_Celular);
                VAR.Obj6_Telefono = DB.REG.getString(ColumEmpleados.Colum6_Telefono);
                VAR.Obj7_CorreoElectronico = DB.REG.getString(ColumEmpleados.Colum7_CorreoElectronico);
                VAR.Obj8_Ciudad = DB.REG.getString(ColumEmpleados.Colum8_Ciudad);
                VAR.Obj9_Colonia = DB.REG.getString(ColumEmpleados.Colum9_Colonia);
                VAR.Obj10_NumeroSeguro = DB.REG.getString(ColumEmpleados.Colum10_NumeroSeguro);
                VAR.Obj11_CURP = DB.REG.getString(ColumEmpleados.Colum11_CURP);
                VAR.Obj12_EstadoCivil = DB.REG.getString(ColumEmpleados.Colum12_EstadoCivil);
                VAR.Obj13_Localidad = DB.REG.getString(ColumEmpleados.Colum13_Localidad);
                VAR.Obj14_Direccion = DB.REG.getString(ColumEmpleados.Colum14_Direccion);
                VAR.Obj15_Alergias = DB.REG.getString(ColumEmpleados.Colum15_Alergias);
                VAR.Obj16_EmergInfo = DB.REG.getString(ColumEmpleados.Colum16_EmergInfo);
                VAR.Obj17_EmergTel = DB.REG.getString(ColumEmpleados.Colum17_EmergTel);
                VAR.Obj18_TipoDeSangre = DB.REG.getString(ColumEmpleados.Colum18_TipoDeSangre);
                VAR.Obj19_Departamento = DB.REG.getString(ColumEmpleados.Colum19_Departamento);
                VAR.Obj20_Puesto = DB.REG.getString(ColumEmpleados.Colum20_Puesto);
                VAR.Obj21_JefeInmediato = DB.REG.getString(ColumEmpleados.Colum21_JefeInmediato);
                VAR.Obj22_FechaContrato = DB.REG.getString(ColumEmpleados.Colum22_FechaContrato);
                VAR.Obj23_Salario = DB.REG.getInt(ColumEmpleados.Colum23_Salario);
                VAR.Obj24_idHorario = DB.REG.getInt(ColumEmpleados.Colum24_idHorario);
                VAR.Obj25_HuellaDig = DB.REG.getString(ColumEmpleados.Colum25_HuellaDig);
                VAR.Obj26_RFC = DB.REG.getString(ColumEmpleados.Colum26_RFC);
                VAR.Obj27_DESISTEMA = DB.REG.getBoolean(ColumEmpleados.Colum27_DESISTEMA);
                VAR.Obj28_ELIMINADO = DB.REG.getBoolean(ColumEmpleados.Colum28_ELIMINADO);
                VAR.Obj29_FECHAHORACAMBIO = DB.REG.getString(ColumEmpleados.Colum29_FECHAHORACAMBIO);
                VAR.Obj30_IDUSUARIO = DB.REG.getString(ColumEmpleados.Colum30_IDUSUARIO);
            }
            
            DB.Close();
            return VAR;
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
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - ConsultarEmpleado - SQLException" , 0);
            return null;
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - ConsultarEmpleado - Exception" , 0);
            return  null;
        }
    }
    //endregion CONSULTAR
    
    //region EXISTE
    public boolean ExisteEmpleado(int idEmpleado){
        
        try
        {
            String Query;
            
            DB.SQLWheres = " WHERE "+ColumEmpleados.Colum28_ELIMINADO+" = '0' AND "+ColumEmpleados.Colum1_idEmpleado+"="+idEmpleado;
            DB.SQLSelect = "SELECT isnull( COUNT(*), 0) AS num FROM " + JCHECKTIME.TABLAS.Empleados;
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
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - ExisteEmpleado - SQLException" , 0);
        }
        catch (Exception e) 
        {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "ClaseEmpleados - ExisteEmpleado - Exception" , 0);
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
