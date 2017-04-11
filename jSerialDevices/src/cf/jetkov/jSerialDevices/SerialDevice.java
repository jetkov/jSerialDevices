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
	 * Creates a new serial device. A new serial port has a default baud rate of 9600
	 * baud.
	 * 
	 * @param serialPort
	 *            A serial port
	 */
	public SerialDevice(SerialPort serialPort) {
		this.serialPort = serialPort;
	}

	/**
	 * Creates a new serial device.
	 * 
	 * @param serialPort
	 *            A serial port
	 * @param baudRate
	 *            The desired baud rate for this serial port
	 */
	public SerialDevice(SerialPort serialPort, int baudRate) {
		this.serialPort = serialPort;
		setBaudRate(baudRate);
	}

	public SerialDevice(String description) {
		setPortMatching(description);
	}

	public SerialDevice(String description, int baudRate) {
		setPortMatching(description);
		setBaudRate(baudRate);
	}

	public SerialPort getPortMatching(String description) {
		String descriptivePortName;

		for (SerialPort port : SerialPort.getCommPorts()) {
			descriptivePortName = port.getDescriptivePortName();
			if (descriptivePortName.matches("(?i).*" + description + ".*")) {
				System.out.println("Found " + descriptivePortName);
				return port;
			}
		}
		throw new NoSuchElementException("ERROR: No port with description matching '" + description + "' found!");
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
