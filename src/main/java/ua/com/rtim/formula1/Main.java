package ua.com.rtim.formula1;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		RacerRepository racerRepository = new RacerRepository("config.properties", "startTimeFile", "finishTimeFile",
				"abbreviationsFile");
		ReportFormatter formatter = new ReportFormatter();
		System.out.println(formatter.format(racerRepository.getRacers(), 15));
	}
}
