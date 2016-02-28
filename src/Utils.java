import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;


public class Utils {

	public static void getAllMonitors() {
		GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		for(GraphicsDevice device : devices) {
			System.out.println(device.toString());
			System.out.println(device.getIDstring());
		}
	}
	
	public static void getAllPorts() {
		
	}
}
