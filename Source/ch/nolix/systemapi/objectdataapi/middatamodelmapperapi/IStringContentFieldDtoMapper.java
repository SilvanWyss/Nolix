package ch.nolix.systemapi.objectdataapi.middatamodelmapperapi;

import ch.nolix.systemapi.middataapi.modelapi.StringContentFieldDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public interface IStringContentFieldDtoMapper {

  /**
   * @param field
   * @return a new {@link StringContentFieldDto} from the given field.
   * @throws RuntimeException if the given field is null.
   */
  StringContentFieldDto mapFieldToStringContentFieldDto(IField field);
}
