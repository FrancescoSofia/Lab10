package it.polito.tdp.porto.model;

import java.util.*;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		List<Author> autori = model.getAllAutori();
		List<Paper> articoli = model.getAllArticoli();
		model.provaCreaGrafo();
		System.out.println("TODO: write a Model class and test it!");
	}

}
