package karaoke;
import java.io.IOException;
import java.util.LinkedList;

public class KaraokeQueue {

	enum Playing {
		NONE, JINGLE, MUSIC
	}
	KaraokePlayer player;
	LinkedList<String> queue;
	Playing playing;
	
	public KaraokeQueue(KaraokePlayer player) {
		this.player = player;
		this.queue = new LinkedList<String>();
		this.playing = Playing.NONE;
	}
	
	public synchronized void addSong(String songId) throws IOException {
		if(playing.equals(Playing.MUSIC)) {
			queue.add(songId);
		} else {
			playing = Playing.MUSIC;
			player.play(songId);
		}
	}
	
	public synchronized void next() throws IOException {
		playing = Playing.NONE;
		if(queue.isEmpty()) {
			playing = Playing.JINGLE;
			player.playJingle();
		} else {
			playing = Playing.MUSIC;
			String songId = queue.pollFirst();
			player.play(songId);
		}
	}
}