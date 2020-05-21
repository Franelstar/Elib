package vn.elib.vue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Authentification {
	
	@FXML
	Label messagec;

	public void Connecter() {
		messagec.setText("votre nom d'utilisateur et/ou mot de passe est incorrect");
	}
}
