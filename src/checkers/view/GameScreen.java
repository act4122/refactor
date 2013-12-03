package checkers.view;
/*
 * CheckerGUI.java
 * 
 * The actual board.
 *
 * Created on January 25, 2002, 2:34 PM
 * 
 * Version
 * $Id: CheckerGUI.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 * 
 * Revisions
 * $Log: CheckerGUI.java,v $
 * Revision 1.1  2002/10/22 21:12:52  se362
 * Initial creation of case study
 *
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import checkers.system.Board;
import checkers.system.CheckersGame;
import checkers.system.Piece.PieceType;

/**
 *
 * @author
 * @version 
 */

@SuppressWarnings("serial")
public class GameScreen extends JPanel implements ActionListener{

	//the facade for the game

	private CheckersGame game; //the facade
	private List<JButton> possibleSquares = new ArrayList<JButton>();//a vector of the squares
	private int timeRemaining;//the time remaining

	public static final String TITLE = "Checkers";
	private JLabel PlayerOnelabel;
	private JLabel playerTwoLabel;
	private JLabel timeRemainingLabel;
	private JLabel secondsLeftLabel;
	private JButton ResignButton;
	private JButton DrawButton;
	private JLabel warningLabel, whosTurnLabel;
	
	//the names and time left
	private static String playerOnesName="", playerTwosName="", timeLeft="";

	/** 
	 *
	 * Constructor, creates the GUI and all its components
	 *
	 * @param facade the facade for the GUI to interact with
	 * @param name1 the first players name
	 * @param name2 the second players name
	 *
	 */

	public GameScreen( CheckersGame game ) {

		this.game = game;
		register();

		initComponents ();
		update();
		//updateTime();
	}


	/*
	 * This method handles setting up the timer
	 */

	private void register() {

		try{
			game.addActionListener( this );

		}catch( Exception e ){

			System.err.println( e.getMessage() );

		}
	}


	/**
	 * This method is called from within the constructor to
	 * initialize the form. It initializes the components
	 * adds the buttons to the Vecotr of squares and adds
	 * an action listener to the components 
	 *
	 */
	private void initComponents() {

		PlayerOnelabel = new JLabel();
		playerTwoLabel = new JLabel();
		whosTurnLabel = new JLabel();

		warningLabel = new JLabel( );
		timeRemainingLabel = new JLabel();
		secondsLeftLabel = new JLabel();

		ResignButton = new JButton();
		ResignButton.addActionListener( this );

		DrawButton = new JButton();
		DrawButton.addActionListener( this );

		//sets the layout and adds listener for closing window
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Set up the buttons
		JButton btn = null;
		int col = 0;
		for (int i = 0; i < 64; i++) {
			btn = new JButton();
			possibleSquares.add(btn);
			btn.addActionListener(this);
			
			
			if (i % 8 == 0) {
				col++;
			}
			c.gridx = i % 8;
			c.gridy = col;
			
			btn.setPreferredSize(new Dimension(80, 80));
			btn.setActionCommand(String.valueOf(i));
			add(btn, c);
			
			if (c.gridx % 2 == 0 && col % 2 == 1 ||
					c.gridx % 2 == 1 && col % 2 == 1) {
				if (c.gridx % 2 == 0) {
					btn.setBackground(Color.white);			
				} else {
					btn.setBackground( new Color(204, 204, 153));		
				}
			} else {
				if (c.gridx % 2 == 1) {
					btn.setBackground(Color.white);			
				} else {
					btn.setBackground( new Color(204, 204, 153));			
				}
			}
			
		}

		PlayerOnelabel.setText("Player 1:     ");
		PlayerOnelabel.setForeground( Color.black );

		c = new java.awt.GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 4;
		add(PlayerOnelabel, c);

		playerTwoLabel.setText("Player 2:     ");
		playerTwoLabel.setForeground( Color.black );

		c = new java.awt.GridBagConstraints();
		c.gridx = 2;
		c.gridy = 9;
		c.gridwidth = 4;
		add(playerTwoLabel, c);

		whosTurnLabel.setText("");
		whosTurnLabel.setForeground( new Color( 0, 100 , 0 ) );

		c.gridx=8;
		c.gridy=1;
		add(whosTurnLabel, c );

		warningLabel.setText( "" );
		warningLabel.setForeground( Color.red );

		c.gridx = 8;
		c.gridy = 2;
		add( warningLabel, c );

		timeRemainingLabel.setText("Time Remaining:");
		timeRemainingLabel.setForeground( Color.black );

		c = new java.awt.GridBagConstraints();
		c.gridx = 8;
		c.gridy = 3;
		add(timeRemainingLabel, c);

		secondsLeftLabel.setText( timeLeft + " sec.");
		secondsLeftLabel.setForeground( Color.black );

		c = new java.awt.GridBagConstraints();
		c.gridx = 8;
		c.gridy = 4;
		add(secondsLeftLabel, c);

		ResignButton.setActionCommand("resign");
		ResignButton.setText("Resign");

		c = new java.awt.GridBagConstraints();
		c.gridx = 8;
		c.gridy = 7;
		add(ResignButton, c);

		DrawButton.setActionCommand("draw");
		DrawButton.setText("Draw");

		c = new java.awt.GridBagConstraints();
		c.gridx = 8;
		c.gridy = 6;
		add(DrawButton, c);

	}

	/**
	 * Takes care of input from users, handles any actions performed
	 *
	 * @param e  the event that has occured
	 */

	@Override
	public void actionPerformed( ActionEvent e ) {

		try{
			//if a square gets clicked
			if( e.getActionCommand().equals(  "1" ) ||
					e.getActionCommand().equals(  "3" ) || 
					e.getActionCommand().equals(  "5" ) ||
					e.getActionCommand().equals(  "7" ) ||
					e.getActionCommand().equals(  "8" ) ||
					e.getActionCommand().equals( "10" ) ||
					e.getActionCommand().equals( "12" ) ||
					e.getActionCommand().equals( "14" ) ||
					e.getActionCommand().equals( "17" ) ||
					e.getActionCommand().equals( "19" ) ||
					e.getActionCommand().equals( "21" ) ||
					e.getActionCommand().equals( "23" ) ||
					e.getActionCommand().equals( "24" ) ||
					e.getActionCommand().equals( "26" ) ||
					e.getActionCommand().equals( "28" ) ||
					e.getActionCommand().equals( "30" ) ||
					e.getActionCommand().equals( "33" ) ||
					e.getActionCommand().equals( "35" ) ||
					e.getActionCommand().equals( "37" ) ||
					e.getActionCommand().equals( "39" ) ||
					e.getActionCommand().equals( "40" ) ||
					e.getActionCommand().equals( "42" ) ||
					e.getActionCommand().equals( "44" ) ||
					e.getActionCommand().equals( "46" ) ||
					e.getActionCommand().equals( "49" ) ||
					e.getActionCommand().equals( "51" ) ||
					e.getActionCommand().equals( "53" ) ||
					e.getActionCommand().equals( "55" ) ||
					e.getActionCommand().equals( "56" ) ||
					e.getActionCommand().equals( "58" ) ||
					e.getActionCommand().equals( "60" ) ||
					e.getActionCommand().equals( "62" ) ) {

				//call selectSpace with the button pressed
				game.selectSpace(Integer.parseInt( e.getActionCommand() ) );

				//if draw is pressed
			}else if( e.getActionCommand().equals( "draw" ) ){
				//does sequence of events for a draw
				game.pressDraw();

				//if resign is pressed
			}else if( e.getActionCommand().equals( "resign" ) ) {
				//does sequence of events for a resign
				game.pressQuit();

				//if the source came from the facade
			}else if( e.getSource().equals( game ) ) {

				//if its a player switch event
				if ( (e.getActionCommand()).equals(CheckersGame.playerSwitch) ) {
					//set a new time
					timeRemaining = game.getTimer();
					//if it is an update event
				} else if ( (e.getActionCommand()).equals(CheckersGame.update) ) {
					//update the GUI
					update();
				} else {
					throw new Exception( "unknown message from game" );
				}
			}
			//catch various Exceptions
		}catch( NumberFormatException excep ){
			System.err.println(
					"GUI exception: Error converting a string to a number" );
		}catch( NullPointerException exception ){
			System.err.println( "GUI exception: Null pointerException "
					+ exception.getMessage() );
			exception.printStackTrace();
		}catch( Exception except ){
			System.err.println( "GUI exception: other: "
					+ except.getMessage() );
			except.printStackTrace();
		}
	}


	/**
	 * Updates the GUI reading the pieces in the board
	 * Puts pieces in correct spaces, updates whos turn it is
	 *
	 * @param the board
	 */

	private void update(){
		if( checkEndConditions() ){
			game.showEndGame(" ");
		}
		
		// Set the names of the players
		String name = game.getPlayerName(1);
		if (name != null) {
			if(name.length() > 7 ){
				name = name.substring(0,7);
			}
			PlayerOnelabel.setText("Player 1:     " + name );
	
			name = game.getPlayerName(2);
			if(name.length() > 7 ){
				name = name.substring(0,7);
			}
			playerTwoLabel.setText("Player 2:     " + name );
		}
		
		//the board to read information from
		Board board = game.stateOfBoard();
		//a temp button to work with
		JButton temp =  null;

		//go through the board
		for( int i = 1; i < board.sizeOf(); i++ ){

			// if there is a piece there
			if( board.occupied( i ) ){

				//check to see if color is blue
				if( board.colorAt( i ) == Color.blue ){

					//if there is a  single piece there
					if((board.getPieceAt(i)).getType() == PieceType.SINGLE){

						//show a blue single piece in that spot board
						temp = possibleSquares.get(i);

						//get the picture from the web
						try{
							temp.setIcon(
									new ImageIcon( new URL("file:lib/images/BlueSingle.gif") ));
						}catch( MalformedURLException e ){
							System.out.println(e.getMessage());
						}

						//if there is a kinged piece there
					}else if((board.getPieceAt(i)).getType() == PieceType.KING ){

						//show a blue king piece in that spot board
						temp= possibleSquares.get(i);

						//get the picture formt the web
						try{
							temp.setIcon(
									new ImageIcon(new URL("file:lib/images/BlueKing.gif") ) );
						}catch( Exception e ){}

					}

					//check to see if the color is white        
				}else if( board.colorAt( i ) == Color.white ){

					//if there is a single piece there
					if((board.getPieceAt(i)).getType() == PieceType.SINGLE){

						//show a blue single piece in that spot board
						temp = possibleSquares.get(i);

						//get the picture from the web
						try{
							temp.setIcon(
									new ImageIcon(new URL("file:lib/images/WhiteSingle.gif")));
						}catch( Exception e ){}

						//if there is a kinged piece there
					}else if((board.getPieceAt(i)).getType() == PieceType.KING){

						//show a blue king piece in that spot board
						temp = possibleSquares.get(i);

						//get the picture from the web
						try{
							temp.setIcon(
									new ImageIcon(new URL("file:lib/images/WhiteKing.gif") ) );
						}catch( Exception e ){}
					}
					//if there isnt a piece there        
				}
			}else {
				//show no picture
				temp = possibleSquares.get(i);
				temp.setIcon( null );
			}
		}

		//this code updates whos turn it is
		if(game.whosTurn() == 2 ){
			playerTwoLabel.setForeground( Color.red );
			PlayerOnelabel.setForeground(Color.black );
			whosTurnLabel.setText( playerTwosName + "'s turn ");
		}else if( game.whosTurn() == 1 ){
			PlayerOnelabel.setForeground( Color.red );
			playerTwoLabel.setForeground(Color.black );
			whosTurnLabel.setText( playerOnesName + "'s turn" );
		}
	}

	/**
	 *
	 * Update the timer
	 *
	 */

	public void updateTime() {            

		if ( game.getTimer() > 0 ) {

			// if the time has run out but not in warning time yet
			// display warning and count warning time
			if ( timeRemaining <= 0 && ( warningLabel.getText() ).equals( "" ) ) {
				timeRemaining = game.getTimerWarning();
				warningLabel.setText( "Time is running out!!!" );

				// if the time has run out and it was in warning time quit game
			} else if ( timeRemaining <= 0 &&
					!( warningLabel.getText() ).equals( "" ) ) {

				game.pressQuit();

			} else {

				timeRemaining--;

			}

			secondsLeftLabel.setText( timeRemaining + " sec." );

		} else {
			secondsLeftLabel.setText( "*****" );
		}
	}

	/**
	 * Checks the ending condotions for the game
	 * see if there a no pieces left
	 *
	 * @return the return value for the method
	 *           true if the game should end
	 *           false if game needs to continue 
	 */

	public boolean checkEndConditions(){

		//the return value
		boolean retVal = false;
		try{
			//the number of each piece left
			int whitesGone = 0 , bluesGone = 0;

			//the board to work with
			Board temp = game.stateOfBoard();

			//go through all the spots on the board
			for( int i=1; i<temp.sizeOf(); i++ ){
				//if there is a piece there
				if( temp.occupied( i  ) ){
					//if its a blue piece there
					if( (temp.getPieceAt( i )).getColor() == Color.blue ){
						// increment number of blues
						bluesGone++;
						//if the piece is white
					}else if( (temp.getPieceAt( i )).getColor()
							== Color.white ){
						//increment number of whites
						whitesGone++;
					}
				}
			}//end of for loop

			//if either of the number are 0
			if( whitesGone == 0 || bluesGone == 0 ){
				retVal = true;
			}

		}catch( Exception e ){

			System.err.println( e.getMessage() );            

		}
		return retVal;

	}//checkEndConditions

}//checkerGUI.java
