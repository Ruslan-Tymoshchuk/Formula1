package ua.com.rtim.formula1;

import static java.time.Duration.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RacerRepositoryTest {

	@Test
	void givenFileNames_whenGetRacers_thenExeptionPropertyFileNotFound() throws IOException {
		RacerRepository racerRepository = new RacerRepository("prop", "startTimeFile", "finishTimeFile",
				"abbreviationsFile");
		Throwable exception = assertThrows(FileNotFoundException.class, () -> racerRepository.getRacers());
		assertEquals("Property file '" + "prop" + "' not found", exception.getMessage());
	}

	@Test
	void givenFileNames_whenGetRacers_thenExeptionFileNotFound() throws IOException {
		RacerRepository racerRepository = new RacerRepository("config.properties", "file", "finishTimeFile",
				"abbreviationsFile");
		Throwable exception = assertThrows(FileNotFoundException.class, () -> racerRepository.getRacers());
		assertEquals("File '" + "file" + "' not found", exception.getMessage());
	}

	@Test
	void givenFileNamesNull_whenGetRacers_thenExeption() throws IOException {
		RacerRepository racerRepository = new RacerRepository(null, null, null, null);
		assertThrows(NullPointerException.class, () -> racerRepository.getRacers());
	}

	@Test
	void givenFileNames_whenGetRacers_thenRacersList() throws IOException {
		List<Racer> expected = new ArrayList<>();
		expected.add(new Racer(parse("PT1M12.013S"), "Daniel Ricciardo", "RED BULL RACING TAG HEUER"));
		expected.add(new Racer(parse("PT1M4.415S"), "Sebastian Vettel", "FERRARI"));
		expected.add(new Racer(parse("PT1M12.46S"), "Lewis Hamilton", "MERCEDES"));
		expected.add(new Racer(parse("PT1M12.639S"), "Kimi Raikkonen", "FERRARI"));
		expected.add(new Racer(parse("PT1M12.434S"), "Valtteri Bottas", "MERCEDES"));
		expected.add(new Racer(parse("PT1M13.028S"), "Esteban Ocon", "FORCE INDIA MERCEDES"));
		expected.add(new Racer(parse("PT1M12.657S"), "Fernando Alonso", "MCLAREN RENAULT"));
		expected.add(new Racer(parse("PT1M12.95S"), "Carlos Sainz", "RENAULT"));
		expected.add(new Racer(parse("PT1M12.848S"), "Sergio Perez", "FORCE INDIA MERCEDES"));
		expected.add(new Racer(parse("PT1M12.941S"), "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA"));
		expected.add(new Racer(parse("PT1M13.065S"), "Nico Hulkenberg", "RENAULT"));
		expected.add(new Racer(parse("PT1M12.463S"), "Stoffel Vandoorne", "MCLAREN RENAULT"));
		expected.add(new Racer(parse("PT1M12.706S"), "Sergey Sirotkin", "WILLIAMS MERCEDES"));
		expected.add(new Racer(parse("PT1M12.829S"), "Charles Leclerc", "SAUBER FERRARI"));
		expected.add(new Racer(parse("PT1M12.93S"), "Romain Grosjean", "HAAS FERRARI"));
		expected.add(new Racer(parse("PT1M13.179S"), "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA"));
		expected.add(new Racer(parse("PT1M13.265S"), "Marcus Ericsson", "SAUBER FERRARI"));
		expected.add(new Racer(parse("PT1M13.323S"), "Lance Stroll", "WILLIAMS MERCEDES"));
		expected.add(new Racer(parse("PT1M13.393S"), "Kevin Magnussen", "HAAS FERRARI"));
		RacerRepository racerRepository = new RacerRepository("config.properties", "startTimeFile", "finishTimeFile",
				"abbreviationsFile");
		assertEquals(expected, racerRepository.getRacers());
	}
}
