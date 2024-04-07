//package declaration
package ch.nolix.system.sqlrawdata.schemainfo;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

//class
public record ColumnInfo(
String columnId,
String columnName,
FieldType columnPropertyType,
DataType columnDataType,
int columnIndexOnEntityNode)
implements IColumnInfo {

  //constructor
  //For a better performance, this implementation does not use all comfortable
  //methods.
  public ColumnInfo( //NOSONAR: This implementations checks the given arguments.
    final String columnId,
    final String columnName,
    final FieldType columnPropertyType,
    final DataType columnDataType,
    final int columnIndexOnEntityNode) {

    if (columnId == null) {
      throw ArgumentIsNullException.forArgumentName("column id");
    }

    if (columnName == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.COLUMN_NAME);
    }

    if (columnPropertyType == null) {
      throw ArgumentIsNullException.forArgumentName("column property type");
    }

    if (columnDataType == null) {
      throw ArgumentIsNullException.forArgumentName("column data type");
    }

    if (columnIndexOnEntityNode < 0) {
      throw NegativeArgumentException.forArgumentNameAndArgument(
        "column index on entity node",
        columnIndexOnEntityNode);
    }

    this.columnId = columnId;
    this.columnName = columnName;
    this.columnPropertyType = columnPropertyType;
    this.columnDataType = columnDataType;
    this.columnIndexOnEntityNode = columnIndexOnEntityNode;
  }

  //method
  @Override
  public DataType getColumnDataType() {
    return columnDataType;
  }

  //method
  @Override
  public FieldType getColumnFieldType() {
    return columnPropertyType;
  }

  //method
  @Override
  public String getColumnId() {
    return columnId;
  }

  @Override
  public int getColumnIndexOnEntityNode() {
    return columnIndexOnEntityNode;
  }

  //method
  @Override
  public String getColumnName() {
    return columnName;
  }
}
