package cf.jetkov.jSerialDevices.examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fazecast.jSerialComm.*;

import cf.jetkov.jSerialDevices.SerialDevice;

public class Test {
	
	public static void main(String[] args) {
		String mydata = "2,498><482,497><482,498><482,497><482,498><482,497><482,498><482,4";
		Pattern pattern = Pattern.compile("\\<(.*?),(.*?)\\>");
		Matcher matcher = pattern.matcher(mydata);
		while (matcher.find())
		{
		    System.out.println(matcher.group(0));
		}
	}
}
