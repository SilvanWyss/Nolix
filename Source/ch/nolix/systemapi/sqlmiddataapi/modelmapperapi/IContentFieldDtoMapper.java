package ch.nolix.systemapi.sqlmiddataapi.modelmapperapi;

import ch.nolix.systemapi.middataapi.modelapi.ObjectValueFieldDto;
import ch.nolix.systemapi.middataapi.schemaviewapi.ColumnViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-11
 */
public interface IContentFieldDtoMapper {

  /**
   * @param string
   * @param columnView
   * @return a new {@link ObjectValueFieldDto} from the given string using the
   *         given columnView.
   */
  ObjectValueFieldDto mapStringToContentFieldDtoUsingColumnView(String string, ColumnViewDto columnView);
}
