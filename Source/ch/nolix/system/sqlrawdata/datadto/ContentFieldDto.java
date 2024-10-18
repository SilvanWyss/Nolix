package ch.nolix.system.sqlrawdata.datadto;

import java.util.Optional;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

public final class ContentFieldDto implements IContentFieldDto {

  private final String columnName;

  private final String valueAsString;

  public ContentFieldDto(final String columnName) {

    if (columnName == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.COLUMN_NAME);
    }

    this.columnName = columnName;
    valueAsString = null;
  }

  public ContentFieldDto(final String columnName, final String valueAsString) {

    if (columnName == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.COLUMN_NAME);
    }

    if (valueAsString == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.VALUE);
    }

    this.columnName = columnName;
    this.valueAsString = valueAsString;
  }

  @Override
  public String getColumnName() {
    return columnName;
  }

  @Override
  public Optional<String> getOptionalValueAsString() {
    return Optional.ofNullable(valueAsString);
  }
}
