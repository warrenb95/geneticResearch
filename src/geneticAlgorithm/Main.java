package geneticAlgorithm;

public class Main {
	
	static String target;
	static int popMax;
	static float mutationRate;
	static Population population;

	public static void main(String[] args) {
		// Set target, popMax, mutationRate
		
		target = "Hello my name is Warren";
		popMax = 200;
		mutationRate = 0.1f;
		
		population = new Population(target, popMax, mutationRate);
		
		
//		// Test loop
//		for(int x = 0; x < 1; x++) {
//			
//			population.calcFitness();
//			
//			population.generateMatingPool();
//			
//			population.generateNextGeneration();
//			
//			population.calcFitness();
//			
//			population.evaluate();
//			
//			System.out.printf("\n\nBest match: %s \n", population.getBest().getPhrase());
//			System.out.printf("Best match fitness: %s \n", population.getBest().fitness);
//			System.out.printf("Generation count: %s \n", population.getGenerationCount());
//			
//		}
		
		// Main loop
		while(!population.isFinished()) {
			
			population.generateMatingPool();
			
			population.generateNextGeneration();
			
			population.calcFitness();
			
			population.evaluate();
			
			System.out.printf("\n\nBest answer: %s \n", population.getBest().getPhrase());
			System.out.printf("Best fitness: %s \n", population.getBest().fitness);
			System.out.printf("Generation count: %s \n", population.getGenerationCount());
			
		}

	}

}
