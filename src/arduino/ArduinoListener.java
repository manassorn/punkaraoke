package arduino;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.util.TooManyListenersException;

import karaoke.NumpadController;

public class ArduinoListener implements SerialPortEventListener {

	private ArduinoSerialPort arduino;
	private NumpadController controller;
	
	public ArduinoListener(String portName, NumpadController controller) {
		this.arduino = new ArduinoSerialPort(portName);
		this.controller = controller;
	}

	public void listen() throws TooManyListenersException {
		arduino.initialize();
		arduino.addListener(this);
	}

	
	public void execute(String command) {
		try {
			try {
				Integer.parseInt(command);
				controller.pressNumber(command);
			} catch(NumberFormatException e) {
				if("*".equals(command)) {
					controller.toggleChannel();
				} else if("#".equals(command)) {
					controller.enter();
				} else if("\n".equals(command)) {
					controller.enter();
				} else {
//					player.write(command + "\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = arduino.getInput().readLine();
				System.out.println(inputLine);
				execute(inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
		
	}

}
