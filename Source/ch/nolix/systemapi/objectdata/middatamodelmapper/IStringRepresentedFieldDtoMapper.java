package ch.nolix.systemapi.objectdata.middatamodelmapper;

import ch.nolix.systemapi.middata.model.StringRepresentedFieldDto;
import ch.nolix.systemapi.objectdata.model.IField;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public interface IStringRepresentedFieldDtoMapper {

  /**
   * @param field
   * @return a new {@link StringRepresentedFieldDto} from the given field.
   * @throws RuntimeException if the given field is null.
   */
  StringRepresentedFieldDto mapFieldToStringRepresentedFieldDto(IField field);
}
