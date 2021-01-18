package modelo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="lote") //Mediante una anotación le indicamos cual es la raiz del XML
@XmlType(propOrder={"cantidad", "tipo", "modelo", "homologada", "material", "conFiltro", "caducidad", "fabricante"}) //Indico el orden de los nodos del XML
public class LoteMascarilla {
	private int cantidad;
	private String tipo;
	private String modelo;
	private Boolean homologada;
	private String material;
	private Boolean conFiltro;
	private String caducidad;
	private String fabricante;

	//constructor de la clase LoteMascarilla
	public LoteMascarilla(int cantidad, String tipo, String modelo, Boolean homologada, String material, Boolean conFiltro, String caducidad, String fabricante) {
		this.cantidad = cantidad;
		this.tipo = tipo;
		this.modelo = modelo;
		this.homologada = homologada;
		this.material = material;
		this.conFiltro = conFiltro;
		this.caducidad = caducidad;
		this.fabricante = fabricante;
	}
	
	
	public LoteMascarilla() {
		this.cantidad = 0;
		this.tipo = "";
		this.modelo = "";
		this.homologada = false;
		this.material = "";
		this.conFiltro = false;
		this.caducidad = "";
		this.fabricante = "";
	}




	//getters y setters
	@XmlElement(name="cantidad")
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@XmlElement(name="tipo")
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@XmlElement(name="modelo")
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@XmlElement(name="homologada")
	public Boolean getHomologada() {
		return homologada;
	}
	public void setHomologada(Boolean homologada) {

		this.homologada = homologada;
	}
	
	@XmlElement(name="material")
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
	@XmlElement(name="conFiltro")
	public Boolean getConFiltro() {
		return conFiltro;
	}

	public void setConFiltro(Boolean conFiltro) {
		this.conFiltro = conFiltro;
	}

	@XmlElement(name="caducidad")
	public String getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(String caducidad) {
		this.caducidad = caducidad;
	}

	@XmlElement(name="fabricante")
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
}
