//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link ShortDescripted} has a short description.
 * 
 * A short description is a {@link String} representation of an object, that can
 * identify the object, but is mostly shorter than the object's common
 * {@link String} representation.
 * 
 * Example 1: There is given a person (surname: Peter, name: Muster, date of
 * birth: 01.01.2000, hair color: brown, hobby: fishing). -{@link String}
 * representation: "Peter, Muster, 2000-01-01, brown, fishing" -short
 * description: "Peter Muster, 2001-01-01"
 * 
 * Example 2: There is given a package (index: 25000, content: HelloWorld).
 * -{@link String} representation: "25000, HelloWorld" -short description:
 * "25000"
 * 
 * @author Silvan Wyss
 * @date 2018-12-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface ShortDescripted {

  // method declaration
  /**
   * @return the short description of the current {@link ShortDescripted}.
   */
  String getShortDescription();

  // method
  /**
   * @return the short description of the current {@link ShortDescripted} in
   *         quotes.
   */
  default String getShortDescriptionInQuotes() {
    return ("'" + getShortDescription() + "'");
  }
}
