/**
 * 
 */
package vn.elib.model.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author franel
 *
 */
public class Livre {

	private String isbn;
	private String titre;
	private String auteur;
	private String editeur;
	private int nbre_page;
	private int tome;
	private Date annee;
	private Genre genre;
	private Set<Exemplaire> listExemplaire = new HashSet<Exemplaire>();
	private int nombreExemplaire;
	
	/**
	 * @param id
	 * @param titre
	 * @param auteur
	 * @param editeur
	 * @param genre
	 */
	public Livre(String id, String titre, String auteur, String editeur, Genre genre) {
		this.isbn = id;
		this.titre = titre;
		this.auteur = auteur;
		this.editeur = editeur;
		this.genre = genre;
		this.nombreExemplaire = 0;
	}
	
	public Livre() {
		this.nombreExemplaire = 0;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return isbn;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.isbn = id;
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
		this.nombreExemplaire = listExemplaire.size();
	}

	/**
	 * @param Exemplaire
	 */
	public void addExemplaire(Exemplaire exemplaire){
		if(!this.listExemplaire.contains(exemplaire)) {
			this.listExemplaire.add(exemplaire);
			this.nombreExemplaire = listExemplaire.size();
		}
	}

	/**
	 * @param Exemplaire
	 */
	public void removeEleve(Exemplaire exemplaire){
		this.listExemplaire.remove(exemplaire);
		this.nombreExemplaire = listExemplaire.size();
	}

	/**
	 * @return the tome
	 */
	public int getTome() {
		return tome;
	}

	/**
	 * @param tome the tome to set
	 */
	public void setTome(int tome) {
		this.tome = tome;
	}

	/**
	 * @return the annee
	 */
	public Date getAnnee() {
		return annee;
	}

	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(Date annee) {
		this.annee = annee;
	}

	/**
	 * @return the nbre_page
	 */
	public int getNbre_page() {
		return nbre_page;
	}

	/**
	 * @param nbre_page the nbre_page to set
	 */
	public void setNbre_page(int nbre_page) {
		this.nbre_page = nbre_page;
	}

	/**
	 * @return the nombreExemplaire
	 */
	public int getNombreExemplaire() {
		return nombreExemplaire;
	}

	/**
	 * @param nombreExemplaire the nombreExemplaire to set
	 */
	public void setNombreExemplaire(int nombreExemplaire) {
		this.nombreExemplaire = nombreExemplaire;
	}
	
	/**
	 * @return Boolean if one exemplaire is disponible
	 */
	public Boolean estDisponible() {
		for(Exemplaire exp :listExemplaire) {
			if(exp.getDisponible())
				return true;
		}
		return false;
	}
	
	/**
	 * @return One exemplaire disponible
	 */
	public Exemplaire getOneDisponible() {
		for(Exemplaire exp :listExemplaire) {
			if(exp.getDisponible())
				return exp;
		}
		return null;
	}
}
