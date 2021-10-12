package ua.com.rtim.formula1;

import static java.nio.file.Files.lines;
import static java.time.Duration.between;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacerRepository {

	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
	private final String property;
	private final String startTime;
	private final String finishTime;
	private final String abbreviations;

	public RacerRepository(String property, String startTime, String finishTime, String abbreviations) {
		this.property = property;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.abbreviations = abbreviations;
	}

	public List<Racer> getRacers() throws IOException {
		Map<String, LocalDateTime> startTimes = splitToTime(getFileLines(startTime));
		Map<String, LocalDateTime> finishTimes = splitToTime(getFileLines(finishTime));
		return getFileLines(abbreviations).stream().map(s -> s.split("_"))
				.map(s -> new Racer(between(startTimes.get(s[0]), finishTimes.get(s[0])), s[1], s[2]))
				.collect(Collectors.toList());
	}

	private List<String> getFileLines(String inputName) throws IOException {
		Properties namesProperty = new Properties();
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(property)) {
			if (inputStream != null) {
				namesProperty.load(inputStream);
			} else {
				throw new FileNotFoundException();
			}
		} catch (IOException e) {
			throw new FileNotFoundException("Property file '" + property + "' not found");
		}
		File file;
		try {
			String fileName = namesProperty.getProperty(inputName);
			if (fileName != null) {
				file = new File(getClass().getClassLoader().getResource(fileName).getFile());
			} else {
				throw new FileNotFoundException();
			}
		} catch (IOException e) {
			throw new FileNotFoundException("File '" + inputName + "' not found");
		}
		try (Stream<String> fileLines = lines(file.toPath())) {
			return fileLines.collect(Collectors.toList());
		}
	}

	private Map<String, LocalDateTime> splitToTime(List<String> timeLines) {
		return timeLines.stream().collect(
				Collectors.toMap(s -> s.substring(0, 3), s -> LocalDateTime.parse(s.substring(3), TIME_FORMATTER)));
	}
}
