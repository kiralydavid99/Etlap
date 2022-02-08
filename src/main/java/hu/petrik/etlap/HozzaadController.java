package hu.petrik.etlap;


import hu.petrik.etlap.EtlapDb;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class HozzaadController extends Controller {
    @FXML
    private TextField inputNev;
    @FXML
    private TextField inputKategoria;
    @FXML
    private Spinner<Integer> inputAr;


    @FXML
    public void onHozzaadButtonClick(ActionEvent actionEvent) {
        String nev = inputNev.getText().trim();
        String kategoria = inputKategoria.getText().trim();
        int ar = 0;

        if (nev.isEmpty()) {
            alert("Név megadása kötelező");
            return;
        }
        if (kategoria.isEmpty()) {
            alert("Kategória megadása kötelező");
            return;
        }
        try {
            ar = inputAr.getValue();
        } catch (NullPointerException ex) {
            alert("Az ár megadása kötelező");
            return;
        } catch (Exception ex) {
            alert("A hossz csak 1 és 999 közötti szám lehet");
            return;
        }
        if (ar < 1 || ar > 999) {
            alert("A hossz csak 1 és 999 közötti szám lehet");
            return;
        }


        try {
            EtlapDb db = new EtlapDb();
            int siker = db.etelHozzaadasa(nev, kategoria, ar);
            if (siker == 1) {
                alert("Étel hozzáadása sikeres");
            } else {
                alert("Étel hozzáadása sikeretelen");
            }
        } catch (Exception e) {
            hibaKiir(e);
        }

    }
}