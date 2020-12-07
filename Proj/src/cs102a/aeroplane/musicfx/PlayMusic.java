package cs102a.aeroplane.musicfx;

public class PlayMusic {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer("src\\BGM_Incarnation.wav");
        player.setVolume(6f);
        player.setLoop(true);
        player.play();
    }

}
