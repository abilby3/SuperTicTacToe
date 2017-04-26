
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

public class GameGUI extends JFrame {

		public static int turn = 0;
		private JTextField gameInfo;
		private GameBoard gameBoard;   
		private AI ai; 
		private int MATRIX = 5;
		
		GameGUI(final String gameType, String AI_Difficulty)
		{
			final String type = gameType;
			//initialize field variables
			if(AI_Difficulty.equals("Hard"))
				ai = new HardAi();
			else if(AI_Difficulty.equals("Easy"))
				//ai = new EasyAi();
				ai = new EasyAi();
			
			if(type.equals("AI vs AI")){
				if(AI_Difficulty.equals("Hard"))
					ai = new HardAi();
				else if(AI_Difficulty.equals("Easy"))
					ai = new EasyAi();
				
				
			}
			gameBoard = new GameBoard(); 
			
			//Configure GUI
			setSize(500, 700);
			setTitle("Super TIC TAC TOE:");
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 5, 0, 0));
			
			
			//Create 5x5 Grid
			for(int i = 0; i < 25; i++)
			{
				JButton newButton = new JButton();
				newButton.setFont(new Font("Arial", Font.PLAIN, 72));
				if(type.equals("AI vs AI")){
					newButton.setEnabled(false);
				}
				//Logic for player vs AI
				newButton.addActionListener(new ActionListener() {
					
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						JButton btn = (JButton)e.getSource();
						if(btn.isEnabled() )
						{				
							gameBoard.placeMove(btn, turn);
							turn++; 
							
							if(getWinner().equals(""))
							{
								int move = ai.makeMove(gameBoard, turn);
								gameBoard.placeMove(move, turn);
								turn++;
							}
							gameOver(getWinner());	
						}
					}		
				});
			
				gameBoard.addTile(newButton);
				panel.add(newButton, null); 
			}
 
			
			JPanel panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(600, 100));
			getContentPane().add(panel_1, BorderLayout.SOUTH);
			
			JButton btnNewGame = new JButton("New Game");
			
			btnNewGame.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(ai instanceof HardAi){
						gameInfo.setText("Deep Broncho is playing as " + LauncherGUI.aiChar);
					} else{
						gameInfo.setText("AI is playing as " + LauncherGUI.aiChar);
					}
					newGame();
				}
			});
			
			btnNewGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			
			if(gameType.equals("AI vs AI"))
			{
				btnNewGame.setEnabled(false);
				btnNewGame.setVisible(false);
			}
			
			JButton btnQuit = new JButton("Quit");
			
			btnQuit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					System.exit(0);
				}
			});
			
			JPanel panelInfo = new JPanel();
			panelInfo.setPreferredSize(new Dimension(200,100));
			gameInfo = new JTextField();
			
				if(ai instanceof HardAi){
					gameInfo.setText("Deep Broncho is playing as " + LauncherGUI.aiChar);
				} else{
					gameInfo.setText("AI is playing as " + LauncherGUI.aiChar);
				}
			
			gameInfo.setSize(new Dimension(200,100));
			gameInfo.setFont(new Font("Arial", Font.PLAIN, 26));
			gameInfo.setEditable(false);
			gameInfo.setFocusable(false);
			getContentPane().add(panelInfo, "North");
			panelInfo.add(gameInfo);
			
			
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							
							.addComponent(btnQuit, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewGame, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))							
						.addContainerGap(553, Short.MAX_VALUE))
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						
						.addComponent(btnNewGame, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(btnQuit, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			panel_1.setLayout(gl_panel_1);
			
		}
		
		
		//To put in run 
		public void localMakeMove()
		{
			if(getWinner().equals(""))
			{
				
				
				int move = ai.makeMove(gameBoard, turn);
								
				gameBoard.placeMove(move, turn);
				turn++;
			}
		}
		
		public boolean gameOver(String winCondition){
			if(winCondition.equals(""))
				return false;

			gameBoard.disableGameBoard();
			if(winCondition.equals("Draw")){
				this.setTitle("Super TIC TAC TOE: Draw Game Over!!!");
				gameInfo.setText("Draw Game!");
			}
			else{
				this.setTitle("Super TIC TAC TOE: The winner is " + winCondition +  "!!!!");
				if(winCondition.equalsIgnoreCase(LauncherGUI.aiChar) && ai instanceof HardAi){
					gameInfo.setText("The winner is Deep Broncho!!!");
				}else{
					gameInfo.setText("The winner is "+ winCondition +  "!!!!");
				}
				
			//Stop network handler
			}
			
			GameFacade.gameOver = true;
			return true;
		}
		
		public void enemyeMove(int move){
			gameBoard.placeMove(move, turn);
			if(!gameOver(getWinner())){
				turn++;
			}
			//turn++;
		}
		
		public int getMove(){
			
			if(turn < 3){
				//if(!LauncherGUI.aiChar.equals(null)){
					if(ai instanceof HardAi){
						gameInfo.setText("Deep Broncho is playing as " + LauncherGUI.aiChar);
					} else{
						gameInfo.setText("AI is playing as " + LauncherGUI.aiChar);
					}
				//}
			}
			
			
			Long timeStart = System.currentTimeMillis();
			int move = ai.makeMove(gameBoard, turn);
			Long timeEnd = System.currentTimeMillis();
			Long timeTaken = timeEnd - timeStart;
			System.out.println("Time: " + timeTaken);
			try {
				Thread.sleep(4000 - timeTaken);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//int move = ai.makeMove(gameBoard, turn);
			gameBoard.placeMove(move, turn);
			gameOver(getWinner());
			turn++;
			return move;
		}
		
		public void newGame(){
			this.setTitle("");
			turn = 0;
			this.setTitle("Super TIC TAC TOE:");
			gameBoard.resetGameBoard();
		}
			
		public String getWinner(){
			
			int oCount = 0;
			int xCount = 0;
			int tmpCount = 1;
			ArrayList<JButton> tiles = gameBoard.getTiles();
			
			//Check for winner across rows
			for(int i = 0; i < MATRIX * MATRIX; i++)
			{ 
				if(tiles.get(i).getText().equals("X"))
					xCount++;
				else if(tiles.get(i).getText().equals("O"))
					oCount++;
				
				if(tmpCount % MATRIX == 0)
				{
					tmpCount = 0;
					if(oCount == MATRIX)
						return "O";
					else if(xCount == MATRIX)
						return "X";
					oCount = 0;
					xCount = 0;
				}
				tmpCount++;
			} 
			
			oCount = 0;
			xCount = 0;
			tmpCount = 1;
			int index = 0;
			
			//Check for winnder in cols
			for(int i = 0; i < MATRIX; i++)
			{
				index = i;
				while(tmpCount <= MATRIX)
				{
					if(tiles.get(index).getText().equals("X"))
						xCount++;
					else if(tiles.get(index).getText().equals("O"))
						oCount++;
					
					index += MATRIX; 
					tmpCount++;
				}
				
				if(oCount == MATRIX)
					return "O";
				else if(xCount == MATRIX)
					return "X";
				oCount = 0;
				xCount = 0;
				tmpCount = 1;
			}
			
			oCount = 0;
			xCount = 0;			
			index = 0;
			//left to right diagonal
			for(int i = 0; i < MATRIX; i++)
			{
				
				if(tiles.get(index).getText().equals("X"))
					xCount++;
				else if(tiles.get(index).getText().equals("O"))
					oCount++;
				index += MATRIX + 1;
			}
			if(oCount == MATRIX)
				return "O";
			else if(xCount == MATRIX)
				return "X";
			
			oCount = 0;
			xCount = 0;			
			index = MATRIX - 1;
			//right to left diagonal
			for(int i = 0; i < MATRIX; i++)
			{
				
				if(tiles.get(index).getText().equals("X"))
					xCount++;
				else if(tiles.get(index).getText().equals("O"))
					oCount++;
				index += MATRIX - 1;
			}
			if(oCount == MATRIX)
				return "O";
			else if(xCount == MATRIX)
				return "X";
			
			for(int i = 0; i < tiles.size(); i++)
			{
				if(tiles.get(i).getText().equals(""))
					return "";
			}
			
			return "Draw";
		}
		
		
}
