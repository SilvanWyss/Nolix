//package declaration
package ch.nolix.systemapi.elementapi.styleapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;
import ch.nolix.systemapi.elementapi.mainapi.Specified;

//interface
/**
 * A {@link IBaseStyle} can style {@link IStylableElement}s.
 * A {@link IBaseStyle} can distinguish if it would
 * style also the child elements of a given {@link IStylableElement}.
 * 
 * @author Silvan Wyss
 * @date 2023-07-09
 */
@AllowDefaultMethodsAsDesignPattern
public interface IBaseStyle extends Specified {
	
	//method declaration
	/**
	 * @return the attaching attributes of the current {@link IBaseStyle}.
	 */
	IContainer<? extends INode<?>> getAttachingAttributes();
	
	//method declaration
	/**
	 * @return true if the current {@link IBaseStyle} would
	 * select the child elements of a given {@link IStylableElement} to style.
	 */
	boolean selectsChildElements();
	
	//method
	/**
	 * @return true if the current {@link IBaseStyle} would not (!)
	 * select the child elements of a given {@link IStylableElement} to style.
	 */
	default boolean skipsChildElements() {
		return !selectsChildElements();
	}
	
	//method declaration
	/**
	 * Lets the current {@link IBaseStyle} style the given element.
	 * 
	 * @param element
	 */
	void styleElement(IStylableElement<?> element);
}
