import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import modelo.ModeloUsuarios;

public class Progress extends SwingWorker<Integer, String>{
JLabel label;
File file;

JProgressBar jProgressBar;
String separador;
	
	public JLabel getLabel() {
	return label;
}

public void setLabel(JLabel label) {
	this.label = label;
}

public JProgressBar getjProgressBar() {
	return jProgressBar;
}

public Progress(JProgressBar jProgressBar, File file, String separador, JLabel lblTCarga) {
	super();
	this.label=lblTCarga;
	this.separador= separador;
	this.file=file;
	this.jProgressBar = jProgressBar;
}

public void setjProgressBar(JProgressBar jProgressBar) {
	this.jProgressBar = jProgressBar;
}

	protected Integer doInBackground() throws Exception {
		
		getjProgressBar().setIndeterminate(true);
		
		
		
		
		
		
		
		
		 long t1 = System.currentTimeMillis();
		
		
		
        try {
        	String vector_final[];
         
			BufferedReader br = new BufferedReader(new FileReader(file));
            String d;
   
          int t=0;
          int linea=0;
            int codigo=0;


           
            ArrayList<ModeloUsuarios>  Usuarios= new ArrayList<ModeloUsuarios>();
                while ((d = br.readLine()) != null) {
                	
                	
                	vector_final=d.split(separador);
                	ModeloUsuarios mu=new ModeloUsuarios();
               
                    mu.setIndex(codigo);
                    mu.setCodigo(Integer.parseInt(vector_final[0]));// usuario
                    int[] item=new int[1];
                    item[0]=Integer.parseInt(vector_final[1]);
                    mu.setItem(item);
                    double[] voto=new double[1];
                    voto[0]=Double.parseDouble(vector_final[2]);
                    mu.setVotos(voto);
                    
                    
                    //System.out.print (vector_final[0]+"cero       ");
                
                    for ( ModeloUsuarios p : Usuarios ) {
         		       if(p.getCodigo()==Integer.parseInt(vector_final[0])) {
         		    	   int[] ite=p.getItem();// recupero los item
         		    	   double[] vot=p.getVotos();//recupero los votos
         		    	
         		    	   int tamano=ite.length+1;
         		    	
         		    	  int[]aux=new int[tamano] ;
         		    	  double[]auxVoto= new double[tamano];
         		    
         		    /**
         		     * for para pasar los valores aal nuevo array
         		     */
         		    	  for (int i = 0; i < ite.length; i++) {
							aux[i]=ite[i];
							auxVoto[i]=vot[i];
							//System.out.println(aux[i]+"        imprimo            ");
						}
         		    
         		    	  aux[aux.length-1]=Integer.parseInt(vector_final[1]);
         		    	 auxVoto[auxVoto.length-1]=Double.parseDouble(vector_final[2]);
         		    	  mu.setVotos(auxVoto);
         		    	  mu.setItem(aux);
       		 
       		       
       		        t=1;
       		   p.setItem(aux);
       		   p.setVotos(auxVoto);
         		       }else {
       		        	
       	          //   System.out.println("otro....");
       		        }
         	
                    }
                        
                    

       		        if(t==0) {
       	             	Usuarios.add(mu);
                        
    		            codigo++;
       		        }
                t=0;
                
                linea++;
                }
                
                

                
                
           
                    
            
                
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        
        
        long t2 = System.currentTimeMillis();
	
		label.setText(""+((t2-t1)/1e6)+"ms");
       // imprimi();
        
	
        JOptionPane.showMessageDialog(null,"acabo");
        
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		getjProgressBar().setIndeterminate(false);
		return 0;
	}



}
