import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Label that prompts users into selecting an action
		JLabel lblNewLabel = new JLabel("Do you want to quiz yourself with flashcards or learn something?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(12, 26, 408, 22);
		frame.getContentPane().add(lblNewLabel);
		
		//The basic options for quiz mode
		String[] quizType = {"Select quiz mode", "Verb dictionary entry", "Noun dictionary entry", "English to Latin Verb", "English to Latin Noun", "Irregular Verbs"};
		JComboBox quizMode = new JComboBox(quizType);
		quizMode.setBounds(47, 93, 143, 22);
		frame.getContentPane().add(quizMode);
		//quizMode.setSelectedIndex(0);  //don't want this!
		
		//The basic options for learning mode
		String[] practType = {"Select learning mode", "Verb dictionary entry", "Verb conjugations", "Noun declensions", "Irregular verbs", "Noun endings"/*, "Grammar rules"*/};
		JComboBox practMode = new JComboBox(practType);
		practMode.setBounds(225, 93, 153, 22);
		frame.getContentPane().add(practMode);
		
		//Button to confirm the selections made with the JComboBox
		JButton btnOk = new JButton("OK");
		btnOk.setEnabled(true);

		btnOk.setBounds(161, 147, 97, 25);
		frame.getContentPane().add(btnOk);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setBounds(129, 185, 178, 22);
		frame.getContentPane().add(lblNewLabel_1);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedQ = quizMode.getSelectedIndex();
				int selectedP = practMode.getSelectedIndex();
				if(selectedP == 0) {
					//QuizMode n = new QuizMode();
					if(selectedQ == 1) {
						QuizUI.main("verb dictionary entries");						
					} else if(selectedQ == 2) {
						QuizUI.main("noun declensions");
					} else if(selectedQ == 3) {
						QuizUI.main("engVerb");
					} else if(selectedQ == 4) {
						QuizUI.main("engNoun");
					} else if(selectedQ == 5) {
						QuizUI.main("irregular verb");
					}
				} else if(selectedQ == 0) {

					if(selectedP == 1) {
						LearnUI.main("verb dictionary entries");
					} else if(selectedP == 2) {
						LearnUI.main("verb endings");
					} else if(selectedP == 3) {
						LearnUI.main("noun declensions");
					} else if(selectedP == 4) {
						LearnUI.main("irregular verb");
					} else if(selectedP == 5) {
						LearnUI.main("noun declension endings");
					}  /*else if(selectedP == 6) {
						x.learn("grammar rules");
					}*/
				} else if(selectedQ != 0 && selectedP != 0) {
					lblNewLabel_1.setEnabled(true);
					lblNewLabel_1.setText("You can only select one!");
					lblNewLabel_1.setForeground(Color.red);
				}
				frame.dispose();
			}
		});
	}
}
