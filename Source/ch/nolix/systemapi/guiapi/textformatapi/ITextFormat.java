//package declaration
package ch.nolix.systemapi.guiapi.textformatapi;

//Java imports
import java.awt.Graphics;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//interface
public interface ITextFormat extends Specified {
	
	//method declaration
	/**
	 * @param text
	 * @return the width of the given text when the current {@link TextFormat} would be applied to it.
	 */
	int getSwingTextWidth(String text);
	
	//method declaration
	/**
	 * Lets the current {@link ITextFormat} paint the given text at the given position using the given graphics.
	 * 
	 * @param graphics
	 * @param xPosition
	 * @param yPosition
	 * @param text
	 */
	void paintSwingText(Graphics graphics, int xPosition, int yPosition, String text);
	
	//method declaration
	/**
	 * Lets the current {@link TextFormat} paint the given text at the given position using the given graphics.
	 * Will paint only the first part of the given text, that is not longer than the given maxWidth.
	 * 
	 * @param graphics
	 * @param xPosition
	 * @param yPosition
	 * @param maxWidth
	 * @param text
	 * @throws NegativeArgumentException if the given maxWidth is negative.
	 */
	void paintSwingText(Graphics graphics, int xPosition, int yPosition, String text, int maxWidth);
	
	//method declaration
	/**
	 * Lets the current {@link ITextFormat} paint the given text using the given graphics.
	 * 
	 * @param graphics
	 * @param text
	 */
	void paintSwingText(Graphics graphics, String text);

	//method declaration
	/**
	 * Lets the current {@link ITextFormat} paint the given text using the given graphics.
	 * Will paint only the first part of the given text, that is not longer than the given maxWidth.
	 * 
	 * @param graphics
	 * @param text
	 * @param maxWidth
	 */
	void paintSwingText(Graphics graphics, String text, int maxWidth);
}
