//package declaration
package ch.nolix.element.baseGUI_API;

//own imports
import ch.nolix.common.mutableAttributeAPI.Titleble;
import ch.nolix.common.rasterAPI.Rectangular;
import ch.nolix.common.skillAPI.IRequestableContainer;
import ch.nolix.common.skillAPI.Closable;
import ch.nolix.common.skillAPI.Refreshable;
import ch.nolix.element.inputs.IInputTaker;

//interface
/**
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 140
 * @param <G> The type of a {@link IBaseGUI}.
 */
public interface IBaseGUI<G extends IBaseGUI<G>>
extends IInputTaker, IRequestableContainer, Closable, Rectangular, Refreshable, Titleble<G> {
	
	//method declaration
	/**
	 * A root {@link IBaseGUI} is a {@link IBaseGUI} that not contained in another {@link IBaseGUI}.
	 * 
	 * @return true if the current {@link IBaseGUI} is a root {@link IBaseGUI}.
	 */
	public abstract boolean isRootGUI();
	
	//method declaration
	/**
	 * 
	 * @return true if the current {@link IBaseGUI} is visible.
	 */
	public abstract boolean isVisible();
}
