//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.ISubjectHolder;

//interface
/**
 * A {@link IFluentMutableOptionalSubjectHolder} is a {@link ISubjectHolder} whose subject can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-06
 * @param <FMOSH> is the type of a {@link IFluentMutableOptionalSubjectHolder}.
 */
public interface IFluentMutableOptionalSubjectHolder<FMOSH extends IFluentMutableOptionalSubjectHolder<FMOSH>>
extends ISubjectHolder {
	
	//method declaration
	/**
	 * Removes the subject of the current {@link IFluentMutableOptionalSubjectHolder}.
	 * 
	 * @return the current {@link IFluentMutableOptionalSubjectHolder}.
	 */
	FMOSH removeSubject();
	
	//method declaration
	/**
	 * Sets the subject of the current {@link IFluentMutableOptionalSubjectHolder}.
	 * 
	 * @param subject
	 * @return the current {@link IFluentMutableOptionalSubjectHolder}.
	 * @throws RuntimeException if the given subject is null.
	 * @throws RuntimeException if the given subject is blank.
	 */
	FMOSH setName(String subject);
}
