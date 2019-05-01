import java.util.HashMap;
import java.util.Scanner;
//import java.util.ArrayList;

public class LearningMode {
//mode that displays the different types of study materials, which are prompted by the main UI for the user to choose from
	private Scanner openQuiz;
	private HashMap<String, String[][]> co = new HashMap<String, String[][]>();
	private String[][] irrVerb;
	private String[][] nounDec;
	private String[][] nounDecEndings;
	private String[][] verbConj;
	private String[][] grammarRules;
	private String[][] verbEndings;
	//private String[] holdEndings;
	//HashMap<String, String> irrVerb = new HashMap<String, String>();
	//HashMap<String, String> verbConj = new HashMap<String, String>();
	//HashMap<String, String> nounDec = new HashMap<String, String>();
	
	public LearningMode() {
		//order is verb, translation, stem, and conjugation
		irrVerb = new String[][] { 
			{"adfero, adferre, attuli, allatum", "bring", "-fero", "3rd"},
			{"confero, conferre, contuli, collatum", "gather", "-fero", "3rd"},
			{"do, dare, dedi, datum", "give", "n/a", "1st"},
			{"eo, ire, ii (ivi), itum", "go", "n/a", "irregular"},
			{"infero, inferre, intuli, illatum", "bear", "-fero", "3rd"},
		};
		
		verbConj = new String[][] {
			{"ambulo, ambulare, ambulavi, ambulatum", "walk", "ambula-", "1st"},
			{"amo, amare, amavi, amatus", "love", "am-", "1st",},
			{"cogito, cogitare, cogitavi, cogitatus", "think", "cogit-", "1st"},
			{"habeo, habere, habui, habitum", "have, hold", "hab-", "2nd"},
			{"video, videre, vidi, visum", "see", "vid-", "2nd"}, 
			{"dico, dicere, dixi, dictum", "say", "dic-", "3rd"}, 
			{"scribo, scribere, scripsi, scriptum", "write", "scrib-", "3rd"},
			{"audio, audire, audivi (audii), auditum", "hear, listen to", "aud-", "4th"},
			{"venio, venire, veni, ventum", "come", "ven-", "4th"}
		};
		
		verbEndings = new String[][] {
			//TODO add the verb endings for all the tenses
		};
		
		nounDec = new String[][] {
			{"cura, curae", "care, concern", "feminine", "1st", "cura-"},
			{"ira, irae", "anger, wrath", "feminine", "1st", "ira-"},
			{"poena, poenae", "punishment", "feminine", "1st", "poen-"},
			{"sapientia, sapientiae", "wisdom", "feminine", "1st", "sapienti-"},
			{"vita, vitae", "life", "feminine", "1st", "vit-"},
			{"animus, animi", "mind, spirit", "masculine", "2nd", "anim-"}, 
			{"deus, dei", "god", "masculine", "2nd", "de-"},
			{"rex, regis", "king", "masculine/feminine", "3rd", "reg-"},
			{"corpus, corporis", "body", "neuter", "3rd", "corpor-"}, 
			{"vultus, vultus", "look, expression", "masculine", "4th", "vult-"}, 
			{"fides, fidei", "trust, faith", "masculine/feminine", "5th", "fid-"}
		};
		
		//sorted by declension, gender, singular endings, plural endings, example nouns
		nounDecEndings = new String[][] {
			{"1st", "feminine", "-a, -ae, -ae, -am, -a, -a", "-ae, -arum, -is, -as, -as,  -is", "\n\tstella, stellae (star)\n\tcura, curae (care)"},
			{"2nd", "masculine", "-us (--), -i, -o, -um, -o, -e", "-i, -orum, -is, -os, is, -i", "\n\tservus, servi (slave)"},
			{"2nd2", "neuter", "-um, -i, -o, -um, -o, -um", "-a, -orum, -is, -a, -is, -s", "\n\tbellum, bella (war)"},
			//we're ignoring the 2nd dec o-stem nouns for now
			{"3rd", "masculine/feminine", "--, -is, -i, -em, -e, --", "-es, -um, -ibus, -es, -ibus, -es", "\n\tnoun goes here"},
			{"3rd3", "neuter", "--, -is, -i, --, -e, --", "-a, -um, -ibus, -a, -ibus, -a", "\n\tnoun goes here"},
			{"4th", "masculine", "-us, -us, -u (ui), -um, -u, -us", "-us, -uum, -ibus, -us, -ibus, -us", "\n\tnoun goes here"},
			{"5th", "masculine/feminine", "-es, -ei, -ei, -em, -e, -es", "-es, -erum, -ebus, -es, -ebus, -es", "\n\tnoun goes here"},
		};
		
		//only ablative at this time since this is what I think is most important
		grammarRules = new String[][] {
		
		};
		
		co.put("irregular verb", irrVerb);
		co.put("verb conjugations", verbConj);
		co.put("verb endings", verbEndings);
		co.put("noun declensions", nounDec);
		co.put("noun declension endings", nounDecEndings);
		co.put("grammar rules", grammarRules);
	}

	//returns if the learning set exists
	public String getLearn() {
		return null;
	}
	
	public String getSmth() {
		return null;
	}
	
	//the information and formatting for the verbs (irregular and not)
	private void getVerbRules(String[][] irr, String name) {
		System.out.println("You can identify the conjugation by looking at the 2nd principle part.");
		for(int i = 0; i < irr.length; i++) {
			for(int j = 0; j < irr[i].length; j++) {
				switch(j) {
				case 0:
					System.out.print("The verb '" + irr[i][j] + "'");
					break;
				case 1:
					System.out.println(" translates as '" + irr[i][j] +"'.");
					break;
				case 2:
					if(name == "verbConj") {
						System.out.println("The root of this verb is: " + irr[i][j]);
					} else if(name == "irrVerb"){
						if(!irr[i][j].contains("n/a"))
							System.out.println(irr[i][j] + " is the compound of this verb.");
					}
					break;
				case 3:
					System.out.println("This is a " + irr[i][j] + " conjugation verb.");
					if(irr[i][j].contains("1st")) {
						System.out.println("A 1st conjugation has an '-are' ending. The 'a' should have a long mark over it. ");
					} else if(irr[i][j].contains("2nd")) {
						System.out.println("A 2nd conjugation has an '-ere' ending. The first 'e' has a long mark over it. ");
					} else if(irr[i][j].contains("3rd")) {
						System.out.println("A 3rd conjugation has an '-ere' ending. ");
						System.out.println("The same thing applies to the 3rd conjugation i-stem verbs - so be careful!");
					} else if(irr[i][j].contains("4th")) {
						System.out.println("A 4th conjugation has an '-ire' ending. The 'i' has a long mark over it.");
					} else if(irr[i][j].contains("irregular")) {
						System.out.println("This verb doesn't really follow a set pattern. So instead of memorizing the pattern, focus on the principle parts!");
					}
					break;
				}
			}
			System.out.println();
		}
	}
	
	//TODO
	/*private void getVerbEnd(String[][] verb, String name) {
		for(int i = 0; i < verb.length; i++) {
			for(int j = 0; j < verb[i].length; j++) {
				switch(j) {
				case 0:
					//TODO
					System.out.print("\n" + verb[i][j]);
					break;
				case 1:
					//TODO
					System.out.println(" - " +verb[i][j]);
					break;
				case 2:
					//TODO
					System.out.println("\t" + verb[i][j]);

					break;
				case 3:
					//TODO
					System.out.println("\t" + verb[i][j]);

					break;
				}
			}
		}
	}*/
	
	private void getNounDec(String[][] noun, String name) {
		boolean one = false, two = false, three = false, four = false, five = false;
		//{"rex, regis", "king", "masculine", "3rd", "reg-"}
		//    0            1          2          3      4

		for(int i = 0; i < noun.length; i++) {
			for(int j = 0; j < noun[i].length; j++) {
				switch(j) {
				case 0: 
					System.out.print("\nThe noun " + noun[i][j] );
					break;
				case 1:
					System.out.println(" translates as " + noun[i][j]);
					break;
				case 2:
					System.out.print("This is a " + noun[i][j] + " and ");
					break;
				case 3:
					System.out.println(noun[i][j] + " declension noun.");
					/*if(noun[i][4] == "1st") {
						one = true;
					} else if(noun[i][4] == "2nd") {
						two = true;
					} else if(noun[i][4] == "3rd") {
						three = true;
					} else if(noun[i][4] == "4th") {
						four = true;
					} else if(noun[i][4] == "5th") {
						five = true;
					}*/
					break;
				case 4:
					System.out.println("Add the " + noun[i][j-1] + " declension endings to the stem " + noun[i][j] + " to decline the noun");
					if(one == true && noun[i][3] == "1st") {
						System.out.println("See above noun of 1st declension for the endings");
					} else if(two == true && noun[i][3] == "2nd") {
						System.out.println("See above noun of 2nd declension for the endings");
					} else if(three == true && noun[i][3] == "3rd") {
						System.out.println("See above noun of 3rd declension for the endings");
					} else if(four = true && noun[i][3] == "4th") {
						System.out.println("See above noun of 4th declension for the endings");
					} else if(five = true && noun[i][3] == "5th") {
						System.out.println("See above noun of 5th declension for the endings");
					} else {
						String[] holdEndings = {""};
						String[] holdEndingsPl = {""};
						if(noun[i][3] == "1st") {
							System.out.println("here");
							one = true;
							holdEndings = nounDecEndings[0][2].split(",");
							holdEndingsPl = nounDecEndings[0][3].split(",");
						} else if(noun[i][3] == "2nd") {
							two = true;
							if(noun[i][2] == "masculine") {
								holdEndings = nounDecEndings[1][2].split(",");
								holdEndingsPl = nounDecEndings[1][3].split(",");
							} else if(noun[i][2] == "neuter") {
								holdEndings = nounDecEndings[2][2].split(",");
								holdEndingsPl = nounDecEndings[2][3].split(",");
							}
						} else if(noun[i][3] == "3rd") {
							three = true;
							if(noun[i][2] == "masculine/feminine") {
								holdEndings = nounDecEndings[3][2].split(",");
								holdEndingsPl = nounDecEndings[3][3].split(",");
							} else if(noun[i][2] == "neuter") {
								holdEndings = nounDecEndings[4][2].split(",");
								holdEndingsPl = nounDecEndings[4][3].split(",");
							}
						} else if(noun[i][3] == "4th") {
							four = true;
							holdEndings = nounDecEndings[5][2].split(",");
							holdEndingsPl = nounDecEndings[5][3].split(",");
						} else if(noun[i][3] == "5th") {
							five = true;
							holdEndings = nounDecEndings[6][2].split(",");
							holdEndingsPl = nounDecEndings[6][3].split(",");
						}
						System.out.format("-Singular- \t -Plural-\n"
								+ "Nom: %5s \t Nom: %5s\n"
								+ "Gen: %5s \t Gen: %5s\n"
								+ "Dat: %5s \t Dat: %5s\n"
								+ "Abl: %5s \t Abl: %5s\n"
								+ "Acc: %5s \t Acc: %5s\n"
								+ "Voc: %5s \t Voc: %5s\n", 
								holdEndings[0], holdEndingsPl[0],
								holdEndings[1], holdEndingsPl[1],
								holdEndings[2], holdEndingsPl[2],
								holdEndings[3], holdEndingsPl[3],
								holdEndings[4], holdEndingsPl[4],
								holdEndings[5], holdEndingsPl[5]);
					}
					break;
				}
			
			}
		}
		
	}
	
	//noun dec endings has 5 columns
	private void getNounEndings(String[][] noun, String name) {
//	{"1st", "feminine", "-a, -ae, -ae, -am, a", "-ae, arum, is, as, is", "stella, stellae (star); cura, curae (care)"},
		String[] holdEndings = new String[6];
		String[] holdEndingsPl = new String[6];
		for(int i = 0; i < noun.length; i++) {
			for(int j = 0; j < noun[i].length; j++) {
				switch(j) {
				case 0: 
					if(!noun[i][j].equals("2nd2") && !noun[i][j].equals("3rd3")) {
						System.out.println("\n" + noun[i][j] + " declension");
					}
					break;
				case 1:
					System.out.format("%-10s %5s\n", "Gender: ", noun[i][j]);
					break;
				case 2:
					//String[] holdEndings = new String[6]
					holdEndings = noun[i][j].split(",");
					/*System.out.format("-Singular- \t -Plural-\n"
							+ "Nom: %5s \t Nom: %5s\n"
							+ "Gen: %5s \t Nom: %5s\n"
							+ "Dat: %5s \t Nom: %5s\n"
							+ "Abl: %5s \t Nom: %5s\n"
							+ "Acc: %5s \t Nom: %5s\n"
							+ "Voc: %5s \t Nom: %5s\n", 
							holdEndings[0], holdEndings[1], 
							holdEndings[2], holdEndings[3], 
							holdEndings[4], holdEndings[5]);*/
					break;
				case 3:
					holdEndingsPl = noun[i][j].split(",");
					System.out.format("-Singular- \t -Plural-\n"
							+ "Nom: %5s \t Nom: %5s\n"
							+ "Gen: %5s \t Gen: %5s\n"
							+ "Dat: %5s \t Dat: %5s\n"
							+ "Abl: %5s \t Abl: %5s\n"
							+ "Acc: %5s \t Acc: %5s\n"
							+ "Voc: %5s \t Voc: %5s\n", 
							holdEndings[0], holdEndingsPl[0],
							holdEndings[1], holdEndingsPl[1],
							holdEndings[2], holdEndingsPl[2],
							holdEndings[3], holdEndingsPl[3],
							holdEndings[4], holdEndingsPl[4],
							holdEndings[5], holdEndingsPl[5]);
					break;
				case 4:
					System.out.println("Some examples of this declension are " + noun[i][j]);
					break;
				}
			}
		}
		
	}
	
	
	public void setLearn() { }
	
	
	//In this method users specify which learning set they want to see and scroll through
	public void learn (String name) {
		System.out.println("Learning Mode - " + name.toUpperCase());
		//System.out.println("get: " + co.get(name));
		if(co.get(name) != null) {
			if(co.get(name).equals(irrVerb)) {				//done
				System.out.println("For more on irregular verbs information check out: "
						+ "http://dcc.dickinson.edu/grammar/latin/irregular-"
						+ "verbs-fer%C5%8D-and-ed%C5%8D\n");
				getVerbRules(co.get(name), name);
				
			} else if(co.get(name).equals(nounDec)){
				//print statement with web link
				System.out.println("http://dcc.dickinson.edu/grammar/latin/1st-declension-stem-paradigm-and-gender");
				System.out.println("Below you'll see the dictionary entries of nouns from each declension.\n"
						+ "The dictionary entry includes the nominative and genitive singular forms."
						+ "\n\tNote that the genitive case to identify the noun's declension.");
				getNounDec(co.get(name), name);
				
			} else if(co.get(name).equals(verbConj)){
				System.out.println("For more information on the four conjugations check out: "
						+ "http://dcc.dickinson.edu/grammar/latin/four-conjugations\n");
				//TODO
				getVerbRules(co.get(name), name);
				
			} /*else if(co.get(name).equals(verbEndings)) {
				//print statement with web link
				//TODO
				getVerbEnd(co.get(name), name);
				
			}*/ else if(co.get(name).equals(nounDecEndings)) {
				//print statement with web link
				System.out.println("If you know the noun stem, can recognize the declension, and gender, then you are off to a good start in declining a noun.\n"
						+ "Latin nouns belong to 5 different groups called declensions. These nouns only belong to one declension and each declension"
						+ "\nhas a distinct set of ending - so don't worry about there being too many ways to form a single noun!\n"
						+ "In Latin, there are 6 cases (both singular and plural) which are identifiable by the case endings from a specific declension.");
				getNounEndings(co.get(name), name);
			}
			
			if(!co.get(name).equals(nounDecEndings) && !co.get(name).equals(verbEndings)) {
				System.out.println("Do you want to open a flashcard set related to " + name + "? You'd be tested on some of the information you saw here.");						
				openQuiz(name);
			}
		}
	}

	//users can open the related quiz if they want or opt out
	private void openQuiz(String name) {
		System.out.println("Please enter Y or N");
		openQuiz = new Scanner(System.in);
		String ans = openQuiz.nextLine();
		if(ans.equalsIgnoreCase("Y") || ans.equalsIgnoreCase("yes")) {
			QuizUI.main(name);
			//QuizMode letsPractice = new QuizMode();
			//letsPractice.quiz(name);
		} else if(ans.equalsIgnoreCase("N") || ans.equalsIgnoreCase("no")) {
			System.out.println("That's fine! Maybe check some of the other learning options?");
		} else {
			openQuiz(name);
		}
		
		openQuiz.close();
	}
}
