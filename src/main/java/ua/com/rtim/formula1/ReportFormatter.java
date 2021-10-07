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
	public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("mm:ss.SSS");

	public String format(List<Racer> racers, int limit) {
		StringBuilder format = new StringBuilder();
		int maxNameLength = getMaxFieldLength(racers, Racer::getFullName);
		int maxTeamLength = getMaxFieldLength(racers, Racer::getTeam);
		AtomicInteger count = new AtomicInteger();
		String pattern = "%02d. %-" + maxNameLength + "s | %-" + maxTeamLength + "s | %s";
		racers.stream().sorted(comparing(Racer::getLapTime)).forEach(s -> {
			if ((count.get() == limit)) {
				format.append(repeatChar(HYPHEN_DELIMITER, maxNameLength + maxTeamLength + 19)).append(lineSeparator());
			}
			format.append(String.format(pattern, count.incrementAndGet(), s.getFullName(), s.getTeam(),
					formatToTime(s.getLapTime()))).append(lineSeparator());
		});
		return format.toString();
	}

	private String repeatChar(String symbol, int times) {
		return Stream.generate(() -> symbol).limit(times).collect(Collectors.joining());
	}

	private int getMaxFieldLength(List<Racer> racers, Function<Racer, String> function) {
		return racers.stream().mapToInt(s -> function.apply(s).length()).max().getAsInt();
	}

	private String formatToTime(Duration duration) {
		return LocalTime.ofNanoOfDay(duration.toNanos()).format(TIME);
	}
}
