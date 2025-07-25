package ch.nolix.systemapi.nodemiddataapi.nodeexaminerapi;

import ch.nolix.coreapi.document.node.IMutableNode;

public interface IEntityNodeExaminer {

  boolean entityNodeHasSaveStamp(IMutableNode<?> entityNode, String saveStamp);
}
