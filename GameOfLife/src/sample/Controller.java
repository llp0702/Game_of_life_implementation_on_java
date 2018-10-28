package sample;

import Logic.Monde;
import Logic.ToolsJeuDeLaVie;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML protected Spinner<Integer> nbColonnesSpinner;
    @FXML protected Spinner<Integer> nbLignesSpinner;
    @FXML protected Spinner<Integer> nbGenerationsSpinner;
    @FXML protected TextField txtFieldSrc;
    @FXML public void parcourirBtn(ActionEvent action){
        Stage stageParcourir = new Stage();
        stageParcourir.setTitle("Parcourir");
        FileChooser fChoser = new FileChooser();
        File f = fChoser.showOpenDialog(stageParcourir);
        String chemin = f.getAbsolutePath();
        txtFieldSrc.setText(chemin);
    }
    @FXML public void validerBtn(ActionEvent action){
        try{
            System.out.println(nbColonnesSpinner.getValue().toString()+ " "+nbLignesSpinner.getValue().toString());
            String[] arg = {nbColonnesSpinner.getValue().toString(),nbLignesSpinner.getValue().toString(),txtFieldSrc.getText()};
            affichage(arg,nbGenerationsSpinner.getValue());
        }catch (Exception e){
            System.out.println("Exception");
        }
    }
    private static void affichage(String [] args,int nbGenerations) throws IOException {
        int x=Integer.parseInt(args[0]),y=Integer.parseInt(args[1]);

        boolean[][] tab = ToolsJeuDeLaVie.readFromImg(args[2],x,y);
        Monde m = new Monde(x,y,0,0,tab);
        SimpleInterface ui = new SimpleInterface(args[2],x*25,y*25); // fenetre
        ui.createArea(m.getL(), m.getH()); // creation de l'image dans l'interface
        ui.refresh();
        for (int t=0;t<nbGenerations;t++){
            try{Thread.sleep(100);}catch(InterruptedException e){e.printStackTrace();}
            m.genSuiv();
            for (int i=0;i<m.getH();i++)
                for (int j=0;j<m.getL();j++){
                    if(m.get(i,j))
                        ui.setRGB(j, i, Color.RED);
                    else
                        ui.setRGB(j, i, Color.WHITE);
                }
            ui.refresh();
        }
    }

    @FXML public void initialize(){
        SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1024,1);
        nbLignesSpinner.setValueFactory(svf);
        nbLignesSpinner.setEditable(true);
        TextFormatter formatter = new TextFormatter(svf.getConverter(), svf.getValue());
        nbLignesSpinner.getEditor().setTextFormatter(formatter);
        svf.valueProperty().bindBidirectional(formatter.valueProperty());

        SpinnerValueFactory<Integer> svf2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1024,1);
        nbColonnesSpinner.setValueFactory(svf2);
        nbColonnesSpinner.setEditable(true);
        TextFormatter formatter2 = new TextFormatter(svf2.getConverter(), svf2.getValue());
        nbColonnesSpinner.getEditor().setTextFormatter(formatter2);
        svf2.valueProperty().bindBidirectional(formatter2.valueProperty());

        SpinnerValueFactory<Integer> svf3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000000,1000);
        nbGenerationsSpinner.setValueFactory(svf3);
        nbGenerationsSpinner.setEditable(true);
        TextFormatter formatter3 = new TextFormatter(svf3.getConverter(), svf3.getValue());
        nbGenerationsSpinner.getEditor().setTextFormatter(formatter3);
        svf3.valueProperty().bindBidirectional(formatter3.valueProperty());

    }
}
