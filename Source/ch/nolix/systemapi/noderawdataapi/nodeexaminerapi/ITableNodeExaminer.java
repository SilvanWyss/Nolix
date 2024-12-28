package ch.nolix.systemapi.noderawdataapi.nodeexaminerapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface ITableNodeExaminer {

  boolean tableNodeContainsEntityNodeWithFieldAtGiven1BasedIndexWithGivenValueIgnoringGivenEntities(
    IMutableNode<?> tableNode,
    int param1BasedIndex,
    String value,
    IContainer<String> entitiesToIgnoreIds);

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
