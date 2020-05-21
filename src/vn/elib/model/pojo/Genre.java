/**
 * 
 */
package vn.elib.model.pojo;

/**
 * @author franel
 *
 */
public class Genre {

	private int id;
	private String nomGenre;
	
	/**
	 * @param id
	 * @param nomGenre
	 */
	public Genre(int id, String nomGenre) {
		super();
		this.id = id;
		this.nomGenre = nomGenre;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nomGenre
	 */
	public String getNomGenre() {
		return nomGenre;
	}

	/**
	 * @param nomGenre the nomGenre to set
	 */
	public void setNomGenre(String nomGenre) {
		this.nomGenre = nomGenre;
	}
}
