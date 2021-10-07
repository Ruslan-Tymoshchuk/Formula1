package ua.com.rtim.formula1;

import java.time.Duration;

public class Racer {

	private Duration lapTime;
	private String fullName;
	private String team;

	public Racer(Duration lapTime, String fullName, String team) {
		this.lapTime = lapTime;
		this.fullName = fullName;
		this.team = team;
	}

	public Duration getLapTime() {
		return lapTime;
	}

	public String getFullName() {
		return fullName;
	}

	public String getTeam() {
		return team;
	}
}
