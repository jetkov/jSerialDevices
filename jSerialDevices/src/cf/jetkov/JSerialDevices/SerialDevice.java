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
		setPortMatching(description);
	}
	
	public SerialDevice(String description, int baudRate) {
		setPortMatching(description);
		this.baudRate = baudRate;
		serialPort.setBaudRate(this.baudRate);
	}
	
	public boolean setPortMatching(String description) {
		String descriptivePortName;
		
		for (SerialPort port : SerialPort.getCommPorts()) {
			descriptivePortName = port.getDescriptivePortName();
			if (descriptivePortName.contains(description)) {
				serialPort = port;
				return true;
			} 		
		}
		System.out.println("No port with description matching '" + description + "' found!");
		return false;
	}
	
	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
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
		if(serialPort.openPort()){
			try {Thread.sleep(100);} catch(Exception e){}
			return true;
		}
		else {
			System.out.println("Error Connecting! Try Another port.");
			return false;
		}
	}
	
	public void closePort() {
		serialPort.closePort();
	}
	
	public abstract void serialWrite(String strng);
	
	public abstract void serialWrite(char chr);
}
