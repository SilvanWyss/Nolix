package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link IQualifiedNameHolder} has a qualified name. The qualified name of a
 * {@link IQualifiedNameHolder} is its name headed by its qualifying prefix.
 * 
 * @author Silvan Wyss
 * @version 2024-01-19
 */
@AllowDefaultMethodsAsDesignPattern
public interface IQualifiedNameHolder extends INameHolder {

  /**
   * @return the qualified name of the current {@link IQualifiedNameHolder}.
   */
  default String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  /**
   * @return the qualifying prefix of the current {@link IQualifiedNameHolder}.
   */
  String getQualifyingPrefix();
}
