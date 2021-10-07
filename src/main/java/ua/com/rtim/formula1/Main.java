package ua.com.rtim.formula1;

public class Main {

	public static void main(String[] args) {
		RacerRepository racerRepository = new RacerRepository();
		ReportFormatter formatter = new ReportFormatter();
		System.out.println(formatter.format(racerRepository.splitToRacersList(), 15));
	}
}
