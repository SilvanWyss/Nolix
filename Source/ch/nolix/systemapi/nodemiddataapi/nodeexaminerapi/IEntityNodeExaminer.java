package ch.nolix.systemapi.nodemiddataapi.nodeexaminerapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface IEntityNodeExaminer {

  boolean entityNodeHasSaveStamp(IMutableNode<?> entityNode, String saveStamp);
}
