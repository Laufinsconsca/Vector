import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextField;
import javax.swing.JButton;

class Preferences extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;
	JButton button;

	@SuppressWarnings("unused")
	public Preferences() {
		try {
			for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (final Exception e) {
		}
		setTitle("Настройки");
		setSize(300, 400);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		final Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setPreferredSize(new Dimension(0, 50));
		contentPane.add(verticalBox_2);
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		final Box verticalBox = Box.createVerticalBox();
		verticalBox.setPreferredSize(new Dimension(0, 15));
		panel_1.add(verticalBox, BorderLayout.CENTER);

		final JLabel label_ACCURACY_ROUND = new JLabel("Точность");
		label_ACCURACY_ROUND.setAlignmentX(Component.CENTER_ALIGNMENT);
		label_ACCURACY_ROUND.setVerticalTextPosition(SwingConstants.TOP);
		label_ACCURACY_ROUND.setVerticalAlignment(SwingConstants.TOP);
		label_ACCURACY_ROUND.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_ACCURACY_ROUND.setHorizontalAlignment(SwingConstants.CENTER);
		label_ACCURACY_ROUND.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_1.add(label_ACCURACY_ROUND, BorderLayout.SOUTH);
		final JPanel panel = new JPanel();
		
		panel.setBackground(SystemColor.activeCaption);
		contentPane.add(panel);
		
		Interface.slider.setBackground(SystemColor.activeCaption);
		Interface.slider.setMajorTickSpacing(10);
		Interface.slider.setPaintTicks(true);
		Interface.slider.setMinorTickSpacing(Main.SLIDER_MIN_VALUE);
		final Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
		for (int i = 2; i < Main.SLIDER_MAX_VALUE+1; i = Main.SLIDER_MAX_VALUE < 24 ?i+2:Main.SLIDER_MAX_VALUE < 48?i+4:Main.SLIDER_MAX_VALUE < 96?i+8:Main.SLIDER_MAX_VALUE < 192?i+16:i+32) {
			labelTable.put(new Integer(i), new JLabel("" + i));
		}
		Interface.slider.setLabelTable(labelTable);
		Interface.slider.setPaintLabels(true);
		Interface.slider.setToolTipText("Точность");
		Interface.slider.setName("Точность");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(Interface.slider);

		final Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setPreferredSize(new Dimension(0, 70));
		contentPane.add(verticalBox_1);

		final JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setPreferredSize(new Dimension(100, 220));
		contentPane.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		final JLabel lblNewLabel_2 = new JLabel("Представление числа");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setPreferredSize(new Dimension(0, 45));
		panel_2.add(lblNewLabel_2);
		
		final JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		panel_2.add(panel_3);

		panel_3.add(Interface.radioButton_Normal);
		Interface.radioButton_Normal.setVerticalTextPosition(SwingConstants.TOP);
		Interface.radioButton_Normal.setVerticalAlignment(SwingConstants.TOP);
		Interface.radioButton_Normal.setHorizontalTextPosition(SwingConstants.CENTER);
		Interface.radioButton_Normal.setHorizontalAlignment(SwingConstants.CENTER);
		
		Interface.radioButton_Exponential.setBackground(SystemColor.activeCaption);
		panel_3.add(Interface.radioButton_Exponential);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.activeCaption);
		panel_2.add(panel_4);
		panel_4.setPreferredSize(new Dimension(100, 0));
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		Box verticalBox_3 = Box.createVerticalBox();
		verticalBox_3.setPreferredSize(new Dimension(0, 50));
		panel_4.add(verticalBox_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.activeCaption);
		panel_4.add(panel_5);
		panel_5.setPreferredSize(new Dimension(0, 30));
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setPreferredSize(new Dimension(50, 0));
		panel_5.add(horizontalBox);
		
		Interface.preferencesTextField = new JTextField();
		panel_5.add(Interface.preferencesTextField);
		Interface.preferencesTextField.setPreferredSize(new Dimension(80, 0));
		Interface.preferencesTextField.setColumns(10);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setPreferredSize(new Dimension(30, 0));
		panel_5.add(horizontalBox_2);
		
		button = new JButton("Изменить");
		panel_5.add(button);
		button.setPreferredSize(new Dimension(100, 0));
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setPreferredSize(new Dimension(50, 0));
		panel_5.add(horizontalBox_1);
		
		Box verticalBox_5 = Box.createVerticalBox();
		verticalBox_5.setPreferredSize(new Dimension(0, 10));
		panel_4.add(verticalBox_5);
		
		JLabel lblNewLabel = new JLabel("Задать точность вручную");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("max - " + Main.ACCURACY_ROUND);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(lblNewLabel_1);
		
		Box verticalBox_4 = Box.createVerticalBox();
		verticalBox_4.setPreferredSize(new Dimension(0, 10));
		panel_4.add(verticalBox_4);
		
		Interface.radioButton_Exponential.setVerticalAlignment(SwingConstants.TOP);
		Interface.radioButton_Exponential.setVerticalTextPosition(SwingConstants.TOP);
		Interface.radioButton_Exponential.setHorizontalAlignment(SwingConstants.CENTER);
		Interface.radioButton_Exponential.setHorizontalTextPosition(SwingConstants.CENTER);

		Interface.radioButton_Normal.addActionListener(this);
		Interface.radioButton_Exponential.addActionListener(this);
		button.addActionListener(this);

		final ChangeListener instantChange = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (event.getSource() == Interface.slider) {
					Interface.sliderValue = Interface.slider.getValue();
					Interface.preferencesTextField.setText(Interface.slider.getValue() + "");
				}
				if (event.getSource() == Interface.slider | event.getSource() == Interface.radioButton_Normal
						| event.getSource() == Interface.radioButton_Exponential | event.getSource() == button) {
					Interface.output(Interface.calculation.setOutputValuesIfCalculationTrue());
				}
			}
		};
		Interface.slider.addChangeListener(instantChange);
		Interface.radioButton_Normal.addChangeListener(instantChange);
		Interface.radioButton_Exponential.addChangeListener(instantChange);
		button.addChangeListener(instantChange);

		setLocationRelativeTo(null);
		setResizable(false);
		addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Interface.radioButton_Normal) {
			if (Interface.radioButton_Exponential.isSelected() == true) {
				Interface.radioButton_Exponential.setSelected(false);
			}
			else {
				Interface.radioButton_Normal.setSelected(true);
			}
			Interface.isNormalView = true;
		}
		if (e.getSource() == Interface.radioButton_Exponential) {
			if (Interface.radioButton_Normal.isSelected() == true) {
				Interface.radioButton_Normal.setSelected(false);
			}
			else {
				Interface.radioButton_Exponential.setSelected(true);
			}
			Interface.isNormalView = false;
		}
		if (e.getSource() == button) {
			Interface.sliderValue = Integer.valueOf(Interface.preferencesTextField.getText());
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		Interface.openPreferences = false;
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
		Interface.openPreferences = true;
	}
}