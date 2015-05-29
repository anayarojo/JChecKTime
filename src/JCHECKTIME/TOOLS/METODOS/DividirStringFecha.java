
package JCHECKTIME.TOOLS.METODOS;

//METODO JUAN
public class DividirStringFecha {
    
    //Metodo que recibe un string con la fecha y lo divide en tres variables indendientes
    
    public static int getFechaAño(String Fecha/*Formato año/mes/dia 
             2012/09/21*/) {

        String AÑO1 = Fecha.charAt(0) + "";
        String AÑO2 = Fecha.charAt(1) + "";
        String AÑO3 = Fecha.charAt(2) + "";
        String AÑO4 = Fecha.charAt(3) + "";
        String AÑO = AÑO1 + AÑO2 + AÑO3 + AÑO4;
        int año = Integer.parseInt(AÑO);
        return año;
    }

    public static String getFechaMes(String Fecha) {
        String MES1 = Fecha.charAt(5) + "";
        String MES2 = Fecha.charAt(6) + "";
        String MES = MES1 + MES2;
        String mes = "";
        switch (MES) {
            case "01":
                mes = "Enero";
                break;
            case "02":
                mes = "Febrero";
                break;
            case "03":
                mes = "Marzo";
                break;
            case "04":
                mes = "Abril";
                break;
            case "05":
                mes = "Mayo";
                break;
            case "06":
                mes = "Junio";
                break;
            case "07":
                mes = "Julio";
                break;
            case "08":
                mes = "Agosto";
                break;
            case "09":
                mes = "Septiembre";
                break;
            case "10":
                mes = "Octubre";
                break;
            case "11":
                mes = "Noviembre";
                break;
            case "12":
                mes = "Diciembre";
                break;
        }
        return mes;

    }

    public static String getFechaDia(String Fecha) {

        String DIA1 = Fecha.charAt(8) + "";
        String DIA2 = Fecha.charAt(9) + "";
        String DIA = DIA1 + DIA2;
        //int dia = Integer.parseInt(DIA);
        return DIA;
    }
}
