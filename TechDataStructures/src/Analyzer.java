import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {

		List<Sentence> sentences = new ArrayList<>();
		StringBuilder lineString = new StringBuilder();
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				lineString.setLength(0);
				lineString.append(sc.nextLine());
				if(isWellFormated(lineString)) {
					int score = getIntFromLine(lineString);
					sentences.add(new Sentence(score, getStringFromLine(lineString)));
				}
			}
		} catch (Exception e) {
			return sentences;
		}
		return sentences;

	}
	
	private static boolean isWellFormated(StringBuilder lineString) {
		String line = lineString.toString();
		if((line.startsWith("-1") || line.startsWith("-2")) && line.charAt(2) == ' ') {
			return true;
		} else if ((line.matches("^[012]") && line.charAt(1) == ' ')){
			return true;
		} else {
			return false;
		}
	}
	
	private static int getIntFromLine(StringBuilder lineString) {
		String line = lineString.toString();
		return Integer.parseInt(line.startsWith("-") ? line.substring(0, 2) : line.substring(0, 1));
	}
	
	private static String getStringFromLine(StringBuilder lineString) {
		String line = lineString.toString();
		return line.startsWith("-") ? line.substring(3) : line.substring(2);
	} 
	
	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
		Set<Word> wordsSet = new HashSet<>();		
		if (sentences.isEmpty() || sentences == null) {
			return wordsSet;
		}
		//Uso un mapa que tendra el key como el string de un Word, para evitar iterar sobre un Set.
		HashMap<StringBuilder, Word> wordsMap = new HashMap<>();
		for (Sentence sentence : sentences) {
			if(sentence == null) { continue; }
			// Convierto cada texto de Sentence en un arraylist con sus strings.
			String[] wordString = sentence.getText().split(" ");
			List<String> arrayWordString = new ArrayList<>();
			arrayWordString = Arrays.asList(wordString);
			//añado Words y acumulo sus puntuaciones.
			cumulateScores(wordsMap, arrayWordString, sentence.getScore());
		}
		//Creo el Set con los Words del HashMap
		wordsSet = new HashSet<>(wordsMap.values());
		
		return wordsSet; // this line is here only so this code will compile if you don't modify it

	}
	
	private static void cumulateScores(HashMap<StringBuilder, Word> wordsMap, List<String> arrayWordString, int score) {
		Iterator<String> wordStringIt = arrayWordString.iterator();
		Word localWord;
		// Iterar sobre la lista de strings
		while (wordStringIt.hasNext()) {
			StringBuilder wordString = new StringBuilder();
			wordString.setLength(0);
			wordString.append(wordStringIt.next().toLowerCase());
			//Si el string no inicia con una letra, saltar al siguiente string
			if (!wordString.substring(0, 1).matches("[a-z]")) { continue; }
			// Si el mapa contiene un Word con la llave del string
			// Se obtiene el objeto Word, se incrementa el total y se lo vuelve a añadir al mapa.
			if (wordsMap.containsKey(wordString)) {
				localWord = wordsMap.remove(wordString);
				localWord.increaseTotal(score);
				wordsMap.put(wordString, localWord);
			} else {
				// Si no, Se crea un nuevo objeto Word y se lo añade al mapa
				localWord = new Word(wordString.toString());
				wordsMap.put(wordString, localWord);
			}
			wordString.setLength(0);
		}
	}
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {

		/* IMPLEMENT THIS METHOD! */
		
		return null; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

		/* IMPLEMENT THIS METHOD! */
		
		return 0; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
