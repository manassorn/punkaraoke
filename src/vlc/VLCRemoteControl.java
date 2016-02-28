package vlc;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

// VLC RC Command references/examples
//   - https://github.com/mguinada/vlc-client/blob/master/lib/vlc-client/client/media_controls.rb
//   - http://getluky.net/2006/04/19/vlcs-awesome-rc-interface/
class VLCRemoteControl {

	public enum AudioChannel {
		LEFT, RIGHT, STEREO, STEREO1
	}

	private InputStream in;
	private OutputStream out;
	private AudioChannel channel = AudioChannel.LEFT;
	private int port;

	public VLCRemoteControl(String monitor) {
		this.port = 50000;
	}

	public VLCRemoteControl(String monitor, int port) {
		this.port = port;
	}

	public void open() throws IOException {
		Runtime.getRuntime()
				.exec("\"C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe\" --intf rc --rc-host localhost:50000");
		openSocket();
	}

	public void openSocket() throws IOException {
		InetAddress localhost = InetAddress.getByName("localhost");
		Socket socket = new Socket(localhost, port);
		out = socket.getOutputStream();
		in = socket.getInputStream();
	}

	public void close() throws IOException {
		out.close();
		in.close();
	}

	public InputStream getInputStream() {
		return in;
	}

	public OutputStream getOutputStream() {
		return out;
	}
	
	public void write(String str) throws IOException {
		out.write(str.getBytes());
	}
	
	public void play() throws IOException {
		write("play\n");
	}
	
	public void stop() throws IOException {
		write("stop\n");
	}
	
	public void isPlaying() throws IOException {
		write("is_playing\n");
	}
	
	public void toggleChannel() throws IOException {
		channel = AudioChannel.values()[((channel.ordinal() + 1) % AudioChannel
				.values().length)];
		System.out.println("channel: " + channel.ordinal());
		write("achan " + channel.ordinal() + "\n");
	}

}