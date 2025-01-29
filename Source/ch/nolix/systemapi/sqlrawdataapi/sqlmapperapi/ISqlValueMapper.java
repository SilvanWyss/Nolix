package ch.nolix.systemapi.sqlrawdataapi.sqlmapperapi;

import ch.nolix.systemapi.rawdataapi.modelapi.StringContentFieldDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface ISqlValueMapper {

  /**
   * @param stringContentFieldDto
   * @return a SQL value from the given stringContentFieldDto
   * @throws RuntimeException if the given stringContentFieldDto is not valid.
   */
  String mapStringContentFieldDtoToSqlValue(StringContentFieldDto stringContentFieldDto);
}
