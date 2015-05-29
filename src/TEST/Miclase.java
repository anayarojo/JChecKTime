/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TEST;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

/**
 *
 * @author Raul
 */
public class Miclase {
    
    public static void Portapapeles(String str){
        
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,ss);
        
    }
    
    public static void main(String[] args) {
       /*
        int[] num=new int[1];
        Clase C=new Clase(num);
        
        for (int i = 0; i <num.length; i++) 
        {
            System.out.println(num[i]);
        }*/
        
        Portapapeles("Hola que hace?, haceindo la tarea a ultimo momento o que hace");
    }
}
