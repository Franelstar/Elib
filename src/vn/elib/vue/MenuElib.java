/**
 * 
 */
package vn.elib.vue;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.JTextField;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import vn.elib.controller.Global;
import vn.elib.model.dao.DAO;
import vn.elib.model.dao.DAOFactory;
import vn.elib.model.pojo.Abonne;
import vn.elib.model.pojo.Emprunt;
import vn.elib.model.pojo.Exemplaire;
import vn.elib.model.pojo.Genre;
import vn.elib.model.pojo.Livre;
import vn.elib.model.pojo.LivreEmprunte;

/**
 * @author franel
 *
 */
public class MenuElib implements Initializable {

	int by;
	/*
	private Livre livreEmpr;
	private Magazine magazineEmpr;
	private Dictionnaire dictionnaireEmpr;
	private  Compte cmpt;
	private Adherent adherent;
	private Historique historiqueSelection;
	*/ 
	
	
	//les FXML de tableu livre
    @FXML
    private TableView<Livre> tableulivre;
    @FXML
    private TableColumn<Livre,String> isbn;
    @FXML
    private TableColumn<Livre,String> titre;
    @FXML
    private TableColumn<Livre,String> auteur;
    @FXML
    private TableColumn<Livre,String>editeur;
    @FXML
    private TableColumn<Livre,Date> anne;
    @FXML
    private TableColumn<Livre,Integer> nbrpage;
    @FXML
    private TableColumn<Livre,String> tome;
    @FXML
    private TableColumn<Livre,Genre> genre;
	@FXML
    private TextField cherche;
    @FXML
    private JFXButton emprunterB;
    @FXML
    private Label echecemprunter;
    @FXML
    private Label succeeprunter;
    
    //les FXML de tableu livre emprunté
    @FXML
    private TableView<LivreEmprunte> tableulivreE;
    @FXML
    private TableColumn<LivreEmprunte,String> isbnE;
    @FXML
    private TableColumn<LivreEmprunte,String> rfidE;
    @FXML
    private TableColumn<LivreEmprunte,String> titreE;
    @FXML
    private TableColumn<LivreEmprunte,String> auteurE;
    @FXML
    private TableColumn<LivreEmprunte,String>editeurE;
    @FXML
    private TableColumn<LivreEmprunte,Integer> nbrpageE;
    @FXML
    private TableColumn<LivreEmprunte,String> tomeE;
    @FXML
    private TableColumn<LivreEmprunte,Date> date_empruntE;
    @FXML
    private TableColumn<LivreEmprunte,Date> delaisE;
    @FXML
    private JFXButton remettreE;
    @FXML
    private Label echecRemettre;
    @FXML
    private Label succeeRemetre;
    
  //les FXML de tableu livre Historique
    @FXML
    private TableView<LivreEmprunte> tableulivreH;
    @FXML
    private TableColumn<LivreEmprunte,String> isbnH;
    @FXML
    private TableColumn<LivreEmprunte,String> titreH;
    @FXML
    private TableColumn<LivreEmprunte,String> auteurH;
    @FXML
    private TableColumn<LivreEmprunte,String>editeurH;
    @FXML
    private TableColumn<LivreEmprunte,Date> anneH;
    @FXML
    private TableColumn<LivreEmprunte,Integer> nbrpageH;
    @FXML
    private TableColumn<LivreEmprunte,String> tomeH;
    @FXML
    private TableColumn<LivreEmprunte,Date> date_empruntH;
    @FXML
    private TableColumn<LivreEmprunte,Date> date_remisH;
    
    @FXML
    private Label title;
    
    private ObservableList<Livre> data = FXCollections.observableArrayList();
    private ObservableList<LivreEmprunte> dataEmprunte = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		title.setText(Global.abonne.getPrenom());
		tableLivre();
		tableLivreEmprunt();
		tableLivreHistorique();
	}
		
	public void  tableLivre(){
    	
    	tableulivre.getItems().clear();
    	
    	DAO<Livre> livreDao = DAOFactory.getLivreDAO();
		data = livreDao.find();
			
		isbn.setCellValueFactory(new PropertyValueFactory<Livre,String>("id"));
		titre.setCellValueFactory(new PropertyValueFactory<Livre,String>("titre"));
		auteur.setCellValueFactory(new PropertyValueFactory<Livre,String>("auteur"));
		editeur.setCellValueFactory(new PropertyValueFactory<Livre,String>("editeur"));
		anne.setCellValueFactory(new PropertyValueFactory<Livre,Date>("annee"));
		anne.setCellFactory(column -> {
	        TableCell<Livre, Date> cell = new TableCell<Livre, Date>() {
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            
	            Calendar c = Calendar.getInstance();

	            @Override
	            protected void updateItem(Date item, boolean empty) {
	                super.updateItem(item, empty);
	                if(empty) {
	                    setText(null);
	                }
	                else {
	                	c.setTime(item);
	                    this.setText(sdf.format(c.getTime()));

	                }
	            }
	        };

	        return cell;
	    });
		nbrpage.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("nbre_page"));
		tome.setCellValueFactory(new PropertyValueFactory<Livre,String>("tome"));
		//genre.setCellValueFactory(new PropertyValueFactory<Livre,Genre>("genre Nomgenre"));
		/*genre.setCellFactory(column -> {
	        TableCell<Livre, Genre> cell = new TableCell<Livre, Genre>() {
	            private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	            @Override
	            protected void updateItem(Genre item, boolean empty) {
	                super.updateItem(item, empty);
	                if(empty) {
	                    setText(null);
	                }
	                else {
	                    this.setText("toto");

	                }
	            }
	        };

	        return cell;
	    });*/
		
		tableulivre.setItems(data);
    }
	
	public void  tableLivreEmprunt(){
    	
    	tableulivreE.getItems().clear();
    	
    	DAO<LivreEmprunte> livreEmprunte = DAOFactory.getLivreEmprunteDAO();
    	dataEmprunte = livreEmprunte.find();
			
		isbnE.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,String>("isbn"));
		rfidE.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,String>("rfid"));
		titreE.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,String>("titre"));
		auteurE.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,String>("auteur"));
		editeurE.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,String>("editeur"));
		tomeE.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,String>("tome"));
		nbrpageE.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,Integer>("nbre_page"));
	    date_empruntE.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,Date>("date_emprunt"));
	    date_empruntE.setCellFactory(column -> {
	        TableCell<LivreEmprunte, Date> cell = new TableCell<LivreEmprunte, Date>() {
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            
	            Calendar c = Calendar.getInstance();

	            @Override
	            protected void updateItem(Date item, boolean empty) {
	                super.updateItem(item, empty);
	                if(empty) {
	                    setText(null);
	                }
	                else {
	                	c.setTime(item);
	                    this.setText(sdf.format(c.getTime()));

	                }
	            }
	        };

	        return cell;
	    });
	    delaisE.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,Date>("delais"));
	    delaisE.setCellFactory(column -> {
	        TableCell<LivreEmprunte, Date> cell = new TableCell<LivreEmprunte, Date>() {
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            
	            Calendar c = Calendar.getInstance();

	            @Override
	            protected void updateItem(Date item, boolean empty) {
	                super.updateItem(item, empty);
	                if(empty) {
	                    setText(null);
	                }
	                else {
	                	c.setTime(item);
	                    this.setText(sdf.format(c.getTime()));

	                }
	            }
	        };

	        return cell;
	    });
		
		tableulivreE.setItems(dataEmprunte.filtered(t -> t.getDate_remise() == null));
    }
	
public void  tableLivreHistorique(){
    	
    	tableulivreH.getItems().clear();
    	
    	DAO<LivreEmprunte> livreEmprunte = DAOFactory.getLivreEmprunteDAO();
    	dataEmprunte = livreEmprunte.find();
    	
    	
			
		isbnH.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,String>("isbn"));
		titreH.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,String>("titre"));
		auteurH.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,String>("auteur"));
		editeurH.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,String>("editeur"));
		tomeH.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,String>("tome"));
		nbrpageH.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,Integer>("nbre_page"));
		date_empruntH.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,Date>("date_emprunt"));
		date_empruntH.setCellFactory(column -> {
	        TableCell<LivreEmprunte, Date> cell = new TableCell<LivreEmprunte, Date>() {
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            
	            Calendar c = Calendar.getInstance();

	            @Override
	            protected void updateItem(Date item, boolean empty) {
	                super.updateItem(item, empty);
	                if(empty) {
	                    setText(null);
	                }
	                else {
	                	c.setTime(item);
	                    this.setText(sdf.format(c.getTime()));

	                }
	            }
	        };

	        return cell;
	    });
		date_remisH.setCellValueFactory(new PropertyValueFactory<LivreEmprunte,Date>("date_remise"));
		date_remisH.setCellFactory(column -> {
	        TableCell<LivreEmprunte, Date> cell = new TableCell<LivreEmprunte, Date>() {
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            
	            Calendar c = Calendar.getInstance();

	            @Override
	            protected void updateItem(Date item, boolean empty) {
	                super.updateItem(item, empty);
	                if(empty) {
	                    setText(null);
	                }
	                else {
	                	c.setTime(item);
	                    this.setText(sdf.format(c.getTime()));

	                }
	            }
	        };

	        return cell;
	    });
		
		tableulivreH.setItems(dataEmprunte.filtered(t -> t.getDate_remise() != null));
    }

	   /*

	    @FXML
	    private TextField cherchehistorique;

	    
		private void  detDictionnaire(ActionEvent e) throws IOException{
			try {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("DetDict.fxml" ));
				Scene scene = new Scene(root,865,347);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();	
			} catch(Exception event) {
				event.printStackTrace();
			}
		}
		
		
	    public void afficheDetDictionnaire(ActionEvent e)throws IOException,SQLException {
			DocumentBD.getConnection();
			detDictionnaire(e);
	    }
	    
	    
		*/
		
	    /*
		public static Connection getConnection() {
			Connection con=null;
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/projet_bibliotheque?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			con = DriverManager.getConnection(url,"root","");
			}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			return con;
		}
		
		
		private void  detMagazine(ActionEvent e) throws IOException{
			try {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("DetMag.fxml" ));
				Scene scene = new Scene(root,865,347);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();	
			} catch(Exception event) {
				event.printStackTrace();
			}
		}
		
		
	    public void afficheMagazine(ActionEvent e)throws IOException,SQLException {
			DocumentBD.getConnection();
			detMagazine(e);
	    }
	    
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Menu Principale////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	    
	    static Connection con = null;
	    @Override
		public void initialize(URL location, ResourceBundle resources){
			cmpt=CompteBD.getCompteByEtat(1);
		
	    	tableLivre();
	    	tableMagazine();
	    	tableDictionnaire();
	    	title.setText(cmpt.getPseudo_nom());
	    	
		}
		
	    
	    
	    
	    */
		/*
		
		public void emprunter(ActionEvent e)throws IOException,SQLException {
			//emprunter.toFront();
			documents.toBack();
			parametres.toBack();
			//rendre.toBack();
		
		}
		*/
		
/*
		 public ObservableList<Livre> data = FXCollections.observableArrayList();
		 public ObservableList<Magazine> dataMagazine = FXCollections.observableArrayList();
		 public ObservableList<Dictionnaire> dataDictionnaire = FXCollections.observableArrayList();
		 public ObservableList<Historique> dataHistorique = FXCollections.observableArrayList();
	    
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Livre////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	    

//////////////////////////Chercher/////////////////////////////////////

	   
*/
		@FXML
	    public void ByISBN(ActionEvent e)throws IOException,SQLException{ //ok
	    	by=1;
			emprunterB.setDisable(true);

	    }
		@FXML
	    public void ByTitre(ActionEvent e)throws IOException,SQLException{ //ok
	    	by=2;
			emprunterB.setDisable(true);

	    }
	   /* public void ByEdition(ActionEvent e)throws IOException,SQLException{
	    	by=3;
			emprunterB.setDisable(true);

	    }*/
		@FXML
	    public void BY(ActionEvent e)throws IOException,SQLException{ //ok
	    	emprunterB.setDisable(true);
	    	switch(by) {
	    	//case 1: ChercheByIsbn(e);break;
	    	//case 2: ChercheByEdition(e);break;
	    	case 3:break;
	    	default: break;
	    	}
	    }
	    
	    
	    /*
	    public void ChercheByIsbn(ActionEvent e)throws IOException,SQLException{
	    	tableulivre.getItems().clear();
	    	emprunterB.setDisable(true);
			String sql="SELECT document.isbn,titre,editeur,annee,nbr_page,type_livre,tome_livre,count(document.isbn) as nbr_exmpl  from Document,Livre where document.isbn=livre.isbn and code_adh is null and document.isbn=? group by document.isbn limit 1";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,cherche.getText());
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()) {
			data.add(new Livre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8)));

				}
			con.close();
			isbn.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
			titre.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
			editeur.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
			anne.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
			nbrpage.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("Nbrpage"));
			type.setCellValueFactory(new PropertyValueFactory<Livre,String>("type"));
			tome.setCellValueFactory(new PropertyValueFactory<Livre,String>("tome"));
			nbrdispo.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));

			
			tableulivre.setItems(data);
	    	
	    }
	    	    
	    public void ChercheByEdition(ActionEvent e)throws IOException,SQLException{
	    	tableulivre.getItems().clear();
	    	emprunterB.setDisable(true);			String sql="SELECT document.isbn,titre,editeur,annee,nbr_page,type_livre,tome_livre,count(document.isbn) as nbr_exmpl  from Document,Livre where document.isbn=livre.isbn and code_adh is null and titre=? group by document.isbn limit 1";
			Connection con = DocumentBD.getConnection();
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			preparedStatement.setString(1,cherche.getText());
			ResultSet rs  = preparedStatement.executeQuery();
			while(rs.next()) {
			data.add(new Livre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8)));
			
		}
			con.close();
			isbn.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
			titre.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
			editeur.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
			anne.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
			nbrpage.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("Nbrpage"));
			type.setCellValueFactory(new PropertyValueFactory<Livre,String>("type"));
			tome.setCellValueFactory(new PropertyValueFactory<Livre,String>("tome"));
			nbrdispo.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
			tableulivre.setItems(data);
	    	
	    }
	    */
	
    public void display(MouseEvent e)throws IOException,SQLException {
		Livre livreSelection = tableulivre.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur");
		if(livreSelection == null) {
			emprunterB.setDisable(true);
		}
		else if (!livreSelection.estDisponible()){
			alert.setContentText("Aucun exemplaire disponible pour ce livre");
			alert.showAndWait();
		}
		else {
			//Set<Livre> livreEmpr = abonneDao.getLivreEmprunte(Global.abonne);
			if(Global.abonne.getEmpruntEnCour().size() < Global.MaxPret) {
				emprunterB.setDisable(false);
			}
			else {
				emprunterB.setDisable(true);
				alert.setContentText("Vous avez atteind le nombre maximal de livre à emprunter");
				alert.showAndWait();
			}
		}
    }
	   
	@FXML
	public void Emprunter(ActionEvent e)throws IOException,SQLException{
		Livre livreSelection = tableulivre.getSelectionModel().getSelectedItem();
		
		Exemplaire exemplaire = livreSelection.getOneDisponible();
		
		if(exemplaire != null) {
			DAO<Emprunt> empruntDao = DAOFactory.getEmpruntDAO();
			Emprunt emprunt = new Emprunt(Global.abonne, exemplaire, Calendar.getInstance().getTime(), null);
			if(empruntDao.create(emprunt)) {
				DAO<Abonne> abonneDao = DAOFactory.getAbonneDAO();
				Global.abonne = abonneDao.find(Integer.parseInt(Global.abonne.getCarteMagnetique().getCode()));
				succeeprunter.setText("Vous avez emprunter "+livreSelection.getTitre());
				echecemprunter.setVisible(false);
			} else {
				echecemprunter.setText("Désole! il ya une erreur.");
				succeeprunter.setVisible(false);
			}
		} else {
			echecemprunter.setText("Aucun exemplaire disponible pour ce livre");
		}
		emprunterB.setDisable(true);
		DAO<LivreEmprunte> livreEmprunte = DAOFactory.getLivreEmprunteDAO();
		dataEmprunte = livreEmprunte.find();
    	tableulivreE.setItems(dataEmprunte.filtered(t -> t.getDate_remise() == null));
    	emprunterB.setDisable(true);
	}
	
	public void EmpruntSelection(MouseEvent e)throws IOException,SQLException {
		LivreEmprunte livreSelection = tableulivreE.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur");
		if(livreSelection == null) {
			remettreE.setDisable(true);
		}
		else {
			remettreE.setDisable(false);
		}
	}
	
	@FXML
	public void Remettre() {
		LivreEmprunte livreSelection = tableulivreE.getSelectionModel().getSelectedItem();
		if(livreSelection != null) {
			DAO<Emprunt> empruntDao = DAOFactory.getEmpruntDAO();
			Emprunt emprunt = empruntDao.find(livreSelection.getId_emprunt());
			
			if(empruntDao.update(emprunt)) {
				DAO<Abonne> abonneDao = DAOFactory.getAbonneDAO();
				Global.abonne = abonneDao.find(Integer.parseInt(Global.abonne.getCarteMagnetique().getCode()));
				succeeRemetre.setText("Vous avez remis le livre "+livreSelection.getTitre());
				echecRemettre.setVisible(false);
			} else {
				echecRemettre.setText("Désole! il ya une erreur.");
				succeeRemetre.setVisible(false);
			}
			DAO<LivreEmprunte> livreEmprunte = DAOFactory.getLivreEmprunteDAO();
			dataEmprunte = livreEmprunte.find();
	    	tableulivreE.setItems(dataEmprunte.filtered(t -> t.getDate_remise() == null));
	    	tableulivreH.setItems(dataEmprunte.filtered(t -> t.getDate_remise() != null));
		}
		remettreE.setDisable(true);
	}
		
	/*
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Magazin////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	public void  tableMagazine(){

		tableumagazine.getItems().clear();
		try {
		String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,periode,date_edit  from Document,magazin where document.isbn=magazin.isbn and code_adh IS NULL group by document.isbn";
		Connection con = DocumentBD.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs  = preparedStatement.executeQuery();
		while(rs.next()) {
		dataMagazine.add(new Magazine(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7)));
	}
		
		con.close();
		}catch(SQLException e) {
		e.printStackTrace();
		}
		isbnm.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
		titrem.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
		editeurm.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
		annem.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
		periode.setCellValueFactory(new PropertyValueFactory<Magazine,Integer>("periode"));
		dateedition.setCellValueFactory(new PropertyValueFactory<Magazine,String>("date_edit"));
		nbrdispom.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
		
		tableumagazine.setItems(dataMagazine);
		}

//////////////////////////Chercher/////////////////////////////////////


public void magazineBY(ActionEvent e)throws IOException,SQLException{
	emprunterBM.setDisable(true);
	switch(by) {
	case 1: ChercheMagazineByIsbn(e);break;
	case 2: ChercheMagazineByTitre(e);break;
	case 3:break;
	default: break;
	}
}



public void ChercheMagazineByIsbn(ActionEvent e)throws IOException,SQLException{
	tableumagazine.getItems().clear();
	emprunterBM.setDisable(true);
	String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,periode,date_edit  from Document,magazin where document.isbn=magazin.isbn and code_adh IS NULL and document.isbn=? group by document.isbn";
	Connection con = DocumentBD.getConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
	preparedStatement.setString(1,cherchem.getText());
	ResultSet rs  = preparedStatement.executeQuery();
	while(rs.next()) {
	dataMagazine.add(new Magazine(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7)));
	
	}
	con.close();
	isbnm.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
	titrem.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
	editeurm.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
	annem.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
	periode.setCellValueFactory(new PropertyValueFactory<Magazine,Integer>("periode"));
	dateedition.setCellValueFactory(new PropertyValueFactory<Magazine,String>("date_edit"));
	nbrdispom.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
	
	tableumagazine.setItems(dataMagazine);
	
	}

public void ChercheMagazineByTitre(ActionEvent e)throws IOException,SQLException{
	tableumagazine.getItems().clear();
	emprunterBM.setDisable(true);			
	String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,periode,date_edit  from Document,magazin where document.isbn=magazin.isbn and code_adh IS NULL and titre=? group by document.isbn";
	Connection con = DocumentBD.getConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
	preparedStatement.setString(1,cherche.getText());
	ResultSet rs  = preparedStatement.executeQuery();
	while(rs.next()) {
		dataMagazine.add(new Magazine(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7)));
		
		}
		con.close();
		isbnm.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
		titrem.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
		editeurm.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
		annem.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
		periode.setCellValueFactory(new PropertyValueFactory<Magazine,Integer>("periode"));
		dateedition.setCellValueFactory(new PropertyValueFactory<Magazine,String>("date_edit"));
		nbrdispom.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));
		
		tableumagazine.setItems(dataMagazine);

}

	public void tableMagazineSelection(MouseEvent e)throws IOException,SQLException {
		int type;
		String code = cmpt.getCode_adh();
		Magazine magazineSelection= tableumagazine.getSelectionModel().getSelectedItem();
		if(magazineSelection==null) {
			emprunterBM.setDisable(true);
		}
		else {
			magazineEmpr = DocumentBD.getMagazineByISBN(magazineSelection.getISBN());
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			type=AdherentBD.getTypeByCode(code);
			switch(type) {
			case 0: if(AdherentBD.getEtudiantByCode(code).getNbr_eprunter()<3) {
					emprunterBM.setDisable(false);
					}
					else {
						alert.setContentText("Vous avez deja depasser le nombre maximal");
						alert.showAndWait();
					}break;
		
		case 1: if(AdherentBD.getProfesseurByCode(code).getNbr_eprunter()<5) {
					emprunterBM.setDisable(false);
				}
				else {
					alert.setContentText("Vous avez deja depasser le nombre maximal");
					alert.showAndWait();
				}break;
		
		default: if(AdherentBD.getPersonneByCode(code).getNbr_eprunter()<1) {
					emprunterBM.setDisable(false);
				}
				else {
					alert.setContentText("Vous avez deja depasser le nombre maximal");
					alert.showAndWait();
				}break;
		}}
}


	public void EmprunterMagazine(ActionEvent e)throws IOException,SQLException{
		succeeprunterm.setVisible(false);
		echecemprunterm.setVisible(false);
		int test1 =DocumentBD.magazineEmprunter(magazineEmpr,cmpt.getCode_adh());
		int test2 = DocumentBD.ajouterHistorique(magazineEmpr,cmpt.getCode_adh());
		if(test2*test1!=0) {
			succeeprunterm.setText("Vous avez emprunter le document.");
			echecemprunterm.setVisible(false);
		}
		else {
			echecemprunterm.setText("D�sole! il ya un erreur.");
			succeeprunterm.setVisible(false);
		}
		tableMagazine();
		emprunterBM.setDisable(true);
	
	}

	
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Dictionnaire////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
public void  tableDictionnaire(){

tableudictionnaire.getItems().clear();
try {
String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,langue_dic,tome  from Document,dictionnaire where document.isbn=dictionnaire.isbn and code_adh IS NULL group by document.isbn";
Connection con = DocumentBD.getConnection();
PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
ResultSet rs  = preparedStatement.executeQuery();
while(rs.next()) {
dataDictionnaire.add(new Dictionnaire(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
}

con.close();
}catch(SQLException e) {
e.printStackTrace();
}
isbnd.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
titred.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
editeurd.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
anned.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
langue.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("langue"));
tomed.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("tome"));
nbrdispod.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));

tableudictionnaire.setItems(dataDictionnaire);
}

//////////////////////////Chercher/////////////////////////////////////


public void dictionnaireBY(ActionEvent e)throws IOException,SQLException{
emprunterBD.setDisable(true);
switch(by) {
case 1: ChercheDictionnaireByIsbn(e);break;
case 2: ChercheDictionnaireByTitre(e);break;
case 3:break;
default: break;
}
}



public void ChercheDictionnaireByIsbn(ActionEvent e)throws IOException,SQLException{
tableudictionnaire.getItems().clear();
emprunterBD.setDisable(true);
String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,langue_dic,tome  from Document,dictionnaire where document.isbn=dictionnaire.isbn and code_adh IS NULL and document.isbn=? group by document.isbn";
Connection con = DocumentBD.getConnection();
PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
preparedStatement.setString(1,cherched.getText());
ResultSet rs  = preparedStatement.executeQuery();
while(rs.next()) {
dataDictionnaire.add(new Dictionnaire(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
}
con.close();
isbnd.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
titred.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
editeurd.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
anned.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
langue.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("langue"));
tomed.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("tome"));
nbrdispod.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));

tableudictionnaire.setItems(dataDictionnaire);

}

public void ChercheDictionnaireByTitre(ActionEvent e)throws IOException,SQLException{
	tableudictionnaire.getItems().clear();
	emprunterBD.setDisable(true);
	String sql="SELECT document.isbn,titre,editeur,annee,count(document.isbn) as nbr_exmpl ,langue_dic,tome  from Document,dictionnaire where document.isbn=dictionnaire.isbn and code_adh IS NULL and document.titre=? group by document.isbn";
	Connection con = DocumentBD.getConnection();
	PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
	preparedStatement.setString(1,cherched.getText());
	ResultSet rs  = preparedStatement.executeQuery();
	while(rs.next()) {
	dataDictionnaire.add(new Dictionnaire(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
	}
	con.close();
	isbnd.setCellValueFactory(new PropertyValueFactory<Document,String>("ISBN"));
	titred.setCellValueFactory(new PropertyValueFactory<Document,String>("Titre"));
	editeurd.setCellValueFactory(new PropertyValueFactory<Document,String>("editeur"));
	anned.setCellValueFactory(new PropertyValueFactory<Document,Integer>("Annee"));
	langue.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("langue"));
	tomed.setCellValueFactory(new PropertyValueFactory<Dictionnaire,String>("tome"));
	nbrdispod.setCellValueFactory(new PropertyValueFactory<Document,Integer>("nombreExemplaire"));

	tableudictionnaire.setItems(dataDictionnaire);
}

public void tableDictionnaireSelection(MouseEvent e)throws IOException,SQLException {
int type;
String code = cmpt.getCode_adh();
Dictionnaire dictionnaireSelection= tableudictionnaire.getSelectionModel().getSelectedItem();
if(dictionnaireSelection==null) {
emprunterBD.setDisable(true);
}
else {
dictionnaireEmpr = DocumentBD.getDictionnaireByISBN(dictionnaireSelection.getISBN());
Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Erreur");
type=AdherentBD.getTypeByCode(code);
switch(type) {
case 0: if(AdherentBD.getEtudiantByCode(code).getNbr_eprunter()<3) {
emprunterBD.setDisable(false);
}
else {
alert.setContentText("Vous avez deja depasser le nombre maximal");
alert.showAndWait();
}break;

case 1: if(AdherentBD.getProfesseurByCode(code).getNbr_eprunter()<5) {
emprunterBD.setDisable(false);
}
else {
alert.setContentText("Vous avez deja depasser le nombre maximal");
alert.showAndWait();
}break;

default: if(AdherentBD.getPersonneByCode(code).getNbr_eprunter()<1) {
emprunterBD.setDisable(false);
}
else {
alert.setContentText("Vous avez deja depasser le nombre maximal");
alert.showAndWait();
}break;
}}
}


public void EmprunterDictionnaire(ActionEvent e)throws IOException,SQLException{
	
	int test1 =DocumentBD.dictionnaireEmprunter(dictionnaireEmpr,cmpt.getCode_adh());
	int test2 = DocumentBD.ajouterHistorique(dictionnaireEmpr,cmpt.getCode_adh());
	if(test2*test1!=0) {
	succeeprunterd.setText("Vous avez emprunter le document.");
	echecemprunterd.setVisible(false);
	}
	else {
	echecemprunterd.setText("D�sole! il ya un erreur.");
	succeeprunterd.setVisible(false);
	}
	tableDictionnaire();
	emprunterBD.setDisable(true);

}

//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Historique///////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	
	
	
	
	 public void  tableHistorique(){
	    	tableuHistorique.getItems().clear();
	    	retourB.setDisable(true);
			try {
				String sql="SELECT num_enrg,isbn,titre,type,date_debut,date_fin from historique where code_adh=?";
				Connection con = DocumentBD.getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
				preparedStatement.setString(1,cmpt.getCode_adh());
				ResultSet rs  = preparedStatement.executeQuery();
				while(rs.next()) {
					
					switch(rs.getInt(4)) {
					case 0:dataHistorique.add(new Historique(rs.getInt(1),rs.getString(2),rs.getString(3),"Livre",rs.getString(5),rs.getString(6)));break;
					case 1:dataHistorique.add(new Historique(rs.getInt(1),rs.getString(2),rs.getString(3),"Magazine",rs.getString(5),rs.getString(6)));break;
					default:dataHistorique.add(new Historique(rs.getInt(1),rs.getString(2),rs.getString(3),"Dictionnaire",rs.getString(5),rs.getString(6)));
					}
					
					
				}
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			isbnH.setCellValueFactory(new PropertyValueFactory<Historique,String>("ISBN"));
			titreH.setCellValueFactory(new PropertyValueFactory<Historique,String>("Titre"));
			typeH.setCellValueFactory(new PropertyValueFactory<Historique,String>("Type"));
			dateEH.setCellValueFactory(new PropertyValueFactory<Historique,String>("DateE"));
			dateRH.setCellValueFactory(new PropertyValueFactory<Historique,String>("DateR"));
			num_enrgH.setCellValueFactory(new PropertyValueFactory<Historique,Integer>("num_enrg"));
			totalelivre.setText(DocumentBD.statistiqueLivre(cmpt.getCode_adh()));
			totalemagazine.setText(DocumentBD.statistiqueMagazine(cmpt.getCode_adh()));
			totaledictionnaire.setText(DocumentBD.statistiqueDictionnaire(cmpt.getCode_adh()));
			tableuHistorique.setItems(dataHistorique);
	    }
	
	
		public void selectionHistorique(MouseEvent e)throws IOException,SQLException {
			 historiqueSelection= tableuHistorique.getSelectionModel().getSelectedItem();
			if(historiqueSelection==null) {
				retourB.setDisable(true);
			}
			else {
				if(historiqueSelection.getDateR().equals("non retourner")) {
				retourB.setDisable(false);
				}
				else {
					retourB.setDisable(true);
				}
			}
		
	}
		public void retour(ActionEvent e)throws IOException,SQLException{
			DocumentBD.retour(historiqueSelection.getNum_enrg(),cmpt.getCode_adh());
			tableHistorique();
		}*/
		
//////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
//////////////////////////Modifier les informations de Profile///////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
	
/*	
	//Mot de passe
public void motpassModif(ActionEvent e )throws IOException,ParseException{
		Compte cmp = new Compte();
		if(oldmotpass.getText().equals(cmpt.getMot_pass())) {
				if(newmotpass.getText().equals(confirmer.getText())) {
					
					cmp.setMot_pass(confirmer.getText());
					int statue = CompteBD.motpassModif(cmp,1);
					if(statue>0) {
						motpassmodifsucce.setText("Vous avez modifier votre mot de passe");
						motpassmodifechec.setVisible(false);
					}else {
						motpassmodifechec.setText("Erreur technique");
						motpassmodifsucce.setVisible(false);
					}
				}
			else {
				motpassmodifechec.setText("Mot de pass different.");
				motpassmodifsucce.setVisible(false);
			}
			}
		else {
			motpassmodifechec.setText("Mot de passe incorrect");
			motpassmodifsucce.setVisible(false);
		}
		
	}
	*/
	

///////////////Pseudo Nom///////////////////////////////////////////////
/*
	public void personneModif(ActionEvent e )throws IOException,ParseException{

		Compte cmp = new Compte();
		cmp.setPseudo_nom(pseudomodif.getText());
		cmp.setTelephone(telephonemodif.getText());
		cmp.setAdresse(adressemodif.getText());
		int statue = CompteBD.personneModif(cmp,cmpt.getCode_adh());
		if(statue>0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setContentText("Vous avez modifier les informations! ");
			
			alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Message");
			alert.setContentText("Erreur, essayez encore");
			
			alert.showAndWait();
		}
		
	}
*/

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@FXML
	public void deconnecter(ActionEvent e)throws IOException,SQLException{
		Global.abonne = null;
		loadAuthen(e);
	}
	
	public void loadAuthen(ActionEvent e) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
		Scene s = new Scene(root,1070,590);
		Stage fenetre = (Stage) ((Node)e.getSource()).getScene().getWindow();
		fenetre.setScene(s);
		fenetre.setResizable(false);
        fenetre.show();		
	}
		
	@FXML
    public void afficheDetLivre(ActionEvent e)throws IOException,SQLException {
		//DocumentBD.getConnection();
		detLivre(e);
    }
	
	private void detLivre(ActionEvent e) throws IOException{
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("DetailLivre.fxml"));
			Scene scene = new Scene(root,865,347);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();	
		} catch(Exception event) {
			event.printStackTrace();	
		}
	}
}
