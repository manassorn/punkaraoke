import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import arduino.ArduinoListener;

import karaoke.KaraokePlayer;
import karaoke.NumpadController;



public class Main {

	public static void main(String[] args) throws Exception {
//		boolean found = new NativeDiscovery().discover();
//        System.out.println(found);
//        System.out.println(LibVlc.INSTANCE.libvlc_get_version());
//		

		KaraokePlayer player = new KaraokePlayer();
		KeyboardListener keyboard = new KeyboardListener(player.getController());
		keyboard.listen();
		
//		String portName = args.length > 0 ? args[0] : "COM11";
//		ArduinoListener arduino = new ArduinoListener(portName, player.getController());
//		arduino.listen();
	}
}
