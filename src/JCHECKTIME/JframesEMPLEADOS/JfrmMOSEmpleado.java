
package JCHECKTIME.JframesEMPLEADOS;

import JCHECKTIME.EMPLEADOS.Empleados;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.ImageIcon;

public final class JfrmMOSEmpleado extends javax.swing.JFrame {

    //Estructura contenedora de los datos del empleado
    public int idEmpleado;
    public Empleados.strEmpleados varEmpleado = new Empleados.strEmpleados();
    
    //Variables de las paginas del formulario para modificar de empleados
    pnlMOSEmpleadoPagina1 Pagina1;
    pnlMOSEmpleadoPagina2 Pagina2;
    
    String Datos;
    public JfrmMOSEmpleado(int id) {
        initComponents();
        
        idEmpleado=id;
        
        
        this.setLocationRelativeTo(null); //Ventana centrada
        this.setTitle("Consultar empleado");
        this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Iconos/ConsultarEmpleado.png")).getImage());
        
        InicializarEstructura();
        CargarDatos();
        MostrarPagina1();
    }
    
    public void MostrarPagina1(){
        
        pnlPrincipal.removeAll();
        Pagina1=new pnlMOSEmpleadoPagina1(pnlPrincipal.getSize());
        pnlPrincipal.add(Pagina1, BorderLayout.CENTER);
        //Pagina1.CapturarDatos(this);
        Pagina1.CargarDatos(this);
        pnlPrincipal.repaint();
        
        btnAnterior.setVisible(false);
        btnSiguiente.setVisible(true);
        btnCopiarPortapapeles.setVisible(true);
        btnCancelar.setVisible(true);
    }
    
    public void MostrarPagina2(){
        
        pnlPrincipal.removeAll();
        Pagina2=new pnlMOSEmpleadoPagina2(pnlPrincipal.getSize());
        pnlPrincipal.add(Pagina2, BorderLayout.CENTER);
        pnlPrincipal.repaint();
        
        btnAnterior.setVisible(true);
        btnSiguiente.setVisible(false);
        btnCopiarPortapapeles.setVisible(true);
        btnCancelar.setVisible(true);
    }
    
    public void CargarDatos(){
        
        Empleados E=new Empleados();
        varEmpleado=E.ConsultarEmpleado(idEmpleado);
        
        Datos="idEmpleado: "+varEmpleado.Obj1_idEmpleado+"\n"+
                "Nombre(s):"+varEmpleado.Obj2_NombreEmpleado+"\n"+
                "Apellidos:"+varEmpleado.Obj3_Apellidos+"\n"+
                "Fecha de nacieminto:"+varEmpleado.Obj4_FechaNacimiento+"\n"+
                "Celular:"+varEmpleado.Obj5_Celular+"\n"+
                "Telefono:"+varEmpleado.Obj6_Telefono+"\n"+
                "Correo Electronico:"+varEmpleado.Obj7_CorreoElectronico+"\n"+
                "Direccion:"+varEmpleado.Obj14_Direccion+"\n"+
                "Colonia:"+varEmpleado.Obj9_Colonia+"\n"+
                "Localidad:"+varEmpleado.Obj13_Localidad+"\n"+
                "Ciudad:"+varEmpleado.Obj8_Ciudad+"\n"+
                "Estado civil:"+varEmpleado.Obj12_EstadoCivil+"\n"+
                "Numero de seguro:"+varEmpleado.Obj10_NumeroSeguro+"\n"+
                "CURP:"+varEmpleado.Obj11_CURP+"\n"+
                "Alergias:"+varEmpleado.Obj15_Alergias+"\n"+
                "Contacto en caso de emergencias:"+varEmpleado.Obj16_EmergInfo+"\n"+
                "Telefono en caso de emergencias:"+varEmpleado.Obj17_EmergTel+"\n"+
                "Tipo de sangre:"+varEmpleado.Obj18_TipoDeSangre+"\n"+
                "Puesto:"+varEmpleado.Obj20_Puesto+"\n"+
                "Departamento:"+varEmpleado.Obj19_Departamento+"\n"+
                "Jefe inmediato:"+varEmpleado.Obj21_JefeInmediato+"\n"+
                "Fecha de contrato:"+varEmpleado.Obj22_FechaContrato+"\n"+
                "Salario:"+varEmpleado.Obj23_Salario+"\n"+
                "RFC:"+varEmpleado.Obj26_RFC+"\n"
                ;
    }
    
    public void InicializarEstructura(){
        
        varEmpleado.Obj2_NombreEmpleado="";
        varEmpleado.Obj3_Apellidos="";
        varEmpleado.Obj4_FechaNacimiento="";
        varEmpleado.Obj5_Celular="";
        varEmpleado.Obj6_Telefono="";
        varEmpleado.Obj7_CorreoElectronico="";
        varEmpleado.Obj8_Ciudad="";
        varEmpleado.Obj9_Colonia="";
        varEmpleado.Obj10_NumeroSeguro="";
        varEmpleado.Obj11_CURP="";
        varEmpleado.Obj12_EstadoCivil="";
        varEmpleado.Obj13_Localidad="";
        varEmpleado.Obj14_Direccion="";
        varEmpleado.Obj15_Alergias="";
        varEmpleado.Obj16_EmergInfo="";
        varEmpleado.Obj17_EmergTel="";
        varEmpleado.Obj18_TipoDeSangre="";
        varEmpleado.Obj19_Departamento="";
        varEmpleado.Obj20_Puesto="";
        varEmpleado.Obj21_JefeInmediato="";
        varEmpleado.Obj22_FechaContrato="";
        varEmpleado.Obj23_Salario=0;
        varEmpleado.Obj24_idHorario=0;
        varEmpleado.Obj25_HuellaDig="";
        varEmpleado.Obj26_RFC="";
        varEmpleado.Obj27_DESISTEMA=false;
        varEmpleado.Obj28_ELIMINADO=false;
        varEmpleado.Obj29_FECHAHORACAMBIO="";
        varEmpleado.Obj30_IDUSUARIO="";
    }
    
    public void Portapapeles(String str){
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, ss);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBotones = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnCopiarPortapapeles = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        pnlPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnlBotones.setBackground(new java.awt.Color(153, 153, 153));
        pnlBotones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnCancelar.setBackground(new java.awt.Color(153, 153, 153));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setFocusable(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCopiarPortapapeles.setBackground(new java.awt.Color(153, 153, 153));
        btnCopiarPortapapeles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Copiar.png"))); // NOI18N
        btnCopiarPortapapeles.setText("Copiar a portapapeles");
        btnCopiarPortapapeles.setFocusable(false);
        btnCopiarPortapapeles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarPortapapelesActionPerformed(evt);
            }
        });

        btnSiguiente.setBackground(new java.awt.Color(153, 153, 153));
        btnSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Siguiente.png"))); // NOI18N
        btnSiguiente.setText("Siguiente");
        btnSiguiente.setFocusable(false);
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAnterior.setBackground(new java.awt.Color(153, 153, 153));
        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Anterior.png"))); // NOI18N
        btnAnterior.setText("Anterior");
        btnAnterior.setFocusable(false);
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotonesLayout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addComponent(btnAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiguiente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCopiarPortapapeles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnCopiarPortapapeles)
                    .addComponent(btnSiguiente)
                    .addComponent(btnAnterior))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCopiarPortapapelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarPortapapelesActionPerformed
        Portapapeles(Datos);
    }//GEN-LAST:event_btnCopiarPortapapelesActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        MostrarPagina2();
        Pagina2.CargarDatos(this);
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        MostrarPagina1();
        Pagina1.CargarDatos(this);
    }//GEN-LAST:event_btnAnteriorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JfrmMOSEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfrmMOSEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfrmMOSEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfrmMOSEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfrmMOSEmpleado(1).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCopiarPortapapeles;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
