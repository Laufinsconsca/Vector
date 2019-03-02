import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;

public class Solution extends JFrame implements ActionListener, WindowListener, ClipboardOwner {
	private static final long serialVersionUID = 1L;
	File file;
	String solutionDescription;
	BufferedImage image;
	JButton bufferButton, saveButton;
	int k;

	public Solution(String solutionDescription, String textPNG, int k) {
		this.k = k;
		this.solutionDescription = solutionDescription;
		final TeXFormula formula = new TeXFormula(textPNG);
		final Icon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 25);
		image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		final Graphics2D g = image.createGraphics();
		icon.paintIcon(new JLabel(), g, 0, 0);
		JLabel label_solution = new JLabel(new ImageIcon(image));

		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setViewportBorder(UIManager.getBorder("ScrollPane.border"));
		scrollPane.setPreferredSize(new Dimension(0, 0));
		contentPane.add(scrollPane);

		scrollPane.setViewportView(label_solution);

		JPanel panel = new JPanel();
		panel.setVisible(true);
		panel.setBackground(Color.WHITE);
		scrollPane.setColumnHeaderView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{20, 500, 40, 500, 20, 0};
		gbl_panel.rowHeights = new int[]{0, 25, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		Component verticalGlue = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue = new GridBagConstraints();
		gbc_verticalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue.gridx = 2;
		gbc_verticalGlue.gridy = 0;
		panel.add(verticalGlue, gbc_verticalGlue);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_1 = new GridBagConstraints();
		gbc_horizontalGlue_1.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_1.gridx = 0;
		gbc_horizontalGlue_1.gridy = 1;
		panel.add(horizontalGlue_1, gbc_horizontalGlue_1);
		
		bufferButton = new JButton("Копировать в HTML");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		panel.add(bufferButton, gbc_btnNewButton);
		bufferButton.addActionListener(this);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.fill = GridBagConstraints.HORIZONTAL;
		gbc_horizontalGlue.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue.gridx = 2;
		gbc_horizontalGlue.gridy = 1;
		panel.add(horizontalGlue, gbc_horizontalGlue);
		
		saveButton = new JButton("Сохранить на диске C в формате png");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.gridx = 3;
		gbc_button.gridy = 1;
		panel.add(saveButton, gbc_button);
		saveButton.addActionListener(this);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_2 = new GridBagConstraints();
		gbc_horizontalGlue_2.gridx = 4;
		gbc_horizontalGlue_2.gridy = 1;
		panel.add(horizontalGlue_2, gbc_horizontalGlue_2);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(1100, 683);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Описание решения");

		addWindowListener(this);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		Interface.openSolution = false;
	}

	@Override
	public void windowClosing(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		Interface.openSolution = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bufferButton) {
			final StringSelection stringSelection = new StringSelection(solutionDescription);
			final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, this);
		}
		if (e.getSource() == saveButton) {
			final Object[] options = { "Да", "Нет" };
			final int n = JOptionPane.showOptionDialog(null, "Сохранить на диск?",
				"Подтверждение", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (n == 0) {
			file = new File("c:\\Solutions\\Solution" + k + ".png"); 
			file.mkdirs();
			try {
			ImageIO.write(image, "png", file.getAbsoluteFile()); 
			}
			catch (IOException ex) {}
			JOptionPane.showMessageDialog(null, "Успешно", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {}

}