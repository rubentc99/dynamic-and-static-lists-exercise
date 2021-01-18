package modelo;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="inventario") //Mediante una anotación le indicamos cual es la raiz del XML
@XmlType(propOrder={"lotes"}) //Indico el orden de los nodos del XML
public class InventarioEstatico {
	
	//atributo de la clase InventarioEstatico
	//arraylist de objetos LoteMascarilla
	private ArrayList<LoteMascarilla> lotes;
	
	//constructor de la clase InventarioEstatico
	public InventarioEstatico() {
		this.lotes=new ArrayList<LoteMascarilla>();
	}
	
	//getter y setter
	@XmlElementWrapper(name="lotes") //Con wraper le indicamos que es un envoltorio
	@XmlElement(name="lote") //Con el name le indicamos lo que retorna el envoltorio de XML - elemento=nodo	
	public ArrayList<LoteMascarilla> getLotes() {
		return this.lotes;
	}

	public void setLotes(ArrayList<LoteMascarilla> lotes) {	
		this.lotes = lotes;
	}
	

	//funcion que añade un objeto de tipo LoteMascarilla al array
	public void insertar(LoteMascarilla elem) {
		this.lotes.add(elem);
	}
	
	/***
	 * Función con la que obtengo la suma total de las mascarillas insertadas
	 * @return
	 */
	public int mostrarTotalMascarillas() {
		int contador=0;
		for(int i = 0; i < this.lotes.size(); i++) {
			contador+=this.lotes.get(i).getCantidad();
		}
		
		return contador;
	}
	
	public InventarioDinamico parserDinamic(InventarioDinamico<LoteMascarilla> inventario) {
		//paso los objetos del arrayList estatico al dinamico
		for(int i = 0; i < this.lotes.size(); i++) {
			
			if(this.lotes.get(i).getHomologada()){
				inventario.insertar(i+1, this.lotes.get(i));
			}
		}
		return inventario;
	}



	
}