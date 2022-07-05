//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeuniversalapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.IInfoStringHolder;

//interface
/**
 * A {@link IMutableInfoStringHolder} is a {@link IInfoStringHolder} whose info string can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <S> is the type of a {@link IMutableInfoStringHolder}.
 */
public interface IMutableInfoStringHolder<S extends IMutableInfoStringHolder<S>> extends IInfoStringHolder {
	
	//method declaration
	/**
	 * Sets the info string of the current {@link IMutableInfoStringHolder}.
	 * 
	 * @param infoString
	 * @return the current {@link IMutableInfoStringHolder}.
	 */
	S setInfoString(String infoString);
}
