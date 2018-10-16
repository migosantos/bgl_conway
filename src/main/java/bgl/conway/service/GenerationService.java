package bgl.conway.service;

import bgl.conway.model.World;

public interface GenerationService {
	
    public World setInitialAliveCells(World world, String coordinates);
    public int countAliveNeighbors(World world, int row, int column);
    public World applyRulesForOneGeneration(World world);
    public void printWorld(World world, int worldNumber);
    
}
