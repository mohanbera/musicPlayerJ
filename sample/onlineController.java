package sample;

import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;


public class onlineController {
    @FXML
    private ImageView imageViewO;
    @FXML
    private JFXSlider sliderO;
    @FXML
    private Text curTO;
    @FXML
    private Text totlTO;

    static MediaPlayer mediaPlayer;
    static double tTimemilO=0;
    public static Media soundO=null;
    public static Duration durationO=null;
    public static double volumeO=0;

    public void initialize()
    {
        Controller controller=new Controller();
        mediaPlayer=controller.retMePlayer();
        tTimemilO=controller.retTime();
        soundO=controller.retMed();
        volumeO=mediaPlayer.getVolume();



        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                System.out.println("HHHHH");
                durationO = soundO.getDuration();
                tTimemilO = durationO.toMillis();
                double tTime2 = durationO.toSeconds();
                int mnt = (int) tTime2 / 60;

                int sec = (int) tTime2 % 60;
                String minStr0 = String.valueOf(mnt), secStr0 = String.valueOf(sec);
                if (mnt < 10) {
                    minStr0 = "0" + mnt;
                }
                if (sec < 10) {
                    secStr0 = "0" + sec;
                }
                final String str01 = minStr0 + " : " + secStr0;
                Platform.runLater(() -> {
                    totlTO.setText(str01);
                });
                mediaPlayer.play();
                mediaPlayer.setVolume(volumeO);
            }
        });

        mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                Platform.runLater(() ->
                {
                    System.out.println("YES");
                    double curTime1 = mediaPlayer.getCurrentTime().toSeconds();
                    sliderO.setValue((curTime1 * 1000 / tTimemilO) * 100);
                    String str01= String.valueOf((int) curTime1 / 60);
                    String str02 = String.valueOf((int) curTime1 % 60);
                    //timeLbl1.setText(str1 + " : " + str2);
                });
            }
        });

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                sliderO.setValue(0);
                mediaPlayer.dispose();
                mediaPlayer.play();
            }
        });

        imageViewO.setImage(controller.retImage());
    }

    @FXML
    void playOnlineSong(String address,String id1) throws InterruptedException, IOException
    {

    }


}
