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

		Scanner scan;
		
		Pattern pattern = Pattern.compile("\\<(.*?),(.*?)\\>");
		Matcher matcher;

		while (arduino.openPort()) {
	
			buf = arduino.serialRead(100);
			String[] coord = buf.split("/");
			
			for (String xy : coord) {
				if (xy.matches("\\<(.*?),(.*?)\\>")) {
					//System.out.println(xy);
					x = Integer.valueOf((xy.substring(xy.indexOf("<") + 1, xy.indexOf(","))));
					y = Integer.valueOf((xy.substring(xy.indexOf(",") + 1, xy.indexOf(">"))));
					System.out.printf("[%d,%d]\n", x, y);
				}
			}
			
			

		}
		
		arduino.closePort();

	}

}
