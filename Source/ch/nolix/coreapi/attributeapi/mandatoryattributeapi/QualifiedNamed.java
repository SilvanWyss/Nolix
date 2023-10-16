//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link QualifiedNamed} is a {@link Named} that has a qualifying prefix that
 * completes the name of the {@link QualifiedNamed} to a qualified name.
 * 
 * @author Silvan Wyss
 * @date 2022-01-28
 */
@AllowDefaultMethodsAsDesignPattern
public interface QualifiedNamed extends Named {

  //method
  /**
   * @return the qualified name of the current {@link QualifiedNamed}.
   */
  default String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  //method
  /**
   * @return the qualified name of the current {@link QualifiedNamed} in quotes.
   */
  default String getQualifiedNameInQuotes() {
    return ("'" + getQualifiedName() + "'");
  }

  //method declaration
  /**
   * @return the qualifying prefix of the current {@link QualifiedNamed}. The
   *         qualifying prefix of a {@link QualifiedNamed} completes the name of
   *         the {@link QualifiedNamed} to a qualified name.
   */
  String getQualifyingPrefix();
}
