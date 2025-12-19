package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link IDatabaseNameHolder} has a database name.
 * 
 * @author Silvan Wyss
 */
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
