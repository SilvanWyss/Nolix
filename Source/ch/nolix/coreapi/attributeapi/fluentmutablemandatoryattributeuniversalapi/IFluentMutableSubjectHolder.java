//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.ISubjectHolder;

//interface
/**
 * A {@link IFluentMutableSubjectHolder} is a {@link ISubjectHolder} whose subject can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2021-06-15
 * @param <MSO> is the type of a {@link IFluentMutableSubjectHolder}.
 */
public interface IFluentMutableSubjectHolder<MSO extends IFluentMutableSubjectHolder<MSO>> extends ISubjectHolder {
	
	//method declaration
	/**
	 * Sets the subject of the current {@link IFluentMutableSubjectHolder}.
	 * 
	 * @param subject
	 * @return the current {@link IFluentMutableSubjectHolder}.
	 */
	MSO setSubject(String subject);
}
