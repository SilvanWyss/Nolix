//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ISubjectHolder;

//interface
/**
 * A {@link IMutableSubjectHolder} is a {@link ISubjectHolder} whose subject can
 * be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface IMutableSubjectHolder extends ISubjectHolder {

  // method declaration
  /**
   * Sets the subject of the current {@link IMutableSubjectHolder}.
   * 
   * @param subject
   * @throws RuntimeException if the given subject is null.
   * @throws RuntimeException if the given subject is blank.
   */
  void setSubject(String subject);
}
