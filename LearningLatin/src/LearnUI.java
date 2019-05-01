//no longer needed for the project to run, but the code
//remains as a reminder of how the QuizMode was 
//previously implemented.

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.HashMap;
import javax.swing.JScrollPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LearnUI extends JFrame {

	private JPanel learnPane;
	private static String learnName = "verb conjugations";
	private int k = 0;
	private static JButton btnGoToFlash;
	private static LearnUI frame;
	private static JTextArea displaySpace;
	
	/**
	 * Launch the application.
	 */
	public static void main(String name) {
		learnName = name;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LearningMode();
					frame = new LearnUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LearnUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				if(learnName.equals("verb endings") || learnName.equals("grammar rules") || learnName.equals("noun declension endings")){
					btnGoToFlash.setEnabled(false);
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 436);
		learnPane = new JPanel();
		learnPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(learnPane);
		learnPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 50, 526, 240);
		//scrollPane.getViewport().setViewPosition(new Point(0, 0));
		//scrollPane.getViewport().setAlignmentY(0);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				scrollPane.getViewport().setViewPosition(new Point(0, 0));
			}
		});
		learnPane.add(scrollPane);
		
		displaySpace = new JTextArea();
		scrollPane.setViewportView(displaySpace);
		displaySpace.setBackground(Color.WHITE);
		displaySpace.setEditable(false);
		displaySpace.setLineWrap(true);
		displaySpace.setWrapStyleWord(true);
		displaySpace.setFont(new Font("Tahoma", Font.PLAIN, 16));
		displaySpace.setText(getSetName(learnName));
		
		JButton btnReturnToMain = new JButton("Main Menu");
		btnReturnToMain.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReturnToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//close the JPanel and reopen the main UI
				MainUI.main(null);
				frame.dispose();
			}
		});
		btnReturnToMain.setBounds(12, 336, 120, 40);
		learnPane.add(btnReturnToMain);
		
		btnGoToFlash = new JButton("Go to Related Set");
		btnGoToFlash.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//btnGoToFlash.setEnabled(true);
		btnGoToFlash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				QuizUI.main(learnName);
			}
		});
		btnGoToFlash.setBounds(501, 336, 144, 40);
		learnPane.add(btnGoToFlash);
		
		JLabel lblLearningMode = new JLabel("Learning Mode");
		lblLearningMode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLearningMode.setBounds(64, 13, 235, 24);
		learnPane.add(lblLearningMode);
		
		JLabel label = new JLabel(learnName); //name of set goes here
		label.setBounds(243, 13, 240, 21);
		learnPane.add(label);
		
		JButton back = new JButton("<<");   //decrease the value of the int k
		back.setEnabled(false);
		back.setFont(new Font("Tahoma", Font.PLAIN, 16));
		back.setBounds(229, 310, 70, 51);
		learnPane.add(back);
		
		JButton forward = new JButton(">>");	//increase the value of the int k
		forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				k++;
				
				if(k < theSize()) {
					if(!back.isEnabled()) {
						back.setEnabled(true);
						forward.setEnabled(true);
						displaySpace.setText(learn(learnName));
					} else if(k > 0) {
						forward.setEnabled(true);
						back.setEnabled(true);
						displaySpace.setText(learn(learnName));	
					}
					if(k == theSize()-1) {
						forward.setEnabled(false);
					}
				} else if(k >= theSize()-1) {
					//stops the program from moving forward
					forward.setEnabled(false);
					back.setEnabled(true);
				} 
			}
		});
		forward.setFont(new Font("Tahoma", Font.PLAIN, 16));
		forward.setBounds(307, 308, 70, 53);
		learnPane.add(forward);
		
		/*JScrollPane scrollPane = new JScrollPane(displaySpace);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//scrollPane.setPreferredSize(new Dimension(500, 500));
		scrollPane.setBounds(586, 294, -524, -244);
		learnPane.add(scrollPane);*/
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				k--;

				if(k < theSize()) {
					if(!back.isEnabled()) {
						back.setEnabled(true);
						forward.setEnabled(true);
						displaySpace.setText(learn(learnName));
					} else if(k >= 0) {
						forward.setEnabled(true);
						back.setEnabled(true);
						displaySpace.setText(learn(learnName));	
					}
					if(k == 0) {
						back.setEnabled(false);
					}
				} else if(k >= theSize()-1) {
					//stops the program from moving forward
					forward.setEnabled(false);
					back.setEnabled(true);
				} 
				
			/*	if(k > theSize()-1) {
					System.out.println("forward = false");
					forward.setEnabled(false);
					back.setEnabled(true);
					//forward.enabl
				}
				else if(k > 0 && !back.isEnabled()) {
					System.out.println("back = true");
					back.setEnabled(true);
					forward.setEnabled(true);
					displaySpace.setText(learn(learnName));
				}
				else if(k >= 0 && k <= theSize()-1) {
					forward.setEnabled(true);
					back.setEnabled(true);
					displaySpace.setText(learn(learnName));
				}*/
			}
		});
	}
	
	

	//mode that displays the different types of study materials, which are prompted by the main UI for the user to choose from
	//private Scanner openQuiz;
	private static HashMap<String, String[][]> co = new HashMap<String, String[][]>();
	private static String[][] irrVerb;
	private static String[][] nounDec;
	private static String[][] nounDecEndings;
	private static String[][] grammarRules;
	private static String[][] verbEndings;
	private static String[][] verbDict;
	//private String[] holdEndings;
	//HashMap<String, String> irrVerb = new HashMap<String, String>();
	//HashMap<String, String> verbConj = new HashMap<String, String>();
	//HashMap<String, String> nounDec = new HashMap<String, String>();
			
	public static void LearningMode() {
		//order is verb, translation, stem, and conjugation
		irrVerb = new String[][] { 
			{"adfero, adferre, attuli, allatum", "bring", "-fero", "3rd"},
			{"confero, conferre, contuli, collatum", "gather", "-fero", "3rd"},
			{"do, dare, dedi, datum", "give", "n/a", "1st"},
			{"eo, ire, ii (ivi), itum", "go", "n/a", "irregular"},
			{"infero, inferre, intuli, illatum", "bear", "-fero", "3rd"}
		};
				
		verbDict = new String[][] {
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
			//conjugation number, endings, participle forms?, example verbs
			{"Present Active Indicative", "(present stem) 1st", "-o, -s, -t", "-mus, -tis, -nt", "'verbs or is verbing'"}, 
			{"Imperfect Active Indicative", "(present stem) 1st", "-bam, -bas, -bat", "-bamus, -batis, -bant", "'was verbing or verbed'"}, 
			//; divides 1st/2nd conj and 3rd/4th conj endings for future
			{"Future Active Indicative", "(present stem) 1st", "-bo, -bis, -bit", "-bius, -bitis, -bunt; -am, -es, -et, -emus, -etis, -ent", "'will verb'"},
			{"Perfect Active Indicative", "(perfect stem) 3rd", "-i, -isti, -it", "-imus, -istis, -erunt (-ere)", "'have verbed, verbed, or did verb'"},
			{"Pluperfect Active Indicative", "(perfect stem) 3rd", "-eram, -eras, -erat", "-etamus, -eratis, -erant", "'had verbed'"},
			{"Present Passive Indicative", "(present stem) 1st", "-or, -res (-re), -tur", "-mur, -mini, -ntur", "'is verbed or being verbed'"}, 
			{"Imperfect Passive Indicative", "(present stem) 1st","-bar, -baris (-bare), -batur", "-bamur, -bamini, -bantur", "'was being verbed or was verbed'"}, 
			{"Future Passive Indicative", "(present stem) 1st","-bor, -beris (-bere), -bitur", "-bimur, -bimini, -buntur; -ar, -eris (-ere), -etur, -emur, -emini, -entur", "'will be verbed'"},
			{"Perfect Passive Indicative", "(perfect stem) 4th","-tus (-ta; -tum) sum, -tus (-ta; -tum) es, -tus (-ta; -tum) est", "-ti (-tae; -ta) sumus, -ti (-tae; -ta) estis, -ti (-tae; -ta) sunt", "'was verbed or has been verbed'"},
			{"Pluperfect Passive Indicative", "(perfect stem + esse) 4th", "-tus (-ta; -tum) eram, -tus (-ta; -tum) eras, -tus (-ta; -tum) erat", "-ti (-tae; -ta) eramus, -ti (-tae; -ta) eratis, -ti (-tae; -ta) erant", "'had been sung'"},
			
			{"Present Active Subjunctive", "(present stem) 1st", "-m, -s, -t", "-mus, -tis, -nt", "'I may/might/would/let me verb'"}, 
			{"Imperfect Active Subjunctive", "(present stem) 1st", "-rem, -res, -ret", "-remus, -retis, -rent", "'If only... were verbed or Would have verbed'"}, 
			{"Perfect Active Subjunctive", "(perfect stem) 3rd", "-erim, -eris, -erit", "-erimus, -eritis, -erint", "Do/might verb"}, 
			{"Pluperfect Active Subjunctive", "(perfect stem) 3rd", "-issem, -isses, -isset", "-issemus, -issetis, -issent", "'If only... had verbed'"}, 

			{"Present Passive Subjunctive", "(present stem) 1st", "-r, -ris (-re), -tur", "-mur, -mini, -ntur", "'I may be/might be/would be/let me be verb'"}, 
			{"Imperfect Passive Subjunctive", "(present stem) 1st", "-rer, -reris (-rere), -retur", "-remur, -remini, -rentur", "'I might/would have been verb'"}, 
			{"Perfect Passive Subjunctive", "(perfect stem) 4th", "-tus (-ta; -tum) sim, -tus (-ta; -tum) sis, -tus (-ta; -tum) sit", "-ti (-tae; -ta) simus, -ti (-tae; -ta) sitis, -ti (-tae; -ta) sint", "'Do/might be verbed'"},
			{"Pluperfect Passive Subjunctive", "(perfect stem) 4th", "-tus (-ta; -tum) essem, -tus (-ta; -tum) esses, -tus (-ta; -tum) esset", "-ti (-tae; -ta) essemus, -ti (-tae; -ta) essetis, -ti (-tae; -ta) essent", "'If only.... were verbed'"},

			//make note in the method that these are different
			{"Present Active Imperative", "(present stem) 1st", "--", "-re", "'Verb'"},
			{"Future Active Imperative", "(present stem) 1st", "-to, -to", "-tote, -nto", "'You will verb'"},
			{"Present Passive Imperative", "(present stem) 1st", "-re", "-mini", "'It will be verbing'"}, 
			{"Future Passive Imperative", "(present stem) 1st", "-tor, -tor", "--, -ntor", "'It will verb'"},
			
			//					present;      future; 					perfect;  		gerundive
			{"Participles", "-ns, -ntis", "-turus (-a, -um) esse", "-tus (-a, -um)", "-ndus (-a, -um)"}
		};
		
		//TODO make a set that conjugates the verbConj set?
		//set consists of the stems for each form {"ambul", "ambulav", ...}
		//each {} is a new verb stem and the verbEndings set is called to
		//get the endings for each conjugation
		
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
			{"2nd", "neuter", "-um, -i, -o, -um, -o, -um", "-a, -orum, -is, -a, -is, -s", "\n\tbellum, belli (war)"},
			//we're ignoring the 2nd dec o-stem nouns for now
			{"3rd", "masculine/feminine", "--, -is, -i, -em, -e, --", "-es, -um, -ibus, -es, -ibus, -es", "\n\tconsul, consulis (consul)"},
			{"3rd", "neuter", "--, -is, -i, --, -e, --", "-a, -um, -ibus, -a, -ibus, -a", "\n\tpoema, poematis (poem)"},
			{"3rd (i-stem)", "masculine/feminine", "--, -is, -i, -em, -i (-e), --", "-es, -ium, -ibus, -is (-es), -ibus, -es", "\n\tignis, ignis (fire); imber, imberis (rain)"},
			{"3rd (i-stem)", "neuter", "-- (-e), -is, -i, -- (-e), -i, -- (-es)", "-i, -ia, -ibus, -ia, -ibus, -i", "\n\tanimal, animalis (animal)"},
			{"4th", "masculine/feminine", "-us, -us, -u (ui), -um, -u, -us", "-us, -uum, -ibus, -us, -ibus, -us", "\n\tmanus, manus (hand); lacus, lacus (lake)"},
			{"4th", "neuter", "-u, -us, -u, -u, -u, -u", "-ua, -uum, -ibus, -ua, -ibus, -ua", "\n\tgenu, genus (knee)"},
			{"5th", "masculine/feminine", "-es, -ei, -ei, -em, -e, -es", "-es, -erum, -ebus, -es, -ebus, -es", "\n\tres, rei (thing); dies, diei/die (day)"},
		};
				
		//deets from LtRL pg 16
		grammarRules = new String[][] {
			{"Genitive of Possession", "an idea expressed by the genitive case of ownership or possession", "the book of the girl (girl would be in the genitive in latin)"},
			{"Dative of Reference", ""},
			{"Dative of Indirect Object", },
			{},
		};
				
		co.put("irregular verb", irrVerb);  				//has quiz set
		co.put("verb dictionary entries", verbDict);  		//has quiz set
		co.put("verb endings", verbEndings);
		co.put("noun declensions", nounDec);  				//has quiz set
		co.put("noun declension endings", nounDecEndings);
		co.put("grammar rules", grammarRules);
	}

	//the information and formatting for the verbs (irregular and not)
		private String getVerbRules(String[][] irr, String name, int k2) {
			String concat = "";
			concat = "You can identify the conjugation by looking at the 2nd principal part.\n";
			for(int j = 0; j < irr[k2].length; j++) {
				//System.out.println("here!");
				switch(j) {
					case 0:
						concat = concat.concat("The verb '" + irr[k2][j] + "'");
						break;
					case 1:
						concat = concat.concat(" translates as '" + irr[k2][j] +"'.\n");
						break;
					case 2:
						if(name == "verbConj") {
							concat = concat.concat("The root of this verb is: " + irr[k2][j] + "\n");
						} else if(name == "irrVerb"){
							if(!irr[k2][j].contains("n/a"))
								concat = concat.concat(irr[k2][j] + " is the compound of this verb.\n");
						}
						break;
					case 3:
						concat = concat.concat("This is a " + irr[k2][j] + " conjugation verb.\n");
						if(irr[k2][j].contains("1st")) {
							concat = concat.concat("A 1st conjugation has an '-are' ending. The 'a' should have a long mark over it. \n");
						} else if(irr[k2][j].contains("2nd")) {
							concat = concat.concat("A 2nd conjugation has an '-ere' ending. The first 'e' has a long mark over it. \n");
						} else if(irr[k2][j].contains("3rd")) {
							concat = concat.concat("A 3rd conjugation has an '-ere' ending.\n"
									+ "The same thing applies to the 3rd conjugation i-stem verbs - so be careful!\n");
						} else if(irr[k2][j].contains("4th")) {
							concat = concat.concat("A 4th conjugation has an '-ire' ending. The 'i' has a long mark over it.\n");
						} else if(irr[k2][j].contains("irregular")) {
							concat = concat.concat("This verb doesn't really follow a set pattern. So instead of memorizing the pattern, focus on the principal parts!\n");
						}
						break;
				}
				//System.out.println();
			}
			return concat;
		}
		
		private String getVerbEnd(String[][] verb, String name, int k2) {
		//{"Present Active Indicative", "1st", "-o, -s, -t", "-mus, -tis, -nt", "verbs or is verbing"}, 
			String concat = "";
			String[] holdEndings = new String[3];
			String[] holdEndingsPl = new String[3];
			if(verb[k2][0].equals("Participles")) {
				concat = getParticiples(verb[k2]);
				//break;
				//return concat;
			} else if(verb[k2][0].contains("Imperative")) {
				concat = getImp(verb[k2]);
				//return concat;
			} else {
			for(int j = 0; j < verb[k2].length; j++) {
				switch(j) {
					case 0: //name of conjugation
						concat = concat.concat(verb[k2][j] +"\n---------------------\n");
						break;
					case 1: //principle part used to form
						concat = concat.concat("Formed with the " + verb[k2][j] + " principal part\n");
						//System.out.println(" - " +verb[k2][j]);
						break;
					case 2: //endings
						holdEndings = verb[k2][j].split(",");
						break;
					case 3:
						holdEndingsPl = verb[k2][j].split(",");
						String hold = String.format("-Singular Person-\t-Plural Person-\n"
								+ "1st: %5s \t 1st: %5s\n"
								+ "2nd: %5s \t 2nd: %5s\n"
								+ "3rd: %5s \t 3rd: %5s\n",
								holdEndings[0], holdEndingsPl[0],
								holdEndings[1], holdEndingsPl[1],
								holdEndings[2], holdEndingsPl[2]);
						concat = concat.concat(hold);
						break;
					case 4: //translation
						concat = concat.concat("\nTranslates along the lines of " + verb[k2][j]);
						break;
					}
			}
			}
			return concat;
		}
	
	private String getImp(String[] strings) {
		String con = "\nThink of these as commands\n";
		for(int j = 0; j < strings.length; j++) {
			String[] holdS; String[] holdP;
			if(strings[0].contains("Present Active")) {
				con = String.format(strings[0] + "\nUses the " + strings[1] + " principal part\n" + 
			"Singular\tPlural\n1st%5s %5s\nwhich translates as %s", strings[2], strings[3], strings[4]);
				//add the String format style
			} else if(strings[0].contains("Present Passive")) {
				//holdS = strings[2].split(",");
				//holdP = strings[3].split(",");
				con = String.format("%s\nUses the %s principal part\nSingular \t Plural\n"
						+ "1st %5s  %5s\nwhich translates as %s\n", strings[0], strings[1], strings[2], strings[3], strings[4]);
			} else if(strings[0].contains("Future Active")) {
				con = String.format(strings[0] + "\nUses the " + strings[1] + " principal part\n" + 
						"Singular\tPlural\n1st%5s %5s\n which translates as %s", strings[2], strings[3], strings[4]);
			} else if(strings[0].contains("Future Passive")) {
				holdS = strings[2].split(",");
				holdP = strings[3].split(",");
				con = String.format("%s\nUses the %s principal part\nSingular\tPlural\n"
						+ "1st %5s  %5s\n2nd %5s %5s\nwhich translates as %s\n", strings[0], strings[1], holdS[0], holdP[0], holdS[1], holdP[1], strings[4]);
			} 

		}
			return con;
	}

	private String getParticiples(String[] strings) {
			//{"Participles", "-ns, -ntis", "-turus (-a, -um) esse", "-tus (-a, -um)", "-ndus (-a, -um)"}
			String concat = "";
			concat = concat.concat("Participles are tricky at first, but as you see them more in latin text you'll start getting used to them - just like anything else! Think"
					+ "of them as adding '-ing' to the end of a verb translation. So a perfect passive participle"
					+ "\nParticiples\n");
			//concat = String.format("Present\n%s\nFuture\n%s\nPerfect\n\", strings[1]);
					concat = concat.concat("Present\n" + strings[1] + "\n\nFuture\n" + strings[2] + "\n\nPerfect\n" + strings[3] + "\n\nGerundive\n" + strings[4] +
					"\n\nNote: gerundives (in the passive pariphrastic) translate as 'must verb' with the verb changing to suit the tense and voice.");
			return concat;
		}

	//see about adding italics to the stem (anim-)
	//THIS IS FINE BUT NOT VERB ENDINGS????
	private String getNounDec(String[][] noun, String name, int k2) {
		//{"rex, regis", "king", "masculine", "3rd", "reg-"}
		//    0            1          2          3      4
		String concat = "";
		for(int j = 0; j < noun[k2].length; j++) {
			switch(j) {
				case 0: 
					concat = concat.concat("The noun " + noun[k2][j] );
					break;
				case 1:
					concat = concat.concat(" translates as " + noun[k2][j] + "\n");
					break;
				case 2:
					concat = concat.concat("This is a " + noun[k2][j] + " and ");
					break;
				case 3:
					concat = concat.concat(noun[k2][j] + " declension noun.\n");
					break;
				case 4:
					concat = concat.concat("Add the " + noun[k2][j-1] + " declension endings to the stem " + noun[k2][j] + " to decline the noun\n");
					String[] holdEndings = {""};
					String[] holdEndingsPl = {""};
					if(noun[k2][3] == "1st") {
						//one = true;
						holdEndings = nounDecEndings[0][2].split(",");
						holdEndingsPl = nounDecEndings[0][3].split(",");
					} else if(noun[k2][3] == "2nd") {
						//two = true;
						if(noun[k2][2] == "masculine") {
							holdEndings = nounDecEndings[1][2].split(",");
							holdEndingsPl = nounDecEndings[1][3].split(",");
						} else if(noun[k2][2] == "neuter") {
							holdEndings = nounDecEndings[2][2].split(",");
							holdEndingsPl = nounDecEndings[2][3].split(",");
						}
					} else if(noun[k2][3] == "3rd") {
						//three = true;
						if(noun[k2][2] == "masculine/feminine") {
							holdEndings = nounDecEndings[3][2].split(",");
							holdEndingsPl = nounDecEndings[3][3].split(",");
						} else if(noun[k2][2] == "neuter") {
							holdEndings = nounDecEndings[4][2].split(",");
							holdEndingsPl = nounDecEndings[4][3].split(",");
						}
					} else if(noun[k2][3] == "4th") {
						//four = true;
						holdEndings = nounDecEndings[5][2].split(",");
						holdEndingsPl = nounDecEndings[5][3].split(",");
					} else if(noun[k2][3] == "5th") {
						//five = true;
						holdEndings = nounDecEndings[6][2].split(",");
						holdEndingsPl = nounDecEndings[6][3].split(",");
					}
					String holdThis = String.format("-Singular- \t -Plural-\n"
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
					concat = concat.concat(holdThis);
				break;
			}
		}
		return concat;
		
	}	

	private String getNounEndings(String[][] noun, String name, int k2) {
//		{"1st", "feminine", "-a, -ae, -ae, -am, a", "-ae, arum, is, as, is", "stella, stellae (star); cura, curae (care)"},
			String[] holdEndings = new String[6];
			String[] holdEndingsPl = new String[6];
			String con = "";
			for(int j = 0; j < noun[k2].length; j++) {
					switch(j) {
					case 0: 
						con = con.concat("Declension: " + noun[k2][j]);
						break;
					case 1:
						String hold1 = String.format("\n%-10s %5s\n", "Gender: ", noun[k2][j]);
						con = con.concat(hold1);
						break;
					case 2:
						holdEndings = noun[k2][j].split(",");
						break;
					case 3:
						holdEndingsPl = noun[k2][j].split(",");
						String hold = String.format("-Singular- \t -Plural-\n"
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
						con = con.concat(hold);
						break;
					case 4:
						con = con.concat("Some examples of this declension are " + noun[k2][j]);
						break;
					}
			}
			return con;
	}
		
	public void setLearn() { }
		
	//In this method users specify which learning set they want to see and scroll through
	public String learn (String name) {
		String holdReturn = "";
		if(co.get(name) != null) {
			if(co.get(name).equals(irrVerb)) {
				holdReturn = getVerbRules(co.get(name), name, k);
			} else if(co.get(name).equals(nounDec)){
				holdReturn = getNounDec(co.get(name), name, k);
			} else if(co.get(name).equals(verbDict)){
				holdReturn = getVerbRules(co.get(name), name, k);
			} else if(co.get(name).equals(verbEndings)) {
				holdReturn = getVerbEnd(co.get(name), name, k);
			} else if(co.get(name).equals(nounDecEndings)) {
				//print statement with web link
				holdReturn = getNounEndings(co.get(name), name, k);
			}
		}
		//k++;
		return holdReturn;
	}

	private String getSetName(String name) {
		if(co.get(name) != null) {
			String holdReturn = "";
			if(co.get(name).equals(irrVerb)) {
				holdReturn = getVerbRules(co.get(name), name, k);
				return "For more on irregular verbs information check out:\n"
						+ "http://dcc.dickinson.edu/grammar/latin/irregular-"
						+ "verbs-fer%C5%8D-and-ed%C5%8D\n\n" + holdReturn;
				
			} else if(co.get(name).equals(nounDec)){
				//print statement with web link
				holdReturn = getNounDec(co.get(name), name, k);
				return "For more on noun declensions check out:\n"
						+ "http://dcc.dickinson.edu/grammar/latin/1st-declension-stem-paradigm-and-gender \n"
						+ "Below you'll see the dictionary entries of nouns from each declension.\n"
						+ "The dictionary entry includes the nominative and genitive singular forms."
						+ "\n**Note that the genitive case identifies the noun's declension.\n\n" + holdReturn;
				
			} else if(co.get(name).equals(verbDict)){
				holdReturn = getVerbRules(co.get(name), name, k);
				return "For more information on the four conjugations check out: \n"
						+ "http://dcc.dickinson.edu/grammar/latin/four-conjugations\n\n" + holdReturn;	
				
			} else if(co.get(name).equals(verbEndings)) {
				holdReturn = getVerbEnd(co.get(name), name, k);
				return "Dickinson College has a list of verb endings here: \n http://dcc.dickinson.edu/grammar/latin/verb-endings"
						+ "\nThe endings listed here are the basic forms listed on the website and do not account for "
						+ "the vowel changes that occur with the different conjugations.\n\n" + holdReturn;
				
			} else if(co.get(name).equals(nounDecEndings)) {
				//print statement with web link
				holdReturn = getNounEndings(co.get(name), name, k);
				return "If you know the noun stem, can recognize the declension, and gender, then you are off to a good start in declining a noun!\n"
						+ "Latin nouns belong to 5 different groups called declensions. These nouns only belong to one declension and each declension"
						+ "\nhas a distinct set of ending - so don't worry about there being too many ways to form a single noun!\n"
						+ "In Latin, there are 6 cases (both singular and plural) which are identifiable by the case endings from a specific declension.\n\n" + holdReturn;
			
			}/* else if(co.get(name).equals(grammarRules)){
				btnGoToFlash.setEnabled(false);
				holdReturn = getGrammar(co.get(name), name, k);
				return "here are the grammar rules......." + holdReturn;
			}
			*/
		}
		return "not here";
	}
	
	private static int theSize() {
		int size = co.get(learnName).length;
		return size;
	}
}
