package cs102a.aeroplane.util;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MusicPlayerBkp extends JFrame {
    private File f;
    private URI uri;
    private URL url;
    private AudioClip aau;

    public MusicPlayerBkp() {
        try {
//            System.out.println("start");
            f = new File(System.getProperty("user.dir") + "/Music/Summer.wav");
            uri = f.toURI();
            url = uri.toURL();

            aau = Applet.newAudioClip(url);

            aau.loop();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void stopMusic() {
        aau.stop();
    }


}
