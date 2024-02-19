
package Vista;

import Controlador.Dao.Modelo.PozoDao;
import Controlador.TDA.ListaDinamica.Excepcion.ListaVacia;
import Controlador.TDA.ListaDinamica.ListaDinamica;
import Modelo.Pozo;
import Modelo.Ubicacion;
import Vista.ModeloTabla.ModeloTablaPozo;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Victor
 */
public class VistaRegistroPozo extends javax.swing.JFrame {
    PozoDao pozoControlDao = new PozoDao(Modelo.Pozo.class);
    ModeloTablaPozo mtp = new ModeloTablaPozo();
    ListaDinamica<Pozo> listaPozo = new ListaDinamica<>();
    private File ImagenPortada;
    private File ImagenLogo;
    private String rutaImagenGuardadaPortada;
    private String rutaImagenGuardadaLogo;

    /**
     * Creates new form VistaRegistroPizzeria
     */
    public VistaRegistroPozo() {
        initComponents();
        this.setLocationRelativeTo(null);
        CargarTabla();
    }
        
    private void CargarTabla() {
        mtp.setPozoTabla(pozoControlDao.getListaPozo());
        tblPozo.setModel(mtp);
        tblPozo.updateUI();
    }
        
    private void Limpiar() throws ListaVacia {
        txtNombre.setText("");
        txtDueño.setText("");
        txtLongitud.setText("");
        txtLatitud.setText("");
        txtLogo.setText("");
        txtPortada.setText("");
        pozoControlDao.setPozo(null);
        CargarTabla();
    }
        
    private void Guardar() throws ListaVacia {

        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Porfavor ingrese el nombre del pozo");
        }
        else if(txtDueño.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Porfavor ingrese el dueño del pozo");
        }
        else if(txtLongitud.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Porfavor ingrese la longitud del pozo");
        }
        else if(txtLatitud.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Porfavor ingrese la latitud del pozo");
        }
        else if(txtLogo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Porfavor ingrese el logo del pozo");
        }
        else if(txtPortada.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Porfavor ingrese la portada del pozo");
        }
        else{
            Integer idPozo = listaPozo.getLongitud()+1;
            String Nombre = txtNombre.getText();
            String Duenio = txtDueño.getText();
            String Logo = txtLogo.getText();
            String Portada = txtPortada.getText();

            Double Longitud = Double.valueOf(txtLongitud.getText());
            Double Latitud = Double.valueOf(txtLatitud.getText());

            Pozo nuevaPozo = new Pozo();
            nuevaPozo.setIdPozo(idPozo);
            nuevaPozo.setNombre(Nombre);
            nuevaPozo.setDuenio(Duenio);
            nuevaPozo.setLogo(Logo);
            nuevaPozo.setPortada(Portada);

            Ubicacion nuevaUbicacion = new Ubicacion();
            nuevaUbicacion.setIdUbicacion(idPozo);
            nuevaUbicacion.setLongitud(Longitud);
            nuevaUbicacion.setLatitud(Latitud);

            nuevaPozo.setUbicacionPozo(nuevaUbicacion);
            pozoControlDao.setPozo(nuevaPozo);
            
            if (pozoControlDao.Persist()) {
                JOptionPane.showMessageDialog(null, "POZO GUARDADA EXISTOSAMENTE", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
//                CargarTabla();
//                Limpiar();
                pozoControlDao.setPozo(null);
            } 
            else {
                JOptionPane.showMessageDialog(null, "NO SE PUEDE GUARDAR", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
            }
            Limpiar();
        }
    }
    
    private void Seleccionar(){
        int fila = tblPozo.getSelectedRow();
        if(fila < 0){
            JOptionPane.showMessageDialog(null, "Escoga un registro");
        }
        else{
            try {
                pozoControlDao.setPozo(mtp.getPozoTabla().getInfo(fila));
                
                txtNombre.setText(pozoControlDao.getPozo().getNombre());
                txtDueño.setText(pozoControlDao.getPozo().getDuenio());
                txtLogo.setText(pozoControlDao.getPozo().getLogo());
                txtPortada.setText(pozoControlDao.getPozo().getPortada());
                txtLongitud.setText(pozoControlDao.getPozo().getUbicacionPozo().getLongitud().toString());
                txtLatitud.setText(pozoControlDao.getPozo().getUbicacionPozo().getLatitud().toString());
                
            } 
            catch (Exception e) {
                
            }
        }
    }
    
    private File CargarFoto() throws Exception {
        File obj = null;
        JFileChooser choosser = new JFileChooser();

        String downloadsPath = System.getProperty("user.home") + File.separator + "Downloads";
        choosser.setCurrentDirectory(new File(downloadsPath));

        choosser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes", "jpg", "png", "jpeg");
        choosser.addChoosableFileFilter(filter);

        Integer resp = choosser.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            obj = choosser.getSelectedFile();
            System.out.println("Foto cargada correctamente");
        } 
        else {
            System.out.println("No se puede cargar la imagen");
        }
        return obj;
    }
    
    private void mostrarImagenEnVentana(String rutaImagen) {
        try {
            if (rutaImagen != null && !rutaImagen.isEmpty()) {
                VistaPresentarImagen f = new VistaPresentarImagen();
                f.mostrarImagen(rutaImagen);
                f.setVisible(true);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al mostrar la imagen", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void copiarArchivo(File origen, File destino) throws Exception {
        Files.copy(origen.toPath(),(destino).toPath(),StandardCopyOption.REPLACE_EXISTING);
    }
    
    public static String extension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtLongitud = new javax.swing.JTextField();
        txtLatitud = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDueño = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtLogo = new javax.swing.JTextField();
        txtPortada = new javax.swing.JTextField();
        btnSubirLogo = new javax.swing.JButton();
        btnSubirPortada = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPozo = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRO DE POZO");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Coordenada");

        jLabel5.setText("Longitud");

        jLabel6.setText("Latitud");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLongitud, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(txtLatitud))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Nombre");

        jLabel3.setText("Dueño");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Informacion");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtDueño))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDueño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Imagenes");

        jLabel9.setText("Logo");

        jLabel10.setText("Portada");
        jLabel10.setToolTipText("");

        txtLogo.setEditable(false);
        txtLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLogoMouseClicked(evt);
            }
        });

        txtPortada.setEditable(false);
        txtPortada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPortadaMouseClicked(evt);
            }
        });

        btnSubirLogo.setText("SUBIR");
        btnSubirLogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirLogoActionPerformed(evt);
            }
        });

        btnSubirPortada.setText("SUBIR");
        btnSubirPortada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirPortadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLogo)
                            .addComponent(txtPortada))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSubirLogo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSubirPortada, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubirLogo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPortada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubirPortada))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("LISTA DE POZOS");

        tblPozo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPozo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPozoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPozo);

        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setToolTipText("");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jButton1.setText("VER ADYACENCIAS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 966, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubirLogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirLogoActionPerformed
        
        try {
            ImagenLogo = CargarFoto();
            if (ImagenLogo != null) {
                txtLogo.setText(ImagenLogo.getAbsolutePath());
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar la imagen", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnSubirLogoActionPerformed

    private void btnSubirPortadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirPortadaActionPerformed
        
        try {
            ImagenPortada = CargarFoto();
            if (ImagenPortada != null) {
                txtPortada.setText(ImagenPortada.getAbsolutePath());
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar la imagen", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnSubirPortadaActionPerformed

    private void txtLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLogoMouseClicked
        
        if (evt.getClickCount() == 2) {
            mostrarImagenEnVentana(txtLogo.getText());
        }
        
    }//GEN-LAST:event_txtLogoMouseClicked

    private void txtPortadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPortadaMouseClicked
        
        if (evt.getClickCount() == 2) {
            mostrarImagenEnVentana(txtPortada.getText());
        }
        
    }//GEN-LAST:event_txtPortadaMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        try {
            Guardar();
        } 
        catch (Exception e) {
        
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        int fila = tblPozo.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoga un registro");
        } 
        else {
            if (txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Porfavor ingrese el nombre del pozo");
            } 
            else if (txtDueño.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Porfavor ingrese el dueño del pozo");
            } 
            else if (txtLongitud.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Porfavor ingrese la longitud del pozo");
            }
            else if (txtLatitud.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Porfavor ingrese la latitud del pozo");
            } 
            else if (txtLogo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Porfavor ingrese el logo del pozo");
            } 
            else if (txtPortada.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Porfavor ingrese la portada del pozo");
            }
            else {
                Integer IdPersona = pozoControlDao.getPozo().getIdPozo();
                String Nombre = txtNombre.getText();
                String Duenio = txtDueño.getText();
                String Logo = txtLogo.getText();
                String Portada = txtPortada.getText();

                Double Longitud = Double.valueOf(txtLongitud.getText());
                Double Latitud = Double.valueOf(txtLatitud.getText());

                Pozo pozoModificado = new Pozo();
                pozoModificado.setNombre(Nombre);
                pozoModificado.setDuenio(Duenio);
                pozoModificado.setLogo(Logo);
                pozoModificado.setPortada(Portada);

                Ubicacion up = new Ubicacion();
                up.setIdUbicacion(IdPersona);
                up.setLatitud(Latitud);
                up.setLongitud(Longitud);
                pozoModificado.setUbicacionPozo(up);

                pozoControlDao.Merge(pozoModificado, IdPersona - 1);

                CargarTabla();

                try {
                    Limpiar();
                } 
                catch (Exception e) {

                }
            }
        }
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        int fila = tblPozo.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoga un registro","REGISTRO NO SELECCIONADO",JOptionPane.WARNING_MESSAGE);
        }
        else {
            pozoControlDao.Eliminar(fila);
            CargarTabla();
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblPozoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPozoMouseClicked
        Seleccionar();
    }//GEN-LAST:event_tblPozoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        try {
            VistaGrafoPozo vgp = new VistaGrafoPozo();
            vgp.setVisible(true);
            this.setVisible(false);
        } 
        catch (Exception e) {
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(VistaRegistroPozo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaRegistroPozo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaRegistroPozo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaRegistroPozo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaRegistroPozo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSubirLogo;
    private javax.swing.JButton btnSubirPortada;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPozo;
    private javax.swing.JTextField txtDueño;
    private javax.swing.JTextField txtLatitud;
    private javax.swing.JTextField txtLogo;
    private javax.swing.JTextField txtLongitud;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPortada;
    // End of variables declaration//GEN-END:variables
}
