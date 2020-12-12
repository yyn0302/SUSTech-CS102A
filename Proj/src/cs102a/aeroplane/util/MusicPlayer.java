package cs102a.aeroplane.util;

import cs102a.aeroplane.presets.Sound;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MusicPlayer {

    private final File file;                  // wav文件的路径
    private boolean isLoop = false;     // 是否循环播放
    private boolean isPlaying;          // 是否正在播放
//    private float newVolume = 7;        // FloatControl.Type.MASTER_GAIN的值(可用于调节音量)

    private playSoundThread playSoundThread;

    public MusicPlayer(Sound sound) {
        this.file = sound.getMusicFile();
    }

    // 播放音乐
    public void play() {
        playSoundThread = new playSoundThread();
        playSoundThread.start();
    }

    // 结束音乐
    public void over() {
        isPlaying = false;
        if (playSoundThread != null) {
            playSoundThread = null;
        }
    }

    /**
     * 设置循环播放
     *
     * @param isLoop
     * @return 返回当前对象
     */
    public MusicPlayer setLoop(boolean isLoop) {
        this.isLoop = isLoop;
        return this;
    }

    /**
     * -80.0~6.0206测试,越小音量越小
     * <p>
     * //     * @param newVolume
     *
     * @return 返回当前对象
     */
//    public MusicPlayer setVolume(float newVolume) {
//        this.newVolume = newVolume;
//        return this;
//    }

    // 异步播放线程
    private class playSoundThread extends Thread {

        @Override
        public void run() {
            isPlaying = true;

            do {
                SourceDataLine sourceDataLine = null;
                BufferedInputStream bufferedInputStream = null;
                AudioInputStream audioInputStream = null;
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);

                    AudioFormat format = audioInputStream.getFormat();
                    sourceDataLine = AudioSystem.getSourceDataLine(format);
                    sourceDataLine.open();
                    // 必须open之后
//                    if (newVolume != 7) {
//                        FloatControl control = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
////						System.out.println(control.getMaximum());
////						System.out.println(control.getMinimum());
//                        control.setValue(newVolume);
//                    }

                    sourceDataLine.start();
                    byte[] buf = new byte[512];
//					System.out.println(audioInputStream.available());
                    int len = -1;
                    while (isPlaying && (len = audioInputStream.read(buf)) != -1) {
                        sourceDataLine.write(buf, 0, len);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    if (sourceDataLine != null) {
                        sourceDataLine.drain();
                        sourceDataLine.close();
                    }
                    try {
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (audioInputStream != null) {
                            audioInputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } while (isPlaying && isLoop);

        }
    }

}