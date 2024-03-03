//package declaration
package ch.nolix.system.noderawdata.datareader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.sqlrawdata.datadto.LoadedContentFieldDto;
import ch.nolix.system.sqlrawdata.datareader.ValueMapper;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

//class
public final class ContentFieldDtoMapper {

  //constant
  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  //method
  public ILoadedContentFieldDto createContentFieldDtoFromContentFieldNode(
    final INode<?> contentFieldNode,
    final IColumnInfo columnInfo) {

    if (contentFieldNode.containsChildNodes()) {
      return new LoadedContentFieldDto(columnInfo.getColumnName());
    }

    if (!contentFieldNode.hasHeader()) {
      return new LoadedContentFieldDto(columnInfo.getColumnName());
    }

    return new LoadedContentFieldDto(
      columnInfo.getColumnName(),
      VALUE_MAPPER.createValueFromString(contentFieldNode.getHeader(), columnInfo));
  }
}
