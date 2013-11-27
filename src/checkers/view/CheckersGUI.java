package checkers.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import checkers.system.CheckersGame;

@SuppressWarnings("serial")
public class CheckersGUI extends JFrame implements ActionListener {

	private static final String FIRST_SCREEN = "first";
	private static final String SECOND_SCREEN = "second";
	private static final String GAME_SCREEN = "game";
	private CheckersGame game;
	private JPanel screens;
	private FirstScreen firstScreen;
	private SecondScreen secondScreen;
	private GameScreen gameScreen;
	
	public CheckersGUI(CheckersGame game) {
		super();
		this.game = game;
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		
		initComponents();
		layoutComponents();
		
		pack();
		setVisible(true);
	}
	
	private void initComponents() {
		screens = new JPanel(new MyCardLayout());
		firstScreen = new FirstScreen(game);
		setTitle(FirstScreen.TITLE);
		secondScreen = new SecondScreen(game);
		gameScreen = new GameScreen(game);
	}
	
	private void layoutComponents() {
		getContentPane().add(screens, BorderLayout.CENTER);
		screens.add(firstScreen, FIRST_SCREEN);
		firstScreen.addOKListener(this);
		firstScreen.addCancelListener(this);
		screens.add(secondScreen, SECOND_SCREEN);
		secondScreen.addOKListener(this);
		secondScreen.addCancelListener(this);
		screens.add(gameScreen, GAME_SCREEN);
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        MyCardLayout cl = (MyCardLayout) screens.getLayout();
        if (source == firstScreen.OKButton) {
        	cl.show(screens, SECOND_SCREEN);
        	pack();
        	setTitle(SecondScreen.TITLE);
        } else if (source == firstScreen.CancelButton) {
        	dispose();
        } else if (source == secondScreen.okButton) {
        	cl.show(screens, GAME_SCREEN);
        	pack();
        	setTitle(GameScreen.TITLE);
        } else if (source == secondScreen.cancelButton) {
        	cl.show(screens, FIRST_SCREEN);
        	pack();
        	setTitle(FirstScreen.TITLE);
        }
    }
	
	@Override
    public void dispose() {
    	if (getTitle().equals(GameScreen.TITLE)) {
    		game.pressQuit();
    	}
    	System.exit(0);
    }
    
	private static void createAndShowGUI() {
		CheckersGame game = new CheckersGame();
		new CheckersGUI(game);
	}
	
	/*
	 * The main method to play checkers
	 *
	 *@param args[] the command line arguments
	 * 
	 */

	public static void main( String args[] ){

		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createAndShowGUI();
			}
			
		});

	}
}
