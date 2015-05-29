
package JCHECKTIME.TOOLS.INTERFACES;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Calendario {
    
    public enum enumMeses{
        
        Enero(31), 
        Febrero(28), 
        Marzo(31), 
        Abril(30), 
        Mayo(31), 
        Junio(30), 
        Julio(31), 
        Agosto(31), 
        Septiembre(30), 
        Octubre(31), 
        Noviembre(30), 
        Diciembre(31);
        
        private int dias;
        private enumMeses(int dias) 
        {
           this.dias = dias;
        }
        public int getValue() 
        {
           return dias;
        }
    }
    
    public static void InicializarCalendario(JComboBox cboAño, JComboBox cboMes, JComboBox cboDia) {
        
        cboAño.removeAllItems();
        cboMes.removeAllItems();
        cboDia.removeAllItems();
        
        int AñoInicial=1900;
        int AñoFinal=2050;
        
        //Cargar años
        for (int i = AñoFinal; i > AñoInicial; i--) 
        {
            cboAño.addItem(i);
        }
        
        //Arreglo contenedor de los meses
        String[] Meses ={"Enero", 
            "Febrero",
            "Marzo", 
            "Abril",
            "Mayo", 
            "Junio",
            "Julio", 
            "Agosto",
            "Septiembre", 
            "Octubre",
            "Noviembre", 
            "Diciembre"};
        
        //Cargar meses desde el arreglo
        for (int i = 0; i < Meses.length; i++) 
        {
            cboMes.addItem(Meses[i]);
        }
        
        //Cargar los dias
        CargarDias(250, "Enero", cboDia);
    }
    
    private static void CargarDias(int Año, String Mes, JComboBox cboDia){
        
        cboDia.removeAllItems();
        String dia;
        
        if(Mes.equals("Enero")|Mes.equals("Marzo")|Mes.equals("Mayo")|Mes.equals("Julio")|Mes.equals("Agosto")|Mes.equals("Octubre")|Mes.equals("Diciembre"))
        {
            for (int i = 1; i < 32; i++) 
            {
                 dia=i+"";
                 if(dia.length()==1)
                 {
                     cboDia.addItem("0"+dia);
                 }
                 else
                 {
                     cboDia.addItem(dia);
                 }
            }
        }
        else if(Mes.equals("Abril")|Mes.equals("Junio")|Mes.equals("Septiembre")|Mes.equals("Noviembre"))
        {
            for (int i = 1; i < 31; i++) 
            {
                dia=i+"";
                 if(dia.length()==1)
                 {
                     cboDia.addItem("0"+dia);
                 }
                 else
                 {
                     cboDia.addItem(dia);
                 }
            }
        }
        else if(Mes.equals("Febrero"))
        {
            if(Año%4==0)
            {
                for (int i = 1; i < 30; i++) 
                {
                    dia=i+"";
                    if(dia.length()==1)
                    {
                        cboDia.addItem("0"+dia);
                    }
                    else
                    {
                        cboDia.addItem(dia);
                    }
                }
            }
            else
            {
                for (int i = 1; i < 29; i++) 
                {
                    dia=i+"";
                    if(dia.length()==1)
                    {
                        cboDia.addItem("0"+dia);
                    }
                    else
                    {
                        cboDia.addItem(dia);
                    }
                }
            }
        }
        else
        { 
        }
    }
    
    public static void CambiarComboAño(JComboBox cboMes, JComboBox cboDia){
        
        if(cboMes.getComponentCount()!=0&&cboDia.getComponentCount()!=0)
        {
            try
            {
                cboDia.removeAllItems();
                cboMes.setSelectedIndex(0);

                String dia;
                for (int i = 1; i < 32; i++)
                {
                    dia=i+"";
                    if(dia.length()==1)
                    {
                        cboDia.addItem("0"+dia);
                    }
                    else
                    {
                        cboDia.addItem(dia);
                    }
                }
            }
            catch(IllegalArgumentException | NullPointerException e)
            {
               //No hacer nada
            }
            catch(Exception e)
            {
                //JOptionPane
                //(Frame, Mensaje, Titulo, Tipo)
                // Tipo(0) Error
                // Tipo(1) Informacion
                // Tipo(2) Advertencia
                // Tipo(3) Interragacion

                JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);
            }
        }
    }
    
    public static void CambiarComboMes(JComboBox cboAño, JComboBox cboMes, JComboBox cboDia){
        
        if(cboAño.getComponentCount()!=0&&cboMes.getComponentCount()!=0&&cboDia.getComponentCount()!=0)
        {
            try
            {
                int Año=Integer.parseInt(cboAño.getSelectedItem().toString());
                String Mes=cboMes.getSelectedItem().toString();
                CargarDias(Año, Mes, cboDia);
            }
            catch(IllegalArgumentException | NullPointerException e)
            {
                //No hacer nada
            }
            catch(Exception e)
            {
                //JOptionPane
                //(Frame, Mensaje, Titulo, Tipo)
                // Tipo(0) Error
                // Tipo(1) Informacion
                // Tipo(2) Advertencia
                // Tipo(3) Interragacion

                JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);
            }
        }
    }
}
