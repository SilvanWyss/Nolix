package ch.nolix.systemapi.noderawdataapi.modelmapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.rawdataapi.modelapi.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;

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

  /**
   * @param entityNode
   * @param tableView
   * @return new {ContentFieldDto}s from the given entityNode.
   * @throws RuntimeException if the given entityNode is null.
   */
  IContainer<ContentFieldDto<Object>> mapEntityNodeToContentFieldDtos(
    IMutableNode<?> entityNode,
    TableSchemaViewDto tableView);
}
