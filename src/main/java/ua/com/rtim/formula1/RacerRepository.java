package ua.com.rtim.formula1;

import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;
import static java.time.Duration.between;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacerRepository {

	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
	public static final String FILE_PATH = "src/main/resources/";

	public List<Racer> splitToRacersList() {
		Map<String, LocalDateTime> startTime = splitToTime(readFileAsStream("startTime.log"));
		Map<String, LocalDateTime> finishTime = splitToTime(readFileAsStream("endTime.log"));
		return readFileAsStream("abbreviations.txt").map(s -> s.split("_"))
				.map(s -> new Racer(between(startTime.get(s[0]), finishTime.get(s[0])), s[1], s[2]))
				.collect(Collectors.toList());
	}

	private Stream<String> readFileAsStream(String fileName) {
		Stream<String> line;
		try {
			line = lines(get(FILE_PATH, fileName));
		} catch (IOException | RuntimeException exception) {
			throw new RuntimeException("I can't find the file! " + fileName);
		}
		return line;
	}

	private Map<String, LocalDateTime> splitToTime(Stream<String> lines) {
		return lines.collect(
				Collectors.toMap(s -> s.substring(0, 3), s -> LocalDateTime.parse(s.substring(3), TIME_FORMATTER)));
	}
}
