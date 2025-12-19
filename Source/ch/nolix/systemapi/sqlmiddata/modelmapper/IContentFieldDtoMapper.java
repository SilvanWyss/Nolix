package ch.nolix.systemapi.sqlmiddata.modelmapper;

import ch.nolix.systemapi.middata.model.FieldDto;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;

/**
 * @author Silvan Wyss
 */
public interface IContentFieldDtoMapper {
  /**
   * @param nullableStringRepresentedValue
   * @param nullableAdditionalValue
   * @param columnView
   * @return a new {@link FieldDto} from the given nullableStringRepresentedValue.
   */
  FieldDto mapNullableStringRepresentedValueToContentFieldDto(
    String nullableStringRepresentedValue,
    String nullableAdditionalValue,
    ColumnViewDto columnView);
}
