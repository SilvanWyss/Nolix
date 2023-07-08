//package declaration
package ch.nolix.systemapi.elementapi.styleapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.mainapi.Specified;

//interface
/**
 * A {@link IStyle} can style {@link IStylableElement}s.
 * 
 * @author Silvan Wyss
 * @date 2022-05-29
 */
public interface IStyle extends Specified {
	
	//method declaration
	/**
	 * @return the attaching attributes of the current {@link IStyle}.
	 */
	IContainer<? extends INode<?>> getAttachingAttributes();
	
	//method declaration
	/**
	 * Lets the current {@link IStyle} style the given element.
	 * 
	 * @param element
	 */
	void styleElement(IStylableElement<?> element);
}
