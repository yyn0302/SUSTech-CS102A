public class PlayMusic {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer("src\\1.wav");
        player.setVolumn(6f);
        player.setLoop(true);
        player.play();
    }

}
