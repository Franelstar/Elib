/**
 * 
 */
package vn.elib.model.dao;

import java.sql.Connection;

import vn.elib.model.ElibConnexion;
import vn.elib.model.pojo.Abonne;
import vn.elib.model.pojo.Livre;

/**
 * @author franel
 *
 */
public class DAOFactory {

	protected static final Connection conn = ElibConnexion.getInstance();
	
	/**
	 * Retourne un objet Livre interagissant avec la BDD
	 * @return DAO
	 */
	public static DAO<Livre> getLivreDAO(){
	   return new LivreDAO(conn);
	}
	
	/**
	 * Retourne un objet Abonne interagissant avec la BDD
	 * @return DAO
	 */
	public static DAO<Abonne> getAbonneDAO(){
	   return new AbonneDAO(conn);
	}
}
