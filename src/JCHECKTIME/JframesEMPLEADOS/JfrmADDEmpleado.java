
package JCHECKTIME.JframesEMPLEADOS;

//Recursos locales
import JCHECKTIME.EMPLEADOS.Empleados;
import JCHECKTIME.EMPLEADOS.Empleados.strEmpleados;
import JCHECKTIME.RESULTADO;
import JCHECKTIME.RESULTADO.enumResultados;

//Librerias para la huella digital
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
import com.digitalpersona.onetouch.verification.DPFPVerification;
import org.apache.commons.codec.binary.Base64;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public final class JfrmADDEmpleado extends javax.swing.JFrame {

    //Estructura contenedora de los datos del empleado
    public strEmpleados varEmpleado;
    
    boolean HuellaLista=false;
    boolean HuellaComprobada=false;
    int contador=0;
    
    public byte[] HuellaDigital;
    
    //Variables para la Huella digital
    public String AvisoHuella;
    public String EstadoHuella="Faltan 4 Huellas para completar el registro";
    
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
    
    //Variables de las paginas del formulario de registro de empleados
    pnlADDEmpleadoPagina1 Pagina1;
    pnlADDEmpleadoPagina2 Pagina2;
    
    public JfrmADDEmpleado() {
        initComponents();
        
        this.setLocationRelativeTo(null); //Ventana centrada
        this.setTitle("Registrar Empleado");
        this.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Iconos/RegistrarEmpleado.png")).getImage());
        
        InicializarEstructura();
        
        MostrarPagina1();
        
        //Eventos del lector de huella
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
    
    public void MostrarPagina1(){
        
        pnlPrincipal.removeAll();
        Pagina1=new pnlADDEmpleadoPagina1(pnlPrincipal.getSize());
        pnlPrincipal.add(Pagina1, BorderLayout.CENTER);
        //Pagina1.CapturarDatos(this);
        Pagina1.CargarDatos(this);
        pnlPrincipal.repaint();
        
        btnAnterior.setVisible(false);
        btnSiguiente.setVisible(true);
        btnCompletarRegistro.setVisible(false);
        btnCancelar.setVisible(true);
    }
    
    public void MostrarPagina2(){
        
        pnlPrincipal.removeAll();
        Pagina2=new pnlADDEmpleadoPagina2(pnlPrincipal.getSize());
        pnlPrincipal.add(Pagina2, BorderLayout.CENTER);
        pnlPrincipal.repaint();
        
        btnAnterior.setVisible(true);
        btnSiguiente.setVisible(false);
        btnCompletarRegistro.setVisible(true);
        btnCancelar.setVisible(true);
    }
    
    public void InicializarEstructura(){
        
        varEmpleado=new strEmpleados();
        
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
        varEmpleado.Obj25_HuellaDig=null;
        varEmpleado.Obj26_RFC="";
        varEmpleado.Obj27_DESISTEMA=false;
        varEmpleado.Obj28_ELIMINADO=false;
        varEmpleado.Obj29_FECHAHORACAMBIO="";
        varEmpleado.Obj30_IDUSUARIO="";
    }
    
    
    private RESULTADO.enumResultados ValidarDatos(){
        
        String Mensaje="Es necesario que rellene los siguientes campos para completar el registro: \n";
        int contErr=0;
        
        if(varEmpleado.Obj2_NombreEmpleado.equals("")||varEmpleado.Obj2_NombreEmpleado.equals(" "))
        {
            Mensaje+="Nombre(s) \n";
            contErr++;
        }
        
        if(varEmpleado.Obj3_Apellidos.equals("")||varEmpleado.Obj3_Apellidos.equals(""))
        {
            Mensaje+="Apellidos \n";
            contErr++;
        }
        
        if(varEmpleado.Obj5_Celular.equals("")||varEmpleado.Obj5_Celular.equals(""))
        {
            Mensaje+="Celular \n";
            contErr++;
        }
        
        if(varEmpleado.Obj6_Telefono.equals("")||varEmpleado.Obj6_Telefono.equals(""))
        {
            Mensaje+="Telefono \n";
            contErr++;
        }
        
        if(varEmpleado.Obj7_CorreoElectronico.equals("")||varEmpleado.Obj7_CorreoElectronico.equals(""))
        {
            Mensaje+="Correo electronico \n";
            contErr++;
        }
        
        if(varEmpleado.Obj8_Ciudad.equals("")||varEmpleado.Obj8_Ciudad.equals(""))
        {
            Mensaje+="Ciudad \n";
            contErr++;
        }
        
        if(varEmpleado.Obj9_Colonia.equals("")||varEmpleado.Obj9_Colonia.equals(""))
        {
            Mensaje+="Colonia \n";
            contErr++;
        }
        
        if(varEmpleado.Obj10_NumeroSeguro.equals("")||varEmpleado.Obj10_NumeroSeguro.equals(""))
        {
            Mensaje+="Numero de Seguro \n";
            contErr++;
        }
        
        if(varEmpleado.Obj11_CURP.equals("")||varEmpleado.Obj11_CURP.equals(""))
        {
            Mensaje+="CURP \n";
            contErr++;
        }
        
        if(varEmpleado.Obj12_EstadoCivil.equals("")||varEmpleado.Obj12_EstadoCivil.equals(""))
        {
            Mensaje+="EstadoCivi \n";
            contErr++;
        }
        
        if(varEmpleado.Obj13_Localidad.equals("")||varEmpleado.Obj13_Localidad.equals(""))
        {
            Mensaje+="Localidad \n";
            contErr++;
        }
        
        if(varEmpleado.Obj14_Direccion.equals("")||varEmpleado.Obj14_Direccion.equals(""))
        {
            Mensaje+="Direccion \n";
            contErr++;
        }
        
        if(varEmpleado.Obj15_Alergias.equals("")||varEmpleado.Obj15_Alergias.equals(""))
        {
            Mensaje+="Alergias \n";
            contErr++;
        }
        
        if(varEmpleado.Obj16_EmergInfo.equals("")||varEmpleado.Obj16_EmergInfo.equals(""))
        {
            Mensaje+="En caso de accidente informar a \n";
            contErr++;
        }
        
        if(varEmpleado.Obj17_EmergTel.equals("")||varEmpleado.Obj17_EmergTel.equals(""))
        {
            Mensaje+="Telefono para emergencias \n";
            contErr++;
        }
        
        if(varEmpleado.Obj18_TipoDeSangre.equals("")||varEmpleado.Obj18_TipoDeSangre.equals(""))
        {
            Mensaje+="Tipo de sangre \n";
            contErr++;
        }
        
        if(varEmpleado.Obj19_Departamento.equals("")||varEmpleado.Obj19_Departamento.equals(""))
        {
            Mensaje+="Departamento \n";
            contErr++;
        }
        
        if(varEmpleado.Obj20_Puesto.equals("")||varEmpleado.Obj20_Puesto.equals(""))
        {
            Mensaje+="Puesto \n";
            contErr++;
        }
        
        if(varEmpleado.Obj21_JefeInmediato.equals("")||varEmpleado.Obj21_JefeInmediato.equals(""))
        {
            Mensaje+="JefeInmediato \n";
            contErr++;
        }
        
        if(varEmpleado.Obj23_Salario==0)
        {
            Mensaje+="Salario \n";
            contErr++;
        }
        
        if(varEmpleado.Obj25_HuellaDig==null)
        {
            Mensaje+="Huella \n";
            contErr++;
        }
        
        if(varEmpleado.Obj26_RFC.equals("")||varEmpleado.Obj26_RFC.equals(""))
        {
            Mensaje+="RFC \n";
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
    
    private enumResultados RegistrarEmpleado(){
        
        Empleados E=new Empleados();
        
        if(E.RegistrarEmpleado(varEmpleado)==enumResultados.OperacionCorrecta)
        {
            //JOptionPane
           //(Frame, Mensaje, Titulo, Tipo)
           // Tipo(0) Error
           // Tipo(1) Informacion
           // Tipo(2) Advertencia
           // Tipo(3) Interragacion
           JOptionPane.showMessageDialog(null, "El registro se completo correctamente", "Notificacion" , 1);
           return enumResultados.OperacionCorrecta;
        }
        
        return enumResultados.ErrorDeSentencia;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        pnlBotones = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnCompletarRegistro = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );

        pnlBotones.setBackground(new java.awt.Color(153, 153, 153));
        pnlBotones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnCancelar.setBackground(new java.awt.Color(153, 153, 153));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCompletarRegistro.setBackground(new java.awt.Color(153, 153, 153));
        btnCompletarRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Aceptar.png"))); // NOI18N
        btnCompletarRegistro.setText("Completar registro");
        btnCompletarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompletarRegistroActionPerformed(evt);
            }
        });

        btnSiguiente.setBackground(new java.awt.Color(153, 153, 153));
        btnSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Siguiente.png"))); // NOI18N
        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAnterior.setBackground(new java.awt.Color(153, 153, 153));
        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Anterior.png"))); // NOI18N
        btnAnterior.setText("Anterior");
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
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(btnAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiguiente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCompletarRegistro)
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
                    .addComponent(btnCompletarRegistro)
                    .addComponent(btnSiguiente)
                    .addComponent(btnAnterior))
                .addContainerGap())
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
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        Pagina1.CapturarDatos(this);
        MostrarPagina2();
        Pagina2.CargarDatos(this);
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        Pagina2.CapturarDatos(this);
        MostrarPagina1();
        Pagina1.CargarDatos(this);
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnCompletarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompletarRegistroActionPerformed
        Pagina2.CapturarDatos(this);
        
        if(ValidarDatos()==enumResultados.DatosValidados){
            
            if(RegistrarEmpleado()==enumResultados.OperacionCorrecta)
            {
                this.dispose();
            }
        }
    }//GEN-LAST:event_btnCompletarRegistroActionPerformed
    
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

        public DPFPFeatureSet featuresinscripcion;
        public DPFPFeatureSet featuresverificacion;
        
        
        
	protected void ProcesarCaptura(DPFPSample sample)
	{
            // Dibujar huella digital 
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
            
            // Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
            featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

            // Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
            featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
            
            
            // Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
            if (featuresinscripcion != null)
                try{
                System.out.println("Las caracteristicas de la huella han sido creadas");
                Reclutador.addFeatures(featuresinscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear

                // Dibuja la huella dactilar capturada.
                //Image image=CrearImagenHuella(sample);
                //DibujarHuella(image);

                
                //btnVerificar.setEnabled(true);
                //btnIdentificar.setEnabled(true);

                }catch (DPFPImageQualityException e) {
                System.err.println("Error: "+e.getMessage());
                }
            
            finally {
            EstadoHuellas();
            // Comprueba si la plantilla se ha creado.
               switch(Reclutador.getTemplateStatus())
               {
                   case TEMPLATE_STATUS_READY:	// informe de éxito y detiene  la captura de huellas
                   stop();
                   setTemplate(Reclutador.getTemplate());
                   
                   
                   /*
                   //METODO CONVERSION BYTE TO STRING
                   byte[] a = new byte[1632];
                   a=Reclutador.getTemplate().serialize();
                   String basestring = Base64.encodeBase64String(a);
                   //String basestring = Convert.ToBase64String(a);
                   */
                   
                   //METODO CONVERSION BYTE TO STRING
                   
                   EnviarTexto("Huella registrada lista para comprobar");
                   
                   Aviso("Huella registrada lista para comprobar");
                   Pagina2.lblResultado.setForeground(new java.awt.Color(0,176,80));
                   Pagina2.btnComprobar.setEnabled(true);
                   HuellaLista=true;
                   break;

                   case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
                   Reclutador.clear();
                   stop();
                   EstadoHuellas();
                   setTemplate(null);
                   JOptionPane.showMessageDialog(this, "La huella no pudo ser registrada, Repita el proceso", "Error - Registro de huella", JOptionPane.ERROR_MESSAGE);
                   
                   Aviso("Registre nuevamente la nuella");
                   Pagina2.lblResultado.setForeground(new java.awt.Color(255,0,0));
                   Pagina2.btnComprobar.setEnabled(false);
                   HuellaLista=false;
                   start();
                   break;
               }
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
	
	protected DPFPFeatureSet  extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose)
	{
		DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
		try {
			return extractor.createFeatureSet(sample, purpose);
		} catch (DPFPImageQualityException e) {
			return null;
		}
	}
        
        public void DibujarHuella(Image image) {
            
            try
            {
                 Pagina2.imgHuellaDig.setIcon(new ImageIcon(
		 image.getScaledInstance(Pagina2.imgHuellaDig.getWidth(), Pagina2.imgHuellaDig.getHeight(), Image.SCALE_DEFAULT)));
            }
            catch(Exception e){}
		
	}	
	public Image CrearImagenHuella(DPFPSample sample) {
            
            byte[] a = new byte[1632];
            a=sample.serialize();
            String basestring = Base64.encodeBase64String(a);
            
            this.varEmpleado.Obj25_HuellaDig=basestring;
            
            //String basestring = Convert.ToBase64String(a);
            
            this.HuellaDigital=sample.serialize();
            this.Pagina2.HuellaDig=sample.serialize();
            return DPFPGlobal.getSampleConversionFactory().createImage(sample);
            
	}
        
        public  void Aviso(String string){
            try
            {
                 Pagina2.lblResultado.setText(string);
            }
            catch(Exception e){ }   
        }
        
        public  void EstadoHuellas(){
            try
            {
                int Faltantes = Reclutador.getFeaturesNeeded();
                EstadoHuella =((Faltantes==1)?"Falta ":"Faltan ")+Faltantes+((Faltantes==1)?" Huella ":" Huellas ")+ " para completar el registro";
                Pagina2.lblEstado.setText(EstadoHuella);
            }
            catch(Exception e){ }  
        }

        public void EnviarTexto(String string) {
            try
            {
                 Pagina2.lstModelo.addElement(string);
            }
            catch(Exception e){ }  
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
            java.util.logging.Logger.getLogger(JfrmADDEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfrmADDEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfrmADDEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfrmADDEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfrmADDEmpleado().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCompletarRegistro;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
