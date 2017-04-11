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
	 *            A serial port to be assigned to the device
	 */
	public SerialDevice(SerialPort serialPort) {
		this.serialPort = serialPort;
	}

	/**
	 * Creates a new serial device with a given serial port and baud rate.
	 * 
	 * @param serialPort
	 *            A serial port to be assigned to the device
	 * @param baudRate
	 *            The desired baud rate for this serial port
	 */
	public SerialDevice(SerialPort serialPort, int baudRate) {
		this.serialPort = serialPort;
		setBaudRate(baudRate);
	}

	/**
	 * Creates a new serial device. The serial device's port will be attempted to be set
	 * by matching a descriptive port name to the keyword given here. See
	 * {@link #getPortMatching(String descriptor)}.
	 * 
	 * @param keyword
	 *            A keyword of a serial port description to find a match with
	 */
	public SerialDevice(String keyword) {
		setPortMatching(keyword);
	}

	/**
	 * Creates a new serial device with a keyword-matched port and a given baud rate. The
	 * serial device's port will be attempted to be set by matching a descriptive port
	 * name to the keyword given here. See {@link #getPortMatching(String descriptor)}.
	 * 
	 * @param keyword
	 *            A keyword of a serial port description to find a match with
	 * @param baudRate
	 *            The desired baud rate for this serial port
	 */
	public SerialDevice(String keyword, int baudRate) {
		setPortMatching(keyword);
		setBaudRate(baudRate);
	}

	/**
	 * Iterates through a list of all available serial ports on the machine, checking the
	 * descriptive port name of each one for a match with the given keyword.
	 * 
	 * @param keyword
	 *            - A keyword of a serial port description to find a match with. The keyword
	 *            can be simply a word or few identifying characters that could be found
	 *            within the port description. Match-checking is case insensitive.
	 * @return The first serial port found with a matching description.
	 * @throws NoSuchElementException if no matching port is found.
	 */
	public SerialPort getPortMatching(String keyword) {
		String descriptivePortName;

		for (SerialPort port : SerialPort.getCommPorts()) {
			descriptivePortName = port.getDescriptivePortName();
			if (descriptivePortName.matches("(?i).*" + keyword + ".*")) {
				System.out.println("Found " + descriptivePortName +".");
				return port;
			}
		}
		
		throw new NoSuchElementException("ERROR: No port with description matching '" + keyword + "' found!");
	}

	public void setPortMatching(String description) {
		try {
			serialPort = getPortMatching(description);
		} catch (NoSuchElementException e) {
			System.err.println(e.getMessage());
		}
	}

	public void setBaudRate(int baudRate) {
		try {
			this.baudRate = baudRate;
			serialPort.setBaudRate(this.baudRate);
		} catch (NullPointerException e) {
			System.err.println("ERROR: Port not found! Cannot set baud rate.");
		}
	}

	public int getBaudRate() {
		return baudRate;
	}

	public void setSerialPort(SerialPort serialPort) {
		this.serialPort = serialPort;
	}

	public SerialPort getSerialPort() {
		return serialPort;
	}

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

	public void closePort() {
		serialPort.closePort();
	}

	public abstract String serialRead();

	public abstract String serialRead(int tokens);

	public abstract void serialWrite(String strng);

}
