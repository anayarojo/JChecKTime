
package JCHECKTIME.JframesEMPLEADOS;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.processing.DPFPTemplateStatus;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public final class JDialogComprobarHuella extends javax.swing.JDialog {

    DefaultListModel lstModelo=new DefaultListModel();
    
    //Varible que permite iniciar el dispositivo de lector de huella conectado
    // con sus distintos metodos.
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    //Varible que permite establecer las capturas de la huellas, para determina sus caracteristicas
    // y poder estimar la creacion de un template de la huella para luego poder guardarla
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();

    //Esta variable tambien captura una huella del lector y crea sus caracteristcas para auntetificarla
    // o verificarla con alguna guarda en la BD
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();

    //Variable que para crear el template de la huella luego de que se hallan creado las caracteriticas
    // necesarias de la huella si no ha ocurrido ningun problema
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "template";
    
    //Variables para la Huella digital
    private DPFPSample Sample = DPFPGlobal.getSampleFactory().createSample();
    private DPFPTemplate TemplateHuellaDig = DPFPGlobal.getTemplateFactory().createTemplate();
    
    public DPFPFeatureSet CaracteristicasSample;
    //public DPFPFeatureSet featuresverificacion;
    
    public JDialogComprobarHuella(java.awt.Frame parent, boolean modal, byte [] HuellaDig) {
        super(parent, modal);
        initComponents();
        
        this.setLocationRelativeTo(null); //Ventana centrada
        this.setTitle("Comprobar huella digital");
        this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Validar.png")).getImage());
        
        Sample.deserialize(HuellaDig);
        CaracteristicasSample=extraerCaracteristicas(Sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
        
        while(Reclutador.getTemplateStatus()== DPFPTemplateStatus.TEMPLATE_STATUS_INSUFFICIENT||
              Reclutador.getTemplateStatus()== DPFPTemplateStatus.TEMPLATE_STATUS_UNKNOWN){
        if (CaracteristicasSample != null)
            try
            {
            System.out.println("Las caracteristicas de la huella han sido creadas");
            Reclutador.addFeatures(CaracteristicasSample);// Agregar las caracteristicas de la huella a la plantilla a crear

            }
            catch (DPFPImageQualityException e) 
            {
            //JOptionPane
                //(Frame, Mensaje, Titulo, Tipo)
                // Tipo(0) Error
                // Tipo(1) Informacion
                // Tipo(2) Advertencia
                // Tipo(3) Interragacion

                JOptionPane.showMessageDialog(null, e.toString(), "DPFPImageQualityException" , 0);
            }}
        
        System.out.println(Reclutador.getTemplateStatus());
        TemplateHuellaDig = Reclutador.getTemplate();
        
        this.addComponentListener(new ComponentAdapter() {
			@Override public void componentShown(ComponentEvent e) {
				init();
				start();
			}
			@Override public void componentHidden(ComponentEvent e) {
				stop();
			}
			
		});
    }
    
    //region CAPTURAR HUELLA
    public void init()
	{
            Lector.addDataListener(new DPFPDataAdapter() {
            @Override public void dataAcquired(final DPFPDataEvent e) {
            SwingUtilities.invokeLater(new Runnable() {	public void run() {
            EnviarTexto("La Huella Digital ha sido Capturada");
            ProcesarCaptura(e.getSample());
            }});}
           });

           Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override public void readerConnected(final DPFPReaderStatusEvent e) {
            SwingUtilities.invokeLater(new Runnable() {	public void run() {
            EnviarTexto("El lector de huella digital esta Activo.");
            }});}
            @Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
            SwingUtilities.invokeLater(new Runnable() {	public void run() {
            EnviarTexto("El lectora de huella digital esta Desactivado o no Conectado.");
            }});}
           });

           Lector.addSensorListener(new DPFPSensorAdapter() {
            @Override public void fingerTouched(final DPFPSensorEvent e) {
            SwingUtilities.invokeLater(new Runnable() {	public void run() {
            EnviarTexto("El dedo ha sido colocado sobre el lector de huella.");
            }});}
            @Override public void fingerGone(final DPFPSensorEvent e) {
            SwingUtilities.invokeLater(new Runnable() {	public void run() {
            EnviarTexto("El dedo ha sido quitado del lector de Huella.");
            }});}
           });

           Lector.addErrorListener(new DPFPErrorAdapter(){
            public void errorReader(final DPFPErrorEvent e){
            SwingUtilities.invokeLater(new Runnable() {  public void run() {
            EnviarTexto("Error: "+e.getError());
            }});}
           });
	}

	protected void ProcesarCaptura(DPFPSample sample)
	{
            
            //Dibujar huella Capturada
            try
            {
                DibujarHuella(CrearImagenHuella(sample));
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
            
		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

		// Check quality of the sample and start verification if it's good
                
                DPFPVerificationResult result = null;
		if (features != null)
		{
			// Compare the feature set with our template
			result = Verificador.verify(features, TemplateHuellaDig);
			ActualizarEstado(result.getFalseAcceptRate());
                        
			if (result.isVerified())
				EnviarTexto("La huella registrada coincide");
			else
				EnviarTexto("La huella registrada NO coincide");
		}
                
                try
                {
                    if(result.getFalseAcceptRate()!=0)
                {
                    lblResultado.setForeground(new java.awt.Color(255,0,0));
                    Aviso("La verficacion de la huella tiene errores");
                }
                    else
                    {
                        lblResultado.setForeground(new java.awt.Color(0,176,80));
                        Aviso("Huella huella digital verificada");
                    }
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

	public void start()
	{
		Lector.startCapture();
		EnviarTexto("El lector de huellas se ha iniciado.");
	}

	public void stop()
	{
		Lector.stopCapture();
                EnviarTexto("El lector de huellas de ha detenido.");
	}

	public void Estado(String string) {
            
             try
            {
                 lblEstado.setText(string);
            }
            catch(Exception e)
            {
            
            }
	}
	public void Aviso(String string) {
            
            try
            {
                 lblResultado.setText(string);
            }
            catch(Exception e)
            {
            
            }
		
	}
	public void EnviarTexto(String string) {
            try
            {
                 lstModelo.addElement(string);
            }
            catch(Exception e)
            {
            
            }
	}
        
        public void DibujarHuella(Image image) {
            
            try
            {
                 imgHuellaDig.setIcon(new ImageIcon(
		 image.getScaledInstance(imgHuellaDig.getWidth(), imgHuellaDig.getHeight(), Image.SCALE_DEFAULT)));
            }
            catch(Exception e)
            {
            
            }
		
	}	
	public Image CrearImagenHuella(DPFPSample sample) {
            
            return DPFPGlobal.getSampleConversionFactory().createImage(sample);
            
	}
        
        protected DPFPFeatureSet  extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose)
	{
		DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
		try {
			return extractor.createFeatureSet(sample, purpose);
		} catch (DPFPImageQualityException e) {
			return null;
		}
	}
        
	protected DPFPFeatureSet extractFeatures(DPFPSample sample, DPFPDataPurpose purpose)
	{
		DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
		try {
			return extractor.createFeatureSet(sample, purpose);
		} catch (DPFPImageQualityException e) {
			return null;
		}
	}
        
        private void ActualizarEstado(int FAR)
	{
		// Show "False accept rate" value
		Estado(String.format("ERROR = %1$s", FAR));
                System.out.println(String.format("ERROR = %1$s", FAR));
	}
        
        public DPFPTemplate getTemplate() {
            return template;
        }

        public void setTemplate(DPFPTemplate template) {
            DPFPTemplate old = this.template;
            this.template = template;
            firePropertyChange(TEMPLATE_PROPERTY, old, template);
        }
      //endregion CAPTURAR HUELLA 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEstado = new javax.swing.JLabel();
        pnlHuella = new javax.swing.JPanel();
        imgHuellaDig = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblResultado = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstLog = new javax.swing.JList();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblEstado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstado.setText("ERROR=0");

        pnlHuella.setBackground(new java.awt.Color(255, 255, 255));
        pnlHuella.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlHuellaLayout = new javax.swing.GroupLayout(pnlHuella);
        pnlHuella.setLayout(pnlHuellaLayout);
        pnlHuellaLayout.setHorizontalGroup(
            pnlHuellaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgHuellaDig, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );
        pnlHuellaLayout.setVerticalGroup(
            pnlHuellaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgHuellaDig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel13.setText("Huella");

        lblResultado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblResultado.setForeground(new java.awt.Color(255, 0, 0));
        lblResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResultado.setText("Registre la huella de nuevo para verificarla");

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Aceptar.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        lstLog.setModel(lstModelo);
        jScrollPane1.setViewportView(lstLog);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHuella, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlHuella, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblResultado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAceptar)
                            .addComponent(btnCancelar))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        stop();
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        stop();
        this.dispose();
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
            java.util.logging.Logger.getLogger(JDialogComprobarHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogComprobarHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogComprobarHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogComprobarHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogComprobarHuella dialog = new JDialogComprobarHuella(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    public javax.swing.JLabel imgHuellaDig;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblEstado;
    public javax.swing.JLabel lblResultado;
    private javax.swing.JList lstLog;
    private javax.swing.JPanel pnlHuella;
    // End of variables declaration//GEN-END:variables
}
