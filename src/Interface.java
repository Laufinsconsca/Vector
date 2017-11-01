package theComputationOfTheRootsOfAnyEquation;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

public class Interface extends JFrame {

	private static final long serialVersionUID = 1L;
	private int k = 0;
	private JPanel contentPane;
	JPanel panel, panel_2;
	JScrollPane scrollPane;
	JLabel labelInfo, labelGlue, label_solution;
	public static JSlider slider;
	public static JRadioButton radioButton_Normal, radioButton_Exponential;
	public static JComboBox<Object> box = new JComboBox<>();
	private KeyListener[] textFilter = new KeyListener[5];
	private JLabel[] label = new JLabel[9];
	private static JTextField[] textField = new JTextField[9];
	private JButton calculationButton, resetButton, helpButton, solutionButton, preferencesButton;
	private String[] labelText = { "коэф. A", "коэф. B", "коэф. C", "коэф. D", "коэф. E", "x1 =", "x2 =", "x3 =", "x4 =" };
	private String[] labelInfoText = { "<html><font size=5>Общий вид уравнения: Ax + B = 0", "<html><font size=5>Общий вид уравнения: Ax&sup2 + Bx + C = 0", "<html><font size=5>Общий вид уравнения: Ax&sup3 + Bx&sup2 + Cx + D = 0", "<html><font size=5>вид уравнения: Ax<font size = 3><sup><small>4</sup><small></font>" + "<html><font size=5> + Bx&sup3 + Cx&sup2 + Dx + E = 0" };
	Component horizontalGlue = Box.createHorizontalGlue();
	Component horizontalGlue_1 = Box.createHorizontalGlue();
	public static boolean isNormalView = true, openPreferences;
	static boolean openSolution = false;
	private static boolean IS_EQUATION_OF_FOURTH_POW;
	private static boolean IS_CUBIC_EQUATION;
	private static boolean IS_QUADRATIC_EQUATION;
	private static boolean IS_LINEAR_EQUATION;
	public static Calculation calculation = new Calculation();
	private FormationOfSolutions formation = new FormationOfSolutions();
	public Interface() {
		super("Вычисление корней любого уравнения");
		createGUI();
	}
	public void createGUI() {
		try {
			for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (final Exception e) {
		}
		
		slider = new JSlider();
		slider.setMaximum(Main.SLIDER_MAX_VALUE);
		slider.setMinimum(Main.SLIDER_MIN_VALUE);
		slider.setValue(24);
		radioButton_Normal = new JRadioButton("Обычное");
		radioButton_Normal.setSelected(true);
		radioButton_Exponential = new JRadioButton("Экспоненциальное");
		radioButton_Exponential.setSelected(false);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Interface.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setSize(700, 433);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent event) {
			}

			@Override
			public void windowClosed(WindowEvent event) {
			}

			@Override
			public void windowClosing(WindowEvent event) {
				final Object[] options = { "Да", "Нет" };
				final int n = JOptionPane.showOptionDialog(event.getWindow(), "Закрыть программу?",
						"Подтверждение закрытия", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[0]);
				if (n == 0) {
					System.exit(0);
				}
			}

			@Override
			public void windowDeactivated(WindowEvent event) {
			}

			@Override
			public void windowDeiconified(WindowEvent event) {
			}

			@Override
			public void windowIconified(WindowEvent event) {
			}

			@Override
			public void windowOpened(WindowEvent event) {
			}
		});
		
		final JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.activeCaption);
		panel_4.setPreferredSize(new Dimension(10, 30));
		contentPane.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		final Component horizontalGlue = Box.createHorizontalGlue();
		panel_4.add(horizontalGlue);
		final JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		panel_3.setPreferredSize(new Dimension(10, 300));
		contentPane.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(80, 10));
		panel_3.add(panel_2);
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setLayout(new GridLayout(0, 1, 5, 5));
		final Component glue_2 = Box.createGlue();
		panel_2.add(glue_2);
		final JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		labelPanel.setBackground(SystemColor.activeCaption);
		panel_2.add(labelPanel);
		final Component glue = Box.createVerticalGlue();
		glue.setPreferredSize(new Dimension(100, 0));
		labelPanel.add(glue);
		labelGlue = new JLabel();
		labelGlue.setPreferredSize(new Dimension(70, 0));
		labelGlue.setHorizontalAlignment(SwingConstants.RIGHT);
		labelGlue.setFont(new Font("Arial", labelGlue.getFont().getStyle() | Font.BOLD, 20));
		labelPanel.add(labelGlue); 
		
		createLabel(0);  
		
		panel = new JPanel();
		panel_3.add(panel);
		panel.setBackground(SystemColor.activeCaption);
		panel.setPreferredSize(new Dimension(400, 10));
		panel.setLayout(new GridLayout(0, 1, 0, 5));
		box = new JComboBox<>();
		box.setModel(new DefaultComboBoxModel<Object>(new String[] { "Линейное уравнение", "Квадратное уравнение ", "Кубическое уравнение ", "Уравнение четвёртой степени" }));
		panel.add(box);
		
		box.setSelectedIndex(0);
		IS_LINEAR_EQUATION = true; // установка линейного уравнения в качестве начального
		
		box.setEditable(false);
		
		box.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				IS_LINEAR_EQUATION = box.getSelectedIndex() == 0;
				IS_QUADRATIC_EQUATION = box.getSelectedIndex() == 1;
				IS_CUBIC_EQUATION = box.getSelectedIndex() == 2;
				IS_EQUATION_OF_FOURTH_POW = box.getSelectedIndex() == 3;
				if (IS_LINEAR_EQUATION) {
					setLineEquation();
				}
				if (IS_QUADRATIC_EQUATION) {
					setQuadraticEquation();
				}
				if (IS_CUBIC_EQUATION) {
					setCubicEquation();
				}
				if (IS_EQUATION_OF_FOURTH_POW) {
					setEquationOfFourthPow();
				}
				calculation = new Calculation();
			}
		});
		
		createTextField(0);
		
		for (int i = 0; i < 5; i++) {
			final Integer j = new Integer(i);
			textFilter[i] = new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					final char a = e.getKeyChar();
					if (a == 'i') {
						if (textField[j].getText().indexOf("i") != -1) {
							e.consume();
						}
					} else if (a == '+') {
						if (!(isEmpty(textField[j]) || textField[j].getSelectionStart() == 0)) {
						if (textField[j].getText().toCharArray()[textField[j].getSelectionStart()-1] != '-') {
						if (textField[j].getText().toCharArray()[textField[j].getSelectionStart()-1] != '+') {
						if (textField[j].getText().indexOf("+") != -1) {
						e.consume();
						}
						}
						else {
						e.consume();
						}
						}
						else {
						e.consume();
						}
						}
						else {
						e.consume();
						}
						if (textField[j].getText().toCharArray()[textField[j].getSelectionStart()-1] == 'i' ) {
						e.consume();
						}
					} else if (a == '-') {
						if (!isEmpty(textField[j])) {
						if (textField[j].getSelectionStart() != 0) { 
						if (textField[j].getText().toCharArray()[textField[j].getSelectionStart()-1] != '-' ) {
						if (textField[j].getText().toCharArray()[textField[j].getSelectionStart()-1] != '+') {
						int countMinuse = 0;
						for (final char element : textField[j].getText().replaceAll(" ", "").toCharArray()) {
							if (element == '-') {
								countMinuse++;
							}
						}
						if (countMinuse > 1) {
							e.consume();
						} else {
							if (textField[j].getText().indexOf("+") != -1) {
								if (countMinuse > 0) {
									e.consume();
								}
							}
						}
						}
						else {
						e.consume();
						}
						}
						else {
						e.consume();
						}
						if (textField[j].getText().toCharArray()[textField[j].getSelectionStart()-1] == 'i' ) {
						e.consume();
						}
						}
						}
					} else {
						if (textField[j].getSelectionStart() > textField[j].getText().indexOf("i")) {
							if (textField[j].getText().indexOf("i") != -1) {
								e.consume();
							} else {
								if (!Character.isDigit(a) && a != '-' && a != '+' && a != '.'
										&& a != '\b') {
									e.consume();
								}
							}
						} else {
							if (!Character.isDigit(a) && a != '-' && a != '+' && a != '.' && a != '\b') {
								e.consume();
							}
						}
					}
				}
			};
			textField[i].addKeyListener(textFilter[i]);
		}

		final JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(30, 10));
		panel_7.setBackground(SystemColor.activeCaption);
		panel_3.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		final Component verticalGlue_1 = Box.createVerticalGlue();
		panel_7.add(verticalGlue_1);
		final JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(100, 10));
		panel_3.add(panel_1);
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setLayout(new GridLayout(0, 1, 0, 5));
		calculationButton = new JButton("Вычислить");
		panel_1.add(calculationButton);
		solutionButton = new JButton("Решение");
		panel_1.add(solutionButton);
		resetButton = new JButton("Сброс");
		panel_1.add(resetButton);
		preferencesButton = new JButton("Настройки");
		panel_1.add(preferencesButton);
		helpButton = new JButton("Справка");
		panel_1.add(helpButton);
		final JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.activeCaption);
		panel_3.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		final Component verticalGlue = Box.createVerticalGlue();
		panel_6.add(verticalGlue);
		verticalGlue.setPreferredSize(new Dimension(30, 0));
		final JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.activeCaption);
		panel_5.setPreferredSize(new Dimension(10, 0));
		contentPane.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		final Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_5.add(horizontalGlue_1);
		
		calculationButton.addActionListener(processing);
		resetButton.addActionListener(processing);
		helpButton.addActionListener(processing);
		preferencesButton.addActionListener(processing);
		solutionButton.addActionListener(processing);
			
		labelInfo = new JLabel(labelInfoText[0]);
		labelInfo.setFont(new Font("Arial", labelInfo.getFont().getStyle() | Font.BOLD, 20));
		labelInfo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(labelInfo);
		setLineEquation();
	}
	
	ActionListener processing = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
			final boolean[] isEmptyTextField = new boolean[9];
			for (int i = 0; i < 5; i++) {
				isEmptyTextField[i] = isEmpty(textField[i]);
			}
			if (event.getSource() == calculationButton) {
				processingCalculationsOrSolutions(true, textField, isEmptyTextField);
			}
			if (event.getSource() == solutionButton) {
				processingCalculationsOrSolutions(false, textField, isEmptyTextField);
			}
			if (event.getSource() == resetButton) {
				if (!isEmpty(textField)) {
					final Object[] options = { "Да", "Нет" };
					final int n = JOptionPane.showOptionDialog(null, "Сбросить?", "Подтверждение сброса",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (n == 0) {
						reset();
						focus();
					}
				}
			}
			if (event.getSource() == preferencesButton) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							if (!openPreferences) {
							final Preferences preferences = new Preferences();
							preferences.setVisible(true);
							}
						} catch (final Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			if (event.getSource() == helpButton) {
				JOptionPane.showMessageDialog(null, "Программа предназначена для вычисления \n"
						+ "корней линейного, квадратного, кубического \n"
						+ "уравнения и уравнения четвертой степени \n"
						+ "как с действительными, так и \n"
						+ "с комплексными коэффициентами. \n \n"
						+ "Разработчик: Валентин Логачев \n \n"
						+ "                          " + "2017 год", "Справка", JOptionPane.INFORMATION_MESSAGE);
				focus();
			}
		}
	};
	
	private void focus() {
		final int x = this.getWidth();
		final int y = this.getHeight();
		setSize(x + 1, y + 1);
		setSize(x, y);
	}

	private boolean isEmpty(JTextField textField) {
		final boolean n = textField.getText().isEmpty();
		return n;
	}
	
	private boolean isEmpty(JTextField[] textField) {
		boolean tmp = true;
		for (JTextField tmpTextField : textField) {
		tmp = tmp & tmpTextField.getText().isEmpty();
		}
		return tmp;
	}
	
	private void processingCalculationsOrSolutions(boolean isCalculation, JTextField[] textField, boolean[] isEmptyTextField) {
		for (int i = 0; i < 4; i++) {
			if (box.getSelectedIndex() == i) {
				boolean tmp = true;
				for (int j = 0; j < i + 2; j++) {
					tmp&= isEmptyTextField[j];
				}
				if (tmp) {
					JOptionPane.showMessageDialog(null, "Введите коэффициенты!", "Ошибка", JOptionPane.WARNING_MESSAGE);
				} else {
					for (int j = 0; j < i + 2; j++) {
						processingEmptyTextField(textField[j]);
					}
					calculation(isCalculation);
				}
			}
		}
	}

	private void processingEmptyTextField(JTextField textField) {
		if (textField.getText().isEmpty()) {
			textField.setText("0");
		}
	}
	
	public static void input() {
		if (IS_LINEAR_EQUATION) {
		calculation = new Calculation(null, null, null, textField[0].getText(), textField[1].getText());
		}
		if (IS_QUADRATIC_EQUATION) {
			calculation = new Calculation(null, null, textField[0].getText(), textField[1].getText(), textField[2].getText());
		}
		if (IS_CUBIC_EQUATION) {
			calculation = new Calculation(null, textField[0].getText(), textField[1].getText(), textField[2].getText(), textField[3].getText());
		}
		if (IS_EQUATION_OF_FOURTH_POW) {
			calculation = new Calculation(textField[0].getText(), textField[1].getText(), textField[2].getText(), textField[3].getText(), textField[4].getText());
		}
	}
	
	public void calculation(boolean isCalculation) {
		if (isCalculation) {
				input();
				output(calculation);
		} else {
			if (!openSolution) {
				input();
				k++;
				solution();
			}
		}
	}
	
	private void solution() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
						if (IS_LINEAR_EQUATION) {
						formation = new FormationOfSolutions(null, null, null, textField[0].getText(), textField[1].getText());
						}
						if (IS_QUADRATIC_EQUATION) {
						formation = new FormationOfSolutions(null, null, textField[0].getText(), textField[1].getText(), textField[2].getText());
						}
						if (IS_CUBIC_EQUATION) {
						formation = new FormationOfSolutions(null, textField[0].getText(), textField[1].getText(), textField[2].getText(), textField[3].getText());
						}
						if (IS_EQUATION_OF_FOURTH_POW) {
						formation = new FormationOfSolutions(textField[0].getText(), textField[1].getText(), textField[2].getText(), textField[3].getText(), textField[4].getText());
						}
					final Solution solution = new Solution(formation.getSolutionDescription(), formation.getTextPNG(), k);
					solution.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void setLineEquation() {
		labelInfo.setText(labelInfoText[0]);
		labelGlue.setText("");
		removeLabel();
		createLabel(0);
		label[2].setText("x =  ");
		for (int i = 3; i < 9; i++) {
			label[i].setText("");
		}
		removeTextField();
		createTextField(0);
		reset();
	}

	private void setQuadraticEquation() {
		labelInfo.setText(labelInfoText[1]);
		labelGlue.setText("");
		removeLabel();
		createLabel(1);
		label[2].setText(labelText[2]);
		label[3].setText("x1 =  ");
		label[4].setText("x2 =  ");
		label[5].setText("");
		label[6].setText("");
		label[7].setText("");
		label[8].setText("");
		removeTextField();
		createTextField(1);
		reset();
	}

	private void setCubicEquation() {
		labelInfo.setText(labelInfoText[2]);
		labelGlue.setText("");
		removeLabel();
		createLabel(2);
		label[2].setText(labelText[2]);
		label[3].setText(labelText[3]);
		label[4].setText(labelText[5]);
		label[5].setText(labelText[6]);
		label[6].setText(labelText[7]);
		label[7].setText("");
		label[8].setText("");
		removeTextField();
		createTextField(2);
		reset();
	}

	private void setEquationOfFourthPow() {
		labelInfo.setText(labelInfoText[3]);
		labelGlue.setText("Общий");
		removeLabel();
		createLabel(3);
		label[8].setText("x4 = ");
		removeTextField();
		createTextField(3);
		reset();
	}

	private void createLabel(int n) {
		for (int i = 0; i < 9; i++) {
			label[i] = new JLabel(labelText[i]);
			label[i].setHorizontalAlignment(SwingConstants.CENTER);
			panel_2.add(label[i]);
			if (i == n + 1) {
				panel_2.add(horizontalGlue);
			}
		}
	}

	private void removeLabel() {
		for (int i = 0; i < 9; i++) {
			panel_2.remove(label[i]);
		}
		panel_2.remove(horizontalGlue);
	}

	private void createTextField(int n) {
		for (int i = 0; i < 9; i++) {
			textField[i] = new JTextField();
			panel.add(textField[i]);
			if (i == n + 1) {
				panel.add(horizontalGlue_1);
			}
			if (i < n + 2) {
				textField[i].addKeyListener(textFilter[i]);
			}
		}
		if (n == 0) {
			for (int i = 3; i < 9; i++) {
				textField[i].setVisible(false);
			}
			for (int i = 0; i < 3; i++) {
				addContextMenuMouseListener(textField[i]);
				if (i > 1) {
					textField[i].setEditable(false);
				}
			}
		}
		if (n == 1) {
			textField[3].setVisible(true);
			textField[4].setVisible(true);
			for (int i = 5; i < 9; i++) {
				textField[i].setVisible(false);
			}
			for (int i = 0; i < 5; i++) {
				addContextMenuMouseListener(textField[i]);
				if (i > 2) {
					textField[i].setEditable(false);
				}
			}
		}
		if (n == 2) {
			for (int i = 3; i < 7; i++) {
				textField[i].setVisible(true);
			}
			textField[7].setVisible(false);
			textField[8].setVisible(false);
			for (int i = 0; i < 7; i++) {
				addContextMenuMouseListener(textField[i]);
				if (i > 3) {
					textField[i].setEditable(false);
				}
			}
		}
		if (n == 3) {
			for (int i = 3; i < 9; i++) {
				textField[i].setVisible(true);
			}
			for (int i = 0; i < 9; i++) {
				addContextMenuMouseListener(textField[i]);
				if (i > 4) {
					textField[i].setEditable(false);
				}
			}
		}
	}

	private void removeTextField() {
		for (int i = 0; i < 9; i++) {
			panel.remove(textField[i]);
		}
		panel.remove(horizontalGlue_1);
	}

	private void addContextMenuMouseListener(Component component) {
		if (component instanceof JTextComponent) {
			
			((JTextComponent) component).addMouseListener(new ContextMenuMouseListener());
		}
		if (component instanceof Container) {
			for (final Component c : ((Container) component).getComponents()) {
				addContextMenuMouseListener(c);
			}
		}
	}
	
	private void reset() {
		for (int i = 0; i < 9; i++) {
			textField[i].setText("");
		}
	}
	
	public static void output(Calculation calculation) {
		if (IS_EQUATION_OF_FOURTH_POW) {
			textField[5].setText(calculation.getRoots()[0]);
			textField[6].setText(calculation.getRoots()[1]);
			textField[7].setText(calculation.getRoots()[2]);
			textField[8].setText(calculation.getRoots()[3]);
		}
		if (IS_CUBIC_EQUATION) {
			textField[4].setText(calculation.getRoots()[0]);
			textField[5].setText(calculation.getRoots()[1]);
			textField[6].setText(calculation.getRoots()[2]);
		}
		if (IS_QUADRATIC_EQUATION) {
			textField[3].setText(calculation.getRoots()[0]);
			textField[4].setText(calculation.getRoots()[1]);
		}
		if (IS_LINEAR_EQUATION) {
			textField[2].setText(calculation.getRoots()[0]);
		}
	}
}

