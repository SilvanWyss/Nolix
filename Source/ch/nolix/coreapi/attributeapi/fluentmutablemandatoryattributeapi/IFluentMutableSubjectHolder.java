//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ISubjectHolder;

//interface
/**
 * A {@link IFluentMutableSubjectHolder} is a {@link ISubjectHolder} whose
 * subject can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2021-06-15
 * @param <FMSH> is the type of a {@link IFluentMutableSubjectHolder}.
 */
public interface IFluentMutableSubjectHolder<FMSH extends IFluentMutableSubjectHolder<FMSH>> extends ISubjectHolder {

  // method declaration
  /**
   * Sets the subject of the current {@link IFluentMutableSubjectHolder}.
   * 
   * @param subject
   * @return the current {@link IFluentMutableSubjectHolder}.
   * @throws RuntimeException if the given subject is null.
   * @throws RuntimeException if the given subject is blank.
   */
  FMSH setSubject(String subject);
}
