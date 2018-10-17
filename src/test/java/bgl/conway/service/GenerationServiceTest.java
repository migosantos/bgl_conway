package bgl.conway.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bgl.conway.exceptions.InvalidInputException;
import bgl.conway.model.World;

public class GenerationServiceTest {
	private GenerationService genService;
	private static final int ROW_SIZE = 200;
	private static final int COLUMN_SIZE = 200;
	

	@Before
	public void setUp() throws Exception {
		genService = new GenerationServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetInitialAliveCells() throws InvalidInputException {
		World world = new World(ROW_SIZE,COLUMN_SIZE);
		String aliveCoordinates = "[[5, 5], [6, 5], [7, 5], [5, 6], [6, 6], [7, 6]]";
		genService.setInitialAliveCells(world, aliveCoordinates);
		assertTrue(world.getMatrix()[5][5].isAlive());
		assertTrue(world.getMatrix()[6][5].isAlive());
		assertTrue(world.getMatrix()[7][5].isAlive());
		assertTrue(world.getMatrix()[5][6].isAlive());
		assertTrue(world.getMatrix()[6][6].isAlive());
		assertTrue(world.getMatrix()[7][6].isAlive());
		assertFalse(world.getMatrix()[100][100].isAlive());
	}

	@Test
	public void testCountAliveNeighbors() throws InvalidInputException {
		World world = new World(ROW_SIZE,COLUMN_SIZE);
		String aliveCoordinates = "[[5, 5], [6, 5], [7, 5], [8, 6]";
		genService.setInitialAliveCells(world, aliveCoordinates);
		assertTrue(genService.countAliveNeighbors(world, 5, 5) == 1);
		assertTrue(genService.countAliveNeighbors(world, 6, 5) == 2);
		assertTrue(genService.countAliveNeighbors(world, 7, 5) == 2);
		assertTrue(genService.countAliveNeighbors(world, 8, 6) == 1);
	}
	
	@Test
	public void testUnderPopulation() throws InvalidInputException {
		World world = new World(ROW_SIZE,COLUMN_SIZE);
		String aliveCoordinates = "[[5, 5]]";
		genService.setInitialAliveCells(world, aliveCoordinates);
		assertTrue(genService.countAliveNeighbors(world, 5, 5) == 0);
		
		World newWorld = genService.applyRulesForOneGeneration(world);
		assertFalse(newWorld.getMatrix()[5][5].isAlive());
		
	}
	
	@Test
	public void testOverPopulation() throws InvalidInputException {
		World world = new World(ROW_SIZE,COLUMN_SIZE);
		String aliveCoordinates = "[[5, 5], [5, 6], [5, 4], [4, 5], [6, 5]]";
		genService.setInitialAliveCells(world, aliveCoordinates);
		assertTrue(genService.countAliveNeighbors(world, 5, 5) == 4);
		
		World newWorld = genService.applyRulesForOneGeneration(world);
		assertFalse(newWorld.getMatrix()[5][5].isAlive());
		
	}
	
	@Test
	public void testLiveUntilNextGeneration() throws InvalidInputException {
		World world = new World(ROW_SIZE,COLUMN_SIZE);
		String aliveCoordinates = "[[5, 5], [5, 6], [5, 4], [4, 5]]";
		genService.setInitialAliveCells(world, aliveCoordinates);
		assertTrue(genService.countAliveNeighbors(world, 5, 5) == 3);
		
		World newWorld = genService.applyRulesForOneGeneration(world);
		assertTrue(newWorld.getMatrix()[5][5].isAlive());
		
	}
	
	@Test
	public void testReproduction() throws InvalidInputException {
		World world = new World(ROW_SIZE,COLUMN_SIZE);
		String aliveCoordinates = "[[5, 5], [6, 5], [7, 5]]";
		genService.setInitialAliveCells(world, aliveCoordinates);
		assertFalse(world.getMatrix()[6][6].isAlive());
		assertTrue(genService.countAliveNeighbors(world, 6, 6) == 3);
		
		World newWorld = genService.applyRulesForOneGeneration(world);
		assertTrue(newWorld.getMatrix()[6][6].isAlive());
		
	}
	
	@Test
	public void testInvalidInput()  {
		World world = new World(ROW_SIZE,COLUMN_SIZE);
		String aliveCoordinates = "xyasdf123";
		try {
			genService.setInitialAliveCells(world, aliveCoordinates);
		} catch (InvalidInputException e) {
			assertTrue(e != null);
		}
		
	}
}
