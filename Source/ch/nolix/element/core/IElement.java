//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.interfaces.TypeRequestable;
import ch.nolix.core.specificationInterfaces.Specified;

//interface
public interface IElement extends Specified, TypeRequestable {

	//method
	/**
	 * @return the type of this type element.
	 */
	default String getType() {
		return TypeRequestable.super.getType();
	}
}
