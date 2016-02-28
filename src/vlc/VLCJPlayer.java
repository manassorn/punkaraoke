package vlc;
import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.co.caprica.vlcj.binding.internal.libvlc_audio_output_channel_t;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.DefaultAdaptiveRuntimeFullScreenStrategy;

public class VLCJPlayer {

    private final JFrame frame;

    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public VLCJPlayer(MediaPlayerEventAdapter adapter) {

        new NativeDiscovery().discover();
        
        frame = new JFrame("My First Media Player");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(e);
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayerComponent.getMediaPlayer().setFullScreenStrategy(
            new DefaultAdaptiveRuntimeFullScreenStrategy(frame)
        );
        contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

        mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(adapter);

        frame.setContentPane(contentPane);
        frame.setVisible(true);
        
        // full screen
//        mediaPlayerComponent.getMediaPlayer().setFullScreen(true);
    }
    
    public void play(String songPath) {
        mediaPlayerComponent.getMediaPlayer().playMedia(songPath);
    }

    public void closeWindow() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
    
    public void setOverlay(Window overlay) {
    	mediaPlayerComponent.getMediaPlayer().setOverlay(overlay);
    	mediaPlayerComponent.getMediaPlayer().enableOverlay(true);
    }
    
    public JFrame getFrame() {
    	return frame;
    }
    
    public void toggleAudioChannel() {
    	int channel = mediaPlayerComponent.getMediaPlayer().getAudioChannel();
    	int length = libvlc_audio_output_channel_t.values().length;
    	channel = (channel + 1) % length;
    	mediaPlayerComponent.getMediaPlayer().setAudioChannel(channel);
    }
    
    public String getAudioChannel() {
    	int channel = mediaPlayerComponent.getMediaPlayer().getAudioChannel();
    	String name = libvlc_audio_output_channel_t.values()[channel].name();
    	return name;
    }
    
    public void enableOverlay(boolean enable) {
    	mediaPlayerComponent.getMediaPlayer().enableOverlay(enable);
    }
}