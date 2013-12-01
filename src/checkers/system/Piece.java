package checkers.system;
/**
 * Piece.java
 *
 * Version:
 *   $Id: Piece.java,v 1.1 2002/10/22 21:12:53 se362 Exp $
 *
 * Revisions:
 *   $Log: Piece.java,v $
 *   Revision 1.1  2002/10/22 21:12:53  se362
 *   Initial creation of case study
 *
 */

/**
 * This is an abstract class representing any piece that
 * know about it's color and possible moves and captures
 *
 * @author
 *
 */

import java.awt.Color;

public class Piece {
	
   private Color color; // the color of the piece
   public enum PieceType { SINGLE, KING };
   private PieceType type;
      
   /**
    * The constructor for this piece
    * 
    * @param c - the color for this piece
    */
   public Piece( PieceType type, Color c ) {

	   this.type = type;
	   color = c;
   }

   /**
    * The method which is abstract
    * 
    * @return the type of the piece
    */
   public PieceType getType() {
	   return type;
   }
   
   /**
    * This method returns the color of this piece
    * 
    * @return the color for this piece
    */
   public Color getColor() {
  
	   return color;
   }

}// Piece
