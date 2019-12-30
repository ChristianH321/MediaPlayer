/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer101;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

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
    private Slider slider;
    
    @FXML
    private Slider seekSlider;
    
    
    
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
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();
            
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        
            slider.setValue(mediaplayer.getVolume() *100);
            slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaplayer.setVolume(slider.getValue()/100);
            }
            });
        
            mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
               seekSlider.setValue(newValue.toSeconds());
            }
            });
            
            seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaplayer.seek(Duration.seconds(seekSlider.getValue()));
            }
            });
        
        mediaplayer.play();
        }
    }
    
    @FXML
    private void pauseVideo(ActionEvent event) {
        mediaplayer.pause();
    }
    
    @FXML
    private void playVideo(ActionEvent event) {
        mediaplayer.play();
        mediaplayer.setRate(1);
    }
    
    @FXML
    private void stopVideo(ActionEvent event) {
        mediaplayer.stop();
    }
    
    @FXML
    private void fastVideo(ActionEvent event) {
        mediaplayer.setRate(1.5);
    }
    
    @FXML
    private void fasterVideo(ActionEvent event) {
        mediaplayer.setRate(2);
    }
    
    @FXML
    private void slowVideo(ActionEvent event) {
        mediaplayer.setRate(0.75);
    }
    
    @FXML
    private void slowerVideo(ActionEvent event) {
        mediaplayer.setRate(0.5);
    }
    
    @FXML
    private void exitVideo(ActionEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
