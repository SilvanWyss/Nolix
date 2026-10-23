/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
public interface IColumnNodeSearcher {
  /**
   * @param columnNode
   * @return the back referenceable column ids from the given columnNode
   */
  ExtendedIterable<String> getBackReferenceableColumnIdsFromColumnNode(IMutableNode<?> columnNode);

  /**
   * @param columnNode
   * @return the {@link DataType} from the given columnNode
   */
  DataType getColumnDataTypeFromColumnNode(IMutableNode<?> columnNode);

  /**
   * @param columnNode
   * @return the {@link FieldType} from the given columnNode
   */
  FieldType getColumnFieldTypeFromColumnNode(IMutableNode<?> columnNode);

  /**
   * @param columnNode
   * @return the column id from the given columnNode
   */
  String getColumnIdFromColumnNode(IMutableNode<?> columnNode);

  /**
   * @param columnNode
   * @return the column name from the given columnNode
   */
  String getColumnNameFromColumnNode(IMutableNode<?> columnNode);

  /**
   * @param columnNode
   * @return the referenceable table ids from the given columnNode
   */
  ExtendedIterable<String> getReferenceableTableIdsFromColumnNode(IMutableNode<?> columnNode);

  /**
   * @param columnNode
   * @return the back referenceable column ids node from the given columnNode
   */
  IMutableNode<?> getStoredBackReferenceableColumnIdsNodeFromColumnNode(IMutableNode<?> columnNode);

  /**
   * @param columnNode
   * @return the content model node from the given columnNode
   */
  IMutableNode<?> getStoredContentModelNodeFromColumnNode(IMutableNode<?> columnNode);

  /**
   * @param columnNode
   * @return the data type node from the given columnNode
   */
  IMutableNode<?> getStoredDataTypeNodeFromColumnNode(IMutableNode<?> columnNode);

  /**
   * @param columnNode
   * @return the field type node from the given columnNode
   */
  IMutableNode<?> getStoredFieldTypeNodeFromColumnNode(IMutableNode<?> columnNode);

  /**
   * @param columnNode
   * @return the id node from the given columnNode
   */
  IMutableNode<?> getStoredIdNodeFromColumnNode(IMutableNode<?> columnNode);

  /**
   * @param columnNode
   * @return the name node from the given columnNode
   */
  IMutableNode<?> getStoredNameNodeFromColumnNode(IMutableNode<?> columnNode);

  /**
   * @param contentModelNode
   * @return the referenceable table ids node from the given contentModelNode
   */
  IMutableNode<?> getStoredReferenceableTableIdsNodeFromColumnNode(IMutableNode<?> contentModelNode);
}
