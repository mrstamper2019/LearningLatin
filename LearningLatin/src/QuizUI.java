import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class QuizUI extends JFrame {

	private JPanel contentPane;
	public static JTextField urAns;							//where answer is typed
	public static JTextField correct;						//if correct or not
	public static JLabel quest;// = new JLabel();			//question goes here
	public static JLabel quizType;
	public static String quizName;
	public static JButton btnConfirmAnswer;
	public static int k = 0;
	public static String missedQ = "";
	private static QuizUI frame;
	/**
	 * Launch the application.
	 */
	public static void main(String name) {
		quizName = name;
		k = 0;  //see if this changes the learning mode?
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					QuizMode();
					frame = new QuizUI();
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
	public QuizUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuizMode = new JLabel("Quiz Mode");		//tells users at top what mode they've entered
		lblQuizMode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuizMode.setBounds(44, 25, 158, 27);
		contentPane.add(lblQuizMode);
		
		quizType = new JLabel(quizName);		//name of study set goes here
		quizType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		quizType.setBounds(252, 25, 143, 27);
		contentPane.add(quizType);
		
		urAns = new JTextField("");		//where your answer goes
		urAns.setFont(new Font("Tahoma", Font.PLAIN, 16));
		urAns.setBounds(224, 178, 308, 27);
		contentPane.add(urAns);
		urAns.setColumns(10);
		
		JLabel lblYourAnswer = new JLabel("Your Answer");
		lblYourAnswer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblYourAnswer.setBounds(75, 178, 95, 27);
		contentPane.add(lblYourAnswer);
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuestion.setBounds(75, 125, 95, 27);
		contentPane.add(lblQuestion);
		
		JButton btnConfirmAnswer = new JButton("Confirm Answer");
		btnConfirmAnswer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfirmAnswer.setBounds(272, 276, 180, 36);
		contentPane.add(btnConfirmAnswer);
		
		JButton btnGoBack = new JButton("Main Menu");
		btnGoBack.setEnabled(false);
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGoBack.setBounds(75, 355, 111, 36);
		contentPane.add(btnGoBack);
		
		JButton btnGoToRelated = new JButton("Go to Related Notes");
		btnGoToRelated.setEnabled(false);
		btnGoToRelated.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGoToRelated.setBounds(503, 355, 158, 36);
		contentPane.add(btnGoToRelated);
		
		JLabel lblCorrectAnswer = new JLabel("Correct Answer");
		lblCorrectAnswer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCorrectAnswer.setBounds(75, 238, 127, 27);
		contentPane.add(lblCorrectAnswer);
		
		quest = new JLabel();
		quest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		quest.setBounds(162, 125, 446, 27);
		contentPane.add(quest);
		quest.setText(getQuestion(k).toString());
		
		correct = new JTextField();
		quest.setEnabled(true);
		correct.setEditable(false);
		correct.setFont(new Font("Tahoma", Font.PLAIN, 16));
		correct.setBounds(228, 238, 304, 25);
		contentPane.add(correct);
		correct.setColumns(10);
		
		JButton next = new JButton("Next Question");
		next.setEnabled(false);
		next.setFont(new Font("Tahoma", Font.PLAIN, 16));
		next.setBounds(272, 276, 180, 36);
		contentPane.add(next);
		next.setVisible(false);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnConfirmAnswer.setEnabled(true);
				btnConfirmAnswer.setVisible(true);
				next.setVisible(false);
				next.setEnabled(false);
				k++;
				if(k >= theSize() || urAns.getText().equals("COMPLETE SET")) {
					System.out.println(repeatSet);
					if(!wrong.isEmpty() && repeatSet == true) { 
						System.out.println("Since you missed a question, let's go back to the beginning.");	
						urAns.setText(null);
						repeatSet = false; 
						k = 0;
						correct.setText(null);
						quest.setText(getQuestion(k).toString());
					} else {
						btnConfirmAnswer.setEnabled(false);
						urAns.setEditable(false);
						next.setEnabled(false);
						btnGoToRelated.setEnabled(true);
						btnGoBack.setEnabled(true);
						
						//parent.setBounds(100, 100, 100, 100);
						JFrame parent = new JFrame();
						String wQ = getWrongQuestions();
						System.out.println(wQ); 
						if(wQ.equals("")) {
							JOptionPane.showMessageDialog(parent, "You didn't miss anything!" + '\n' + "Great job!!");

						} else {
							JOptionPane.showMessageDialog(parent, "Missed:" + '\n' +  wQ.toString());
						}
					}
					
				} else {
					correct.setText(null);
					urAns.setText(null);
					quest.setText(getQuestion(k).toString());
				}
			}
		});
		
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//MainUI m = new MainUI();
				MainUI.main(null);
				
				frame.dispose();
				//quiz(quizName);
			}
				//String answer = urAns.getText();
		});
		
		btnGoToRelated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();

				if(collection.get(quizName).equals(verb)) {
					LearnUI.main("verb dictionary entries");
				} else if(collection.get(quizName).equals(noun)) {
					LearnUI.main("noun declensions");
				} else if(collection.get(quizName).equals(irregVerb)) {
					LearnUI.main("irregular verb");
				}
				else {
					JFrame parent = new JFrame();
					JOptionPane.showMessageDialog(parent, "Sorry, that set doesn't exist!");

				}
				//quiz(quizName);
			}
				//String answer = urAns.getText();
		});
		
		btnConfirmAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				quiz(quizName); 
				btnConfirmAnswer.setVisible(false);
				btnConfirmAnswer.setEnabled(false);
				
				next.setVisible(true);
				next.setEnabled(true);
			}
		});
		
		
	}
	
	//ALL the bits for quizMode
	public static HashMap <String, HashMap<String, String>> collection = new HashMap<String, HashMap<String, String>>();
	
	//pre-made flashcard sets
	public static HashMap <String, String> verb = new HashMap<String, String>();
	public static HashMap <String, String> engVerb = new HashMap<String, String>();
	public static HashMap <String, String> noun = new HashMap<String, String>();
	public static HashMap <String, String> engNoun = new HashMap<String, String>();
	public static HashMap <String, String> irregVerb = new HashMap<String, String>();

	//stores the incorrect answers and the number of times they appear
	private static HashMap<String, Integer> wrong = new HashMap<String, Integer>();
	public static boolean repeatSet; //tracks if a set should be repeated based on if the user got all the answers right in a run through
	private static String[] splitAns;
	private static boolean engToLatin = false;
	
	public static void QuizMode() {
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
		irregVerb.put("adfero, adferre, attuli, allatum", "bring");
		irregVerb.put("confero, conferre, contuli, collatum", "gather");
		irregVerb.put("eo, ire, ii (ivi), itum", "go");
		irregVerb.put("infero, inferre, intuli, illatum", "bear");

		//english to latin for verb and noun set
		engVerb.put("walk", "ambulo, ambulare, ambulavi, ambulatum");
		engVerb.put("love", "amo, amare, amavi, amatus");
		engVerb.put("think", "cogito, cogitare, cogitavi, cogitatus");
		engVerb.put("give", "do, dare, dedi, datus");
		engVerb.put("reward", "dono, donare, donavi, donatus");
		
		engNoun.put("care", "cura, curae");
		engNoun.put("anger", "ira, irae");
		engNoun.put("punishment", "poena, poenae");
		engNoun.put("wisdom", "sapientia, sapientiae");
		engNoun.put("life", "vita, vitae");
		engNoun.put("mind", "animus, animi");

		collection.put("verb dictionary entries", verb);
		collection.put("noun declensions", noun);
		collection.put("engVerb", engVerb);
		collection.put("engNoun", engNoun);
		collection.put("irregular verb", irregVerb);
		repeatSet = false;
	}
	
	public static Object getQuestion(int location) { 
		Object key = collection.get(quizName).keySet().toArray()[location];
		return key;  
	}
	public static String getAnswer(String question) {
		String key = collection.get(quizName).get(question);
		return key;  
	}
	
	private static int theSize() {
		//int size = 0;
		int size = collection.get(quizName).size();
		return size;
	}
	
	public static void answerWrong(String question) {
		if(!wrong.containsKey(question)) {
		//if the question is not in the map, then it is added and the value set to 1
			wrong.put(question, 1);
		} else {
		//the question should exist in the map and so only the value should be updated
			int oldValue = wrong.get(question);
			oldValue++;
			wrong.replace(question, oldValue);
		}
	}

		//prints the contents of the 'wrong' HashMap
	public static String getWrongQuestions() {
		if(wrong.isEmpty()) {
			//System.out.println("You didn't miss anything! Great job!");
			return "";
		} else {
			System.out.println("You missed: "); //add each question missed to new data structure, keep track of how many questions missed?
			wrong.forEach((question, missed) -> { 
				if(!missedQ.contains(question)) {
				//missedQ = question + ": " + missed + " time(s) " + '\n';
					missedQ = missedQ.concat(question + ": " + missed + " times" + '\n').toString(); 
				}
				//iterates through the HashMap, prints the key (missed question) and the value (times missed)
				//System.out.println(question + ": " + missed + " times");
			});
			return missedQ;
		}
	}

	public static String getQuizName() {
		return quizName;
	}

	public static void setQuizName(String quizName) {
		QuizUI.quizName = quizName;
	}
		
	public void quiz(String name) {
		if(collection.get(name) != null) {
			//System.out.println("Opening " + name); 
			if(name == "engVerb" || name == "engNoun") {
				engToLatin = true;
			}
			check(collection.get(name));
			//System.out.println("Set complete!");
		} else if(name == null) {
			System.out.println("Please enter a name");
		} else {
			System.out.println("Sorry, " + name + " isn't a quiz set!");
		}
	}
	
	//this method checks to see if the input name exists in the collection list
	private static void check(HashMap<String, String> map) {
		if(map != null) {
			String answer = getAnswer(getQuestion(k).toString());
			String here = urAns.getText();
			here = here.toLowerCase();
			
			if(answer.contains(",") && engToLatin != true) {			//allows for multiple correct answers to be accepted
				boolean tr = false;
				String[] urSplit = here.split(",");
				//splitAns[0] = answer;
				//System.out.println(splitAns);
				splitAns = answer.split(",");
				System.out.println(splitAns);
				for(int i = 0; i < splitAns.length; i++) {
					for(int j = 0; j < urSplit.length; j++) {
						//System.out.println("out loop: ans " + splitAns[i] + " & ur " + urSplit[j]);
						if(splitAns[i].contains(urSplit[j]) || here.equals(answer)) {
							//System.out.println("in loop: ans " + splitAns[i] + " & ur " + urSplit[j]);
							correct.setText("CORRECT");
							tr = true;
							break;
						} 
					}
				}
				if(tr != true) {
					correct.setText(answer);
					answerWrong(quest.getText());
					tr = false;
					repeatSet = true;
				}
			} else {
				if(answer.compareTo(here) == 0) {
					correct.setText("CORRECT");
				} else { 
					correct.setText(answer);	//too long quest.getText() + " is " + answer);
					//System.out.println("Correct answer: " + answer);
					answerWrong(quest.getText());
					repeatSet = true;
				}
			}
			//
			if(k < theSize()) {
				quest.setText(getQuestion(k).toString());
				urAns.setText(null);
			} else if (k <= theSize()){
				if(!wrong.isEmpty() && repeatSet == true) { 
					//System.out.println("Since you missed a question, let's go back to the beginning.");	
					urAns.setText(null);
					repeatSet = false; 
					k = 0;
					quest.setText(getQuestion(k).toString());
				} else {
					urAns.setText("COMPLETE SET");
					urAns.setEditable(false);
				}
			}
		}
	}
}
