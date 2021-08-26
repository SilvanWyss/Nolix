//package declaration
package ch.nolix.common.attributeapi.mutablemandatoryattributeapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.IInfoStringHolder;

//interface
/**
 * A {@link IMutableInfoStringHolder} is a {@link IInfoStringHolder} whose info string can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 20
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
