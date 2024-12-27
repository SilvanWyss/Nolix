package ch.nolix.systemapi.noderawdataapi.nodeexaminerapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface ITableNodeExaminer {

  boolean tableNodeContainsEntityNodeWithGivenId(IMutableNode<?> tableNode, String id);

  boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
    IMutableNode<?> tableNode,
    int valueIndex,
    String value);

  boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexHasGivenHeader(
    IMutableNode<?> tableNode,
    int index,
    String header);
}
