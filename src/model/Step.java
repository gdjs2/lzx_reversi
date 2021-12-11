package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Step {
	private Position putPosition;
	private GameMode mode;
	private List<Position> flipPosition;

	public Step(Position putPosition, GameMode mode) {
		this.putPosition = putPosition;
		this.mode = mode;
		flipPosition = new ArrayList<>();
	}

	public Position getPutPosition() {
		return putPosition;
	}

	public GameMode getMode() {
		return mode;
	}

	public List<Position> getFlipPosition() {
		return flipPosition;
	}

	public void addFlip(Position p) {
		flipPosition.add(p);
	}
}
