package bgl.conway.model;

public class World {
	private Cell[][] matrix;
	
	public World(int rows, int columns) {
		this.matrix = new Cell[rows][columns];
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				this.matrix[i][j] = new Cell(false);
			}
		}
	}

	public Cell[][] getMatrix() {
		return matrix;
	}

	public void setCellState(int row, int column, boolean isAlive) {
		this.matrix[row][column].setAlive(isAlive);
	}
}
