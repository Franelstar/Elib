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

	    	if(result.first()){
	    		CarteMagnetique carte_magnetique = new CarteMagnetique(result.getString("code"), result.getInt("code_pin"), result.getDate("validite"));
	    		abonne = new Abonne(result.getInt("id_abonne"), result.getString("nom_abonne"), result.getString("prenom_abonne"));
	    		abonne.setCarteMagnetique(carte_magnetique);
	    		
	    		String query2 = "SELECT * FROM emprunt";
	    		query2 += " INNER JOIN exemplaire ON emprunt.id_exemplaire = exemplaire.id_exemplaire";
		        query2 += " WHERE id_abonne = ?";
		        
		        PreparedStatement prepare2 = this.connect.prepareStatement(query2,
		    			ResultSet.TYPE_SCROLL_SENSITIVE,
	                    ResultSet.CONCUR_UPDATABLE);
		    	
		    	prepare2.setInt(1, abonne.getId());
		    	
		    	ResultSet result2 = prepare2.executeQuery();
		    	
		    	while(result2.next()) {
		    		Emprunt emp = new Emprunt();
		    		
		    		Rfid rfid = new Rfid(result2.getString("rfid"));
					Exemplaire exp = new Exemplaire(result2.getInt("id_exemplaire"), rfid,
							!result2.getBoolean("etat_emprunt"));
					
					emp.setExempalire(exp);
					emp.setAbonne(Global.abonne);
					emp.setDate_emprunt(result2.getDate("date_emprunt"));
					emp.setDate_retour(result2.getDate("date_retour"));
					emp.setId(result2.getInt("id_emprunt"));
					
					abonne.addEmprunt(emp);
				}
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    
	    return abonne;
	}

	@Override
	public ObservableList<Abonne> find() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void getLivreEmprunte(Abonne a) {
		
	}
}
