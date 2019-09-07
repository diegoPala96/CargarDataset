package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import modelo.ModeloUsuarios;
/**
 * 
 * @author Diego
 *
 */

public class Lectura {

/**
 * 
 * @return un String con la ruta del archivo
 * abre un filechooser para la busqueda del archivo
 * y almacena en una variable path
 * 
 * 
 */
public String archivo() {
	JFileChooser j = new JFileChooser();
    FileNameExtensionFilter filtros = new FileNameExtensionFilter("DAT, TXT, CSV", "dat", "txt", "csv"); // lista de extensiones de archivos para carga 
    j.setFileSelectionMode(JFileChooser.FILES_ONLY); //Solo va a poder seleccionar un archivo y no multiples
       j.setMultiSelectionEnabled(false);    //Modificamos el filtro
       j.setFileFilter(filtros);
       j.setAcceptAllFileFilterUsed(false);

	String path = null;
	int seleccionado = j.showOpenDialog(null);

	if (seleccionado == JFileChooser.APPROVE_OPTION) {

		path = j.getSelectedFile().getAbsolutePath();
		
		//txtRuta.setText(path);
	}
	return path;
	
}

/**
 * 
 * @return el separador del archivo
 * muestra un opcionPanel para pedir el campo de separacion
 */
public String separador() {
	String separador = String.valueOf(JOptionPane.showInputDialog("Ingrese el separador"));
	return separador;
	
}

/**
 * 
 * @param path la ruta del archivo seleccionado
 * @param separador separador para cada columna de la lectura
 * @return la lista de Usuarios
 * se da lectura al archivo y por cada linea se identifica
 * si esta repetido solo incrementa en el item perteneciente al usuario repetido,
 * en el caso de no estar el usuario en la lista  agrega un nuevo usuario a la lista.
 * 
 */
public ArrayList<ModeloUsuarios> cargaDatos(String path,String separador){
	
	ArrayList<ModeloUsuarios> Usuarios = new ArrayList<ModeloUsuarios>();
	try {
		File file = new File(path);
		String vector_final[];
		BufferedReader br = new BufferedReader(new FileReader(file));
		String d;

		int comprobador = 0; //comprueba si ya existe un usuario o no
		int indexCliente = 0; //indice para el cliente
		int cliente=0; // cliente de lectura
		
		while ((d = br.readLine()) != null) {
			vector_final = d.split(separador);
			int[] item = new int[1];
			double[] voto = new double[1];
				try {
					 cliente = Integer.parseInt(vector_final[0]);
				} catch (NumberFormatException ex) {
					d = br.readLine();
					vector_final = d.split(separador);
					 cliente = Integer.parseInt(vector_final[0]);
				}
			int codItem=Integer.parseInt(vector_final[1]);
			double votoUsItem=Double.parseDouble(vector_final[2]);
			for (ModeloUsuarios p : Usuarios) {
				
				if (p.getCodigo() == cliente) {
					
					int[] ite = p.getItem();// recupero los item
					double[] vot = p.getVotos();// recupero los votos
					int tamano = ite.length + 1;
					int[] aux = new int[tamano];
					double[] auxVoto = new double[tamano];
					System.arraycopy(ite,0,aux,0,ite.length);
					System.arraycopy(vot,0,auxVoto,0,vot.length);
					aux[tamano -1] = codItem;
					auxVoto[tamano - 1] = votoUsItem;
					comprobador = 1;
					p.setItem(aux);
					p.setVotos(auxVoto);
					
				}
			}

			if (comprobador == 0) {
				ModeloUsuarios mu = new ModeloUsuarios(); 
				item[0] = codItem;
				voto[0] = votoUsItem;
				mu.setIndex(indexCliente);
				mu.setItem(item);
				mu.setCodigo(cliente);
				mu.setVotos(voto);
				Usuarios.add(mu);
				indexCliente++;
			}
			comprobador = 0;

			// linea++;
		//	System.out.println(linea);
		}
		br.close();

	} catch (IOException e) {
		JOptionPane.showMessageDialog(null, "Error: " + e);
	}
return Usuarios;
	
	
}

/**
 * 
 * @param listaUsuarios parametro de la lista de usuarios con todos sus datos
 * realiza un recorrido de la lista e imprime los datos pertenecientes a cada usuario
 * 
 */

public void imprime(ArrayList<ModeloUsuarios> listaUsuarios) {

	for (ModeloUsuarios p : listaUsuarios) {

		System.out.println(p.getIndex() + "indices");	//obtener el valor del index 
		System.out.println(p.getCodigo() + "usuario");	//obtener valor de Cliente(usuario)
		int[] ite;
		double[] vot;
		ite = p.getItem();

		vot = p.getVotos();
		for (int i = 0; i < ite.length; i++) {
			System.out.println(ite[i] + "item    " + vot[i] + "     voto ");
		}

		System.out.println();

	}
	
}

}
