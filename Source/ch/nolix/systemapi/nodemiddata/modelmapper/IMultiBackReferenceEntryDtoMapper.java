/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.modelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;

/**
 * @author Silvan Wyss
 */
public interface IMultiBackReferenceEntryDtoMapper {
  /**
   * @param tableName
   * @param entityId
   * @param multiBackReferenceColumnId
   * @param multiBackReferenceEntryNode
   * @return a new {@link MultiBackReferenceEntryDto} from the given tableName,
   *         entityId, multiBackReferenceColumnId and multiBackReferenceEntryNode.
   */
  MultiBackReferenceEntryDto mapMultiBackReferenceEntryNodeToMultiBackReferenceEntryDto(
    String tableName,
    String entityId,
    String multiBackReferenceColumnId,
    INode<?> multiBackReferenceEntryNode);

  /**
   * @param tableName
   * @param entityId
   * @param multiBackReferenceColumnId
   * @param multiBackReferenceNode
   * @return new {@link MultiBackReferenceEntryDto}s from the given given
   *         tableName, entityId, multiBackReferenceColumnId and
   *         multiBackReferenceNode.
   */
  IContainer<MultiBackReferenceEntryDto> mapMultiBackReferenceNodeToMultiBackReferenceEntryDtos(
    String tableName,
    String entityId,
    String multiBackReferenceColumnId,
    INode<?> multiBackReferenceNode);
}
