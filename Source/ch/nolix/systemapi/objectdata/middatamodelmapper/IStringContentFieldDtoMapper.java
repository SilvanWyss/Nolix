package ch.nolix.systemapi.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.StringValueFieldDto;
import ch.nolix.systemapi.objectdata.model.IField;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public interface IStringContentFieldDtoMapper {

  /**
   * @param field
   * @return a new {@link StringValueFieldDto} from the given field.
   * @throws RuntimeException if the given field is null.
   */
  StringValueFieldDto mapFieldToStringContentFieldDto(IField field);
}
