package karaoke;
import java.io.IOException;

import vlc.VLCEventListener;
import vlc.VLCPlayer;

public class KaraokePlayer implements VLCEventListener {
	
	VLCPlayer vlcPlayer;
	KaraokeQueue queue;
	SongInfoOverlay overlay;
	NumpadController controller;
	String songId;

	public KaraokePlayer() throws IOException {
		this.queue = new KaraokeQueue(this);
		
		this.vlcPlayer = new VLCPlayer(this);
		this.overlay = new SongInfoOverlay(vlcPlayer.getFrame());
		this.vlcPlayer.setOverlay(overlay);
		
		this.controller = new NumpadController(this);
	}
	
	
	public void toggleAudioChannel() throws IOException {
		vlcPlayer.toggleAudioChannel();
		overlay.showAudioChannel(getAudioChannel());

		vlcPlayer.refreshOverlay();
	}
	
	public String getAudioChannel() {
		return vlcPlayer.getAudioChannel();
	}
	
	
	public void play(String songId) throws IOException {
		vlcPlayer.play(SongDB.getSongById(songId));
	}
	
	public void playJingle() throws IOException {
		vlcPlayer.play(SongDB.getJingle());
	}
	
	public void onStop() {
		System.out.println("onVLCStop");
		try {
			queue.next();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public KaraokeQueue getQueue() {
		return this.queue;
	}
	
	public NumpadController getController() {
		return this.controller;
	}
	
	public SongInfoOverlay getOverlay() {
		return this.overlay;
	}
	
	public void setSongId(String songId) {
		if(!songId.equals(this.songId)) {
			this.songId = songId;
			if(songId.length() > 0) {
				overlay.showSongId(songId);
			} else {
				overlay.hideSongId();
			}
			if(songId.length() == 5) {
				String[] songInfo = SongDB.find(songId);
				if(songInfo != null) {
					overlay.showSongInfo(songInfo[0], songInfo[1]);
				}
			} else {
				overlay.hideSongInfo();
			}
			refreshOverlay();
		}
	}
	
	public void refreshOverlay() {
		vlcPlayer.refreshOverlay();
	}
	
	public void addSong(String songId) {
		try {
			queue.addSong(songId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
