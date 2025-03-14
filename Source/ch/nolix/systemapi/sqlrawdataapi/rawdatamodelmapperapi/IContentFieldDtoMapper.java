package ch.nolix.systemapi.sqlrawdataapi.rawdatamodelmapperapi;

import ch.nolix.systemapi.rawdataapi.modelapi.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;

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
