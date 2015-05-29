
package JCHECKTIME.TOOLS.METODOS;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

public class Fecha {
    
    SimpleDateFormat F;
    
    Calendar Ca;
    java.util.Date Actual;
    
    public Fecha(){
       Ca=Calendar.getInstance();
       Actual =Ca.getTime();
    }
    
    public String getFecha(){
        F = new SimpleDateFormat("yyyy-MM-dd", new Locale("ES"));
        return F.format(Actual);
    }
    
    public String getFechaCompleta(){
        F = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", new Locale("ES"));
        return F.format(Actual);
    }
    
    public String getHora(){
        F = new SimpleDateFormat("hh:mm:ss a", new Locale("ES"));
        return F.format(Actual);
    }
    
    public String getFechaTexto(){
        F = new SimpleDateFormat("EEE, d MMM yyyy", new Locale("ES"));
        return F.format(Actual);
    }
    
    public String ConvertirTexto(String Texto){
        F = new SimpleDateFormat("yyyy-MM-dd", new Locale("ES"));
        
        Date Fecha;
        String Conversion="";
        
        try 
        {
            Fecha=F.parse(Texto);
            F = new SimpleDateFormat("EEEE, d MMM yyyy", new Locale("ES"));
            Conversion=F.format(Fecha);
            
        }
        catch (Exception e) 
        {
            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);
        }
        
        return Conversion;
    }
}
