//package declaration
package ch.nolix.element.dialog;

//own import
import ch.nolix.common.specification.Configurable;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 20
 */
public interface IDialog
extends Configurable {

	//abstract method
	/**
	 * @return the root rectangle of this dialog.
	 */
	public abstract Rectangle<?, ?> getRefRootRectangle();
}
