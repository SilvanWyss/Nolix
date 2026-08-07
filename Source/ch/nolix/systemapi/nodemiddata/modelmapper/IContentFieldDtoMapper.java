/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.modelmapper;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.middata.model.FieldDto;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IContentFieldDtoMapper {
  /**
   * @param contentFieldNode
   * @param columnView
   * @return a new {@link FieldDto} from the given contentFieldNode
   * @throws RuntimeException if the given contentFieldNode is null
   * @throws RuntimeException if the given columnView is null
   */
  FieldDto mapContentFieldNodeToContentFieldDto(
    Node<?> contentFieldNode,
    ColumnInfoDto columnView);

  /**
   * @param entityNode
   * @param tableView
   * @return new {ContentFieldDto}s from the given entityNode
   * @throws RuntimeException if the given entityNode is null
   */
  ExtendedIterable<FieldDto> mapEntityNodeToContentFieldDtos(
    IMutableNode<?> entityNode,
    TableInfoDto tableView);
}
