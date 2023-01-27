//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.IInfoStringHolder;

//interface
/**
 * A {@link IFluentMutableInfoStringHolder} is a {@link IInfoStringHolder} whose info string can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <S> is the type of a {@link IFluentMutableInfoStringHolder}.
 */
public interface IFluentMutableInfoStringHolder<S extends IFluentMutableInfoStringHolder<S>> extends IInfoStringHolder {
	
	//method declaration
	/**
	 * Sets the info string of the current {@link IFluentMutableInfoStringHolder}.
	 * 
	 * @param infoString
	 * @return the current {@link IFluentMutableInfoStringHolder}.
	 */
	S setInfoString(String infoString);
}
