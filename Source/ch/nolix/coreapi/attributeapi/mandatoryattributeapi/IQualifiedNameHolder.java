//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IQualifiedNameHolder} has a qualified name. The qualified name of a
 * {@link IQualifiedNameHolder} is its name headed by its qualifying prefix.
 * 
 * @author Silvan Wyss
 * @date 2024-01-19
 */
@AllowDefaultMethodsAsDesignPattern
public interface IQualifiedNameHolder extends INameHolder {

  //method
  /**
   * @return the qualified name of the current {@link IQualifiedNameHolder}.
   */
  default String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  //method declaration
  /**
   * @return the qualifying prefix of the current {@link IQualifiedNameHolder}.
   */
  String getQualifyingPrefix();
}
