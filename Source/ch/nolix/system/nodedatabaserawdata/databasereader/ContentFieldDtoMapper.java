//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.sqldatabaserawdata.databasedto.LoadedContentFieldDto;
import ch.nolix.system.sqldatabaserawdata.databasereader.ValueMapper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;

//class
public final class ContentFieldDtoMapper {

  // constant
  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  // method
  public ILoadedContentFieldDto createContentFieldDtoFromContentFieldNode(
      final IMutableNode<?> contentFieldNode,
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
