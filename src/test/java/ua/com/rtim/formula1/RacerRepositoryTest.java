package ua.com.rtim.formula1;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RacerRepositoryTest {

	@Mock
	private RacerRepository racerRepository;

	@Test
	void readFromFiles_whenSplitToRacersList_thenRacersList() {
			List<Racer>expected = new ArrayList<>();
			expected.add(new Racer(ofSeconds(72), "Sebastian Vettel", "FERRARI"));
			expected.add(new Racer(ofSeconds(74), "Lewis Hamilton", "MERCEDES"));
			Mockito.when(racerRepository.splitToRacersList()).thenReturn(expected);
			assertEquals(expected, racerRepository.splitToRacersList());
			Mockito.verify(racerRepository).splitToRacersList();
	    }
}