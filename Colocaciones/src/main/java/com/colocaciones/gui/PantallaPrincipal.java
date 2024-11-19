package com.colocaciones.gui;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.colocaciones.model.Colocacion;
import com.colocaciones.service.IColocacionService;
import com.colocaciones.util.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

import java.util.Vector;


public class PantallaPrincipal extends JFrame implements ActionListener, ItemListener{


	private static final long serialVersionUID = 1L;

	private JMenuBar barra;

	private JMenu menuCarga;

	private JMenuItem itemCargaColocaciones;

	private DefaultTableModel modelo;

	private JTable tablaDatos;

	private JScrollPane scroll;

	private JPanel panelPrincipal, panelTabla, panelBuscar, panelBoton;

	private List<Colocacion> colocaciones;
	


	public PantallaPrincipal() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(true);
		setLayout(new GridLayout(1, 1));
		setSize(800, 400);
		setLocationRelativeTo(null);
		setTitle("PRUEBA CONCEPTO");
		
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();  
            }
        });
		
		colocaciones = fetchColocaciones();
		
		colocaciones.stream().forEach(a -> System.out.println(a));

		menuCarga = new JMenu("CARGA");

		itemCargaColocaciones = new JMenuItem("CARGA COLOCACIONES");
		itemCargaColocaciones.addActionListener(this);

		menuCarga.add(itemCargaColocaciones);

		barra = new JMenuBar();
		barra.add(menuCarga);

		Vector<Vector<Object>> data = new Vector<>();
		String[] columnNames = {"ID", "FECHA_CORTE", "DPT_ID", "DPT_NOMBRE",
				"MPO_ID", "MPO_NOMBRE", "CIIU_COD", "CIIU_NOMBRE", "DEN_ID",
				"IND_NOMBRE", "OCU_ID", "OCUPACION", "GENERO", "ROM", 
				"INDIGENAS", "AFROCOLOMBIANO", "POBLACION_PALENQUE",
				"RAIZALES", "DISCAPACIDAD_PSICOSO", "DISCAPACIDAD_AUDITIVA",
				"DISCAPACIDAD_SORDOCEG", "DISCAPACIDAD_FISI", "DISCAPACIDAD_VISUAL",
				"DISCAPACIDAD_MULTIPLE", "DISCAPACIDAD_INTELECT", "EDAD",
				"SUELDO_MES", "TOTAL_COLOCA", "FECHA_CARGUE"};
		
        for (Colocacion colocacion : colocaciones) {
            Vector<Object> row = new Vector<>();
            row.add(colocacion.getId());
            row.add(colocacion.getFechaCorte());
            row.add(colocacion.getDptId());
            row.add(colocacion.getDptNombre());
            row.add(colocacion.getMpoId());
            row.add(colocacion.getMpoNombre());
            row.add(colocacion.getCiiuCod());
            row.add(colocacion.getCiiuNombre());
            row.add(colocacion.getDenId());
            row.add(colocacion.getIndNombre());
            row.add(colocacion.getOcuId());
            row.add(colocacion.getOcupacion());
            row.add(colocacion.getGenero());
            row.add(colocacion.getRom());
            row.add(colocacion.getIndigenas());
            row.add(colocacion.getAfrocolombiano());
            row.add(colocacion.getPoblacionPalenque());
            row.add(colocacion.getRaizales());
            row.add(colocacion.getDiscapacidadPsicoso());
            row.add(colocacion.getDiscapacidadAuditiva());
            row.add(colocacion.getDiscapacidadSordoceg());
            row.add(colocacion.getDiscapacidadFisi());
            row.add(colocacion.getDiscapacidadVisual());
            row.add(colocacion.getDiscapacidadMultiple());
            row.add(colocacion.getDiscapacidadIntelect());
            row.add(colocacion.getEdad());
            row.add(colocacion.getSueldoMes());
            row.add(colocacion.getTotalColoca());
            row.add(colocacion.getFechaCargue());
           
            data.add(row);
        }		

		setJMenuBar(barra);
		
	     modelo = new DefaultTableModel(data, new Vector<>(List.of(columnNames)));

		
		

		tablaDatos = new JTable();
		tablaDatos.setModel(modelo);
		scroll = new JScrollPane(tablaDatos);

		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createTitledBorder(""));

		panelTabla = new JPanel(new GridLayout(1, 1));
		panelTabla.setBorder(BorderFactory.createTitledBorder("Colocaciones"));

		panelTabla.add(scroll);
		
		tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelPrincipal.add(panelTabla, BorderLayout.CENTER);

		add(panelPrincipal);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == itemCargaColocaciones) {
			System.out.println("INICIA VENTANA CARGA");
			uploadFile();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}


	private void uploadFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Seleccionar Archivo Excel");
		int result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			if (selectedFile.exists()) {
				try {
					sendFileToServer(selectedFile);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, "Error al subir el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Archivo no válido", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	private void sendFileToServer(File file) throws IOException {
		OkHttpClient client = new OkHttpClient();

		RequestBody fileBody = RequestBody.create(file, MediaType.parse("application/vnd.ms-excel"));

		MultipartBody requestBody = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("file", file.getName(), fileBody)
				.build();

		Request request = new Request.Builder()
				.url("http://localhost:8080/colocacion/carga")
				.post(requestBody)
				.build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful()) {
				this.updateTable();
				JOptionPane.showMessageDialog(this, "Archivo subido exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);				
			} else {
				JOptionPane.showMessageDialog(this, "Error al subir el archivo: " + response.message(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	public List<Colocacion> fetchColocaciones() {

		final String URL = "http://localhost:8080/colocacion/findAll";

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(URL) // URL del servicio REST
				.get()
				.build();
		
		Gson gson = new GsonBuilder()
			    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			    .create();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful() && response.body() != null) {
				// Convertir el JSON en una lista de objetos Persona
				String responseBody = response.body().string();
				java.lang.reflect.Type listType = new TypeToken<List<Colocacion>>() {}.getType();
				return gson.fromJson(responseBody, listType);
			} else {
				JOptionPane.showMessageDialog(null, "Error al obtener datos: " + response.message(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al conectar con el servicio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null; // Si algo falla, retorna null
	}
	
  
    public void updateTable() {
    	System.out.println("ACTUALIZA TABLA");
    	List<Colocacion> colocacionesFind = fetchColocaciones();
	
        modelo.setRowCount(0); 
        
        for (Colocacion colocacion : colocacionesFind) {
            Vector<Object> row = new Vector<>();
            row.add(colocacion.getId());
            row.add(colocacion.getFechaCorte());
            row.add(colocacion.getDptId());
            row.add(colocacion.getDptNombre());
            row.add(colocacion.getMpoId());
            row.add(colocacion.getMpoNombre());
            row.add(colocacion.getCiiuCod());
            row.add(colocacion.getCiiuNombre());
            row.add(colocacion.getDenId());
            row.add(colocacion.getIndNombre());
            row.add(colocacion.getOcuId());
            row.add(colocacion.getOcupacion());
            row.add(colocacion.getGenero());
            row.add(colocacion.getRom());
            row.add(colocacion.getIndigenas());
            row.add(colocacion.getAfrocolombiano());
            row.add(colocacion.getPoblacionPalenque());
            row.add(colocacion.getRaizales());
            row.add(colocacion.getDiscapacidadPsicoso());
            row.add(colocacion.getDiscapacidadAuditiva());
            row.add(colocacion.getDiscapacidadSordoceg());
            row.add(colocacion.getDiscapacidadFisi());
            row.add(colocacion.getDiscapacidadVisual());
            row.add(colocacion.getDiscapacidadMultiple());
            row.add(colocacion.getDiscapacidadIntelect());
            row.add(colocacion.getEdad());
            row.add(colocacion.getSueldoMes());
            row.add(colocacion.getTotalColoca());
            row.add(colocacion.getFechaCargue());
           
            modelo.addRow(row);
        }
    
        modelo.fireTableDataChanged();  
    }
    
    
    

    public void close() {
        int option = JOptionPane.showConfirmDialog(this, 
                                                   "¿Desea realmente dejar el sistema?", 
                                                   "Salir del sistema", 
                                                   JOptionPane.YES_NO_OPTION, 
                                                   JOptionPane.QUESTION_MESSAGE);
        
        
        System.out.println("Option: "+option);
        System.out.println("YES_OPTION: "+JOptionPane.YES_OPTION);
        System.out.println("NO_OPTION: "+JOptionPane.NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
	
}



