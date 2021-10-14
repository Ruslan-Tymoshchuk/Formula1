package ua.com.rtim.formula1;

import static java.lang.System.lineSeparator;
import static java.util.Comparator.comparing;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportFormatter {

	public static final String HYPHEN_DELIMITER = "-";
	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("mm:ss.SSS");

	public String format(List<Racer> racers, int bestRacersNumber) {
		StringBuilder result = new StringBuilder();
		int maxNameLength = getMaxFieldLength(racers, Racer::getFullName);
		int maxTeamLength = getMaxFieldLength(racers, Racer::getTeam);
		AtomicInteger number = new AtomicInteger();
		String pattern = "%02d. %-" + maxNameLength + "s | %-" + maxTeamLength + "s | %s";
		racers.stream().sorted(comparing(Racer::getLapTime)).forEach(s -> {
			if ((number.get() == bestRacersNumber)) {
				result.append(repeatChar(maxNameLength + maxTeamLength + 19)).append(lineSeparator());
			}
			result.append(String.format(pattern, number.incrementAndGet(), s.getFullName(), s.getTeam(),
					formatToTime(s.getLapTime()))).append(lineSeparator());
		});
		return result.toString();
	}

	private String repeatChar(int times) {
		return Stream.generate(() -> HYPHEN_DELIMITER).limit(times).collect(Collectors.joining());
	}

	private int getMaxFieldLength(List<Racer> racers, Function<Racer, String> function) {
		return racers.stream().map(function).mapToInt(String::length).max().orElse(0);
	}

	private String formatToTime(Duration duration) {
		return LocalTime.ofNanoOfDay(duration.toNanos()).format(TIME_FORMATTER);
	}
}
