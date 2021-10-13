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
	void givenFileNames_whenGetRacers_thenExeptionFileNotFound() throws IOException {
		RacerRepository racerRepository = new RacerRepository("startTime.log", "file", "abbreviations.txt");
		Throwable exception = assertThrows(FileNotFoundException.class, () -> racerRepository.getRacers());
		assertEquals("File '" + "file" + "' not found", exception.getMessage());
	}

	@Test
	void givenFileNamesNull_whenGetRacers_thenExeption() throws IOException {
		RacerRepository racerRepository = new RacerRepository(null, null, null);
		assertThrows(NullPointerException.class, () -> racerRepository.getRacers());
	}

	@Test
	void givenFileNames_whenGetRacers_thenRacersList() throws IOException {
		List<Racer> expected = new ArrayList<>();
		expected.add(new Racer(parse("PT1M12.941S"), "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA"));
		expected.add(new Racer(parse("PT1M12.639S"), "Kimi Raikkonen", "FERRARI"));
		expected.add(new Racer(parse("PT1M13.323S"), "Lance Stroll", "WILLIAMS MERCEDES"));
		RacerRepository racerRepository = new RacerRepository("startTime.log", "endTime.log", "abbreviations.txt");
		assertEquals(expected, racerRepository.getRacers());
	}
}
