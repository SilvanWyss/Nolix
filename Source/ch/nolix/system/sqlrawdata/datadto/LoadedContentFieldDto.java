package ch.nolix.system.sqlrawdata.datadto;

import java.util.Optional;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDto;

public final class LoadedContentFieldDto implements ILoadedContentFieldDto {

  private final String columnName;

  private final Object value;

  public LoadedContentFieldDto(final String columnName) {

    if (columnName == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.COLUMN_NAME);
    }

    this.columnName = columnName;
    value = null;
  }

  public LoadedContentFieldDto(final String columnName, final Object value) {

    if (columnName == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.COLUMN_NAME);
    }

    if (value == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.VALUE);
    }

    this.columnName = columnName;
    this.value = value;
  }

  @Override
  public String getColumnName() {
    return columnName;
  }

  @Override
  public Optional<Object> getOptionalValue() {
    return Optional.ofNullable(value);
  }
}
