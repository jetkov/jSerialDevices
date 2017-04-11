package cf.jetkov.jSerialDevices;

import java.io.PrintWriter;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

public class Arduino extends SerialDevice {

	SerialPort serialPort;

	public Arduino() {
		super("(arduino|ACM)");
		serialPort = getSerialPort();
	}

	public Arduino(int baudRate) {
		super("(arduino|ACM)", baudRate);
		serialPort = getSerialPort();
	}
	
	public Arduino(String keyword) {
		super(keyword);
		serialPort = getSerialPort();
	}
	
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
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

		String recieved = "";
		Scanner scan = new Scanner(serialPort.getInputStream());

		try {
			for (int i = 0; i <= tokens && scan.hasNext(); i++) {
				System.out.println(scan.next());
				recieved += (scan.next() + "\n");
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
