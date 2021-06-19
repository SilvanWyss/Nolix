//package declaration
package ch.nolix.common.attributeapi.mutablemandatoryattributeapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.ISubjectHolder;

//interface
/**
 * A {@link IMutableSubjectHolder} is a {@link ISubjectHolder} whose subject can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2021-06-15
 * @lines 20
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
