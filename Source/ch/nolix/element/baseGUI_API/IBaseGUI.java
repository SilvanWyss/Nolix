//package declaration
package ch.nolix.element.baseGUI_API;

//own imports
import ch.nolix.core.attributeAPI.Titleble;
import ch.nolix.core.rasterAPI.Rectangular;
import ch.nolix.core.skillAPI.IRequestableContainer;
import ch.nolix.core.skillAPI.OptionalClosable;
import ch.nolix.core.skillAPI.Refreshable;

//interface
/**
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 140
 * @param <G> The type of a {@link IBaseGUI}.
 */
public interface IBaseGUI<G extends IBaseGUI<G>>
extends IEventTaker, IRequestableContainer, OptionalClosable, Rectangular, Refreshable, Titleble<G> {
	
	//abstract method
	/**
	 * A root {@link IBaseGUI} is a {@link IBaseGUI} that not contained in another {@link IBaseGUI}.
	 * 
	 * @return true if the current {@link IBaseGUI} is a root {@link IBaseGUI}.
	 */
	public abstract boolean isRootGUI();
	
	//abstract method
	/**
	 * 
	 * @return true if the current {@link IBaseGUI} is visible.
	 */
	public abstract boolean isVisible();
}
