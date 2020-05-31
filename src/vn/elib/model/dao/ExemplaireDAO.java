/**
 * 
 */
package vn.elib.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import vn.elib.controller.Global;
import vn.elib.model.pojo.Abonne;
import vn.elib.model.pojo.CarteMagnetique;
import vn.elib.model.pojo.Emprunt;
import vn.elib.model.pojo.Exemplaire;
import vn.elib.model.pojo.Rfid;

/**
 * @author franel
 *
 */
public class ExemplaireDAO extends DAO<Exemplaire> {

	public ExemplaireDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Exemplaire obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Exemplaire obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Exemplaire obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Exemplaire find(int id) {
		// TODO Auto-generated method stub
		
		Exemplaire exemplaire = null;    
		
	    try {
	    	String query = "SELECT * FROM exempmlaire";
	        query += " INNER JOIN rfid ON code_rfid = rfid";
	        query += " WHERE id_exemplaire = ?";
	    	
	    	PreparedStatement prepare = this.connect.prepareStatement(query,
	    			ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
	    	
	    	prepare.setInt(1, id);
	    	
	    	ResultSet result = prepare.executeQuery();

	    	if(result.first()){
	    		Rfid rfid = new Rfid(result.getString("code_rfid"));
	    		exemplaire = new Exemplaire(result.getInt("id_exemplaire"), rfid,
						!result.getBoolean("etat_emprunt"));
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return exemplaire;
	}

	@Override
	public ObservableList<Exemplaire> find() {
		// TODO Auto-generated method stub
		return null;
	}

}
