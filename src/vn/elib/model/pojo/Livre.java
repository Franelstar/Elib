/**
 * 
 */
package vn.elib.model.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author franel
 *
 */
public class Livre {

	private int id;
	private String titre;
	private String auteur;
	private String editeur;
	private Genre genre;
	private Set<Exemplaire> listExemplaire = new HashSet<Exemplaire>();
	
	/**
	 * @param id
	 * @param titre
	 * @param auteur
	 * @param editeur
	 * @param genre
	 */
	public Livre(int id, String titre, String auteur, String editeur, Genre genre) {
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.editeur = editeur;
		this.genre = genre;
	}
	
	public Livre() {
		
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
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	/**
	 * @return the editeur
	 */
	public String getEditeur() {
		return editeur;
	}

	/**
	 * @param editeur the editeur to set
	 */
	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	/**
	 * @return the genre
	 */
	public Genre getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	/**
	 * @return list of exemplaire
	 */
	public Set<Exemplaire> getListExemplaire() {
		return listExemplaire;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setListExemplaire(Set<Exemplaire> listExemplaire) {
		this.listExemplaire = listExemplaire;
	}

	/**
	 * @param Exemplaire
	 */
	public void addExemplaire(Exemplaire exemplaire){
		if(!this.listExemplaire.contains(exemplaire))
	    this.listExemplaire.add(exemplaire);
	}

	/**
	 * @param Exemplaire
	 */
	public void removeEleve(Exemplaire exemplaire){
		this.listExemplaire.remove(exemplaire);
	}
}
