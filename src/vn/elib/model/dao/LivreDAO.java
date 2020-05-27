/**
 * 
 */
package vn.elib.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vn.elib.model.pojo.Exemplaire;
import vn.elib.model.pojo.Genre;
import vn.elib.model.pojo.Livre;
import vn.elib.model.pojo.Rfid;

/**
 * @author franel
 *
 */
public class LivreDAO extends DAO<Livre> {

	public LivreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Livre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Livre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Livre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Livre find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableList<Livre> find() {
		// TODO Auto-generated method stub
		
		ObservableList<Livre> data = FXCollections.observableArrayList();
		
		try {
	    	String query = "SELECT * FROM livre";
	        query += " INNER JOIN genre ON genre = id_genre";
	    	
	    	PreparedStatement prepare = this.connect.prepareStatement(query,
	    			ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
	    	
	    	ResultSet result = prepare.executeQuery();
	    	
			while(result.next()) {
				Livre l = new Livre();
						l.setId(result.getString("isbn")); 
						l.setTitre(result.getString("titre"));
						l.setAuteur(result.getString("auteur"));
						l.setEditeur(result.getString("editeur"));
						l.setNbre_page(result.getInt("nbre_page"));
						l.setTome(result.getInt("tome"));
						
						Genre g = new Genre(result.getInt("id_genre"), result.getString("nom_genre"));
						l.setGenre(g);
						
						String query2 = "SELECT * FROM exemplaire";
				        query2 += " INNER JOIN rfid ON rfid = code_rfid";
				        query2 += " WHERE livre = ?";
				    	
				    	PreparedStatement prepare2 = this.connect.prepareStatement(query2,
				    			ResultSet.TYPE_SCROLL_SENSITIVE,
			                    ResultSet.CONCUR_UPDATABLE);
				    	
				    	prepare2.setString(1, result.getString("isbn"));
				    	
				    	ResultSet result2 = prepare2.executeQuery();
				    	
				    	while(result2.next()) {
				    		Rfid rfid = new Rfid(result2.getString("rfid"));
				    		Exemplaire ex = new Exemplaire(result2.getInt("id_exemplaire"), rfid);
				    		l.addExemplaire(ex);
				    	}
						
						data.add(l);
			}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		
		return data;
	}	
}
