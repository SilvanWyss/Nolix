package ch.nolix.systemapi.sqlmiddata.sqlmapper;

import ch.nolix.systemapi.middata.model.StringValueFieldDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface ISqlValueMapper {

  /**
   * @param stringValueFieldDto
   * @return a SQL value from the given stringContentFieldDto
   * @throws RuntimeException if the given stringContentFieldDto is not valid.
   */
  String mapStringContentFieldDtoToSqlValue(StringValueFieldDto stringValueFieldDto);
}
