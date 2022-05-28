//package declaration
package ch.nolix.systemapi.guiapi.baseapi.colorapi;

//Java imports
import java.awt.Color;

//own imports
import ch.nolix.systemapi.elementapi.IElement;

//interface
public interface IColor extends IElement<IColor> {
	
	//method declaration
	/**
	 * @return a new {@link java.awt.Color} from the current {@link IColor}.
	 */
	Color toSwingColor();
}
