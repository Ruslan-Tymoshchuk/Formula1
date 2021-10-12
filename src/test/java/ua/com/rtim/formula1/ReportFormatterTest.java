package ua.com.rtim.formula1;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ReportFormatterTest {

	private ReportFormatter formatter = new ReportFormatter();

	@Test
	void givenListRacers_whenFormat_thenPrintReport() throws IOException {
		RacerRepository racerRepository = new RacerRepository("config.properties", "startTimeFile", "finishTimeFile",
				"abbreviationsFile");
		StringBuilder expected = new StringBuilder();
		expected.append("01. Sebastian Vettel  | FERRARI                   | 01:04.415" + lineSeparator());
		expected.append("02. Daniel Ricciardo  | RED BULL RACING TAG HEUER | 01:12.013" + lineSeparator());
		expected.append("03. Valtteri Bottas   | MERCEDES                  | 01:12.434" + lineSeparator());
		expected.append("04. Lewis Hamilton    | MERCEDES                  | 01:12.460" + lineSeparator());
		expected.append("05. Stoffel Vandoorne | MCLAREN RENAULT           | 01:12.463" + lineSeparator());
		expected.append("06. Kimi Raikkonen    | FERRARI                   | 01:12.639" + lineSeparator());
		expected.append("07. Fernando Alonso   | MCLAREN RENAULT           | 01:12.657" + lineSeparator());
		expected.append("08. Sergey Sirotkin   | WILLIAMS MERCEDES         | 01:12.706" + lineSeparator());
		expected.append("09. Charles Leclerc   | SAUBER FERRARI            | 01:12.829" + lineSeparator());
		expected.append("10. Sergio Perez      | FORCE INDIA MERCEDES      | 01:12.848" + lineSeparator());
		expected.append("11. Romain Grosjean   | HAAS FERRARI              | 01:12.930" + lineSeparator());
		expected.append("12. Pierre Gasly      | SCUDERIA TORO ROSSO HONDA | 01:12.941" + lineSeparator());
		expected.append("13. Carlos Sainz      | RENAULT                   | 01:12.950" + lineSeparator());
		expected.append("14. Esteban Ocon      | FORCE INDIA MERCEDES      | 01:13.028" + lineSeparator());
		expected.append("15. Nico Hulkenberg   | RENAULT                   | 01:13.065" + lineSeparator());
		expected.append("-------------------------------------------------------------" + lineSeparator());
		expected.append("16. Brendon Hartley   | SCUDERIA TORO ROSSO HONDA | 01:13.179" + lineSeparator());
		expected.append("17. Marcus Ericsson   | SAUBER FERRARI            | 01:13.265" + lineSeparator());
		expected.append("18. Lance Stroll      | WILLIAMS MERCEDES         | 01:13.323" + lineSeparator());
		expected.append("19. Kevin Magnussen   | HAAS FERRARI              | 01:13.393" + lineSeparator());
		assertEquals(expected.toString(), formatter.format(racerRepository.getRacers(), 15));
	}

	@Test
	void givenNull_whenFormat_thenNullPointerException() {
		assertThrows(NullPointerException.class, () -> formatter.format(null, 15));
	}
}
