package modelo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import modelo.InventarioDinamico.Nodo;

@XmlRootElement(name="inventario")
public class InventarioDinamico<LoteMascarilla> {
	
	public class Nodo{
		//Atributos de la clase Nodo.
		private LoteMascarilla info;
		private Nodo sig;
		
		//constructor
		public Nodo(LoteMascarilla elemento, Nodo sig) {
			this.info = elemento;
			this.sig = sig; //otro objeto nodo.
		}
		
		//getters and setters
		public LoteMascarilla getInfo() {
			return info;
		}
		public void setInfo(LoteMascarilla info) {
			this.info = info;
		}
		
		public Nodo getSig() {
			return sig;
		}
		public void setSig(Nodo sig) {
			this.sig = sig;
		}
		
}	
	
	//Atributo de la clase invantarioDinamico
	private Nodo inicio;
	
	//constructor
	public InventarioDinamico(){
	  	this.inicio = null;
	}

	//funcion que nos insertará un elemento en la lita dinámica
	public void insertar(int posicion, LoteMascarilla elemento) {
	   	if(esVacia()) {
  			//Creo un nuevo nodo en la primera posicion
  			this.inicio = new Nodo(elemento,this.inicio);
  		}else {
  			if(posicion == 1){ 
  				//creo un nodo aux y le doy la posicion del nodo inicio
  	  			Nodo aux = new Nodo(elemento,this.inicio);
  	  			
  	  			//no puedes hacer de primeras el this.inicio=aux porque entonces perderías la referencia de la lista y esa lista ya estaría perdida
  	  			aux.sig = this.inicio;
  	  			this.inicio=aux;
  	  		}else {
  	  			Nodo aux = new Nodo(elemento,this.inicio);
		  		int i = 1;
		  		while(aux.sig!=null && i<posicion) {
		  			//Recorre 
		  			aux = aux.sig;
		  			i++;
		  		}
	  			aux.sig = new Nodo(elemento,aux.sig);
  	  		}
  		}
	}
	
	
	public int longitud() {
		Nodo aux = this.inicio;
			   	
		int contador = 0;
		
		//generar aux, hasta encontrar null.
		while(aux != null) {
			aux=aux.sig;
			contador++;
		}

		return contador;
	   
	}
	   

	public void borrar(int posicion) {
	  	Nodo aux = this.inicio;
	  	if(posicion==1) {
	  		this.inicio=this.inicio.sig;
	  	}else {
	  		for(int i=1; i<posicion; i++) {
	  			aux=aux.sig;
	  			//recorremos la lista hasta la posicion antes de la que queremos borrar
	  		}
	  		aux=aux.sig.sig;
	  		//se le dice que el siguiente a esta posición es la sig. del sig.
	  	}
	}
	  
	//funcion que comprueba si la lista está vacia
	public boolean esVacia() {
		
		boolean vacia=false;
		
		if(this.inicio == null) {
			vacia=true;
		}
		
		return vacia;
	}
					   
	public int buscar(Nodo buscando) {
		
		int posicion=1;
		Nodo aux = this.inicio;
			  
		boolean encontrado=false;
			  
		while(encontrado==false && aux!=null) {
			if(aux.equals(buscando)) {
				encontrado=true;
			}else {
				aux=aux.sig;
				posicion++;
			}
		}
		return posicion;
	}
	
	public void vaciarLista() {
		this.inicio=null;
	}
	
	public LoteMascarilla consultar(int pos) {
		Nodo aux = this.inicio;
		
		for(int i=1; i<pos; i++) {
			aux = aux.sig;
		}
		return aux.info;
	}
	
	public InventarioEstatico parserStatic() {
		Nodo aux = this.inicio;
		InventarioEstatico inventario = new InventarioEstatico();
		ArrayList mascarillas = new ArrayList();
		//va copiando de una lista a otra
		while (aux != null) {
			mascarillas.add(aux.getInfo());
			aux = aux.sig;
		}
		inventario.setLotes(mascarillas);
		return inventario;
	}
	
}


