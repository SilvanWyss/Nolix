//package declaration
package ch.nolix.common.attributeapi.mandatoryattributeapi;

//interface
/**
 * A {@link ISubjectHolder} has a subject.
 * 
 * @author Silvan Wyss
 * @date 2021-06-15
 * @lines 50
 */
public interface ISubjectHolder {
	
	//method declaration
	/**
	 * @return the subject of the current {@link ISubjectHolder}.
	 */
	String getSubject();
	
	//method
	/**
	 * @return the subject of the current {@link ISubjectHolder} in quotes.
	 */
	default String getSubjectInQuotes() {
		return ("'" + getSubject() + "'");
	}
	
	//method
	/**
	 * @param subjectHolder
	 * @return true if the current {@link ISubjectHolder} has the same subject as the given subjectOwner.
	 */
	default boolean hasSameIndexAs(final ISubjectHolder subjectHolder) {
		
		//Handles the case that the given subjectOwner is null.
		if (subjectHolder == null) {
			return false;
		}
		
		//Handles the case that the given subjectOwner is not null.
		return hasSubject(subjectHolder.getSubject());
	}
	
	//method
	/**
	 * @param subject
	 * @return true if the current {@link ISubjectHolder} has the given subject.
	 */
	default boolean hasSubject(final String subject) {
		return getSubject().equals(subject);
	}
}
