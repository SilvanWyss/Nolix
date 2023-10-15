//package declaration
package ch.nolix.system.sqldatabaserawdata.databasedto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDto;

//class
public final class LoadedContentFieldDto implements ILoadedContentFieldDto {

  // attribute
  private final String columnName;

  // optional attribute
  private final Object value;

  // constructor
  public LoadedContentFieldDto(final String columnName) {

    if (columnName == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.COLUMN_NAME);
    }

    this.columnName = columnName;
    value = null;
  }

  // constructor
  public LoadedContentFieldDto(final String columnName, final Object value) {

    if (columnName == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.COLUMN_NAME);
    }

    if (value == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.VALUE);
    }

    this.columnName = columnName;
    this.value = value;
  }

  // method
  @Override
  public String getColumnName() {
    return columnName;
  }

  // method
  @Override
  public Object getValueOrNull() {
    return value;
  }
}
