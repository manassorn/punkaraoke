package vlc;
import java.awt.Window;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;


public class VLCPlayer extends MediaPlayerEventAdapter {

	VLCEventListener listener;
	VLCJPlayer vlcjPlayer;
	
	public VLCPlayer(VLCEventListener listener) {
		this.listener = listener;
		this.vlcjPlayer = new VLCJPlayer(this);
	}
	
	public void play(String songPath) throws IOException {
		vlcjPlayer.play(songPath);
	}
	
	public void addEventListener(VLCEventListener listener) {
		this.listener = listener;
	}
	
	public void setOverlay(Window overlay) {
		vlcjPlayer.setOverlay(overlay);
	}
	
	public JFrame getFrame() {
		return vlcjPlayer.getFrame();
	}
	
	public void toggleAudioChannel() {
		vlcjPlayer.toggleAudioChannel();
	}
	
	public String getAudioChannel() {
		return vlcjPlayer.getAudioChannel();
	}
	
	public void refreshOverlay() {
		vlcjPlayer.enableOverlay(false);
		vlcjPlayer.enableOverlay(true);
	}

    @Override
    public void finished(MediaPlayer mediaPlayer) {
    	if(this.listener == null) {
    		return;
    	}
    	final VLCEventListener listener = this.listener;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	try {
					Thread.sleep(1000);
	            	listener.onStop();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }

    @Override
    public void error(MediaPlayer mediaPlayer) {
    	System.out.println("ERROR!!!");
    }

}
