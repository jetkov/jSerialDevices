package cf.jetkov.jSerialDevices.test;

import com.fazecast.jSerialComm.*;

public class Test {
	
	public static void main(String[] args) {
		for (SerialPort port : SerialPort.getCommPorts()) {
			System.out.println(port.getDescriptivePortName());
			System.out.println(port.getSystemPortName());
			System.out.println();
		}
		SerialPort port = SerialPort.getCommPort("COM1");
		System.out.println(port.getDescriptivePortName());
		System.out.println(port.getSystemPortName());
		System.out.println();
	}
}
