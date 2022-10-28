package com.yigiteryelkovan.java.replace_excel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExcelTextReplacerGui extends JPanel {
	private static final long serialVersionUID = -6949709584683739813L;
	private File excelFile = null;
	private File textFile = null;
	
	
	private JButton excelFileChooserButton;
	private JButton textFileChooserButton;
	private JButton convertButton;
	private JTextField excelTextField;
	private JLabel excelFileLabel;
	private JLabel textFileLabel;
	private JTextField textTextField;
	private JLabel resultTextField;

	public ExcelTextReplacerGui() {
		// construct components
		excelFileChooserButton = new JButton("File Choose");
		textFileChooserButton = new JButton("File Choose");
		convertButton = new JButton("Convert");
		excelTextField = new JTextField(5);
		excelFileLabel = new JLabel("Excel File");
		textFileLabel = new JLabel("Replacer File(.txt)");
		textTextField = new JTextField(5);
		resultTextField = new JLabel("");

		// adjust size and set layout
		setPreferredSize(new Dimension(667, 373));
		setLayout(null);
		
		excelFileChooserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int status = chooser.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					if (file == null) {
						return;
					}
					excelFile = file;
					excelTextField.setText(excelFile.getAbsolutePath());
				}
			}
		});
		
		textFileChooserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int status = chooser.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					if (file == null) {
						return;
					}
					textFile = file;
					textTextField.setText(textFile.getAbsolutePath());

				}
			}
		});
		
		convertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resultTextField.setText("Replacing...");
				ExcelTextReplacer run = new ExcelTextReplacer(excelFile, textFile, resultTextField);
				run.calistir();
			}
		});

		// add components
		add(excelFileChooserButton);
		add(textFileChooserButton);
		add(convertButton);
		add(excelTextField);
		add(excelFileLabel);
		add(textFileLabel);
		add(textTextField);
		add(resultTextField);

		// set component bounds (only needed by Absolute Positioning)
		excelFileChooserButton.setBounds(465, 30, 100, 20);
		textFileChooserButton.setBounds(465, 65, 100, 20);
		convertButton.setBounds(465, 100, 100, 20);
		excelTextField.setBounds(205, 30, 255, 20);
		excelFileLabel.setBounds(20, 30, 180, 20);
		textFileLabel.setBounds(20, 65, 180, 20);
		textTextField.setBounds(205, 65, 255, 20);
		resultTextField.setBounds(20, 100, 440, 20);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Excel Text Replacer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new ExcelTextReplacerGui());
		frame.pack();
		frame.setVisible(true);
	}
}
