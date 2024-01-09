//package declaration
package ch.nolix.coreapi.documentapi.nodeapi;

//Java imports
import java.util.function.Predicate;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi.IFluentMutableOptionalHeaderHolder;

//interface
/**
 * A {@link IMutableNode} is a {@link INode} that is mutable.
 * 
 * @author Silvan Wyss
 * @date 2022-06-25
 * @param <MN> is the type of a {@link IMutableNode}.
 */
public interface IMutableNode<MN extends IMutableNode<MN>> extends INode<MN>, IFluentMutableOptionalHeaderHolder<MN> {

  //method declaration
  /**
   * Adds the given childNodes to the current {@link IMutableNode}.
   * 
   * @param childNode
   * @param childNodes
   * @return the current {@link IMutableNode}.
   * @throws RuntimeException if one of the given childNodes is null.
   */
  MN addChildNode(INode<?> childNode, INode<?>... childNodes);

  //method declaration
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
  MN addChildNodeFromString(String string, String... strings);

  //method declaration
  /**
   * Adds the given childNodes to the current {@link IMutableNode}.
   * 
   * @param childNodes
   * @return the current {@link IMutableNode}.
   * @param <N> is the type of the given childNodes.
   * @throws RuntimeException if one of the given childNodes is null.
   */
  <N extends INode<?>> MN addChildNodes(Iterable<N> childNodes);

  //method declaration
  /**
   * Adds the child {@link INode}s from the given strings the current
   * {@link IMutableNode}.
   * 
   * @param strings
   * @return the current {@link IMutableNode}.
   * @throws RuntimeException if one of the given strings does not represent a
   *                          {@link INode}.
   */
  MN addChildNodesFromStrings(Iterable<String> strings);

  //method declaration
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
  MN addPostfixToHeader(String postfix);

  //method declaration
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
  MN addPrefixToHeader(String prefix);

  //method declaration
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
  MN removeAndGetStoredFirstChildNodeThat(Predicate<INode<?>> selector);

  //method declaration
  /**
   * Removes the child {@link INode}s from the current {@link IMutableNode}.
   */
  void removeChildNodes();

  //method declaration
  /**
   * Removes the first child {@link INode} the given selector selects from the
   * current {@link IMutableNode}.
   * 
   * @param selector
   * @throws RuntimeException if the current {@link IMutableNode} does not contain
   *                          a child {@link INode} the given selector selects.
   */
  void removeFirstChildNodeThat(Predicate<? extends INode<?>> selector);

  //method declaration
  /**
   * Removes the first child {@link INode} with the given header from the current
   * {@link IMutableNode}.
   * 
   * @param header
   * @throws RuntimeException if the current {@link IMutableNode} does not contain
   *                          a child {@link INode} with the given header.
   */
  void removeFirstChildNodeWithHeader(String header);

  //method declaration
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

  //method declaration
  /**
   * Removes the header and child {@link INode}s of the current
   * {@link IMutableNode}.
   */
  void reset();

  //method declaration
  /**
   * Resets the current {@link IMutableNode} from the file with the given file
   * path.
   * 
   * @param filePath
   */
  void resetFromFile(String filePath);

  //method declaration
  /**
   * Resets the current {@link IMutableNode} from the given string.
   * 
   * @param string
   * @throws RuntimeException if the given string does not represent a
   *                          {@link IMutableNode}.
   */
  void resetFromString(String string);

  //method declaration
  /**
   * Resets the child {@link INode}s of the current {@link IMutableNode} with the
   * given childNodes.
   * 
   * @param childNodes
   * @return the current {@link IMutableNode}.
   * @throws RuntimeException if one of the given childNodes is null.
   */
  MN setChildNodes(Iterable<? extends INode<?>> childNodes);
}
