/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.schemareader;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public interface ISchemaReaderHelper {
  IContainer<ColumnDto> loadColumnsFromTableNode(IMutableNode<?> tableNode);

  TableDto loadTableFromTableNode(IMutableNode<?> tableNode);
}
