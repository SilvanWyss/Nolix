package ch.nolix.systemapi.sqlmiddataapi.modelmapperapi;

import ch.nolix.systemapi.middataapi.modelapi.ContentFieldDto;
import ch.nolix.systemapi.middataapi.schemaviewmodel.ColumnSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-11
 */
public interface IContentFieldDtoMapper {

  /**
   * @param string
   * @param columnView
   * @return a new {@link ContentFieldDto} from the given string using the given
   *         columnView.
   */
  ContentFieldDto<Object> mapStringToContentFieldDtoUsingColumnView(String string, ColumnSchemaViewDto columnView);
}
