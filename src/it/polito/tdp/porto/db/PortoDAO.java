package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.Paper;
import it.polito.tdp.porto.model.PaperIdMap;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id,AuthorIdMap authorIdMap) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				autore = authorIdMap.put(autore);
				return autore;
			}
			rs.close();
			conn.close();
			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}
			rs.close();
			conn.close();
			
			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Author> getAllAutori(AuthorIdMap authorIdMap){
		
		final String sql = "SELECT * FROM author " ;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			
			List<Author> autori = new ArrayList<Author>();

			while (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				autore = authorIdMap.put(autore);
				autori.add(autore);
			}
			
			rs.close();
			conn.close();
			
			return autori;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}	

	public List<Paper> getAllArticoli(PaperIdMap paperIdMap) {
		
		final String sql = "SELECT * FROM paper " ;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			
			List<Paper> articoli = new ArrayList<Paper>();

			while (rs.next()) {

				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				paper = paperIdMap.put(paper);
				articoli.add(paper);
			}
			
			rs.close();
			conn.close();

			return articoli;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}
	
	public List<Paper> getArticoliAutore(int  authorid,PaperIdMap paperIdMap) {

		final String sql = "SELECT * FROM creator c,paper p where c.authorid=? AND p.eprintid = c.eprintid ";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, authorid);

			ResultSet rs = st.executeQuery();
			
			List<Paper> articoli = new ArrayList<Paper>();

			while (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				paper = paperIdMap.put(paper);
				articoli.add(paper);
			
			}
			rs.close();
			conn.close();
			
			return articoli;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Author> getAutoriArticolo(int  eprintid,AuthorIdMap authorIdMap) {

		final String sql = "SELECT * FROM creator c,author a where c.eprintid=? AND a.id = c.authorid ";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();
			
			List<Author> autori = new ArrayList<Author>();

			while (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				autore = authorIdMap.put(autore);
				autori.add(autore);
				System.out.println(autore);
			}
			
			rs.close();
			conn.close();
			
			return autori;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
}