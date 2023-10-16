//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ISubjectHolder;

//interface
/**
 * A {@link IMutableOptionalSubjectHolder} is a {@link ISubjectHolder} whose
 * subject can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface IMutableOptionalSubjectHolder extends ISubjectHolder {

  //method declaration
  /**
   * Removes the subject of the current {@link IMutableOptionalSubjectHolder}.
   */
  void removeSubject();

  //method declaration
  /**
   * Sets the subject of the current {@link IMutableOptionalSubjectHolder}.
   * 
   * @param subject
   * @throws RuntimeException if the given subject is null.
   * @throws RuntimeException if the given subject is blank.
   */
  void setSubject(String subject);
}
