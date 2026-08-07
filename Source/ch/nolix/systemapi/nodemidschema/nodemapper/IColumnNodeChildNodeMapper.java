/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.nodemapper;

import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.midschema.model.ColumnDto;

/**
 * @author Silvan Wyss
 */
public interface IColumnNodeChildNodeMapper {
  /**
   * @param columnDto
   * @return a new back referenceable column ids {@link Node} from the given
   *         columnDto
   * @throws RuntimeException if the given columnDto is null
   */
  Node<?> mapColumnDtoToBackReferenceableColumnIdsNode(ColumnDto columnDto);

  /**
   * @param columnDto
   * @return a new data type {@link Node} from the given columnDto
   * @throws RuntimeException if the given columnDto is null
   */
  Node<?> mapColumnDtoToDataTypeNode(ColumnDto columnDto);

  /**
   * @param columnDto
   * @return a new field type {@link Node} from the given columnDto
   * @throws RuntimeException if the given columnDto is null
   */
  Node<?> mapColumnDtoToFieldTypeNode(ColumnDto columnDto);

  /**
   * @param columnDto
   * @return a new id {@link Node} from the given columnDto
   * @throws RuntimeException if the given columnDto is null
   */
  Node<?> mapColumnDtoToIdNode(ColumnDto columnDto);

  /**
   * @param columnDto
   * @return a new name {@link Node} from the given columnDto
   * @throws RuntimeException if the given columnDto is null
   */
  Node<?> mapColumnDtoToNameNode(ColumnDto columnDto);

  /**
   * @param columnDto
   * @return a new referenceable table ids node {@link Node} from the given
   *         columnDto
   * @throws RuntimeException if the given columnDto is null
   */
  Node<?> mapColumnDtoToReferenceableTableIdsNode(ColumnDto columnDto);
}
