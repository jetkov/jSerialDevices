package cf.jetkov.JSerialDevices;

import java.util.NoSuchElementException;

import com.fazecast.jSerialComm.*;

abstract class SerialDevice {

	private SerialPort serialPort;
	private int baudRate;

	public SerialDevice() {
	}

	public SerialDevice(SerialPort serialPort) {
		this.serialPort = serialPort;
	}

	public SerialDevice(SerialPort serialPort, int baudRate) {
		this.serialPort = serialPort;
		this.baudRate = baudRate;
		serialPort.setBaudRate(this.baudRate);
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
			if (descriptivePortName.contains(description)) {
				return port;
			}
		}
		throw new NoSuchElementException("No port with description matching '" + description + "' found!");
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
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return true;
		} else {
			System.out.println("Error Connecting! Try Another port.");
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
