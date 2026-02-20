/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodeexaminer;

import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 */
public interface IEntityNodeExaminer {
  boolean entityNodeHasSaveStamp(IMutableNode<?> entityNode, String saveStamp);
}
