package karaoke;

public class SongDB {

	static String path = "C:\\Program Files (x86)\\VideoLAN\\VLC\\";
	
	public static String getSongById(String songId) {
		if(songId.equals("3")) {
			return path + "cocktail.mp4";
		}
		return path + "intro" + songId + ".mp4";
	}
	
	public static String getJingle() {
		return path + "bunny.mp4";
	}
	
	public static String[] find(String songId) {
		return null;
	}
}
