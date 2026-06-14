/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.datamodel.fieldproperty.DataType;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
public interface IContentModelNodeSearcher {
  IWellOrderContainer<String> getBackReferenceableColumnIdsFromContentModelNode(IMutableNode<?> contentModelNode);

  DataType getDataTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  FieldType getFieldTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  IWellOrderContainer<String> getReferenceableTableIdsFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredBackReferenceableColumnIdsNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredDataTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredFieldTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredReferenceableTableIdsNodeFromContentModelNode(IMutableNode<?> contentModelNode);
}
