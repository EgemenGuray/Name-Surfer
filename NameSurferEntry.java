/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants{
	
	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	public NameSurferEntry(String line){
		int ending = line.indexOf(" ");
		name = line.substring(0, ending);
		String numbers = line.substring(ending);
		StringTokenizer tokenizer = new StringTokenizer(numbers);
		for(int count = 0; tokenizer.hasMoreTokens(); count++){
			int popularityRank = Integer.parseInt(tokenizer.nextToken());
			rankings[count] = popularityRank;
		}
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName(){
		// You need to turn this stub into a real implementation //
		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade){
		// You need to turn this stub into a real implementation //
		return rankings[decade];
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString(){
		// You need to turn this stub into a real implementation //
		String value = "\""+name+"[";
		for(int i = 0; i<NDECADES; i++) {
			value = value + getRank(i) + " ";
		}
		value = value + "]\"";
		return value;
	}
	
	private String name;
	
	private int[]rankings = new int[NDECADES];
	
}

