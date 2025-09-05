package ch.nolix.system.sqlmiddata.modelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.middata.valuemapper.ValueMapper;
import ch.nolix.systemapi.middata.model.FieldDto;
import ch.nolix.systemapi.middata.valuemapper.IValueMapper;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.sqlmiddata.modelmapper.IContentFieldDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-01-11
 */
public final class ContentFieldDtoMapper implements IContentFieldDtoMapper {
  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public FieldDto mapNullableStringRepresentedValueToContentFieldDto(
    final String nullableStringRepresentedValue,
    final String nullableAdditionalValue,
    final ColumnViewDto columnView) {
    final var columnName = columnView.name();
    final var fieldType = columnView.fieldType();

    switch (fieldType) {
      case VALUE_FIELD:
        final var dataType = columnView.dataType();
        final var value = VALUE_MAPPER.mapStringToValue(nullableStringRepresentedValue, dataType);

        return new FieldDto(columnName, value, null);
      case REFERENCE, BACK_REFERENCE:
        final var dataType2 = columnView.dataType();
        final var value2 = VALUE_MAPPER.mapStringToValue(nullableStringRepresentedValue, dataType2);

        return new FieldDto(columnName, value2, nullableAdditionalValue);
      case OPTIONAL_VALUE_FIELD:
        if (nullableStringRepresentedValue != null) {
          final var dataType3 = columnView.dataType();
          final var value3 = VALUE_MAPPER.mapStringToValue(nullableStringRepresentedValue, dataType3);

          return new FieldDto(columnName, value3, null);
        }

        return new FieldDto(columnName, null, null);
      case OPTIONAL_REFERENCE, OPTIONAL_BACK_REFERENCE:
        if (nullableStringRepresentedValue != null) {
          final var dataType4 = columnView.dataType();
          final var value4 = VALUE_MAPPER.mapStringToValue(nullableStringRepresentedValue, dataType4);

          return new FieldDto(columnName, value4, nullableAdditionalValue);
        }

        return new FieldDto(columnName, null, null);
      case MULTI_VALUE_FIELD, MULTI_REFERENCE, MULTI_BACK_REFERENCE:
        return new FieldDto(columnName, null, null);
      default:
        throw InvalidArgumentException.forArgument(fieldType);
    }
  }
}
