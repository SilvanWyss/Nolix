package ch.nolix.systemapi.nodemiddataapi.modelmapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.middataapi.modelapi.ObjectValueFieldDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.ColumnViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-18
 */
public interface IContentFieldDtoMapper {

  /**
   * @param contentFieldNode
   * @param supportingColumnInfo
   * @return a new {@link ObjectValueFieldDto} from the given contentFieldNode.
   * @throws RuntimeException if the given contentFieldNode is null.
   * @throws RuntimeException if the given supportingColumnInfo is null.
   */
  ObjectValueFieldDto mapContentFieldNodeToContentFieldDto(
    INode<?> contentFieldNode,
    ColumnViewDto supportingColumnInfo);

  /**
   * @param entityNode
   * @param tableView
   * @return new {ContentFieldDto}s from the given entityNode.
   * @throws RuntimeException if the given entityNode is null.
   */
  IContainer<ObjectValueFieldDto> mapEntityNodeToContentFieldDtos(
    IMutableNode<?> entityNode,
    TableViewDto tableView);
}
