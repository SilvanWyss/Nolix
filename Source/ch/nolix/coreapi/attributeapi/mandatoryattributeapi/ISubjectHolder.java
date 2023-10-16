//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//interface
/**
 * A {@link ISubjectHolder} has a subject.
 * 
 * @author Silvan Wyss
 * @date 2021-06-15
 */
public interface ISubjectHolder {

  //method declaration
  /**
   * @return the subject of the current {@link ISubjectHolder}.
   */
  String getSubject();

  //method declaration
  /**
   * @return the subject of the current {@link ISubjectHolder} in quotes.
   */
  String getSubjectInQuotes();
}
