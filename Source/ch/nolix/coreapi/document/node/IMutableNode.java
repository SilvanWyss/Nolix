package ch.nolix.coreapi.document.node;

import java.util.function.Predicate;

import ch.nolix.coreapi.attribute.fluentmutableoptionalattribute.IFluentMutableOptionalHeaderHolder;
import ch.nolix.coreapi.state.statemutation.Resettable;

/**
 * A {@link IMutableNode} is a {@link INode} that is mutable.
 * 
 * @author Silvan Wyss
 * @param <N> is the type of a {@link IMutableNode}.
 */
public interface IMutableNode<N extends IMutableNode<N>>
extends
INode<N>,
IFluentMutableOptionalHeaderHolder<N>,
Resettable {
  /**
   * Adds the given childNodes to the current {@link IMutableNode}.
   * 
   * @param childNode
   * @param childNodes
   * @return the current {@link IMutableNode}.
   * @throws RuntimeException if one of the given childNodes is null.
   */
  N addChildNode(INode<?> childNode, INode<?>... childNodes);

  /**
   * Adds the child {@link INode}s from the given strings the current
   * {@link IMutableNode}.
   * 
   * @param string
   * @param strings
   * @return the current {@link IMutableNode}.
   * @throws RuntimeException if one of the given strings does not represent a
   *                          {@link INode}.
   */
  N addChildNodeFromString(String string, String... strings);

  /**
   * Adds the given childNodes to the current {@link IMutableNode}.
   * 
   * @param childNodes
   * @return the current {@link IMutableNode}.
   * @param <N2> is the type of the given childNodes.
   * @throws RuntimeException if one of the given childNodes is null.
   */
  <N2 extends INode<?>> N addChildNodes(Iterable<N2> childNodes);

  /**
   * Adds the child {@link INode}s from the given strings the current
   * {@link IMutableNode}.
   * 
   * @param strings
   * @return the current {@link IMutableNode}.
   * @throws RuntimeException if one of the given strings does not represent a
   *                          {@link INode}.
   */
  N addChildNodesFromStrings(Iterable<String> strings);

  /**
   * Adds the given postfix at the end of the header of the current
   * {@link IMutableNode} if the current {@link IMutableNode} has a header. Sets
   * the given postfix as the header of the current {@link IMutableNode} if the
   * current {@link IMutableNode} does not have a header.
   * 
   * @param postfix
   * @return the current {@link IMutableNode}.
   * @throws RuntimeException if the given postfix is null.
   * @throws RuntimeException if the given postfix is blank.
   */
  N addPostfixToHeader(String postfix);

  /**
   * Adds the given prefix at the begin of the header of the current
   * {@link IMutableNode} if the current {@link IMutableNode} has a header. Sets
   * the given prefix as the header of the current {@link IMutableNode} if the
   * current {@link IMutableNode} does not have a header.
   * 
   * @param prefix
   * @return the current {@link IMutableNode}.
   * @throws RuntimeException if the given prefix is null.
   * @throws RuntimeException if the given prefix is blank.
   */
  N addPrefixToHeader(String prefix);

  /**
   * Removes and returns the first child {@link INode} the given selector selects
   * from the current {@link IMutableNode}.
   * 
   * @param selector
   * @return the first child {@link INode} the given selector selects from the
   *         current {@link IMutableNode}.
   * @throws RuntimeException if the current {@link IMutableNode} does not contain
   *                          a child {@link INode} the given selector selects.
   */
  N removeAndGetStoredFirstChildNodeThat(Predicate<INode<?>> selector);

  /**
   * Removes the child {@link INode}s from the current {@link IMutableNode}.
   */
  void removeChildNodes();

  /**
   * Removes the first child {@link INode} the given selector selects from the
   * current {@link IMutableNode}.
   * 
   * @param selector
   * @throws RuntimeException if the current {@link IMutableNode} does not contain
   *                          a child {@link INode} the given selector selects.
   */
  void removeFirstChildNodeThat(Predicate<INode<?>> selector);

  /**
   * Removes the first child {@link INode} with the given header from the current
   * {@link IMutableNode}.
   * 
   * @param header
   * @throws RuntimeException if the current {@link IMutableNode} does not contain
   *                          a child {@link INode} with the given header.
   */
  void removeFirstChildNodeWithHeader(String header);

  /**
   * Replaces the first child {@link INode} with the given header from the current
   * {@link IMutableNode} by the given {@link INode}.
   * 
   * @param header
   * @param node
   * @throws RuntimeException if the current {@link IMutableNode} does not contain
   *                          a child {@link INode} with the given header.
   */
  void replaceFirstChildNodeWithGivenHeaderByGivenNode(String header, INode<?> node);

  /**
   * Resets the current {@link IMutableNode} from the file with the given file
   * path.
   * 
   * @param filePath
   */
  void resetFromFile(String filePath);

  /**
   * Resets the current {@link IMutableNode} from the given node.
   * 
   * @param node
   * @throws RuntimeException if the given node is null.
   */
  void resetFromNode(INode<?> node);

  /**
   * Resets the current {@link IMutableNode} from the given string.
   * 
   * @param string
   * @throws RuntimeException if the given string does not represent a
   *                          {@link IMutableNode}.
   */
  void resetFromString(String string);

  /**
   * Resets the child {@link INode}s of the current {@link IMutableNode} with the
   * given childNodes.
   * 
   * @param childNodes
   * @return the current {@link IMutableNode}.
   * @throws RuntimeException if one of the given childNodes is null.
   */
  N setChildNodes(Iterable<? extends INode<?>> childNodes);
}
