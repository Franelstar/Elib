/**
 * 
 */
package vn.elib.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.elib.model.pojo.Abonne;
import vn.elib.model.pojo.CarteMagnetique;

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
	public Abonne find(int carte) {
		Abonne abonne = new Abonne();    
		
	    try {
	    	String query = "SELECT id_abonne, nom_abonne, prenom_abonne, sexe_abonne, code, code_pin, validite FROM abonne";
	        query += " INNER JOIN carte_magnetique ON id_carte = id_carte_magnetique";
	        query += " WHERE code = ?";
	    	
	    	PreparedStatement prepare = this.connect.prepareStatement(query,
	    			ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
	    	
	    	prepare.setString(1, String.valueOf(carte));
	    	
	    	ResultSet result = prepare.executeQuery();
	    	
			if(result.next()) {
				System.out.println("ok");
				//cmpt.setPseudo_nom(resultSet.getString(2));
				//cmpt.setMot_pass(resultSet.getString(4));
				//cmpt.setCode_adh(resultSet.getString(1));
				//cmpt.setTypecompte(resultSet.getInt(6));
			}

	    	if(result.first()){
	    		CarteMagnetique carte_magnetique = new CarteMagnetique(result.getString("code"), result.getInt("code_pin"), result.getDate("validite"));
	    		abonne = new Abonne(carte, result.getString("nom_abonne"), result.getString("prenom_abonne"));
	    		abonne.setCarteMagnetique(carte_magnetique);
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    	return abonne;
	}	
}
