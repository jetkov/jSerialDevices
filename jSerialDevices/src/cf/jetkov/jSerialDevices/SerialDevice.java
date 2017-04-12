package cf.jetkov.jSerialDevices;

import java.util.NoSuchElementException;

import com.fazecast.jSerialComm.*;

/**
 * This is an abstract class for serial communication devices. It uses the jSerialComm
 * library which has cross platform native support for serial communications. Subclasses
 * must define methods for reading from and writing to the serial device.
 * 
 * @author Alex Petkovic
 * @version 1.0.0b
 *
 */
public abstract class SerialDevice {

	private SerialPort serialPort;
	private int baudRate;

	/**
	 * Creates a new serial device. A serial port (and optionally a baud rate) should be
	 * set.
	 */
	public SerialDevice() {
	}

	/**
	 * Creates a new serial device with a given serial port. A new serial port has a
	 * default baud rate of 9600 baud.
	 * 
	 * @param serialPort
	 *            - A serial port to be assigned to the device
	 */
	public SerialDevice(SerialPort serialPort) {
		this.serialPort = serialPort;
	}

	/**
	 * Creates a new serial device with a given serial port and baud rate.
	 * 
	 * @param serialPort
	 *            - A serial port to be assigned to the device
	 * @param baudRate
	 *            - The desired baud rate for this serial port
	 */
	public SerialDevice(SerialPort serialPort, int baudRate) {
		this.serialPort = serialPort;
		setBaudRate(baudRate);
	}

	/**
	 * Creates a new serial device with a keyword-matched port. See
	 * {@link #getPortMatching(String descriptor)}.
	 * 
	 * @param keyword
	 *            - A keyword to match to a serial port description
	 */
	public SerialDevice(String keyword) {
		setPortMatching(keyword);
	}

	/**
	 * Creates a new serial device with a keyword-matched port and a given baud rate. See
	 * {@link #getPortMatching(String descriptor)}.
	 * 
	 * @param keyword
	 *            - A keyword to match to a serial port description
	 * @param baudRate
	 *            - The desired baud rate for this serial port
	 */
	public SerialDevice(String keyword, int baudRate) {
		setPortMatching(keyword);
		setBaudRate(baudRate);
	}

	/**
	 * Iterates through a list of all available serial ports on the machine, checking the
	 * descriptive port name of each one for a match with the given keyword. Useful for
	 * cases where the port may change or for multi-OS support.
	 * 
	 * @param keyword
	 *            - A keyword of a serial port description to find a match with. The
	 *            keyword can be simply a word or few identifying characters that could be
	 *            found within the port description. Match-checking is case insensitive.
	 * @return The first serial port found with a matching description
	 * @throws NoSuchElementException
	 *             if no matching port is found.
	 */
	public SerialPort getPortMatching(String keyword) {
		String descriptivePortName;

		for (SerialPort port : SerialPort.getCommPorts()) {
			descriptivePortName = port.getDescriptivePortName() + ": " + port.getSystemPortName();
			if (descriptivePortName.matches("(?i).*" + keyword + ".*")) {
				System.out.println("Found " + descriptivePortName + ".");
				return port;
			}
		}

		throw new NoSuchElementException("ERROR: No port with description matching '" + keyword + "' found!");
	}

	/**
	 * Sets the device's serial port to a machine port with a description matching the
	 * given keyword. See {@link #getPortMatching(String descriptor)}.
	 * 
	 * @param keyword
	 *            - A keyword to match to a serial port description
	 */
	public void setPortMatching(String keyword) {
		try {
			serialPort = getPortMatching(keyword);
		} catch (NoSuchElementException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Sets the baud rate of the serial device and port to the specified value. The
	 * default baud rate is 9600 baud.
	 * 
	 * @param baudRate
	 *            - The desired baud rate for this serial port.
	 */
	public void setBaudRate(int baudRate) {
		try {
			this.baudRate = baudRate;
			serialPort.setBaudRate(this.baudRate);
		} catch (NullPointerException e) {
			System.err.println("ERROR: Port not found! Cannot set baud rate.");
		}
	}

	/**
	 * @return The baud rate of the serial device's port.
	 */
	public int getBaudRate() {
		return baudRate;
	}

	/**
	 * Set's the serial device's serial port to the given one.
	 * 
	 * @param serialPort
	 *            A serial port the device is connected to.
	 */
	public void setSerialPort(SerialPort serialPort) {
		this.serialPort = serialPort;
	}

	/**
	 * @return The serial device's current serial port.
	 */
	public SerialPort getSerialPort() {
		return serialPort;
	}

	/**
	 * Attempts to open the serial port of the device.
	 * 
	 * @return True of the port has been opened successfully or was already open. False if
	 *         the port could not be opened (possibly wrong port, port is busy, etc.).
	 */
	public boolean openPort() {
		if (serialPort.openPort()) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return true;
		} else {
			System.err.println("ERROR: Cannot open port!");
			return false;
		}
	}

	/**
	 * Closes the serial device's serial port.
	 */
	public void closePort() {
		serialPort.closePort();
	}

	/**
	 * Reads in a String from the serial device.
	 * 
	 * @return A recieved String
	 */
	public abstract String serialRead();

	/**
	 * Reads in a given number of tokens from the serial device.
	 * 
	 * @param tokens
	 *            - The number of tokens to get
	 * @return A String containing at most the specified number of tokens.
	 */
	public abstract String serialRead(int tokens);

	/**
	 * Writes a String to the serial device.
	 * 
	 * @param strng
	 *            - A String to send to the device
	 */
	public abstract void serialWrite(String strng);
	
	/**
	 * Prints out a list of all available serial ports on the machine.
	 */
	public static void printSerialPorts() {
		for (SerialPort port : SerialPort.getCommPorts()) {
			System.out.print(port.getDescriptivePortName() + ": ");
			System.out.println(port.getSystemPortName());
		}
	}

}
