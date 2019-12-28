/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer101;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

/**
 *
 * @author chris
 */
public class FXMLDocumentController implements Initializable {
    
    
    private MediaPlayer mediaplayer;
    
    @FXML
    private MediaView mediaView;
    
    private String filePath;
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file (*.mp4)", "*.mp4");
        filechooser.getExtensionFilters().add(filter);
        File file = filechooser.showOpenDialog(null);
        filePath = file.toURI().toString();
        
        if(filePath != null) {
        Media media = new Media(filePath);
        mediaplayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaplayer);
        mediaplayer.play();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
