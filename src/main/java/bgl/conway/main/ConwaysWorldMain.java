package bgl.conway.main;

import java.util.Scanner;

import bgl.conway.exceptions.InvalidInputException;
import bgl.conway.model.World;
import bgl.conway.service.GenerationService;
import bgl.conway.service.GenerationServiceImpl;

public class ConwaysWorldMain {
	
	private GenerationService genService;
	private static final int ROW_SIZE = 200;
	private static final int COLUMN_SIZE = 200;
	private static final int GENERATION_TIMES = 100;
	
	public ConwaysWorldMain() {
		genService = new GenerationServiceImpl();
	}
	
	public String askForInput() {
        Scanner sc = new Scanner(System.in); 
        
        System.out.println("Please enter Alive cells coordinates. Example of coordinates input format is [[5, 5], [6, 5], [7, 5], [5, 6], [6, 6], [7, 6]]");
        String coordinates = sc.nextLine(); 
        
        return coordinates;
	}
	
	public World initializeWorld(String aliveCoordinates) throws InvalidInputException {
		World world = new World(ROW_SIZE,COLUMN_SIZE);
		return genService.setInitialAliveCells(world, aliveCoordinates);
	}
	
	public World generateHundredTimes(World world) {
		for(int i=1; i<=GENERATION_TIMES; i++) {
			world = genService.applyRulesForOneGeneration(world);
			genService.printWorld(world, i);
		}
		return world;
	}
		
	public static void main(String[] args) {
		ConwaysWorldMain conwaysWorldMain = new ConwaysWorldMain();
		World world = null;
		
		do {
			String aliveCoordinates = conwaysWorldMain.askForInput();
			
			try {
				world = conwaysWorldMain.initializeWorld(aliveCoordinates);
			} catch (InvalidInputException e) {
				System.out.println("Invalid Input, please try again");
			}		
		} while(world == null);

		System.out.println("Output of the next 100 state:");
		world = conwaysWorldMain.generateHundredTimes(world);
	}
}
