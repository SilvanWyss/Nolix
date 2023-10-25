//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IQualifiedNameHolder} is a {@link Named} that has a qualifying prefix that
 * completes the name of the {@link IQualifiedNameHolder} to a qualified name.
 * 
 * @author Silvan Wyss
 * @date 2022-01-28
 */
@AllowDefaultMethodsAsDesignPattern
public interface IQualifiedNameHolder extends Named {

  //method
  /**
   * @return the qualified name of the current {@link IQualifiedNameHolder}.
   */
  default String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  //method
  /**
   * @return the qualified name of the current {@link IQualifiedNameHolder} in quotes.
   */
  default String getQualifiedNameInQuotes() {
    return ("'" + getQualifiedName() + "'");
  }

  //method declaration
  /**
   * @return the qualifying prefix of the current {@link IQualifiedNameHolder}. The
   *         qualifying prefix of a {@link IQualifiedNameHolder} completes the name of
   *         the {@link IQualifiedNameHolder} to a qualified name.
   */
  String getQualifyingPrefix();
}
