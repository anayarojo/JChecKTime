
package JCHECKTIME.HORARIOS;

import JCHECKTIME.DATABASE;

public class Dias {
    
    DATABASE DB=new DATABASE();
    
    public class strDias{
    
        public int idDia;
        public int idHorario;
        public String Dia;
        public String Entrada;
        public String Salida;
        public boolean DESISTEMA;
        public boolean ELIMINADO;
        public String FECHAHORACAMBIO;
        public String IDUSUARIO;
    }
}
