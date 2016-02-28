import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import karaoke.NumpadController;


public class KeyboardListener {

	private NumpadController controller;
	
	public KeyboardListener(NumpadController controller) {
		this.controller = controller;
	}

	public void listen() {
		AWTEventListener listener = new AWTEventListener() {
			@Override
			public void eventDispatched(AWTEvent event) {
			    try {
			    	KeyEvent evt = (KeyEvent)event;
			    	if(evt.getID() == KeyEvent.KEY_PRESSED) {
				    	char ch = evt.getKeyChar();
				    	System.out.println("PRESS " + ch);
				    	execute("" + ch);
			    	}
		    	} catch(Exception e) {
		    		e.printStackTrace();
		    	}
			}
		};

        Toolkit.getDefaultToolkit().addAWTEventListener(listener, AWTEvent.KEY_EVENT_MASK);
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
}
