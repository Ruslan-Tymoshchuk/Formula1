package ua.com.rtim.formula1;

import static java.lang.System.lineSeparator;
import static java.time.Duration.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ReportFormatterTest {

	private ReportFormatter formatter = new ReportFormatter();

	@Test
	void givenListRacers_whenFormatWithBestRacersNumberIsOne_thenPrintReport() {
		List<Racer> actual = new ArrayList<>();
		actual.add(new Racer(parse("PT1M12.941S"), "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA"));
		actual.add(new Racer(parse("PT1M12.639S"), "Kimi Raikkonen", "FERRARI"));
		actual.add(new Racer(parse("PT1M13.323S"), "Lance Stroll", "WILLIAMS MERCEDES"));
		StringBuilder expected = new StringBuilder();
		expected.append("01. Kimi Raikkonen | FERRARI                   | 01:12.639" + lineSeparator());
		expected.append("----------------------------------------------------------" + lineSeparator());
		expected.append("02. Pierre Gasly   | SCUDERIA TORO ROSSO HONDA | 01:12.941" + lineSeparator());
		expected.append("03. Lance Stroll   | WILLIAMS MERCEDES         | 01:13.323" + lineSeparator());
		assertEquals(expected.toString(), formatter.format(actual, 1));
	}

	@Test
	void givenListRacers_whenFormatWithBestRacersNumberIsEqualOrMore_thenPrintReport() {
		List<Racer> actual = new ArrayList<>();
		actual.add(new Racer(parse("PT1M12.941S"), "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA"));
		actual.add(new Racer(parse("PT1M12.639S"), "Kimi Raikkonen", "FERRARI"));
		actual.add(new Racer(parse("PT1M13.323S"), "Lance Stroll", "WILLIAMS MERCEDES"));
		StringBuilder expected = new StringBuilder();
		expected.append("01. Kimi Raikkonen | FERRARI                   | 01:12.639" + lineSeparator());
		expected.append("02. Pierre Gasly   | SCUDERIA TORO ROSSO HONDA | 01:12.941" + lineSeparator());
		expected.append("03. Lance Stroll   | WILLIAMS MERCEDES         | 01:13.323" + lineSeparator());
		assertEquals(expected.toString(), formatter.format(actual, 3));
	}

	@Test
	void givenNull_whenFormat_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> formatter.format(null, 15));
	}
}
