package cf.jetkov.JSerialDevices;

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
		serialPort = getPortMatching(description);
		if (serialPort == null) {
			System.err.println("ERROR: Port not found!");
			System.exit(1);
		}
	}

	public SerialDevice(String description, int baudRate) {
		serialPort = getPortMatching(description);
		if (serialPort == null) {
			System.err.println("ERROR: Port not found! Cannot set baud rate.");
		} else {
			this.baudRate = baudRate;
			serialPort.setBaudRate(this.baudRate);
		}
	}

	public SerialPort getPortMatching(String description) {
		String descriptivePortName;

		for (SerialPort port : SerialPort.getCommPorts()) {
			descriptivePortName = port.getDescriptivePortName();
			if (descriptivePortName.contains(description)) {
				return port;
			}
		}
		System.err.println("No port with description matching '" + description + "' found!");
		return null;
	}

	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
		serialPort.setBaudRate(this.baudRate);
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

	public abstract String serialRead(int lines);

	public abstract void serialWrite(String strng);

	public abstract void serialWrite(char chr);

}
