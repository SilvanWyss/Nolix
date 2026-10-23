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
public interface IContentModelNodeSearcher {
  /**
   * @param contentModelNode
   * @return the back referenceable column ids from the given contentModelNode
   */
  ExtendedIterable<String> getBackReferenceableColumnIdsFromContentModelNode(IMutableNode<?> contentModelNode);

  /**
   * @param contentModelNode
   * @return the {@link DataType} from the given contentModelNode
   */
  DataType getDataTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  /**
   * @param contentModelNode
   * @return the {@link FieldType} from the given contentModelNode
   */
  FieldType getFieldTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  /**
   * @param contentModelNode
   * @return the referenceable table ids from the given contentModelNode
   */
  ExtendedIterable<String> getReferenceableTableIdsFromContentModelNode(IMutableNode<?> contentModelNode);

  /**
   * @param contentModelNode
   * @return the back referenceable column ids node from the given
   *         contentModelNode
   */
  IMutableNode<?> getStoredBackReferenceableColumnIdsNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  /**
   * @param contentModelNode
   * @return the data type node from the given contentModelNode
   */
  IMutableNode<?> getStoredDataTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  /**
   * @param contentModelNode
   * @return the field type node from the given contentModelNode
   */
  IMutableNode<?> getStoredFieldTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  /**
   * @param contentModelNode
   * @return the referenceable table ids node from the given contentModelNode
   */
  IMutableNode<?> getStoredReferenceableTableIdsNodeFromContentModelNode(IMutableNode<?> contentModelNode);
}
