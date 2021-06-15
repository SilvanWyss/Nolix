//package declaration
package ch.nolix.common.attributeapi.mandatoryattributeapi;

//interface
/**
 * A {@link ISubjectOwner} has a subject.
 * 
 * @author Silvan Wyss
 * @date 2021-06-15
 * @lines 50
 */
public interface ISubjectOwner {
	
	//method declaration
	/**
	 * @return the subject of the current {@link ISubjectOwner}.
	 */
	String getSubject();
	
	//method
	/**
	 * @return the subject of the current {@link ISubjectOwner} in quotes.
	 */
	default String getSubjectInQuotes() {
		return ("'" + getSubject() + "'");
	}
	
	//method
	/**
	 * @param subjectOwner
	 * @return true if the current {@link ISubjectOwner} has the same subject as the given subjectOwner.
	 */
	default boolean hasSameIndexAs(final ISubjectOwner subjectOwner) {
		
		//Handles the case that the given subjectOwner is null.
		if (subjectOwner == null) {
			return false;
		}
		
		//Handles the case that the given subjectOwner is not null.
		return hasSubject(subjectOwner.getSubject());
	}
	
	//method
	/**
	 * @param subject
	 * @return true if the current {@link ISubjectOwner} has the given subject.
	 */
	default boolean hasSubject(final String subject) {
		return getSubject().equals(subject);
	}
}
