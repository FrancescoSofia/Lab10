package it.polito.tdp.porto.db;

import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.PaperIdMap;

public class TestPortoDAO {
	
	public static void main(String args[]) {
		AuthorIdMap authorIdMap = new AuthorIdMap();
		PaperIdMap paperIdMap = new PaperIdMap();
		PortoDAO pd = new PortoDAO();
		
		System.out.println("Autore 85:");
		System.out.println(pd.getArticoliAutore(85, paperIdMap));
		System.out.println("Autore 4287:");
		System.out.println(pd.getArticoliAutore(4287, paperIdMap));
		System.out.println("Autore 2669:");
		System.out.println(pd.getArticoliAutore(2669, paperIdMap));
		System.out.println("Autore 20500:");
		System.out.println(pd.getArticoliAutore(20500, paperIdMap));
//		System.out.println(pd.getAutore(85, null));
//		System.out.println(pd.getArticolo(2293546));
//		System.out.println(pd.getArticolo(1941144));
		System.out.println("");
		System.out.println("Prova articoli");
		System.out.println("Articolo 2498280 ");
		System.out.println(pd.getAutoriArticolo(2498280, authorIdMap));
		System.out.println("Articolo 2505134");
		System.out.println(pd.getAutoriArticolo(2505134, authorIdMap));
		System.out.println("Articolo 2515884");
		System.out.println(pd.getAutoriArticolo(2515884, authorIdMap));
		System.out.println("Articolo 2520938");
		System.out.println(pd.getAutoriArticolo(2520938, authorIdMap));
		System.out.println("Articolo 2520939");
		System.out.println(pd.getAutoriArticolo(2520939, authorIdMap));
		System.out.println("Articolo 2628194");
		System.out.println(pd.getAutoriArticolo(2628194, authorIdMap));
		System.out.println("Articolo 2628314");
		System.out.println(pd.getAutoriArticolo(2628314, authorIdMap));
		
		

	}

}
