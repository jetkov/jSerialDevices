package cf.jetkov.jSerialDevices.examples;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cf.jetkov.jSerialDevices.Arduino;

public class ReadTesting {

	public static void main(String[] args) {

		String coord = "";
		int x = 0, y = 0;

		Arduino arduino = new Arduino(9600); // enter the port name here, and
												// ensure that Arduino is
												// connected, otherwise
												// exception will be thrown.
		arduino.openPort();

		Scanner scan;

		while (true) {
			try {
				scan = new Scanner(arduino.getSerialPort().getInputStream());
				scan.useDelimiter("/");
				while (scan.hasNext()) coord += scan.next();
				scan.close();
			} catch (Exception e) {

			}

			if (coord.matches("\\<(.*?),(.*?)\\>")) {
				x = Integer.valueOf((coord.substring(coord.indexOf("<") + 1, coord.indexOf(","))));
				y = Integer.valueOf((coord.substring(coord.indexOf(",") + 1, coord.indexOf(">"))));
				coord = "";
			}
			
			System.out.printf("[%d,%d]\n", x, y);

			// System.out.println((recieved.substring(recieved.lastIndexOf("X")
			// + 1)));
			// System.out.println((recieved.substring(recieved.lastIndexOf("Y")
			// + 1)));
			//
			// System.out.println("(" + x + ", " + y + ")");
		}
		// Arduino.closePort();

	}

}
