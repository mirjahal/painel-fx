package painelfx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;

public class PainelController implements Initializable {

	@FXML
	private Text senhaDaVez;

	@FXML
	private TextField campoNumeroDigitado;

	private AudioClip audio;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarAudio();
		adicionarEventoInformarSenha();
	}

	private void carregarAudio() {
		String caminhoArquivoDeAudio = PainelController.class.getResource("../../audio/chamada.mp3").toString();
		audio = new AudioClip(caminhoArquivoDeAudio);
	}

	private void adicionarEventoInformarSenha() {
		campoNumeroDigitado.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
            	if (keyEvent.getCode().toString().equals("ENTER")) {
            		String numeroProximaSenha = getNumeroProximaSenha();

            		if (!numeroProximaSenha.isEmpty()) {
	            		audio.stop();
	            		senhaDaVez.setText(numeroProximaSenha);
	            		audio.play();
            		}

            		campoNumeroDigitado.clear();
            	}
            }
        });
	}

	private String getNumeroProximaSenha() {
		String numeroProximaSenha = "";

		if (possuiApenasNumeros(campoNumeroDigitado.getText())) {
			numeroProximaSenha = campoNumeroDigitado.getText();
		} else if (campoNumeroDigitado.getText().isEmpty() && !senhaDaVez.getText().isEmpty()) {
			int numeroSenhaAtual = Integer.valueOf(senhaDaVez.getText());
			numeroProximaSenha = Integer.toString(numeroSenhaAtual + 1);
		}

		return numeroProximaSenha;
	}

	private boolean possuiApenasNumeros(String texto) {
		if (texto.isEmpty()) {
			return false;
		}

		for (int i = 0; i < texto.length(); i++) {
			if (!Character.isDigit(texto.charAt(i))) {
				return false;
			}
		}

		return true;
	}

}
