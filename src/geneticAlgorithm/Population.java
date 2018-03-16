package geneticAlgorithm;

import java.util.ArrayList;
import java.util.Random;

public class Population {
	
	DNA[] population;
	ArrayList<DNA> matingPool;
	boolean finished;
	String target;
	float mutationRate;
	int popMax;
	int generations;
	DNA bestMatch;

	Population(String target, int popMax, float mutationRate){
		this.target = target;
		this.mutationRate = mutationRate;
		
		population = new DNA[popMax];
		
		for(int x = 0; x < population.length; x++) {
			population[x] = new DNA(this.target.length());
		}
		
		calcFitness();
		matingPool = new ArrayList<DNA>();
		finished = false;
		generations = 0;
		
	}
	
	public boolean isFinished() {
		return finished;
	}

	public void calcFitness() {
		for (int x = 0; x < population.length; x++) {
			population[x].calcFitness(target);
		}
		
	}

	public void generateMatingPool() {
		
		matingPool.clear();
		
		float maxFitness = 0;
		for (int x = 0; x < population.length; x++) {
		    if (population[x].fitness > maxFitness) {
		      maxFitness = population[x].fitness;
		    }
		}
		
		for (int x = 0; x < population.length; x++) {
		  
			// This is key to the cross over
		    float fitness = (((population[x].fitness) * (1)) / (maxFitness));
		    // -----------------------------
		    
		    int n = (int) fitness * 100;
		    for (int j = 0; j < n; j++) {
		      matingPool.add(population[x]);
		    }
		}
	}

	public void generateNextGeneration() {
		
		Random rand = new Random();
		
		for (int x = 0; x < population.length; x++) {
			int a = rand.nextInt(matingPool.size());
			int b = rand.nextInt(matingPool.size());
			
			DNA mummy = matingPool.get(a);
			DNA daddy = matingPool.get(b);
			
			DNA child = mummy.crossover(daddy);
			child.mutate(mutationRate);
			
			population[x] = child;
		}
		
		generations++;
		
	}

	public void evaluate() {
		int highestScore = 0;
		int index = 0;
		
		for (int x = 0; x < population.length; x++) {
			
			if (population[x].fitness > highestScore) {
				index = x;
				highestScore = population[x].fitness;
			}
		}
		
		bestMatch = population[index];
		
		if (highestScore == target.length()) {
			finished = true;
		}
		
	}

	public DNA getBest() {
		return bestMatch;
	}

	public int getGenerationCount() {
		return generations;
	}

}
