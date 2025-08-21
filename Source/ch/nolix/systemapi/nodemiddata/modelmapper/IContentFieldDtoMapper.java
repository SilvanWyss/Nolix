package ch.nolix.systemapi.nodemiddata.modelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.middata.model.FieldDto;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-18
 */
public interface IContentFieldDtoMapper {

  /**
   * @param contentFieldNode
   * @param supportingColumnInfo
   * @return a new {@link FieldDto} from the given contentFieldNode.
   * @throws RuntimeException if the given contentFieldNode is null.
   * @throws RuntimeException if the given supportingColumnInfo is null.
   */
  FieldDto mapContentFieldNodeToContentFieldDto(
    INode<?> contentFieldNode,
    ColumnViewDto supportingColumnInfo);

  /**
   * @param entityNode
   * @param tableView
   * @return new {ContentFieldDto}s from the given entityNode.
   * @throws RuntimeException if the given entityNode is null.
   */
  IContainer<FieldDto> mapEntityNodeToContentFieldDtos(
    IMutableNode<?> entityNode,
    TableViewDto tableView);
}
