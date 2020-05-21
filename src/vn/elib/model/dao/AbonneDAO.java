/**
 * 
 */
package vn.elib.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.elib.model.pojo.Abonne;

/**
 * @author franel
 *
 */
public class AbonneDAO extends DAO<Abonne> {

	public AbonneDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Abonne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Abonne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Abonne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Abonne find(int id) {
		Abonne abonne = new Abonne();    
		
	    try {
	    	ResultSet result = this.connect.createStatement(
	    			ResultSet.TYPE_SCROLL_INSENSITIVE, 
	    			ResultSet.CONCUR_READ_ONLY
	    			).executeQuery("SELECT * FROM abonne WHERE id_abonne = " + id); 

	    	if(result.first()){
	    		abonne = new Abonne(id, result.getString("nom"), result.getString("prenom"));
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    	return abonne;
	}	
}
