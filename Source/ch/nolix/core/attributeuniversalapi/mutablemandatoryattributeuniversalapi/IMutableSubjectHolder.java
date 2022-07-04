//package declaration
package ch.nolix.core.attributeuniversalapi.mutablemandatoryattributeuniversalapi;

import ch.nolix.coreapi.attributeuniversalapi.mandatoryattributeuniversalapi.ISubjectHolder;

//interface
/**
 * A {@link IMutableSubjectHolder} is a {@link ISubjectHolder} whose subject can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2021-06-15
 * @param <MSO> is the type of a {@link IMutableSubjectHolder}.
 */
public interface IMutableSubjectHolder<MSO extends IMutableSubjectHolder<MSO>> extends ISubjectHolder {
	
	//method declaration
	/**
	 * Sets the subject of the current {@link IMutableSubjectHolder}.
	 * 
	 * @param subject
	 * @return the current {@link IMutableSubjectHolder}.
	 */
	MSO setSubject(String subject);
}
