package ch.nolix.systemapi.noderawdataapi.modelmapperapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.rawdataapi.modelapi.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;

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
    ColumnSchemaViewDto supportingColumnInfo);
}
