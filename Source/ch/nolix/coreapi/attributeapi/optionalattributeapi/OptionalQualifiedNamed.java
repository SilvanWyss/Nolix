//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link OptionalQualifiedNamed} is a {@link Named} that can have a
 * qualifying prefix that completes the name of the
 * {@link OptionalQualifiedNamed} to a qualified name.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
@AllowDefaultMethodsAsDesignPattern
public interface OptionalQualifiedNamed extends Named {

  //method
  /**
   * @return the qualified name of the current {@link OptionalQualifiedNamed}.
   * @throws RuntimeException if the current {@link OptionalQualifiedNamed} does
   *                          not have a qualifying prefix.
   */
  default String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  //method
  /**
   * @return the qualified name of the current {@link OptionalQualifiedNamed} in
   *         quotes.
   * @throws RuntimeException if the current {@link OptionalQualifiedNamed} does
   *                          not have a qualifying prefix.
   */
  default String getQualifiedNameInQuotes() {
    return ("'" + getQualifiedName() + "'");
  }

  //method declaration
  /**
   * @return the qualifying prefix of the current {@link OptionalQualifiedNamed}.
   *         The qualifying prefix of a {@link OptionalQualifiedNamed} completes
   *         the name of the {@link OptionalQualifiedNamed} to a qualified name.
   * @throws RuntimeException if the current {@link OptionalQualifiedNamed} does
   *                          not have a qualifying prefix.
   */
  String getQualifyingPrefix();

  //method declaration
  /**
   * @return true if the current {@link OptionalQualifiedNamed} has a qualifying
   *         prefix, false otherwise.
   */
  boolean hasQualifyingPrefix();
}
