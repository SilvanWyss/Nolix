//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IOptionalQualifiedNameHolder} is a {@link INameHolder} that can have
 * a qualifying prefix that completes the name of the
 * {@link IOptionalQualifiedNameHolder} to a qualified name.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
@AllowDefaultMethodsAsDesignPattern
public interface IOptionalQualifiedNameHolder extends INameHolder {

  //method
  /**
   * @return the qualified name of the current
   *         {@link IOptionalQualifiedNameHolder}.
   * @throws RuntimeException if the current {@link IOptionalQualifiedNameHolder}
   *                          does not have a qualifying prefix.
   */
  default String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  //method
  /**
   * @return the qualified name of the current
   *         {@link IOptionalQualifiedNameHolder} in quotes.
   * @throws RuntimeException if the current {@link IOptionalQualifiedNameHolder}
   *                          does not have a qualifying prefix.
   */
  default String getQualifiedNameInQuotes() {
    return ("'" + getQualifiedName() + "'");
  }

  //method declaration
  /**
   * @return the qualifying prefix of the current
   *         {@link IOptionalQualifiedNameHolder}. The qualifying prefix of a
   *         {@link IOptionalQualifiedNameHolder} completes the name of the
   *         {@link IOptionalQualifiedNameHolder} to a qualified name.
   * @throws RuntimeException if the current {@link IOptionalQualifiedNameHolder}
   *                          does not have a qualifying prefix.
   */
  String getQualifyingPrefix();

  //method declaration
  /**
   * @return true if the current {@link IOptionalQualifiedNameHolder} has a
   *         qualifying prefix, false otherwise.
   */
  boolean hasQualifyingPrefix();
}
