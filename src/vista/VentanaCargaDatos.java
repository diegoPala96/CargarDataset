package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.Lectura;
import modelo.ModeloUsuarios;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;


public class VentanaCargaDatos extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private JPanel contentPane;
	private JTextField txtRuta;
	Thread h1;
	ArrayList<ModeloUsuarios> listaUsuarios = new ArrayList<ModeloUsuarios>();
	private JLabel lblTiempoCarga;
	private JLabel lblTCarga;
	private JLabel lblCantidadMemo;

	private Lectura leer= new Lectura();

	private JButton btnListarUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					VentanaCargaDatos frame = new VentanaCargaDatos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaCargaDatos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 444);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtRuta = new JTextField();
		txtRuta.setEnabled(false);
		txtRuta.setBounds(114, 11, 389, 32);
		contentPane.add(txtRuta);
		txtRuta.setColumns(10);
		/**
		 * boton que permite la lectura del archivo y la carga en el array list
		 */
		JButton btnCargar = new JButton("Buscar");
		btnCargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Runtime runtime = Runtime.getRuntime();
				
				String path=leer.archivo(); // metodo para obtener la ruta del archivo
				
				System.out.println(path);
				String separador=leer.separador(); // metodo para obtener el separador
	
				System.out.println(separador + "separador");
				long t1 = System.currentTimeMillis();
				listaUsuarios=leer.cargaDatos(path, separador);// metodo para cargar datos a la lista
				long t2 = System.currentTimeMillis();
				//	leer.imprime(listaUsuarios); // metodo para imprimir el array 
				lblTCarga.setText("" + ((t2 - t1) / 1000) + "s");
				int dataSize = 1024 * 1024;
				lblCantidadMemo.setText("" + (runtime.totalMemory() - runtime.freeMemory() / dataSize) + " MB");
				// progressBar.setIndeterminate(false);
				btnListarUsuario.setVisible(true);
				contentPane.repaint();

			}
		});
		btnCargar.setBounds(536, 11, 89, 32);
		contentPane.add(btnCargar);

		JLabel lblRuta = new JLabel("Ruta:");
		lblRuta.setBounds(21, 20, 46, 14);
		contentPane.add(lblRuta);

		lblTiempoCarga = new JLabel("Tiempo Carga:");
		lblTiempoCarga.setBounds(21, 61, 105, 14);
		contentPane.add(lblTiempoCarga);

		lblTCarga = new JLabel("...");
		lblTCarga.setBounds(171, 54, 121, 14);
		contentPane.add(lblTCarga);

		btnListarUsuario = new JButton("listar usuario");
		btnListarUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				MostrarDatos md = new MostrarDatos(listaUsuarios);
				md.setVisible(true);

			}
		});
		btnListarUsuario.setBounds(31, 357, 126, 23);
		btnListarUsuario.setVisible(false);
		contentPane.add(btnListarUsuario);

		JLabel lblMemoria = new JLabel("memoria");
		lblMemoria.setBounds(313, 61, 59, 14);
		contentPane.add(lblMemoria);

		lblCantidadMemo = new JLabel("....");
		lblCantidadMemo.setBounds(430, 61, 141, 14);
		contentPane.add(lblCantidadMemo);
	}



}
