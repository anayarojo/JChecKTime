/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCHECKTIME.JframeHORARIOS;

import JCHECKTIME.HORARIOS.Horario;
import JCHECKTIME.HORARIOS.Horario.strHorario;
import JCHECKTIME.RESULTADO;
import JCHECKTIME.RESULTADO.enumResultados;
import javax.swing.JOptionPane;

/**
 *
 * @author Raul
 */
public class JfrmADDHorario extends javax.swing.JFrame {
    public strHorario varHorario;

    /**
     * Creates new form JfrmADDHorario
     */
    public JfrmADDHorario() {
        initComponents();
    }

  public void InicializarEstructura(){

varHorario=new strHorario();
varHorario.Obj2_Nombre =txtNombre.getText();
varHorario.Obj4_Descripcion = txtDescripcion.getText();
varHorario.Obj9_LunesE=String.valueOf(cbxEL.getSelectedItem());
varHorario.Obj10_LunesS= String.valueOf(cbxSL.getSelectedItem());
varHorario.Obj11_MartesE=String.valueOf(cbxEMa.getSelectedItem());
varHorario.Obj12_MartesS=String.valueOf(cbxSMa.getSelectedItem());
varHorario.Obj13_MiercolesE=String.valueOf(cbxEMi.getSelectedItem());
varHorario.Obj14_MiercolesS=String.valueOf(cbxSMi.getSelectedItem());
  varHorario.Obj15_JuevesE=String.valueOf(cbxEJ.getSelectedItem());
   varHorario.Obj16_JuevesS=String.valueOf(cbxSJ.getSelectedItem());
   varHorario.Obj17_ViernesE=String.valueOf(cbxEV.getSelectedItem());
   varHorario.Obj18_ViernesS=String.valueOf(cbxSV.getSelectedItem());
   varHorario.Obj19_SabadoE=String.valueOf(cbxES.getSelectedItem());
   varHorario.Obj20_SabadoS=String.valueOf(cbxSS.getSelectedItem());
   varHorario.Obj21_DomingoE=String.valueOf(cbxED.getSelectedItem());
    varHorario.Obj22_DomingoS=String.valueOf(cbxSD.getSelectedItem());


  }
  private RESULTADO.enumResultados ValidarDatos(){

     String Mensaje="Es necesario que rellene los siguientes campos para completar el registro: \n";
        int contErr=0;

        if(varHorario.Obj2_Nombre.equals("")||varHorario.Obj2_Nombre.equals(" "))
        {
            Mensaje+="Nombre(s) \n";
            contErr++;
        }
          if(contErr!=0)
       {
           //JOptionPane
           //(Frame, Mensaje, Titulo, Tipo)
           // Tipo(0) Error
           // Tipo(1) Informacion
           // Tipo(2) Advertencia
           // Tipo(3) Interragacion
           JOptionPane.showMessageDialog(null, Mensaje, "Falta rellenar campos" , 0);
           return enumResultados.CamposVacios;
       }
       else
       {
           return enumResultados.DatosValidados;
       }

  }
    private void RegistrarHorario(){

        Horario E=new Horario();

        if(E.RegistrarHorario(varHorario)==enumResultados.OperacionCorrecta)
        {
            //JOptionPane
           //(Frame, Mensaje, Titulo, Tipo)
           // Tipo(0) Error
           // Tipo(1) Informacion
           // Tipo(2) Advertencia
           // Tipo(3) Interragacion
           JOptionPane.showMessageDialog(null, "El registro se completo correctamente", "Notificacion" , 1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        cbxEL = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxSL = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        chkLunes = new javax.swing.JCheckBox();
        chkMartes = new javax.swing.JCheckBox();
        chkMiercoles = new javax.swing.JCheckBox();
        chkJueves = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        chkViernes = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        chkSabado = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        chkDomingo = new javax.swing.JCheckBox();
        cbxSMa = new javax.swing.JComboBox();
        cbxSMi = new javax.swing.JComboBox();
        cbxSV = new javax.swing.JComboBox();
        cbxSS = new javax.swing.JComboBox();
        cbxEMa = new javax.swing.JComboBox();
        cbxEMi = new javax.swing.JComboBox();
        cbxEV = new javax.swing.JComboBox();
        cbxES = new javax.swing.JComboBox();
        btnCancelar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        cbxEJ = new javax.swing.JComboBox();
        cbxSJ = new javax.swing.JComboBox();
        cbxED = new javax.swing.JComboBox();
        cbxSD = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre");

        cbxEL.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxEL.setSelectedIndex(7);

        jLabel2.setText("Entrada");

        jLabel3.setText("Salida");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel4.setText("Descripcion");

        jLabel5.setText("Lunes");

        cbxSL.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxSL.setSelectedIndex(14);

        jLabel6.setText("Martes");

        jLabel7.setText("Miercoles");

        jLabel8.setText("Laborable");

        chkLunes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkLunesStateChanged(evt);
            }
        });

        chkMartes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMartesStateChanged(evt);
            }
        });

        chkMiercoles.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMiercolesStateChanged(evt);
            }
        });

        chkJueves.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkJuevesStateChanged(evt);
            }
        });

        jLabel9.setText("Jueves");

        chkViernes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkViernesStateChanged(evt);
            }
        });

        jLabel10.setText("Viernes");

        jLabel11.setText("Sabado");

        chkSabado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkSabadoStateChanged(evt);
            }
        });

        jLabel12.setText("Domingo");

        chkDomingo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkDomingoStateChanged(evt);
            }
        });

        cbxSMa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxSMa.setSelectedIndex(14);

        cbxSMi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m." }));
        cbxSMi.setSelectedIndex(14);

        cbxSV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxSV.setSelectedIndex(14);

        cbxSS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxSS.setSelectedIndex(14);

        cbxEMa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxEMa.setSelectedIndex(7);

        cbxEMi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxEMi.setSelectedIndex(7);

        cbxEV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxEV.setSelectedIndex(7);

        cbxES.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxES.setSelectedIndex(7);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Validar.png"))); // NOI18N
        btnRegistrar.setText("Registrar horario");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        cbxEJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxEJ.setSelectedIndex(7);

        cbxSJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxSJ.setSelectedIndex(14);

        cbxED.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxED.setSelectedIndex(7);

        cbxSD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00 a.m.", "2:00 a.m.", "3:00 a.m.", "4:00 a.m.", "5:00 a.m.", "6:00 a.m.", "7:00 a.m.", "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m", "1:00 p.m.", "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m.", "6:00 p.m.", "7:00 p.m.", "8:00 p.m.", "9:00 p.m.", "10:00 p.m.", "11:00 p.m.", "12:00 a.m" }));
        cbxSD.setSelectedIndex(14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                            .addComponent(txtNombre)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxSL, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxEL, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbxEMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbxEMi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbxSMa, 0, 1, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbxSMi, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxEJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxSJ, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnRegistrar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancelar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cbxEV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbxES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cbxSV, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbxSS, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxED, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxSD, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkLunes)
                                            .addComponent(jLabel5))
                                        .addGap(51, 51, 51)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(chkMartes)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(189, 189, 189)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(chkMiercoles)))))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkJueves)
                                    .addComponent(jLabel9))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkViernes)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkSabado)
                                    .addComponent(jLabel11))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(chkDomingo)))
                                .addGap(24, 24, 24)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkMiercoles)
                            .addComponent(chkMartes)
                            .addComponent(chkLunes)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkViernes))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkJueves))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkSabado))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkDomingo)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbxEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxEMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxEMi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxEV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbxSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxSMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxEJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxSJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxSMi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxED, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnRegistrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
   if(ValidarDatos()==enumResultados.DatosValidados){

            RegistrarHorario();
            this.dispose();
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void chkLunesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkLunesStateChanged
     if(chkLunes.isSelected()==true){
     cbxEL.disable();
     cbxSL.disable();
     }
     else{
        cbxEL.enable();
        cbxSL.enable();
     }
    }//GEN-LAST:event_chkLunesStateChanged

    private void chkMartesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMartesStateChanged
      if(chkMartes.isSelected()==true){
     cbxEMa.disable();
     cbxSMa.disable();
     }
     else{
        cbxEMa.enable();
        cbxSMa.enable();
     }
    }//GEN-LAST:event_chkMartesStateChanged

    private void chkMiercolesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMiercolesStateChanged
         if(chkMiercoles.isSelected()==true){
     cbxEMi.disable();
     cbxSMi.disable();
     }
     else{
        cbxEMi.enable();
        cbxSMi.enable();
     }
    }//GEN-LAST:event_chkMiercolesStateChanged

    private void chkJuevesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkJuevesStateChanged
          if(chkJueves.isSelected()==true){
     cbxEJ.disable();
     cbxSJ.disable();
     }
     else{
        cbxEJ.enable();
        cbxSJ.enable();
     }
    }//GEN-LAST:event_chkJuevesStateChanged

    private void chkViernesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkViernesStateChanged
          if(chkViernes.isSelected()==true){
     cbxEV.disable();
     cbxSV.disable();
     }
     else{
        cbxEV.enable();
        cbxSV.enable();
     }
    }//GEN-LAST:event_chkViernesStateChanged

    private void chkSabadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkSabadoStateChanged
           if(chkSabado.isSelected()==true){
     cbxES.disable();
     cbxSS.disable();
     }
     else{
        cbxES.enable();
        cbxSS.enable();
     }
    }//GEN-LAST:event_chkSabadoStateChanged

    private void chkDomingoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkDomingoStateChanged
     if(chkDomingo.isSelected()==true){
     cbxED.disable();
     cbxSD.disable();
     }
     else{
        cbxED.enable();
        cbxSD.enable();
     }        // TODO add your handling code here:
    }//GEN-LAST:event_chkDomingoStateChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JfrmADDHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfrmADDHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfrmADDHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfrmADDHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfrmADDHorario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox cbxED;
    private javax.swing.JComboBox cbxEJ;
    private javax.swing.JComboBox cbxEL;
    private javax.swing.JComboBox cbxEMa;
    private javax.swing.JComboBox cbxEMi;
    private javax.swing.JComboBox cbxES;
    private javax.swing.JComboBox cbxEV;
    private javax.swing.JComboBox cbxSD;
    private javax.swing.JComboBox cbxSJ;
    private javax.swing.JComboBox cbxSL;
    private javax.swing.JComboBox cbxSMa;
    private javax.swing.JComboBox cbxSMi;
    private javax.swing.JComboBox cbxSS;
    private javax.swing.JComboBox cbxSV;
    private javax.swing.JCheckBox chkDomingo;
    private javax.swing.JCheckBox chkJueves;
    private javax.swing.JCheckBox chkLunes;
    private javax.swing.JCheckBox chkMartes;
    private javax.swing.JCheckBox chkMiercoles;
    private javax.swing.JCheckBox chkSabado;
    private javax.swing.JCheckBox chkViernes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
