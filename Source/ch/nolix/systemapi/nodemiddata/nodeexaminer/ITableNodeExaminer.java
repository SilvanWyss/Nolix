package ch.nolix.systemapi.nodemiddata.nodeexaminer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.IMutableNode;

public interface ITableNodeExaminer {
  boolean tableNodeContainsEntityNodeWithFieldAtGivenOneBasedIndexWithGivenValueIgnoringGivenEntities(
    IMutableNode<?> tableNode,
    int oneBasedIndex,
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
