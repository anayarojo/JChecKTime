package JCHECKTIME.HORARIOS;

import JCHECKTIME.DATABASE;
import JCHECKTIME.RESULTADO;
import JCHECKTIME.RESULTADO.enumResultados;
import JCHECKTIME.TABLAS;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Horario {

    /*---------------------------------------------------------------------------------*
     *  Ultima revision:             29 septiembre 2013                                 *
     *  Actualizado:                 Juan Manuel Esquer                                    *
     *  Ultima modificacion en:      Columbas BD                                      *
     *----------------------------------------------------------------------------------*/
    public static DATABASE DB = new DATABASE();

    //region ESTRUCTURA
    public static class strHorario {

        public int Obj1_idHorario;
        public String Obj2_Nombre;
        public String Obj3_Turno;
        public String Obj4_Descripcion;
        public boolean Obj5_DESISTEMA;
        public boolean Obj6_ELIMINADO;
        public String Obj7_FECHAHORACAMBIO;
        public String Obj8_IDUSUARIO;
        public String Obj9_LunesE;
        public String Obj10_LunesS;
        public String Obj11_MartesE;
        public String Obj12_MartesS;
        public String Obj13_MiercolesE;
        public String Obj14_MiercolesS;
        public String Obj15_JuevesE;
        public String Obj16_JuevesS;
        public String Obj17_ViernesE;
        public String Obj18_ViernesS;
        public String Obj19_SabadoE;
        public String Obj20_SabadoS;
        public String Obj21_DomingoE;
        public String Obj22_DomingoS;
    }
    //endregion ESTRUCTURA

    //region COLUMNAS
    public static class ColumHorarios {

        public static String Colum1_idHorario = "idHorario";
        public static String Colum2_Nombre = "Nombre";
        public static String Colum3_Turno = "Turno";
        public static String Colum4_Descripcion = "Descripcion";
        public static String Colum5_DESISTEMA = "DESISTEMA";
        public static String Colum6_ELIMINADO = "ELIMINADO";
        public static String Colum7_FECHAHORACAMBIO = "FECHAHORACAMBIO";
        public static String Colum8_IDUSUARIO = "IDUSUARIO";
        public static String Colum9_LunesE="LunesE";
        public static String Colum10_LunesS="LunesS";
        public static String Colum11_MartesE="MartesE";
        public static String Colum12_MartesS="MartresS";
        public static String Colum13_MiercolesE="MiercolesE";
        public static String Colum14_MiercolesS="MiercolesS";
        public static String Colum15_JuevesE="JuevesE";
        public static String Colum16_JuevesS="JuevesS";
        public static String Colum17_ViernesE="ViernesE";
        public static String Colum18_ViernesS="ViernesS";
        public static String Colum19_SabadoE="SabadoE";
        public static String Colum20_SabadoS="SabadoS";
        public static String Colum21_DomingoE="DomingoE";
        public static String Colum22_DomingoS="DomingoS";
    }
    //endregion COLUMNAS

    //region LISTAR
    public RESULTADO.enumResultados ListarHorarios(String Filtro, Horario.strHorario[] ARR) {

        try {
            String Query;

            DB.SQLSelect = "SELECT COUNT(*) AS count FROM " + JCHECKTIME.TABLAS.Horarios;
            DB.SQLWheres = " WHERE ELIMINADO = '0' AND (Nombre + Turno) LIKE '%" + Filtro + "%'";

            Query = DB.SQLSelect + DB.SQLWheres;

            DB.COM = DB.CON.createStatement();
            DB.REG = DB.COM.executeQuery(Query);

            int Cuantos = 0;

            while (DB.REG.next()) {
                Cuantos = Integer.parseInt(DB.REG.getString("count"));
            }

            ARR = new Horario.strHorario[Cuantos];

            DB.SQLSelect = "SELECT * FROM " + JCHECKTIME.TABLAS.Horarios;
            Query = DB.SQLSelect + DB.SQLWheres;

            DB.COM = DB.CON.createStatement();
            DB.REG = DB.COM.executeQuery(Query);

            int i = 0;
            while (DB.REG.next()) {
                ARR[i].Obj1_idHorario = DB.REG.getInt(Horario.ColumHorarios.Colum1_idHorario);
                ARR[i].Obj2_Nombre = DB.REG.getString(Horario.ColumHorarios.Colum2_Nombre);
                ARR[i].Obj3_Turno = DB.REG.getString(Horario.ColumHorarios.Colum3_Turno);
                ARR[i].Obj4_Descripcion = DB.REG.getString(Horario.ColumHorarios.Colum4_Descripcion);
                ARR[i].Obj5_DESISTEMA = DB.REG.getBoolean(Horario.ColumHorarios.Colum5_DESISTEMA);
                ARR[i].Obj6_ELIMINADO = DB.REG.getBoolean(Horario.ColumHorarios.Colum6_ELIMINADO);
                ARR[i].Obj7_FECHAHORACAMBIO = DB.REG.getString(Horario.ColumHorarios.Colum7_FECHAHORACAMBIO);
                ARR[i].Obj8_IDUSUARIO = DB.REG.getString(Horario.ColumHorarios.Colum8_IDUSUARIO);

                i++;
            }

            DB.Close();
            return RESULTADO.enumResultados.OperacionCorrecta;
        } catch (SQLException e) {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "SQLException", 0);
            return RESULTADO.enumResultados.ErrorDeBaseDeDatos;
        } catch (Exception e) {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception", 0);
            return RESULTADO.enumResultados.ErrorDesconocido;
        }
    }
    public  static void MostrarHorario(String filtro, String arr[]){
        try{
  DB.SQLSelect= "Select * From" + TABLAS.Horarios+ " ";
  DB.SQLWheres= "Where " + ColumHorarios.Colum2_Nombre +" = '" +filtro+"'";
  String Query= DB.SQLSelect + DB.SQLWheres;
            DB.COM = DB.CON.createStatement();
            DB.REG = DB.COM.executeQuery(Query);
            while(DB.REG.next()){
arr[0]=DB.REG.getString(ColumHorarios.Colum2_Nombre);
arr[1]=DB.REG.getString(ColumHorarios.Colum4_Descripcion);
arr[2]=DB.REG.getString(ColumHorarios.Colum9_LunesE);
arr[3]=DB.REG.getString(ColumHorarios.Colum10_LunesS);
arr[4]=DB.REG.getString(ColumHorarios.Colum11_MartesE);
arr[5]=DB.REG.getString(ColumHorarios.Colum12_MartesS);
arr[6]=DB.REG.getString(ColumHorarios.Colum13_MiercolesE);
arr[7]=DB.REG.getString(ColumHorarios.Colum14_MiercolesS);
arr[8]=DB.REG.getString(ColumHorarios.Colum15_JuevesE);
arr[9]=DB.REG.getString(ColumHorarios.Colum16_JuevesS);
arr[10]=DB.REG.getString(ColumHorarios.Colum17_ViernesE);
arr[11]=DB.REG.getString(ColumHorarios.Colum18_ViernesS);
arr[12]=DB.REG.getString(ColumHorarios.Colum19_SabadoE);
arr[13]=DB.REG.getString(ColumHorarios.Colum20_SabadoS);
arr[14]=DB.REG.getString(ColumHorarios.Colum21_DomingoE);
arr[15]=DB.REG.getString(ColumHorarios.Colum22_DomingoS);            }

        }
        catch(Exception e){
JOptionPane.showMessageDialog(null, e);
        }

    }
    //endregion LISTAR

    //region REGISTRAR
    public enumResultados RegistrarHorario(strHorario VAR) {

        try {
            String Query;
            DB.SQLFields = ColumHorarios.Colum2_Nombre + ", "
                    + ColumHorarios.Colum3_Turno + ", "
                    + ColumHorarios.Colum4_Descripcion + ", "
                    + ColumHorarios.Colum5_DESISTEMA + ", "
                    + ColumHorarios.Colum6_ELIMINADO + ", "
                    + ColumHorarios.Colum7_FECHAHORACAMBIO + ", "
                    + ColumHorarios.Colum8_IDUSUARIO + ", "
                    + ColumHorarios.Colum9_LunesE + ", "
                    + ColumHorarios.Colum10_LunesS + ", "
                    + ColumHorarios.Colum11_MartesE + ", "
                    + ColumHorarios.Colum12_MartesS + ", "
                    + ColumHorarios.Colum13_MiercolesE + ", "
                    + ColumHorarios.Colum14_MiercolesS + ", "
                    + ColumHorarios.Colum15_JuevesE + ", "
                    + ColumHorarios.Colum16_JuevesS + ", "
                    + ColumHorarios.Colum17_ViernesE + ", "
                    + ColumHorarios.Colum18_ViernesS + ", "
                    + ColumHorarios.Colum19_SabadoE + ", "
                    + ColumHorarios.Colum20_SabadoS + ", "
                    +ColumHorarios.Colum21_DomingoE + ", "
                    +ColumHorarios.Colum22_DomingoS + " ";

            DB.SQLValues = VAR.Obj2_Nombre + ", "
                    + VAR.Obj3_Turno + ", "
                    + VAR.Obj4_Descripcion + ", "
                    + VAR.Obj5_DESISTEMA + ", "
                    + VAR.Obj6_ELIMINADO + ", "
                    + VAR.Obj7_FECHAHORACAMBIO + ", "
                    + VAR.Obj8_IDUSUARIO +", "
                    + VAR.Obj9_LunesE + ", "
                    +VAR.Obj10_LunesS + ", "
                    +VAR.Obj11_MartesE + ", "
                    +VAR.Obj12_MartesS + ", "
                    +VAR.Obj13_MiercolesE +", "
                    +VAR.Obj14_MiercolesS + ", "
                    +VAR.Obj15_JuevesE + ", "
                    +VAR.Obj16_JuevesS + ", "
                    +VAR.Obj17_ViernesE+ ", "
                    +VAR.Obj18_ViernesS+ ", "
                    +VAR.Obj19_SabadoE+ ", "
                    +VAR.Obj20_SabadoS+ ", "
                    +VAR.Obj21_DomingoE+ ", "
                    +VAR.Obj22_DomingoS;


            DB.SQLInsert = "INSERT INTO " + TABLAS.Horarios + " (" + DB.SQLFields + ") " + " values(" + DB.SQLValues + ")";
            Query = DB.SQLInsert;

            DB.COM = DB.CON.createStatement();
            DB.REG = DB.COM.executeQuery(Query);

            DB.Close();
            return enumResultados.OperacionCorrecta;
        } catch (SQLException e) {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "SQLException", 0);
            return enumResultados.ErrorDeBaseDeDatos;
        } catch (Exception e) {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception", 0);
            return enumResultados.ErrorDesconocido;
        }
    }
    //endregion REGISTRAR

    //region MODIFICAR
    public enumResultados ModificarHorario(Horario.strHorario VAR) {

        try {
            String Query;

            DB.SQLSet = Horario.ColumHorarios.Colum2_Nombre + "=" + VAR.Obj2_Nombre + ", "
                    + Horario.ColumHorarios.Colum3_Turno + "=" + VAR.Obj3_Turno + ", "
                    + Horario.ColumHorarios.Colum4_Descripcion + "=" + VAR.Obj4_Descripcion + ", "
                    + Horario.ColumHorarios.Colum5_DESISTEMA + "=" + VAR.Obj5_DESISTEMA + ", "
                    + Horario.ColumHorarios.Colum6_ELIMINADO + "=" + VAR.Obj6_ELIMINADO + ", "
                    + Horario.ColumHorarios.Colum7_FECHAHORACAMBIO + "=" + VAR.Obj7_FECHAHORACAMBIO + ", "
                    + Horario.ColumHorarios.Colum8_IDUSUARIO + "=" + VAR.Obj8_IDUSUARIO;

            DB.SQLWheres = Horario.ColumHorarios.Colum1_idHorario + " = " + VAR.Obj1_idHorario;
            DB.SQLUpdate = "UPDATE " + TABLAS.Horarios + " set " + DB.SQLSet + " WHERE " + DB.SQLWheres;

            Query = DB.SQLUpdate;

            DB.COM = DB.CON.createStatement();
            DB.REG = DB.COM.executeQuery(Query);

            DB.Close();
            return enumResultados.OperacionCorrecta;
        } catch (SQLException e) {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "SQLException", 0);
            return enumResultados.ErrorDeBaseDeDatos;
        } catch (Exception e) {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception", 0);
            return enumResultados.ErrorDesconocido;
        }
    }
    //endregion MODIFICAR

    //region ELIMINAR
    public enumResultados EliminarHorario(int idHorario) {

        try {
            String Query;

            DB.SQLSet = Horario.ColumHorarios.Colum6_ELIMINADO + " = 1, "
                    + Horario.ColumHorarios.Colum8_IDUSUARIO + " = " + JCHECKTIME.SEGURIDAD.IDUSUARIO
                    + Horario.ColumHorarios.Colum7_FECHAHORACAMBIO + " = getdate()";

            DB.SQLWheres = Horario.ColumHorarios.Colum1_idHorario + " = " + idHorario;
            DB.SQLUpdate = "UPDATE " + TABLAS.Horarios + " set " + DB.SQLSet + " WHERE " + DB.SQLWheres;

            Query = DB.SQLUpdate;

            DB.COM = DB.CON.createStatement();
            DB.REG = DB.COM.executeQuery(Query);

            DB.Close();
            return enumResultados.OperacionCorrecta;
        } catch (SQLException e) {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "SQLException", 0);
            return enumResultados.ErrorDeBaseDeDatos;
        } catch (Exception e) {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception", 0);
            return enumResultados.ErrorDesconocido;
        }
    }
    //endregion ELIMINAR

    //region CONSULTAR
    public enumResultados ConsultarHorario(int idHorario, Horario.strHorario VAR) {

        try {
            String Query;

            DB.SQLWheres = " WHERE " + Horario.ColumHorarios.Colum6_ELIMINADO + " = '0' AND " + Horario.ColumHorarios.Colum1_idHorario + "=" + idHorario;
            DB.SQLSelect = "SELECT * FROM " + JCHECKTIME.TABLAS.Horarios;
            Query = DB.SQLSelect + DB.SQLWheres;

            DB.COM = DB.CON.createStatement();
            DB.REG = DB.COM.executeQuery(Query);

            while (DB.REG.next()) {
                VAR.Obj1_idHorario = DB.REG.getInt(Horario.ColumHorarios.Colum1_idHorario);
                VAR.Obj2_Nombre = DB.REG.getString(Horario.ColumHorarios.Colum2_Nombre);
                VAR.Obj3_Turno = DB.REG.getString(Horario.ColumHorarios.Colum3_Turno);
                VAR.Obj4_Descripcion = DB.REG.getString(Horario.ColumHorarios.Colum4_Descripcion);
                VAR.Obj5_DESISTEMA = DB.REG.getBoolean(Horario.ColumHorarios.Colum5_DESISTEMA);
                VAR.Obj6_ELIMINADO = DB.REG.getBoolean(Horario.ColumHorarios.Colum6_ELIMINADO);
                VAR.Obj7_FECHAHORACAMBIO = DB.REG.getString(Horario.ColumHorarios.Colum7_FECHAHORACAMBIO);
                VAR.Obj8_IDUSUARIO = DB.REG.getString(Horario.ColumHorarios.Colum8_IDUSUARIO);
            }

            DB.Close();
            return enumResultados.OperacionCorrecta;
        } catch (SQLException e) {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "SQLException", 0);
            return enumResultados.ErrorDeBaseDeDatos;
        } catch (Exception e) {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception", 0);
            return enumResultados.ErrorDesconocido;
        }
    }
    //endregion CONSULTAR

    //region EXISTE
    public boolean ExisteEmpleado(int idHorario) {

        try {
            String Query;

            DB.SQLWheres = " WHERE " + Horario.ColumHorarios.Colum6_ELIMINADO + " = '0' AND " + Horario.ColumHorarios.Colum1_idHorario + "=" + idHorario;
            DB.SQLSelect = "SELECT isnull( COUNT(*), 0) AS num FROM " + JCHECKTIME.TABLAS.Horarios;
            Query = DB.SQLSelect + DB.SQLWheres;

            DB.COM = DB.CON.createStatement();
            DB.REG = DB.COM.executeQuery(Query);

            int Cuantos = 0;
            while (DB.REG.next()) {
                Cuantos = Integer.parseInt(DB.REG.getString("num"));
            }

            DB.Close();

            if (Cuantos == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "SQLException", 0);
        } catch (Exception e) {
            DB.Close();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception", 0);
        }

        return false;
    }
    //endregion EXISTE

    //region CERRAR
    public void CerrarBaseDeDatos() {
        DB.Close();
    }
    //region CERRAR
}
