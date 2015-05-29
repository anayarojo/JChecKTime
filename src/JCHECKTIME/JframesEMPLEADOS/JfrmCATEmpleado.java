
package JCHECKTIME.JframesEMPLEADOS;

import JCHECKTIME.EMPLEADOS.Empleados;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public final class JfrmCATEmpleado extends javax.swing.JFrame {

    //Arreglo contenedor de los empleados encontrados en la base de datos
    Empleados.strVisEmpleados[] varVisEmpleados;
    
    //Modelo de la lista de empleados
    DefaultTableModel lstModelo=new DefaultTableModel();
    
    public JfrmCATEmpleado() {
        initComponents();
        
        
        this.setLocationRelativeTo(null);
        this.setTitle("Catalogo de Empleados");
        this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Iconos/CatalogoEmpleados.png")).getImage());
        
        ListarEmpleados(txtNombre.getText());
    }
    
    public void ListarEmpleados(String FiltroNombre){
        
        String[] Titulos={"idEmpleado", "Nombre(s)", "Apellidos", "Departamento", "Puesto", "Horario", "Celular"};
        String[] Campo = new String[Titulos.length];
        
        lstModelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false; //Con esto conseguimos que la tabla no se pueda editar
            }
        };
        
        for (int i = 0; i < Titulos.length; i++) 
        {
            lstModelo.addColumn(Titulos[i]);
        }
        
        Empleados E=new Empleados();
        varVisEmpleados=E.ListarVisEmpleados(FiltroNombre);
        
        for (int i = 0; i < varVisEmpleados.length; i++) 
        {
            Campo[0]=varVisEmpleados[i].Obj1_idEmpleado+"";
            Campo[1]=varVisEmpleados[i].Obj2_NombreEmpleado;
            Campo[2]=varVisEmpleados[i].Obj3_Apellidos;
            Campo[3]=varVisEmpleados[i].Obj6_Departamento;
            Campo[4]=varVisEmpleados[i].Obj7_Puesto;
            Campo[5]=varVisEmpleados[i].Obj9_NombreHorario;
            Campo[6]=varVisEmpleados[i].Obj4_Celular;
            
            lstModelo.addRow(Campo);
        }
        lstLista.setModel(lstModelo);
        
        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);
        
        TableColumn columna;
        columna = lstLista.getColumn("idEmpleado");
        columna.setCellRenderer(centrar);
        columna.setPreferredWidth(-100);
        columna = lstLista.getColumn("Nombre(s)");
        columna.setPreferredWidth(250);
        columna = lstLista.getColumn("Apellidos");
        columna.setPreferredWidth(250);
        columna = lstLista.getColumn("Departamento");
        columna.setPreferredWidth(500);
        columna = lstLista.getColumn("Puesto");
        columna.setPreferredWidth(300);
        columna = lstLista.getColumn("Horario");
        columna.setPreferredWidth(400);
        columna = lstLista.getColumn("Celular");
        columna.setCellRenderer(centrar);
        columna.setPreferredWidth(200);
        
    }
    
    private void HabilitarBotones(){
        int Filas=lstLista.getSelectedRowCount();
        
        int Fila=lstLista.getSelectedRow();
        
        if(Fila!=-1)
        {
            if(Filas==1)
            {
                 btnModificar.setEnabled(true);
                 btnEliminar.setEnabled(true);
                 btnConsultar.setEnabled(true);
            }
            else
            {
                btnModificar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnConsultar.setEnabled(false);
            }
        }
        else
        {
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnConsultar.setEnabled(false);
        }
    }
    
    private int ObtenerSeleccion(){
        
        String idEmpleado = null;
        int Fila = 0;

        try 
        {
            Fila=lstLista.getSelectedRow();
            if(Fila==-1)
            {
                //JOptionPane
                //(Frame, Mensaje, Titulo, Tipo)
                // Tipo(0) Error
                // Tipo(1) Informacion
                // Tipo(2) Advertencia
                // Tipo(3) Interragacion

                JOptionPane.showMessageDialog(null, "Ningun empleado se esta seleccionado", "Exception" , 0);
            }
            else
            {
                lstModelo=(javax.swing.table.DefaultTableModel) lstLista.getModel();
                idEmpleado=(String) lstModelo.getValueAt(Fila, 0);
                
            }
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
        
        return Integer.parseInt(idEmpleado);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstLista = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        pnlBotones = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lstLista.setModel(lstModelo);
        lstLista.setFocusable(false);
        lstLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstListaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lstListaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lstListaMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(lstLista);

        jLabel1.setText("Nombre del Empleado");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        pnlBotones.setBackground(new java.awt.Color(153, 153, 153));
        pnlBotones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnNuevo.setBackground(new java.awt.Color(153, 153, 153));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ADDEmpleado.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(153, 153, 153));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MODEmpleado.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(153, 153, 153));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/DELEmpleado.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnConsultar.setBackground(new java.awt.Color(153, 153, 153));
        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MOSEmpleado.png"))); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(153, 153, 153));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Aceptar.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnConsultar)
                    .addComponent(btnCerrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 14, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addContainerGap())
            .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        
        ListarEmpleados(txtNombre.getText());
    }//GEN-LAST:event_txtNombreKeyReleased

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        JfrmADDEmpleado RegistrarEmpleado=new JfrmADDEmpleado();
        RegistrarEmpleado.setVisible(true);
        
        if(RegistrarEmpleado.isVisible()==false)
        {
            //Actualizar la lista de empleados
            txtNombreKeyReleased(null);
            HabilitarBotones();
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int id = ObtenerSeleccion();
        System.out.println(id);
        JfrmMODEmpleado ModificarEmpleado=new JfrmMODEmpleado(this, id);
        ModificarEmpleado.setVisible(true);
        
        if(ModificarEmpleado.isVisible()==false)
        {
            //Actualizar la lista de empleados
            txtNombreKeyReleased(null);
            HabilitarBotones();
        }
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int id = ObtenerSeleccion();
        System.out.println(id);
        JfrmDELEmpleado EliminarEmpleado=new JfrmDELEmpleado(id);
        EliminarEmpleado.setVisible(true);
        
        if(EliminarEmpleado.isVisible()==false)
        {
            //Actualizar la lista de empleados
            txtNombreKeyReleased(null);
            HabilitarBotones();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        int id = ObtenerSeleccion();
        System.out.println(id);
        JfrmMOSEmpleado ConsultarEmpleado=new JfrmMOSEmpleado(id);
        ConsultarEmpleado.setVisible(true);
        
        if(ConsultarEmpleado.isVisible()==false)
        {
            //Actualizar la lista de empleados
            txtNombreKeyReleased(null);
            HabilitarBotones();
        }
        
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void lstListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstListaMouseClicked
        HabilitarBotones();
    }//GEN-LAST:event_lstListaMouseClicked

    private void lstListaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstListaMouseEntered
        HabilitarBotones();
    }//GEN-LAST:event_lstListaMouseEntered

    private void lstListaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstListaMouseExited
        HabilitarBotones();
    }//GEN-LAST:event_lstListaMouseExited

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
            java.util.logging.Logger.getLogger(JfrmCATEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfrmCATEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfrmCATEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfrmCATEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfrmCATEmpleado().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable lstLista;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
