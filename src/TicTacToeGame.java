import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToeGU {
	//fields: creating all my class variables
	JFrame frame = new JFrame();
	JButton [][] buttons = new JButton[3][3];
	int [][] grid = new int [3][3];
	Container scores = new Container();
	Container playingfield = new Container();
	JLabel xwins = new JLabel("x wins:");
	JLabel owins = new JLabel("o wins:");
	int xWins = 0;
	int oWins = 0;
	int currentMove = 1;
	String winner;
	int[] coords = {0, 1, 2};
	
	//constructor:  setting my frame size and layouts	
	public TicTacToeGU(){
		frame.setSize(700,500);
		frame.setLayout(new BorderLayout()); 
		
		//scores Container 
		scores.setLayout(new GridLayout(2,2));
		scores.add(xwins);
		scores.add(owins);
		frame.add(scores, BorderLayout.NORTH);
		
		//playing field Container 
		playingfield.setLayout(new GridLayout(3,3));
		
		//created an array in a for loop for my buttons instead of making 9 diff buttons!!
		generateButtons();
		
		//this is to make the frame quit on the exit button
		//instead of running all thru in the background, also sets frame to visible 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//Action Listeners for each button
		//If move is valid, game logic is run
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					buttons[i][j].addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {					
							setButtonText(e);
						}
					});
				}
			}
			
		
	}
	
	private void generateButtons(){
		for (int i=0; i < buttons.length; i++){
			for (int j =0; j < buttons.length; j++){
				buttons [i][j] = new JButton(j+","+i);
				playingfield.add(buttons[i][j]);
			}
		}
		frame.add(playingfield, BorderLayout.CENTER);
		clearBoard();
	}
	
	private void setButtonText(ActionEvent e){
		JButton button = (JButton)e.getSource();
		
		if (button.getText().equals("X") || button.getText().equals("O")) 
		{
			JOptionPane.showMessageDialog(null, "Invalid Move");
		} 
		else if (currentMove % 2 != 0) 
		{
			button.setText("X");
			checkWin("X");
			currentMove++;
		} 
		else if (currentMove % 2 == 0) 
		{
			button.setText("O");
			checkWin("O");
			currentMove++;
		}
	}
	
	public void checkWin(String player){
		if ((buttons[0][0].getText().equals(player) && buttons[0][1].getText().equals(player) &&
													 buttons[0][2].getText().equals(player)) ||
		   (buttons[1][0].getText().equals(player) && buttons[1][1].getText().equals(player) &&
													 buttons[1][2].getText().equals(player)) ||
		   (buttons[2][0].getText().equals(player) && buttons[2][1].getText().equals(player) &&
													 buttons[2][2].getText().equals(player)) ||
		   (buttons[0][0].getText().equals(player) && buttons[1][0].getText().equals(player) &&
													 buttons[2][0].getText().equals(player)) ||
		   (buttons[0][1].getText().equals(player) && buttons[1][1].getText().equals(player) &&
													 buttons[2][1].getText().equals(player)) ||
		   (buttons[0][2].getText().equals(player) && buttons[1][2].getText().equals(player) &&
													 buttons[2][2].getText().equals(player)) ||
		   (buttons[0][0].getText().equals(player) && buttons[1][1].getText().equals(player) &&
													 buttons[2][2].getText().equals(player)) ||
		   (buttons[0][2].getText().equals(player) && buttons[1][1].getText().equals(player) &&
													 buttons[2][0].getText().equals(player))) 
		{
			declareWin(player);
		}
		else if (currentMove == 9)
		{
			JOptionPane.showMessageDialog(null, "Tie!");
			clearBoard();	
		}
	}
	
	public void clearBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j].setText(" ");
			}
		}
		currentMove = 0;
	}
	
	public void declareWin(String winner) {
		if (winner == "X")
		{
			xWins++;
			xwins.setText("x wins: " + Integer.toString(xWins));
			clearBoard();
		}
		else if (winner == "O")
		{
			oWins++;
			owins.setText("o wins: " + Integer.toString(oWins));
			clearBoard();
		}
			
	}

	public static void main(String[] args) {
		new TicTacToeGUI(); 
	}

	
}

