package cf.jetkov.jSerialDevices;

/**
 * Allows for communications to and from a USB connected arduino device using a CH340
 * comms chipset in a very abstracted and easily understandable fashion. Extends the
 * {@link SerialDevice} class, utilizing the jSerialComm library.
 * 
 * @author Alex Petkovic
 * @version 1.0.0b
 *
 */
public class ArduinoCH340 extends Arduino {

	/**
	 * Creates a new arduino. By default, chooses a serial port that matches an 'arduino'
	 * (for windows) or 'ACM' (on linux) keyword. Set's the arduino's private serial port
	 * to serial device class's serial port.
	 */
	public ArduinoCH340() {
		super("(CH340|ACM)");
	}

	/**
	 * Creates a new arduino. By default, chooses a serial port that matches an 'arduino'
	 * (for windows) or 'ACM' (on linux) keyword--and sets the baud rate as specified.
	 * Set's the arduino's private serial port to serial device class's serial port.
	 * 
	 * @param baudRate
	 *            - The desired baud rate for this serial port
	 */
	public ArduinoCH340(int baudRate) {
		super("(CH340|ACM)", baudRate);
	}

}
