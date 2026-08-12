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
  ExtendedIterable<String> getBackReferenceableColumnIdsFromContentModelNode(IMutableNode<?> contentModelNode);

  DataType getDataTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  FieldType getFieldTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  ExtendedIterable<String> getReferenceableTableIdsFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredBackReferenceableColumnIdsNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredDataTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredFieldTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredReferenceableTableIdsNodeFromContentModelNode(IMutableNode<?> contentModelNode);
}
