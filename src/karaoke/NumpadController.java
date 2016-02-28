package karaoke;

import java.io.IOException;


public class NumpadController {

	KaraokePlayer player;
	String songId = "";
	static int SONGID_MAX_LENGTH = 1;
	
	public NumpadController(KaraokePlayer player) {
		this.player = player;
	}

	public void press(String str) {
		try {
			try {
				Integer.parseInt(str);
				pressNumber(str);
			} catch(NumberFormatException e) {
				if("*".equals(str)) {
					toggleChannel();
				} else if("#".equals(str)) {
					backspace();
				} else if("enter".equals(str)) {
					enter();
				} else {
					System.out.println(">> warning something wrong: " + str);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void pressNumber(String number) {
		if(songId.length() + number.length() <= SONGID_MAX_LENGTH) {
			songId += number;
			player.setSongId(songId);
		}
	}
	
	public void backspace() {
		if(songId.length() > 1) {
			songId = songId.substring(0, songId.length() - 1);
			player.setSongId(songId);
		}
	}
	
	public void enter() throws IOException {
		if(songId.length() == SONGID_MAX_LENGTH) {
			player.addSong(songId);
			songId = "";
			player.setSongId(songId);
		}
	}
	
	public void toggleChannel() throws IOException {
		player.toggleAudioChannel();
//		overlay.showAudioChannel(player.getAudioChannel());
	}
	
}
