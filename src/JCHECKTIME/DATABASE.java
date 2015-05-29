
package JCHECKTIME;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DATABASE {
    
        public String SQLSelect ;
        public String SQLUpdate ;
        public String SQLInsert ;
        public String SQLDelete ;
        public String SQLWheres ;
        public String SQLFields ;
        public String SQLValues ;
        public String SQLSet ;

        public Connection CON  = null;
        public Statement  COM  = null;
        public ResultSet  REG  = null;
        public CallableStatement CST = null;

        public boolean isConnected()
        {
            try
            {
             if ( CON.isClosed() )
                { return false; } else { return true; }
            }
            catch (Exception e)
            {
               return false;
            }
        }

        public void Close()
        {
            try
            {
                if (CON.isClosed())
                      {}
                    else
                      {
                        CON.close();
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
                
                JOptionPane.showMessageDialog(null, e.toString(), "SQLException" , 0);
            }
            catch (Exception e) 
            {
                JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);
            }
        }

        public  DATABASE()
        {
            try 
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                //CON = DriverManager.getConnection("jdbc:sqlserver://RAUL-PC\\SQLEXPRESS2008;databaseName=JCheckTime", "sa","adminihanad");
                CON = DriverManager.getConnection("jdbc:sqlserver://RAUL-PC\\SQLSERVER;databaseName=JCheckTime", "sa","adminihanad");

                System.out.println("Conexion Abierta");
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
            catch (ClassNotFoundException e) 
            {
                JOptionPane.showMessageDialog(null, e.toString(), "ClassNotFoundException" , 0);
            }
            catch (Exception e) 
            {
                JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);
            }
        }

        public int ExecuteStatement(String txtStatement) throws Exception
        {
           int r=0;
           try
           {
               COM = CON.createStatement();
               r = COM.executeUpdate(txtStatement);
               return r;
           }
           catch (Exception e)
             {
                return -1;
             }
        }

        public java.sql.ResultSet ExecuteQuery(String txtStatement)
        {
            java.sql.ResultSet RES;
            try
            {
                COM = CON.createStatement();
                RES =  COM.executeQuery(txtStatement);
           
                return RES;
            }
            catch (Exception e)
            {
                return null;
            }
        }
}
