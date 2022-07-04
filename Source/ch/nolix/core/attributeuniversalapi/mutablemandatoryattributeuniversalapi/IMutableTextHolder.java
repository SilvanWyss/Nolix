//package declaration
package ch.nolix.core.attributeuniversalapi.mutablemandatoryattributeuniversalapi;

import ch.nolix.coreapi.attributeuniversalapi.mandatoryattributeuniversalapi.ITextHolder;

//interface
/**
 * A {@link IMutableTextHolder} is a {@link ITextHolder} whose text can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2021-06-19
 * @param <MTH> is the type of a {@link IMutableTextHolder}.
 */
public interface IMutableTextHolder<MTH extends IMutableTextHolder<MTH>> extends ITextHolder {
	
	//method declaration
	/**
	 * Sets the text of the current {@link IMutableTextHolder}.
	 * 
	 * @param text
	 * @return the current {@link IMutableTextHolder}.
	 */
	MTH setText(String text);
}
