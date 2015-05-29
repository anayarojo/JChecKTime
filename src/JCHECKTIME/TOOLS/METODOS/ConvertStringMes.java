
package JCHECKTIME.TOOLS.METODOS;

public class ConvertStringMes {
    
    public static String toIntMes(String Mes){
        
        String IntMes;
        
        switch(Mes)
        {
            case "Enero":
                IntMes="01";
                break;
                
            case "Febrero":
                IntMes="02";
                break;
                
            case "Marzo":
                IntMes="03";
                break;
              
            case "Abril":
                IntMes="04";
                break;    
               
            case "Mayo":
                IntMes="05";
                break;
                
            case "Junio":
                IntMes="06";
                break;
                
            case "Julio":
                IntMes="07";
                break;
              
            case "Agosto":
                IntMes="08";
                break;   
            
            case "Septiembre":
                IntMes="09";
                break;    
               
            case "Octubre":
                IntMes="10";
                break;
                
            case "Noviembre":
                IntMes="11";
                break;
                
            case "Diciembre":
                IntMes="12";
                break;
              
            default:
                IntMes="01";
                break;     
        }
        
        return IntMes;
    }
}
