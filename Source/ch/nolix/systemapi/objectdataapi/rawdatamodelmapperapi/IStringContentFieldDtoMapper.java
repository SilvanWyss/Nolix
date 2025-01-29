package ch.nolix.systemapi.objectdataapi.rawdatamodelmapperapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.rawdataapi.modelapi.StringContentFieldDto;

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
