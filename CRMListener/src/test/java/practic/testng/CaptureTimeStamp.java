package practic.testng;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.asynchttpclient.util.DateUtils;

public class CaptureTimeStamp {
	public static void main(String[] args) throws Throwable {
		String time = new Date().toString(); // .replace(" ","").replace(":", "");
		System.out.println(time);

		LocalDateTime now = LocalDateTime.now();
		String time2 = now.format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
		System.out.println(time2);
	}
}
