//package declaration
package ch.nolix.system.sqldatabaserawdata.databasereader;

//own imports
import ch.nolix.system.sqldatabaserawdata.databasedto.LoadedContentFieldDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;

//class
public final class ContentFieldMapper {

  // constant
  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  // method
  public ILoadedContentFieldDto createContentFieldFromString(
      final String string,
      final IColumnInfo contentColumnDefinition) {
    return new LoadedContentFieldDto(
        contentColumnDefinition.getColumnName(),
        VALUE_MAPPER.createValueFromString(string, contentColumnDefinition));
  }
}
