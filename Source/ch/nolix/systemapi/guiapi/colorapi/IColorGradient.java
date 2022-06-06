//package declaration
package ch.nolix.systemapi.guiapi.colorapi;

//Java imports
import java.awt.GradientPaint;

import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;
import ch.nolix.systemapi.guiapi.structureproperty.DirectionInRectangle;

//interface
public interface IColorGradient extends Specified {
	
	//method declaration
	/**
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 * @return a new Swing {@link GradientPaint} of the current {@link IColorGradient} with
	 * the given position and size.
	 */
	GradientPaint createSwingGradientPaint(int xPosition, int yPosition, int width, int height);
	
	//method declaration
	/**
	 * @return the color1 of the current {@link IColorGradient}.
	 */
	IColor getColor1();
	
	//method declaration
	/**
	 * @return the color2 of the current {@link IColorGradient}.
	 */
	IColor getColor2();
	
	//method declaration
	/**
	 * @return the direction of the current {@link IColorGradient}.
	 */
	DirectionInRectangle getDirection();
}
