package br.leopiccionia.crypto.urna;

import java.security.SecureRandom;
import java.util.BitSet;

public class ConwayGameOfLife implements Runnable{
	private final int TABLE_HEIGHT = 8;
	private final int TABLE_WIDTH = 8;
	private final int TABLE_SIZE = 64;
	private final int MUTATION_TURNS = 12;
	private BitSet table;
	private volatile boolean sameValue = false;

	
	public ConwayGameOfLife(long seed){
		table = new BitSet(TABLE_SIZE);
		for(int i = 0; i < TABLE_SIZE; i++)
			table.set(i, (seed >> i) == 1);
	}

	public String getCurrentValue(){
		while(sameValue); /* Avoid returning same value twice in a row. */
		sameValue = true;
		return table.toString();
	}
	
	public void mutate(){
		SecureRandom randomness = new SecureRandom();
		for(int turn = 0; turn < MUTATION_TURNS; turn++){
			int index = Math.abs(randomness.nextInt()) % TABLE_SIZE; /* Is it less likely to compute 0? */
			table.set(index, !table.get(index));
		}
	}
	
	public void run(){
		while(true){
			BitSet newTable = new BitSet(TABLE_SIZE);
			for(int i = 0; i < TABLE_HEIGHT; i++){
				for(int j = 0; j < TABLE_WIDTH; j++){
					int index = i * TABLE_WIDTH + j;
					newTable.set(index, willLive(table.get(index), i, j));
				}
			}
			sameValue = false;
			table = newTable;
			//mutate(); /* Comment this line for deterministic behavior. */

		}
	}
	
	private boolean willLive(boolean alive, int i, int j){
		int neighborsCount = 0;
		
		/* Verifying neighbors*/
		if(table.get(TABLE_WIDTH * ((TABLE_HEIGHT + i - 1) % TABLE_HEIGHT) + j) == true)
			neighborsCount++;
		if(table.get(TABLE_WIDTH * ((i + 1) % TABLE_HEIGHT) + j) == true)
			neighborsCount++;
		if(table.get(TABLE_WIDTH * i + ((TABLE_WIDTH + j - 1) % TABLE_WIDTH)) == true)
			neighborsCount++;
		if(table.get(TABLE_WIDTH * i + ((j + 1) % TABLE_WIDTH)) == true)
			neighborsCount++;
		
		/* Diagnosing cell's health */
		if(alive == true){
			if(neighborsCount < 2 || neighborsCount > 3)
				return false;
			return true;
		}
		else{
			if(neighborsCount == 3)
				return true;
			return false;
		}
	}
	
}