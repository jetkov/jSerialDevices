package cf.jetkov.jSerialDevices.test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cf.jetkov.jSerialDevices.Arduino;

public class ReadTesting {

	public static void main(String[] args)  {
		
		String x;
		String y;
		
		Arduino Arduino = new Arduino(9600); //enter the port name here, and ensure that Arduino is connected, otherwise exception will be thrown.
		Arduino.openPort();
		while (true) {
			String recieved = Arduino.serialRead(1);
			System.out.println(recieved + "\n");
			
			
//			System.out.println((recieved.substring(recieved.lastIndexOf("X") + 1)));
//			System.out.println((recieved.substring(recieved.lastIndexOf("Y") + 1)));
//			
//			System.out.println("(" + x + ", " + y + ")");
		}
		//Arduino.closePort();
		
	}

}
