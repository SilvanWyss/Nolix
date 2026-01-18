/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
public interface IContentModelNodeSearcher {
  IContainer<String> getBackReferenceableColumnIdsFromContentModelNode(IMutableNode<?> contentModelNode);

  DataType getDataTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  FieldType getFieldTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  IContainer<String> getReferenceableTableIdsFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredBackReferenceableColumnIdsNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredDataTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredFieldTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredReferenceableTableIdsNodeFromContentModelNode(IMutableNode<?> contentModelNode);
}
