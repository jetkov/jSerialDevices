package cf.jetkov.jSerialDevices.test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cf.jetkov.jSerialDevices.Arduino;

public class ReadTesting {

	public static void main(String[] args)  {
		
		String x;
		String y;
		
		Arduino arduino = new Arduino(9600); //enter the port name here, and ensure that Arduino is connected, otherwise exception will be thrown.
		arduino.openPort();
		
		
		
		while (true) {
			try {
			Scanner scan = new Scanner(arduino.getSerialPort().getInputStream());
			scan.useDelimiter("/");
			while (scan.hasNext()) System.out.println(scan.next());
			scan.close();
			} catch (Exception e) {
				
			}
			
			
//			System.out.println((recieved.substring(recieved.lastIndexOf("X") + 1)));
//			System.out.println((recieved.substring(recieved.lastIndexOf("Y") + 1)));
//			
//			System.out.println("(" + x + ", " + y + ")");
		}
		//Arduino.closePort();
		
	}

}
