//package declaration
package ch.nolix.common.attributeapi.mutablemandatoryattributeapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.ISubjectOwner;

//interface
/**
 * A {@link IMutableSubjectOwner} is a {@link ISubjectOwner} whose subject can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2021-06-15
 * @lines 20
 * @param <MSO> is the type of a {@link IMutableSubjectOwner}.
 */
public interface IMutableSubjectOwner<MSO extends IMutableSubjectOwner<MSO>> extends ISubjectOwner {
	
	//method declaration
	/**
	 * Sets the subject of the current {@link IMutableSubjectOwner}.
	 * 
	 * @param subject
	 * @return the current {@link IMutableSubjectOwner}.
	 */
	MSO setSubject(String subject);
}
