//package declaration
package ch.nolix.system.sqlrawdatabase.databasedto;

import java.util.Optional;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDto;

//class
public final class LoadedContentFieldDto implements ILoadedContentFieldDto {

  //attribute
  private final String columnName;

  //optional attribute
  private final Object value;

  //constructor
  public LoadedContentFieldDto(final String columnName) {

    if (columnName == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.COLUMN_NAME);
    }

    this.columnName = columnName;
    value = null;
  }

  //constructor
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

  //method
  @Override
  public String getColumnName() {
    return columnName;
  }

  //method
  @Override
  public Optional<Object> getOptionalValue() {
    return Optional.ofNullable(value);
  }
}
