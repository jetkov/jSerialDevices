package cf.jetkov.JSerialDevices;

import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

public class Arduino extends SerialDevice {
	
	SerialPort serialPort;

	Arduino() {
		super("arduino");
		serialPort = getSerialPort();
	}

	Arduino(int baudRate) {
		super("arduino");
		serialPort = getSerialPort();
		setBaudRate(baudRate);
	}

	@Override
	public String serialRead() {
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		String out = "";
		Scanner in = new Scanner(serialPort.getInputStream());
		try {
			while (in.hasNext())
				out += (in.next() + "\n");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	@Override
	public String serialRead(int lines) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void serialWrite(String strng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void serialWrite(char chr) {
		// TODO Auto-generated method stub

	}

}
