package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import modelo.InventarioDinamico;
import modelo.InventarioEstatico;
import modelo.LoteMascarilla;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			//defino el scanner para capturar la escritura del usuario por consola
			Scanner entradaEscaner = new Scanner (System.in);
			
			//Instancio la factoria JAXB y le inidico que objetos son mendiante mi clase
			JAXBContext contexto = JAXBContext.newInstance(InventarioEstatico.class);
			
			//creo los objetos, uno de cada
			InventarioDinamico<LoteMascarilla> listaDin = new InventarioDinamico();
			InventarioEstatico listaEst = new InventarioEstatico();
			
			//creo otro objeto de listest que necesitaré para leer el output
			InventarioEstatico listaEstOutput = new InventarioEstatico();
			
			//Con Unmarshaller preparo el paso de xml a memoria y capturo el archivo
			Unmarshaller descargar = contexto.createUnmarshaller();
			
			/*defino el archivo que supuestamente estará ubicado en el output, y mediante un if, si este archivo existe,
			 *lo que haré es llenar la lista estática, y a su vez parsearla y llenar también la dinámica*/
			File archivo = new File("src\\output\\listadoMascarillas.xml");
			if (archivo.exists()) {
				//llena la lista con los datos que ya hay en el archivo que he cargado
				listaEstOutput = (InventarioEstatico) descargar.unmarshal(archivo);
				
				//parseo a dinamica
				listaDin = listaEstOutput.parserDinamic(listaDin);
			}
			//defino la opción que usará el usuario para interactuar con el menú
			int opcion = 0;
			while(opcion!=4) { //menú
				System.out.println("Bienvenido al programa de gestión de mascarillas de la Comunidad de Madrid");
				System.out.println("¿Qué quieres hacer?\n");
				System.out.println("1- Leer fichero XML.");
				System.out.println("2- Imprimir datos.");
				System.out.println("3- Mostrar numero total de mascarillas.");
				System.out.println("4- Salir.");
				opcion = entradaEscaner.nextInt();
				switch(opcion) {
					case 1:{
						//pregunto al usuario por el nombre del fichero a leer
						System.out.println("\nEscribe el nombre del fichero.\n");
						String nombreFichero = entradaEscaner.nextLine();
						nombreFichero = entradaEscaner.nextLine();
						
						//declaro la ruta del archivo
						File file = new File("src\\input\\"+nombreFichero+".xml");
						
						//si esa ruta no exite, creo un bucle while hasta que escriba el nombre del archivo correcto
						while(!file.exists()) {
							System.out.println("No exite ningún archivo con ese nombre. Escríbelo de nuevo.\n");
							nombreFichero = entradaEscaner.nextLine();
							file = new File("src\\input\\"+nombreFichero);
						}
						
						//llena la lista con los datos que ya hay en el archivo que he cargado
						listaEst = (InventarioEstatico) descargar.unmarshal(file);
						
						//parseo a dinamica
						listaDin = listaEst.parserDinamic(listaDin);
						
						System.out.println("\nEl archivo se ha leido correctamente.\n");
						break;
					}
					case 2:{
						//declaro la ruta del archivo que voy a crear
						File file = new File("src\\output\\listadoMascarillas.xml");
						
						//antes de crearlo, borro el que ya había
						if (file.exists()) {
							file.delete();
						}
						
						//con el marshaller crearemos el xml
						Marshaller invent = contexto.createMarshaller();
						invent.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
						OutputStream os = new FileOutputStream("src\\output\\listadoMascarillas.xml");
						
						//generamos el xml con marshal, al que le mandamos la lista dinamica parseada, y la ruta
						invent.marshal(listaDin.parserStatic(),os);
						System.out.println("El fichero ha sido guardado.");
						break;
					}
					case 3:{
						//usamos la función longitud de la lista dinamica para saber el total de mascarillas
						int numMascarillas = listaEst.mostrarTotalMascarillas() + listaEstOutput.mostrarTotalMascarillas();
						System.out.println("\nHay un total de "+numMascarillas+" mascarillas.\n");
						break;
					}
					case 4:{
						System.out.println("\nSaliendo...");
						break;
					}
					default:{
						System.out.println("El número introducido es incorrecto.");
						break;
					}
				}
			}
				
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
