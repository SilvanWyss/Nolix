//package declaration
package ch.nolix.system.sqlrawdatabase.databasedto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;

//class
public final class ContentFieldDto implements IContentFieldDto {

  //attribute
  private final String columnName;

  //optional attribute
  private final String valueAsString;

  //constructor
  public ContentFieldDto(final String columnName) {

    if (columnName == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.COLUMN_NAME);
    }

    this.columnName = columnName;
    valueAsString = null;
  }

  //constructor
  public ContentFieldDto(final String columnName, final String valueAsString) {

    if (columnName == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.COLUMN_NAME);
    }

    if (valueAsString == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.VALUE);
    }

    this.columnName = columnName;
    this.valueAsString = valueAsString;
  }

  //method
  @Override
  public String getColumnName() {
    return columnName;
  }

  //method
  @Override
  public String getValueAsStringOrNull() {
    return valueAsString;
  }
}
