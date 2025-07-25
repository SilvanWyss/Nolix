package ch.nolix.systemapi.sqlmiddata.modelmapper;

import ch.nolix.systemapi.middata.model.ObjectValueFieldDto;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;

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
