//package declaration
package ch.nolix.system.sqlrawdata.datadto;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;

//class
public final class ContentFieldDto implements IContentFieldDto {

  //attribute
  private final String columnName;

  //optional attribute
  private final String valueAsString;

  //constructor
  public ContentFieldDto(final String columnName) {

    if (columnName == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.COLUMN_NAME);
    }

    this.columnName = columnName;
    valueAsString = null;
  }

  //constructor
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

  //method
  @Override
  public String getColumnName() {
    return columnName;
  }

  //method
  @Override
  public Optional<String> getOptionalValueAsString() {
    return Optional.ofNullable(valueAsString);
  }
}
