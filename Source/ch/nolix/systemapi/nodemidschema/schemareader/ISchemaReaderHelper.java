/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.schemareader;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public interface ISchemaReaderHelper {
  ExtendedIterable<ColumnDto> loadColumnsFromTableNode(IMutableNode<?> tableNode);

  TableDto loadTableFromTableNode(IMutableNode<?> tableNode);
}
