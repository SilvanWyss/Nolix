package ch.nolix.systemapi.sqlmiddata.sqlmapper;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface ISqlValueMapper {
  /**
   * @param nullableValueString
   * @return a SQL {@link String} for the given nullableValueString.
   * @throws RuntimeException if the given nullableValueString is not valid.
   */
  String mapNullableValueStringToSqlValue(String nullableValueString);
}
