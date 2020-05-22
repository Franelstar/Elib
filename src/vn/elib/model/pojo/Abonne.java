package vn.elib.model.pojo;

/**
 * 
 * @author franel
 *
 */
public class Abonne {
	
	private int id;
	private String nom;
	private String prenom;
	private CarteMagnetique carte;
	private Character sexe;
	
	public Abonne(int id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Abonne() {
		
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the carteMagnetique
	 */
	public CarteMagnetique getCarteMagnetique() {
		return carte;
	}

	/**
	 * @param carteMagnetique the carteMagnetique to set
	 */
	public void setCarteMagnetique(CarteMagnetique carteMagnetique) {
		if(carteMagnetique != null) {
			this.carte = carteMagnetique;
		}
	}

	/**
	 * @return the sexe
	 */
	public Character getSexe() {
		return sexe;
	}

	/**
	 * @param sexe the sexe to set
	 */
	public void setSexe(Character sexe) {
		this.sexe = sexe;
	}
}
