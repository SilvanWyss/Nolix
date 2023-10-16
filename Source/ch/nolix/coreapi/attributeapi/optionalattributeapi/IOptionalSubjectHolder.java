//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link IOptionalSubjectHolder} can have a subject.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
public interface IOptionalSubjectHolder {

  //method declaration
  /**
   * @return the subject of the current {@link IOptionalSubjectHolder}.
   * @throws RuntimeException if the current {@link IOptionalSubjectHolder} does
   *                          not have a subject.
   */
  String getSubject();

  //method declaration
  /**
   * @return the subject of the current {@link IOptionalSubjectHolder} if it has a
   *         subject, otherwise an empty {@link String}.
   */
  String getSubjectOrEmptyString();

  //method declaration
  /**
   * @return true if the current {@link IOptionalSubjectHolder} has a subject.
   */
  boolean hasSubject();

  //method
  /**
   * @param subject
   * @return true if the current {@link IOptionalSubjectHolder} has the given
   *         subject.
   */
  boolean hasSubject(String subject);
}
