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
public class GenreDAO extends DAO<Genre> {

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Genre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Genre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Genre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Genre find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableList<Genre> find() {
		// TODO Auto-generated method stub
		
		ObservableList<Genre> data = FXCollections.observableArrayList();
		
		try {
	    	String query = "SELECT * FROM genre";
	    	
	    	PreparedStatement prepare = this.connect.prepareStatement(query,
	    			ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
	    	
	    	ResultSet result = prepare.executeQuery();
	    	
			while(result.next()) {
				Genre l = new Genre(result.getInt("id_genre"), result.getString("nom_genre"));
						
				data.add(l);
			}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		
		return data;
	}

}
