package bgl.conway.service;

import bgl.conway.exceptions.InvalidInputException;
import bgl.conway.model.World;

public interface GenerationService {
	
    public World setInitialAliveCells(World world, String coordinates) throws InvalidInputException;
    public int countAliveNeighbors(World world, int row, int column);
    public World applyRulesForOneGeneration(World world);
    public void printWorld(World world, int worldNumber);
    
}
