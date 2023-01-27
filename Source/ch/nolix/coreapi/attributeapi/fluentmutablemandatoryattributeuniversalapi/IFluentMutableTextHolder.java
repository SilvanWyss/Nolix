//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.ITextHolder;

//interface
/**
 * A {@link IFluentMutableTextHolder} is a {@link ITextHolder} whose text can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2021-06-19
 * @param <MTH> is the type of a {@link IFluentMutableTextHolder}.
 */
public interface IFluentMutableTextHolder<MTH extends IFluentMutableTextHolder<MTH>> extends ITextHolder {
	
	//method declaration
	/**
	 * Sets the text of the current {@link IFluentMutableTextHolder}.
	 * 
	 * @param text
	 * @return the current {@link IFluentMutableTextHolder}.
	 */
	MTH setText(String text);
}
