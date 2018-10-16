package bgl.conway.model;

public class Cell {
	private boolean isAlive;
	
	public Cell(boolean isAlive) {
		setAlive(isAlive);
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
}
