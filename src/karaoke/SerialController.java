package karaoke;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;

import arduino.ArduinoSerialPort;


public class SerialController implements SerialPortEventListener {

	private NumpadController controller;
	
	public SerialController(NumpadController controller) {
		this.controller = controller;
	}

	public void serialEvent(SerialPortEvent event) {
		if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				ArduinoSerialPort port = (ArduinoSerialPort) event.getSource();
				String command = port.getInput().readLine();
				System.out.println("command: " + command);
				execute(command);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
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
					controller.backspace();
				} else if("enter".equals(command)) {
					controller.enter();
				} else {
					System.out.println(">> warning something wrong: " + command);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
