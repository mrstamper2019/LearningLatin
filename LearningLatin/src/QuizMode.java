//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Map;
import java.util.Scanner;

public class QuizMode {
	//private static Scanner reader;

	//collection was an ArrayList
	public HashMap <String, HashMap<String, String>> collection = new HashMap<String, HashMap<String, String>>();
	
	//pre-made flashcard sets
	public HashMap <String, String> verb = new HashMap<String, String>();
	public HashMap <String, String> engVerb = new HashMap<String, String>();
	public HashMap <String, String> noun = new HashMap<String, String>();
	public HashMap <String, String> engNoun = new HashMap<String, String>();
	public HashMap <String, String> irregVerb = new HashMap<String, String>();

	//stores the incorrect answers and the number of times they appear
	private static HashMap<String, Integer> wrong = new HashMap<String, Integer>();
	public static boolean repeatSet; //tracks if a set should be repeated based on if the user got all the answers right in a run through
	private static String[] splitAns;
	private static boolean engToLatin = false;
	
	//static Scanner wrongAns;
	//Constructor
	public QuizMode() {
		verb.put("ambulo, ambulare, ambulavi, ambulatum", "walk");
		verb.put("amo, amare, amavi, amatus", "love");
		verb.put("cogito, cogitare, cogitavi, cogitatus", "think");
		verb.put("do, dare, dedi, datus", "give, reward");
		
		noun.put("cura, curae", "care");
		noun.put("ira, irae", "anger");
		noun.put("poena, poenae", "punishment");
		noun.put("sapientia, sapientiae", "wisdom");
		noun.put("vita, vitae", "life");
		noun.put("animus, animi", "mind");
		
		irregVerb.put("do, dare, dedi, datus", "give");

		//english to latin for verb and noun set
		engVerb.put("walk", "ambulo, ambulare, ambulavi, ambulatum");
		engVerb.put("love", "amo, amare, amavi, amatus");
		engVerb.put("think", "cogito, cogitare, cogitavi, cogitatus");
		engVerb.put("give", "do, dare, dedi, datus");
		engVerb.put("reward", "dono, donare, donavi, donatus");
		
		engNoun.put("cura, curae", "care");
		engNoun.put("ira, irae", "anger");
		engNoun.put("poena, poenae", "punishment");
		engNoun.put("sapientia, sapientiae", "wisdom");
		engNoun.put("vita, vitae", "life");
		engNoun.put("animus, animi", "mind");

		collection.put("verb conjugations", verb);
		collection.put("noun declensions", noun);
		collection.put("engVerb", engVerb);
		collection.put("engNoun", engNoun);
		collection.put("irregular verb", irregVerb);
		repeatSet = false;
	}
	
	public QuizMode(String q, String a) {	
		//TODO used to create a new study set. only to be used if the requests to make a new set
	}
	
	//Get and Set
	public String getQuestion(int location) { 
		return "";  
	}
	public String getAnswer(int location) { 
		return "";  
	}
	//if the user has seen this, returns true
	public boolean hasSeen() { return true; }
	
	public void setQuestion() {
		//TODO implement to allow users to create a new flashcard set; might be combined with setAnswer() for efficiency? or just added a new constructor (we'll see)
	}
	public void setAnswer() {	
		//TODO implement to allow users to create a new flashcard set; might be combined with setQuestion() for efficiency? or just added a new constructor (we'll see)
	}
	
	/* 1) takes a string (name) of a set and, if the set exists, will iterate through it
	 * 2) prints a question
	 * 3) waits for input
	 * 4) compares input to the answer
	 * 5) prints if correct or not
	 */
	public void quiz(String name) {
			if(collection.get(name) != null) {
				System.out.println("Opening " + name); 
				//int missed = 0;
				if(name == "engVerb" || name == "engNoun") {
					engToLatin = true;
				}
				check(collection.get(name));
				//check(collection.get(name), missed);
				System.out.println("Set complete!");
				getWrongQuestions();
			}
			else if(name == null) {
				System.out.println("Please enter a name");
			}
			else {
				System.out.println("Sorry, " + name + " isn't a quiz set!");
			}
	}
	
	//this method checks to see if the input name exists in the collection list
	private static void check(HashMap<String, String> map) {
		if(map != null) {
			Scanner reader = new Scanner(System.in);
			map.forEach((question, answer) -> {			//misspelled answers not accepted
				System.out.println(question);
				if(reader.hasNextLine()) { 				//prints the last/next question for some reason
					String hold = reader.nextLine(); 	//error here, reprints question if one is incorrect
					hold = hold.toLowerCase();
					if(answer.contains(",") && engToLatin != true) {			//allows for multiple correct answers to be accepted
						boolean tr = false;
						splitAns = answer.split(",");
						for(int i = 0; i < splitAns.length; i++) {
							//System.out.println("this holds " + splitAns[i] + "\n your answer: " + hold);
							if(splitAns[i].contains(hold)) {
								System.out.println("CORRECT");
								tr = true;
								break;
							} 
						}
						if(tr != true) {
							System.out.println("INCORRECT");
							System.out.println("Correct answer: " + answer);
							answerWrong(question);
							tr = false;
							repeatSet = true;
						}
					} else {
						//splitAns = answer.split(" ");
						//splitAns = new String[1];
						//splitAns[0] = answer; //.split();
						//System.out.println("hold contains: " + hold);
						//System.out.println("answer: " + answer);	//"splitAns: " + splitAns[0]);
						if(answer.compareTo(hold) == 0) {//if(splitAns[0].contains(hold)) { //splitAns[0].contains(answer)) { 
							System.out.println("CORRECT");
							//repeatSet = false;
							//map.remove(question);
						} else { 
							System.out.println("INCORRECT");
							System.out.println("Correct answer: " + answer);
							answerWrong(question);
							repeatSet = true;
						}
					}
				}
			});
			if(!wrong.isEmpty() && repeatSet == true) { 
				System.out.println("Since you missed a question, let's go back to the beginning.");	
				repeatSet = false;
				check(map); 
					
				}
			reader.close();
		} else {
			System.out.println("That's not a set");	
		}
	}
	
	//adds each question missed to the 'wrong' HashMap and updates the number of times it was missed
	public static void answerWrong(String question) {
		if(!wrong.containsKey(question)) {// || wrong.isEmpty()) {
		//if the question is not in the map, then it is added and the value set to 1
			wrong.put(question, 1);
			//System.out.println("Value of " + question + " is: " + wrong.get(question));
		} else {
		//the question should exist in the map and so only the value should be updated
			int oldValue = wrong.get(question);
			oldValue++;
			//System.out.println(oldValue);
			wrong.replace(question, oldValue);
		}
	}
	
	//prints the contents of the 'wrong' HashMap
	public void getWrongQuestions() {
		if(wrong.isEmpty()) {
			System.out.println("You didn't miss anything! Great job!");
		} else {
			System.out.println("You missed: "); //add each question missed to new data structure, keep track of how many questions missed?
			wrong.forEach((question, missed) -> { 
			//iterates through the HashMap, prints the key (missed question) and the value (times missed)
				System.out.println(question + ": " + missed + " times");
			});
		}
	}
}
