package ch.nolix.systemapi.nodemiddata.nodeexaminer;

import ch.nolix.coreapi.document.node.IMutableNode;

public interface IEntityNodeExaminer {

  boolean entityNodeHasSaveStamp(IMutableNode<?> entityNode, String saveStamp);
}
