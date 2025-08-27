package ch.nolix.system.sqlmiddata.modelmapper;

import ch.nolix.coreapi.datamodel.cardinality.BaseCardinality;
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

  //TODO: Re-engineer
  /**
   * {@inheritDoc}
   */
  @Override
  public FieldDto mapStringToContentFieldDtoUsingColumnView(
    final String string,
    final ColumnViewDto columnView) {

    final var columnName = columnView.name();
    final var fieldType = columnView.fieldType();
    final var cardinality = fieldType.getCardinality();
    final var baseCardinality = cardinality.getBaseCardinality();

    if (baseCardinality == BaseCardinality.SINGLE) {

      final var dataType = columnView.dataType();
      final var value = VALUE_MAPPER.mapStringToValue(string, dataType);

      return new FieldDto(columnName, value, null);
    }

    return new FieldDto(columnName, null, null);
  }
}
