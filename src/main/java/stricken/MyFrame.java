package stricken;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MyFrame extends JFrame implements ActionListener {
	
		JButton buttonCreate;
		JButton buttonOpen;
		JButton buttonSave;
		JTextField maschenanzahl;
		JTextField treshold;
		JLabel label;
		String file = "C:/Users/natalja.seeger/git/stricken/src/main/resources/pinguin.png";
		SimpleStricken test;
		JFileChooser fileChooser;
		private JPanel redPanel;
	
	MyFrame() {
		
		
		
		Border border = BorderFactory.createLineBorder(Color.black,3);
		
		buttonCreate = new JButton();
		buttonCreate.setBounds(200, 100, 100, 50);
		buttonCreate.addActionListener(this);
		//button.addActionListener(e -> System.out.println("hallo"));
		buttonCreate.setText("Erstellen");
		buttonCreate.setFocusable(false);
		buttonOpen = new JButton();
		buttonOpen.setBounds(200, 100, 100, 50);
		buttonOpen.addActionListener(this); //ActionListener wird implementiert
		buttonOpen.setText("Öffnen");
		buttonOpen.setFocusable(false);
		buttonSave = new JButton();
		buttonSave.setBounds(200, 100, 100, 50);
		buttonSave.addActionListener(this); 
		buttonSave.setText("Speichern");
		buttonSave.setFocusable(false);
		
		
		maschenanzahl = new JTextField();
		maschenanzahl.setText("30");
		maschenanzahl.setPreferredSize(new Dimension(40, 20));
		maschenanzahl.setVisible(true);
		treshold = new JTextField();
		treshold.setText("150");
		treshold.setVisible(true);
		
		label = new JLabel();
		label.setText("Stricken");
		
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.TOP);
		label.setForeground(new Color(0,0,0));
		label.setFont(new Font("MV Boli", Font.PLAIN, 20));
		label.setIconTextGap(10);
		label.setBackground(Color.white);
		label.setOpaque(true); //Hintergrund von Label sichtbar
		label.setBorder(border);
		//label.setVerticalAlignment(JLabel.CENTER); //Label positionieren mit Border Layout
		label.setBounds(0,0,500,500); //x und y Position von Label und Dimensionen
		label.setVisible(true);
		label.setIcon(new ImageIcon("C:\\Users\\natalja.seeger\\git\\stricken\\src\\main\\resources\\pinguin2.PNG\\"));
		
		
		this.setTitle("Strickmuster");
		//Application wird geschlossen, default ist hide
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setLayout(new GridLayout(1, 2, 5, 5)); //BorderLayout FlowLayout, Abstände zwischen Zellen
		//frame.setResizable(false); Größe nicht mehr veränderbar des Fensters
		this.setVisible(true);
		//this.add(button);
		
		//Icon ändern
		//this.setIconImage(img.getImage());
		//Hintergrundfarbe ändern
		this.getContentPane().setBackground(new Color(100,200,150));
		
		redPanel = new JPanel();
		redPanel.setBackground(Color.red);
		redPanel.setBounds(0, 0, 500, 500);
		//redPanel.setPreferredSize(new Dimension(250, 250);
		//redPanel.setLayout(null);
		
		JPanel bluePanel = new JPanel();
		bluePanel.setBackground(new Color(100,200,150));
		bluePanel.setBounds(500, 0, 500, 500);
		
		redPanel.add(label);
		bluePanel.add(buttonOpen);
		bluePanel.add(maschenanzahl);
		bluePanel.add(treshold);
		bluePanel.add(buttonCreate);
		bluePanel.add(buttonSave);
		
		//frame.add(label);
		this.add(redPanel);
		this.add(bluePanel);
		this.pack(); // Größe des Fensters wird angepasst


	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == buttonOpen) {
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("C:\\Users\\natalja.seeger\\git\\stricken\\src\\main\\resources"));
			int response = fileChooser.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile().getPath();
				//System.out.println(file);
			}	
			label.setIcon(new ImageIcon(file));	
			
		}
		else if (e.getSource() == buttonCreate) {
			test = new SimpleStricken();
			test.loadImage(file);
			int xMaschenanzahl = Integer.parseInt(maschenanzahl.getText());
			int tresholdNumber = Integer.parseInt(treshold.getText());
			test.createKnittingPattern(xMaschenanzahl, tresholdNumber);
		
			
			label.setIcon(new ImageIcon(test.getResultPath()));

			//System.out.println("done");
			
		}
		else if (e.getSource() == buttonSave) {
			String path = "C:\\Users\\natalja.seeger\\git\\stricken";
			int response = fileChooser.showSaveDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				path = fileChooser.getSelectedFile().getPath();
				//System.out.println(path);
			}
					
			test.saveImage(path);
		}
	}

}
