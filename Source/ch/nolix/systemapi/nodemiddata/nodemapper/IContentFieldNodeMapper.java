/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodemapper;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;

/**
 * @author Silvan Wyss
 */
public interface IContentFieldNodeMapper {
  /**
   * @param valueStringFieldDto
   * @return a new content field node from the given valueStringFieldDto.
   * @throws RuntimeException if the given valueStringFieldDto is null.
   */
  INode<?> mapValueStringFieldDtoToContentFieldNode(ValueStringFieldDto valueStringFieldDto);
}
