/**
 * 
 */
package vn.elib.model.dao;

import java.sql.Connection;

import vn.elib.model.pojo.Livre;

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
}
