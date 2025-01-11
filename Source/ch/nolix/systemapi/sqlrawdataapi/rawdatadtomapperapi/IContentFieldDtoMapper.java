package ch.nolix.systemapi.sqlrawdataapi.rawdatadtomapperapi;

import ch.nolix.systemapi.rawdataapi.model.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.ColumnViewDto;

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
  ContentFieldDto<Object> mapStringToContentFieldDtoUsingColumnView(String string, ColumnViewDto columnView);
}
