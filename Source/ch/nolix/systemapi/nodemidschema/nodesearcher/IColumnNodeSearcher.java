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
public interface IColumnNodeSearcher {
  IContainer<String> getBackReferenceableColumnIdsFromColumnNode(IMutableNode<?> columnNode);

  DataType getColumnDataTypeFromColumnNode(IMutableNode<?> columnNode);

  FieldType getColumnFieldTypeFromColumnNode(IMutableNode<?> columnNode);

  String getColumnIdFromColumnNode(IMutableNode<?> columnNode);

  String getColumnNameFromColumnNode(IMutableNode<?> columnNode);

  IContainer<String> getReferenceableTableIdsFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredBackReferenceableColumnIdsNodeFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredContentModelNodeFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredDataTypeNodeFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredFieldTypeNodeFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredIdNodeFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredNameNodeFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredReferenceableTableIdsNodeFromColumnNode(IMutableNode<?> contentModelNode);
}
