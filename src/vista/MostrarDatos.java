package vista;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.ModeloUsuarios;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;

public class MostrarDatos extends JFrame {

	private JPanel contentPane;
	private JTextPane txtdatos;
	private JTextField txtusuario;
	ArrayList<ModeloUsuarios>  Usuarios= new ArrayList<ModeloUsuarios>(); // lista de usuarios
	private int indice=0;
	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarDatos frame = new MostrarDatos(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param usuarios  recuperamos la lista de los Usuarios
	 */
	public MostrarDatos(ArrayList<ModeloUsuarios> usuarios) {
		Usuarios= usuarios;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 712, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btnAnt = new JButton(new ImageIcon(((new ImageIcon("Iconos/anterior.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		btnAnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				indice--;
				sigIndice(indice);
				
			}
		});
	//	btnAnt.setIcon(new ImageIcon("Iconos/anterior.png"));
		btnAnt.setContentAreaFilled(false);
		btnAnt.setBorder(null);
		btnAnt.setBounds(59, 16, 89, 44);
		contentPane.add(btnAnt);
		
		JButton btnSig = new JButton(new ImageIcon(((new ImageIcon("Iconos/siguiente.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		btnSig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indice++;
				sigIndice(indice);
			}
		});
		btnSig.setContentAreaFilled(false);
		btnSig.setBorder(null);
		btnSig.setBounds(511, 16, 89, 44);
		contentPane.add(btnSig);
	
		
		txtusuario = new JTextField();
		txtusuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				IdUsuario(Integer.parseInt(txtusuario.getText()));
				
			}
		});
		txtusuario.setBounds(193, 16, 293, 44);
		contentPane.add(txtusuario);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 71, 580, 234);
	
		
		
		
		
		txtdatos = new JTextPane();
		txtdatos.setBackground(Color.WHITE);
		txtdatos.setBounds(44, 71, 580, 234);
		//contentPane.add(txtdatos);
		
		scrollPane.setViewportView(txtdatos);
		contentPane.add(scrollPane);
		
		sigIndice(indice);
		/*ModeloUsuarios ModUsuario= new ModeloUsuarios();
		ModUsuario= usuarios.get(0);
		
		System.out.println(ModUsuario.getCodigo()+"usuario");
		System.out.println(ModUsuario.getIndex()+"index");
		txtusuario.setText(ModUsuario.getCodigo()+"");
		
		   int[] item;
	        double[] voto;
	        item=ModUsuario.getItem();
	   
	        voto=ModUsuario.getVotos();
	        for (int i=0;i<item.length;i++){ 
	        	// System.out.println(item[i]+"item    "+voto[i]+"     voto "+" \n "); 	
	        
	        	txtdatos.setText(txtdatos.getText()+"item:"+"\t"+item[i]+"\t"+" voto"+"\t"+voto[i]+" \n "); 
	        }
		*/
		
		
	}
/**
 * 
 * @param actualIndice el indice de la lista 
 * mandamos a buscar en la lista el indice 
 * este metodo puede ser llamado con el boton siguiente y el 
 * boton anterior
 */
	protected void sigIndice(int actualIndice) {
		txtdatos.setText(""); // seteamos la caja de texto
		ModeloUsuarios ModUsuario= new ModeloUsuarios();
		ModUsuario= Usuarios.get(actualIndice);
		System.out.println(ModUsuario.getCodigo()+"usuario");
		System.out.println(ModUsuario.getIndex()+"index");
		txtusuario.setText(ModUsuario.getCodigo()+"");
		
		   int[] item;
	        double[] voto;
	        item=ModUsuario.getItem();
	    	indice=ModUsuario.getIndex();
	        voto=ModUsuario.getVotos();
	        for (int i=0;i<item.length;i++){ 
	        	// System.out.println(item[i]+"item    "+voto[i]+"     voto "+" \n "); 	
	        
	        	txtdatos.setText(txtdatos.getText()+"item:"+"\t"+item[i]+"\t"+" voto"+"\t"+voto[i]+" \n "); 
	        }
		
		
	}
	
	/**
	 * 
	 * @param idPersona es el Id de persona el cual se usa para su busqueda en la lista
	 * 
	 */

	protected void IdUsuario(int idPersona) {

		txtdatos.setText(""); // seteamos la caja de texto
		
		/**
		 * recorrido de la lista Usuarios
		 */
	for ( ModeloUsuarios p : Usuarios ) {
            
		
		if (p.getCodigo()==idPersona) {
			indice=p.getIndex();
			 int[] item;
		        double[] voto;
		        item=p.getItem();
		   
		        voto=p.getVotos();
		        for (int i=0;i<item.length;i++){ 

		        	txtdatos.setText(txtdatos.getText()+"item:"+"\t"+item[i]+"\t"+" voto"+"\t"+voto[i]+" \n "); 
		        	// System.out.println(item[i]+"item    "+voto[i]+"     voto "); 	
		        }
		       // System.out.println();     
			
		}
		
	       
	        
	       
	      
	    
	   
	        
	        
	        
	        }
	}
}
