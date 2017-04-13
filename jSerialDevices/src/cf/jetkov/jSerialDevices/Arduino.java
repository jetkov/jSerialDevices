package cf.jetkov.jSerialDevices;

import java.io.PrintWriter;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

/**
 * Allows for communications to and from a USB connected arduino device in a very
 * abstracted and easily understandable fashion. Extends the {@link SerialDevice} class,
 * utilizing the jSerialComm library.
 * 
 * @author Alex Petkovic
 * @version 1.0.0b
 *
 */
public class Arduino extends SerialDevice {

	private SerialPort serialPort;

	/**
	 * Creates a new arduino. By default, chooses a serial port that matches an 'arduino'
	 * (for windows) or 'ACM' (on linux) keyword. Set's the arduino's private serial port
	 * to serial device class's serial port.
	 */
	public Arduino() {
		super("(arduino|ACM)");
		serialPort = getSerialPort();
	}

	/**
	 * Creates a new arduino. By default, chooses a serial port that matches an 'arduino'
	 * (for windows) or 'ACM' (on linux) keyword--and sets the baud rate as specified.
	 * Set's the arduino's private serial port to serial device class's serial port.
	 * 
	 * @param baudRate
	 *            - The desired baud rate for this serial port
	 */
	public Arduino(int baudRate) {
		super("(arduino|ACM)", baudRate);
		serialPort = getSerialPort();
	}

	/**
	 * Creates a new arduino, and tries to set the serial port to a port with a
	 * description pr system name/path matching the given keyword.
	 * 
	 * @param keyword
	 *            - A keyword to match to a serial port description
	 */
	public Arduino(String keyword) {
		super(keyword);
		serialPort = getSerialPort();
	}

	/**
	 * Creates a new arduino, and tries to set the serial port to a port with a
	 * description or system name/path matching the given keyword. Sets the baud rate of
	 * the serial port to the specified value.
	 * 
	 * @param keyword
	 *            - A keyword to match to a serial port description
	 * @param baudRate
	 *            - The desired baud rate for this serial port
	 */
	public Arduino(String keyword, int baudRate) {
		super(keyword, baudRate);
		serialPort = getSerialPort();
	}

	@Override
	public String serialRead() {
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		String recieved = "";
		Scanner scan = new Scanner(serialPort.getInputStream());
		try {
			while (scan.hasNext())
				recieved += (scan.next() + "\n");
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recieved;
	}

	@Override
	public String serialRead(int tokens) {
		return serialRead(tokens, "\n", " ");
	}
	
	public String serialRead(int tokens, String separator) {
		return serialRead(tokens, separator, " ");
	}

	public String serialRead(int tokens, String separator, String delimiter) {
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

		String recieved = "";
		Scanner scan = new Scanner(serialPort.getInputStream());

		scan.useDelimiter(delimiter);

		try {
			for (int i = 0; i <= tokens && scan.hasNext(); i++) {
				recieved += (scan.next() + separator);
			}
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recieved;
	}
	
	

	@Override
	public void serialWrite(String strng) {
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		PrintWriter printer = new PrintWriter(serialPort.getOutputStream());

		printer.print(strng);
		printer.flush();
	}

	public void serialWrite(char c) {
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		PrintWriter pout = new PrintWriter(serialPort.getOutputStream());
		pout.write(c);
		pout.flush();
	}

}
