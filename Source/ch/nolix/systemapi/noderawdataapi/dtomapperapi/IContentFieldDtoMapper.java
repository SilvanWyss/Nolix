package ch.nolix.systemapi.noderawdataapi.dtomapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.rawdataapi.model.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.ColumnViewDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-18
 */
public interface IContentFieldDtoMapper {

  /**
   * @param contentFieldNode
   * @param supportingColumnInfo
   * @return a new {@link ContentFieldDto} from the given contentFieldNode.
   * @throws RuntimeException if the given contentFieldNode is null.
   * @throws RuntimeException if the given supportingColumnInfo is null.
   */
  ContentFieldDto<Object> mapContentFieldNodeToContentFieldDto(
    INode<?> contentFieldNode,
    ColumnViewDto supportingColumnInfo);
}
