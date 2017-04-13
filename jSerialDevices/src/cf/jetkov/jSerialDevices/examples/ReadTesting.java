package cf.jetkov.jSerialDevices.examples;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cf.jetkov.jSerialDevices.Arduino;

public class ReadTesting {

	public static void main(String[] args) {

		String buf = "";
		int x = 0, y = 0;

		Arduino arduino = new Arduino(9600); // enter the port name here, and
												// ensure that Arduino is
												// connected, otherwise
												// exception will be thrown.
		arduino.openPort();

		while (arduino.openPort()) {

			buf = arduino.serialRead(100);
			String[] coords = buf.split("/");

			for (String coord : coords) {
				if (coord.matches("\\<(.*?),(.*?)\\>")) {
					// System.out.println(xy);
					x = Integer.valueOf((coord.substring(coord.indexOf("<") + 1, coord.indexOf(","))));
					y = Integer.valueOf((coord.substring(coord.indexOf(",") + 1, coord.indexOf(">"))));
					System.out.printf("[%d,%d]\n", x, y);
				}
			}

		}

		arduino.closePort();

	}

}
