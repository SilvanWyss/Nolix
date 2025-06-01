package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link IDatabaseNameHolder} has a database name.
 * 
 * @author Silvan Wyss
 * @version 2025-06-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface IDatabaseNameHolder {

  /**
   * @return the database name of the current {@link IDatabaseNameHolder}.
   */
  String getDatabaseName();

  /**
   * @return the database name of the current {@link IDatabaseNameHolder} in
   *         quotes.
   */
  default String getDatabaseNameInQuotes() {
    return ("'" + getDatabaseName() + "'");
  }
}
