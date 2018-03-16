package geneticAlgorithm;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DNA {
	
	public char[] genes;
	public int fitness;

	// Constructor
	public DNA(int length) {
		genes = new char[length];
		fitness = 0;
		
		for(int x = 0; x < length; x++) {
			genes[x] = newChar();
		}
	}
	
	public char newChar() {
		String alphabet ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
		Random rand = new Random();
		
		return alphabet.charAt(rand.nextInt(alphabet.length()));
	}
	
	public String getPhrase() {
		return String.valueOf(genes);
	}
	
	public void calcFitness(String target) {
		
		for (int x = 0; x < genes.length; x++) {
			if (genes[x] == target.charAt(x)) {
				fitness++;
			}
		}
	}
	
	public DNA crossover(DNA partner) {
		
		DNA child = new DNA(this.genes.length);
		
		Random rand = new Random();
		int midPoint = rand.nextInt(this.genes.length);
//		int midPoint = (int) this.genes.length/2;
		
		for (int x = 0; x < genes.length; x++) {
			if ( x > midPoint) {
				child.genes[x] = this.genes[x];
			} else {
				child.genes[x] = partner.genes[x];
			}
			
		}
		
		return child;
	}
	
	public void mutate(float mutationRate) {
		
		Random rand = new Random();
		
		for (int x = 0; x < this.genes.length; x++) {
			if (rand.nextFloat() < mutationRate) {
				this.genes[x] = newChar();
			}
		}
	}

}
