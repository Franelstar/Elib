/**
 * 
 */
package vn.elib.model.pojo;

/**
 * @author franel
 *
 */
public class Genre {

	private int id_genre;
	private String nom_genre;
	
	/**
	 * @param id
	 * @param nomGenre
	 */
	public Genre(int id, String nomGenre) {
		super();
		this.id_genre = id;
		this.nom_genre = nomGenre;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id_genre;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id_genre = id;
	}

	/**
	 * @return the nomGenre
	 */
	public String getNomGenre() {
		return nom_genre;
	}

	/**
	 * @param nomGenre the nomGenre to set
	 */
	public void setNomGenre(String nomGenre) {
		this.nom_genre = nomGenre;
	}
}
