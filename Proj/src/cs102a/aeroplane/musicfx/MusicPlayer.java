package cs102a.aeroplane.musicfx;

import cs102a.aeroplane.presets.Sound;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 根据jdk底层API实现的音乐播放器
 * <p>
 * // * @author passerbyYSQ
 * // * @功能 1、只支持wav，且只能播放一首
 * 2、可循环播放，随时停止（并非暂停）
 * 3、支持一定范围内的音量调节
 * <p>
 * 参考博客:
 * https://blog.csdn.net/qq_21907023/article/details/96174077
 * https://blog.csdn.net/fuckcdn/article/details/83725725
 * // * @create 2020年7月20日 下午4:05:50
 */
public class MusicPlayer {


    //@usage:构造

    private File file;                  // wav文件的路径
    private boolean isLoop = false;     // 是否循环播放
    private boolean isPlaying;          // 是否正在播放
//    private float newVolume = 7;        // FloatControl.Type.MASTER_GAIN的值(可用于调节音量)

    private playSoundThread playSoundThread;

    //	public static void main(String[] args) {
//		try {


//			MusicPlayer player = new MusicPlayer("F:\\初级软件实训\\CrazyArcade-master\\music\\bgm0.wav");
//			player.setVolumn(6f).play();
//			System.out.println("开始播放");
//
//			System.out.println("是否暂停？");
//			Scanner sc = new Scanner(System.in);
//			int isOver = sc.nextInt();
//			if (isOver == 1) {
//				player.over();
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

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