package bgl.conway.service;

import java.util.ArrayList;
import java.util.List;

import bgl.conway.model.Cell;
import bgl.conway.model.World;

public class GenerationServiceImpl implements GenerationService {

	@Override
    public World setInitialAliveCells(World world, String coordinates) {
		coordinates = coordinates.replace(" ","").replace("[", "").replace("]", "");
    	String[] coordinatesArray = coordinates.split(",");
    	
    	if(coordinates.length() > 0) {
	    	for(int i=0; i<coordinatesArray.length; i+=2) {
	    		int row = Integer.valueOf(coordinatesArray[i]);
	    		int column = Integer.valueOf(coordinatesArray[i+1]);
	    		world.setCellState(row, column, true);
	    	}
    	}
    	
		return world;
    }

	@Override
    public int countAliveNeighbors(World world, int row, int column) {
        int totalAliveNeighbors = 0;
        for (int rowDelta=-1; rowDelta<=1; rowDelta++) {
        	for (int columnDelta=-1; columnDelta<=1; columnDelta++) {
                int neighborRow = row+rowDelta;
                int neighborColumn = column+columnDelta;
                Cell[][] matrix = world.getMatrix();
                if (neighborRow >= 0 && neighborRow < matrix.length 
                		&& neighborColumn >= 0 && neighborColumn < matrix[row].length 
                		&& !(rowDelta == 0 && columnDelta == 0) 
                		&& matrix[neighborRow][neighborColumn].isAlive()) {
                    totalAliveNeighbors++;
                }
            }
        }
        return totalAliveNeighbors;
    }

	@Override
    public World applyRulesForOneGeneration(World world) {
    	Cell[][] matrix = world.getMatrix();
    	World newWorld = new World(matrix.length, matrix.length); 
    	
    	for(int row=0; row<matrix.length; row++) {
    		for(int column=0; column<matrix[row].length; column++) {
    			Cell currentCell = matrix[row][column];
    			int aliveNeighbors = countAliveNeighbors(world, row, column);
    			
    			if(currentCell.isAlive()) {
	    			if(aliveNeighbors < 2 || aliveNeighbors > 3) {
	    				newWorld.setCellState(row, column, false);
	    			} else {
	    				newWorld.setCellState(row, column, true);
	    			}
    			} else {
    				if(aliveNeighbors == 3) {
    					newWorld.setCellState(row, column, true);
    				}
    			}
			}
   		}
    	
    	return newWorld;
    }
    
	@Override
	public void printWorld(World world, int worldNo) {
		List<String> printList = new ArrayList<String>();
		Cell[][] matrix = world.getMatrix();
		for(int row=0; row<matrix.length; row++) {
			for(int column=0; column<matrix[row].length; column++) {
				if(matrix[row][column].isAlive()) {
					printList.add(new StringBuilder().append("[").append(row).append(",").append(column).append("]").toString());
				} 
			}
		}
		System.out.println(new StringBuilder().append(worldNo).append(" : ").append(printList));
	}
}
