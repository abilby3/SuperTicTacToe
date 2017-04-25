
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
/*Haseeb was here, testing github*/
public class  LauncherGUI extends JFrame {
	private JTextField txtAddress;
	public static String aiChar;
	public static String oppChar;

	public LauncherGUI()
	{
		getContentPane().setLayout(null);
		setSize(350, 504);
		setVisible(true);
		txtAddress = new JTextField();
		txtAddress.setBounds(89, 226, 104, 20);
		getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		txtAddress.setVisible(false);
		final JLabel lblOpponents = new JLabel("Opponents IP Address");
		lblOpponents.setLabelFor(txtAddress);
		lblOpponents.setBounds(85, 210, 108, 14);
		lblOpponents.setVisible(false);
		getContentPane().add(lblOpponents);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Hard", "Easy"}));
		comboBox.setBounds(203, 93, 104, 20);
		getContentPane().add(comboBox);
		
		JLabel lblAiDifficulty = new JLabel("AI Difficulty");
		lblAiDifficulty.setBounds(203, 74, 104, 14);
		getContentPane().add(lblAiDifficulty);
		
		final JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NetworkHandler networkHandler = new NetworkHandler(9001, lblOpponents.getText());
				//GameFacade gameFacade = new GameFacade(); 
				
				SwingUtilities.invokeLater(new Runnable() {
				    public void run() {
				         GameGUI gameGui = new GameGUI("AI vs AI", comboBox.getSelectedItem().toString());
				         while(true)
				         {
				        	 if(GameGUI.turn != 0)
				        	 {
				        		 //gameGui.localMakeMove();
				        	 }
				        	 
				         }
				    
				    }
				});
				dispose();
				getContentPane().setVisible(false);
				
			}
		});
		
		btnConnect.setBounds(89, 323, 104, 39);
		btnConnect.setVisible(false);
		getContentPane().add(btnConnect);
		
		JButton btnPlayer = new JButton("Player vs AI");
		btnPlayer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
				    public void run() {
				    	aiChar = "X";
				    	oppChar = "O";
				         new GameGUI("Player vs AI", comboBox.getSelectedItem().toString());
				    }
				});
				dispose();
				getContentPane().setVisible(false);
			}
		});
		
		btnPlayer.setBounds(89, 74, 104, 39);
		getContentPane().add(btnPlayer);
		JButton btnAI = new JButton("AI vs AI");
		btnAI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAddress.setVisible(true);
				lblOpponents.setVisible(true);
				btnConnect.setVisible(true);
				
				boolean playFirst = false;
				  if(playFirst){
				  	aiChar = "O";
				  	oppChar ="X";
				  }else{
				  	aiChar = "X";
				  	oppChar ="O";
				  }
				
				new GameGUI("AI vs AI", comboBox.getSelectedItem().toString());
				
				
			}
		});
		btnAI.setBounds(89, 143, 104, 39);
		getContentPane().add(btnAI);
		
	}
}
