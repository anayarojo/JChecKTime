
package JCHECKTIME.JframesEMPLEADOS;

import JCHECKTIME.TOOLS.INTERFACES.Calendario;
import JCHECKTIME.TOOLS.METODOS.ConvertStringMes;
import JCHECKTIME.TOOLS.METODOS.DividirStringFecha;
import JCHECKTIME.TOOLS.METODOS.Fecha;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

public class pnlMODEmpleadoPagina2 extends javax.swing.JPanel {

    //Modelo del listado del Log
    public DefaultListModel lstModelo=new DefaultListModel();
    byte[] HuellaDig;
    
    JfrmMODEmpleado MODEmpleado;
    
    public pnlMODEmpleadoPagina2(Dimension D) {
        initComponents();
        
        this.setSize(D);
        Calendario.InicializarCalendario(cboAño, cboMes, cboDia);
    }
    
    public void CapturarDatos(JfrmMODEmpleado JfrmEmpleado)
    {
        JfrmEmpleado.varEmpleado.Obj19_Departamento=txtDepartamento.getText();
        JfrmEmpleado.varEmpleado.Obj20_Puesto=txtPuesto.getText();
        JfrmEmpleado.varEmpleado.Obj21_JefeInmediato=txtJefeInmediato.getText();
        
        String FechaContrato=cboAño.getSelectedItem().toString()
                                +"-"+ConvertStringMes.toIntMes(cboMes.getSelectedItem().toString())
                                +"-"+cboDia.getSelectedItem();
        
        JfrmEmpleado.varEmpleado.Obj22_FechaContrato=FechaContrato;
        
        
        if(txtSalario.getText().equals(""))
        {
            JfrmEmpleado.varEmpleado.Obj23_Salario=0;
        }
        else
        {
            JfrmEmpleado.varEmpleado.Obj23_Salario=Integer.parseInt(txtSalario.getText());
        }
        
        JfrmEmpleado.varEmpleado.Obj24_idHorario=cboHorario.getSelectedIndex()+1;
        JfrmEmpleado.varEmpleado.Obj26_RFC= txtRFC.getText();
    }
    
    public void CargarDatos(JfrmMODEmpleado JfrmEmpleado)
    {
        MODEmpleado=JfrmEmpleado;
        
        txtDepartamento.setText(JfrmEmpleado.varEmpleado.Obj19_Departamento);
        txtPuesto.setText(JfrmEmpleado.varEmpleado.Obj20_Puesto);
        txtJefeInmediato.setText(JfrmEmpleado.varEmpleado.Obj21_JefeInmediato);
        
        String FechaContrato= JfrmEmpleado.varEmpleado.Obj22_FechaContrato;
        
        if(FechaContrato.equals(""))
        {
            Fecha F=new Fecha();
            FechaContrato=F.getFecha();
        }
            
        // region METODO JUAN
        cboAño.setSelectedItem(DividirStringFecha.getFechaAño(FechaContrato));
        cboMes.setSelectedItem(DividirStringFecha.getFechaMes(FechaContrato));
        cboDia.setSelectedItem(DividirStringFecha.getFechaDia(FechaContrato));
        //endregion METODO JUAN
        
        txtSalario.setText(JfrmEmpleado.varEmpleado.Obj23_Salario+"");
        cboHorario.setSelectedIndex(JfrmEmpleado.varEmpleado.Obj24_idHorario-1);
        
        try
        {
            HuellaDig=Base64.decodeBase64(JfrmEmpleado.varEmpleado.Obj25_HuellaDig);
            
            //Declarar nuestra variable sample que contendra la huella digital
            DPFPSample nuevo = DPFPGlobal.getSampleFactory().createSample();
            
            /*
            //Se cargan los bytes que contienen la huella digital
            HuellaDig=JfrmEmpleado.HuellaDigital;
            nuevo.deserialize(JfrmEmpleado.HuellaDigital);
            */
            
            nuevo.deserialize(HuellaDig);
            
            //Convertir la variable sample a una imagen
            Image imagen = DPFPGlobal.getSampleConversionFactory().createImage(nuevo);
            
            //Mostrar huella digital en pantalla
            imgHuellaDig.setIcon(new ImageIcon(
            imagen.getScaledInstance(133, 167, Image.SCALE_DEFAULT)));
            
        }
        catch(IllegalArgumentException e){

            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            JOptionPane.showMessageDialog(null, "Registra la huella del empleado en la lectora", "Notificacion" , 1);

        }
        catch(Exception e){

            //JOptionPane
            //(Frame, Mensaje, Titulo, Tipo)
            // Tipo(0) Error
            // Tipo(1) Informacion
            // Tipo(2) Advertencia
            // Tipo(3) Interragacion

            JOptionPane.showMessageDialog(null, e.toString(), "Exception" , 0);

        }
        
        txtRFC.setText(JfrmEmpleado.varEmpleado.Obj26_RFC);
        
        
        if(JfrmEmpleado.HuellaLista)
        {
            lblResultado.setForeground(new java.awt.Color(0,176,80));
            JfrmEmpleado.Aviso("");
            lblEstado.setText(JfrmEmpleado.EstadoHuella);
            
            btnComprobar.setEnabled(true);
        }
        else
        {
            lblResultado.setForeground(new java.awt.Color(255,0,0));
            JfrmEmpleado.Aviso("Registra la huella del empleado");
            lblEstado.setText(JfrmEmpleado.EstadoHuella);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPuesto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnComprobar = new javax.swing.JButton();
        lblResultado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstLog = new javax.swing.JList();
        lblEstado = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboDia = new javax.swing.JComboBox();
        cboMes = new javax.swing.JComboBox();
        pnlHuella = new javax.swing.JPanel();
        imgHuellaDig = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtRFC = new javax.swing.JTextField();
        cboHorario = new javax.swing.JComboBox();
        txtDepartamento = new javax.swing.JTextField();
        txtJefeInmediato = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cboAño = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtSalario = new javax.swing.JTextField();

        jLabel5.setText("Salario");

        jLabel6.setText("Horario");

        jLabel3.setText("Puesto");

        jLabel14.setText("Fecha de contratacion");

        btnComprobar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Validar.png"))); // NOI18N
        btnComprobar.setText("Comprobar");
        btnComprobar.setEnabled(false);
        btnComprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprobarActionPerformed(evt);
            }
        });

        lblResultado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblResultado.setForeground(new java.awt.Color(255, 0, 0));
        lblResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResultado.setText("Registre la huella del empleado");

        lstLog.setModel(lstModelo);
        jScrollPane1.setViewportView(lstLog);

        lblEstado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstado.setText("Faltan 4 Huellas para completar el registro");

        jLabel7.setText("* Debe registrar cuatro veces la huella del mismo dedo");

        cboDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "4" }));
        cboDia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDiaItemStateChanged(evt);
            }
        });

        cboMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Septiembre" }));
        cboMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMesItemStateChanged(evt);
            }
        });

        pnlHuella.setBackground(new java.awt.Color(255, 255, 255));
        pnlHuella.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlHuellaLayout = new javax.swing.GroupLayout(pnlHuella);
        pnlHuella.setLayout(pnlHuellaLayout);
        pnlHuellaLayout.setHorizontalGroup(
            pnlHuellaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgHuellaDig, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlHuellaLayout.setVerticalGroup(
            pnlHuellaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgHuellaDig, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel13.setText("Huella");

        cboHorario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matutino", "Vespertino", "Nocturno" }));

        jLabel4.setText("Jefe inmediato");

        jLabel1.setText("RFC");

        cboAño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1992" }));
        cboAño.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboAñoItemStateChanged(evt);
            }
        });

        jLabel2.setText("Departamento o Area");

        txtSalario.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlHuella, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                            .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(btnComprobar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel13))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(7, 7, 7)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtJefeInmediato)
                            .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboAño, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(cboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDia, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRFC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cboHorario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtSalario, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPuesto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel6)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cboHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJefeInmediato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel13)
                        .addGap(4, 4, 4)
                        .addComponent(pnlHuella, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(lblResultado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(8, 8, 8)
                        .addComponent(btnComprobar)))
                .addGap(26, 26, 26))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnComprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprobarActionPerformed
        JDialogComprobarHuella compHuellaDig=new JDialogComprobarHuella(new JFrame(), true, HuellaDig);
        compHuellaDig.setVisible(true);
    }//GEN-LAST:event_btnComprobarActionPerformed

    private void cboDiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDiaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDiaItemStateChanged

    private void cboMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMesItemStateChanged
        Calendario.CambiarComboMes(cboAño, cboMes, cboDia);
    }//GEN-LAST:event_cboMesItemStateChanged

    private void cboAñoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboAñoItemStateChanged
        Calendario.CambiarComboAño(cboMes, cboDia);
    }//GEN-LAST:event_cboAñoItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnComprobar;
    private javax.swing.JComboBox cboAño;
    private javax.swing.JComboBox cboDia;
    private javax.swing.JComboBox cboHorario;
    private javax.swing.JComboBox cboMes;
    public javax.swing.JLabel imgHuellaDig;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblEstado;
    public javax.swing.JLabel lblResultado;
    private javax.swing.JList lstLog;
    private javax.swing.JPanel pnlHuella;
    private javax.swing.JTextField txtDepartamento;
    private javax.swing.JTextField txtJefeInmediato;
    private javax.swing.JTextField txtPuesto;
    private javax.swing.JTextField txtRFC;
    private javax.swing.JTextField txtSalario;
    // End of variables declaration//GEN-END:variables
}
