/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.sql.sqltool;

/**
 * @author Silvan Wyss
 */
public interface ISqlLiteralMapper {
  /**
   * @param nullableValueString
   * @return a SQL literal for the given nullableValueString.
   */
  String mapNullableValueStringToSqlLiteral(String nullableValueString);
}
