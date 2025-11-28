package ch.nolix.coreapi.sql.sqltool;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface ISqlLiteralMapper {
  /**
   * @param nullableValueString
   * @return a SQL literal for the given nullableValueString.
   */
  String mapNullableValueStringToSqlLiteral(String nullableValueString);
}
