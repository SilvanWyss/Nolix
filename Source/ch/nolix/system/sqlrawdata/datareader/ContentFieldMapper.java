//package declaration
package ch.nolix.system.sqlrawdata.datareader;

import ch.nolix.system.sqlrawdata.datadto.LoadedContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

//class
public final class ContentFieldMapper {

  //constant
  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  //method
  public ILoadedContentFieldDto createContentFieldFromString(
    final String string,
    final IColumnInfo contentColumnDefinition) {
    return new LoadedContentFieldDto(
      contentColumnDefinition.getColumnName(),
      VALUE_MAPPER.createValueFromString(string, contentColumnDefinition));
  }
}