//package declaration
package ch.nolix.coreapi.documentapi.nodeapi;

//own imports
import ch.nolix.core.attributeuniversalapi.mutableoptionalattributeuniversalapi.OptionalHeaderable;
import ch.nolix.coreapi.functionuniversalapi.IElementTakerBooleanGetter;

//interface
/**
 * A {@link IMutableNode} is a {@link INode} that is mutable.
 * 
 * @author Silvan Wyss
 * @date 2022-06-25
 */
public interface IMutableNode extends INode<IMutableNode>, OptionalHeaderable<IMutableNode> {
	
	//method declaration
	/**
	 * Adds the given childNodes to the current {@link IMutableNode}.
	 * 
	 * @param childNodes
	 * @return the current {@link IMutableNode}.
	 * @throws RuntimeException if one of the given childNodes is null.
	 */
	IMutableNode addChildNode(INode<?>... childNodes);
	
	//method declaration
	/**
	 * Adds the child {@INode}s from the given strings the current {@link IMutableNode}.
	 * 
	 * @param strings
	 * @return the current {@link IMutableNode}.
	 * @throws RuntimeException if one of the given strings does not represent a {@link INode}.
	 */
	IMutableNode addChildNodeFromString(String... strings);
	
	//method declaration
	/**
	 * Adds the given childNodes to the current {@link IMutableNode}.
	 * 
	 * @param childNodes
	 * @return the current {@link IMutableNode}.
	 * @throws RuntimeException if one of the given childNodes is null.
	 */
	IMutableNode addChildNodes(Iterable<INode<?>> childNodes);
	
	//method declaration
	/**
	 * Adds the child {@INode}s from the given strings the current {@link IMutableNode}.
	 * 
	 * @param strings
	 * @return the current {@link IMutableNode}.
	 * @throws RuntimeException if one of the given strings does not represent a {@link INode}.
	 */
	IMutableNode addChildNodesFromStrings(Iterable<String> strings);
	
	//method declaration
	/**
	 * Adds the given postfix at the end of the header of the current {@link IMutableNode} if
	 * the current {@link IMutableNode} has a header.
	 * Sets the given postfix as the header of the current {@link IMutableNode} if
	 * the current {@link IMutableNode} does not have a header.
	 * 
	 * @param postfix
	 * @throws RuntimeException if the given postfix is null.
	 * @throws RuntimeException if the given postfix is blank.
	 */
	void addPostfixToHeader(String postfix);
	
	//method declaration
	/**
	 * Adds the given prefix at the begin of the header of the current {@link IMutableNode} if
	 * the current {@link IMutableNode} has a header.
	 * Sets the given prefix as the header of the current {@link IMutableNode} if
	 * the current {@link IMutableNode} does not have a header.
	 * 
	 * @param prefix
	 * @throws RuntimeException if the given prefix is null.
	 * @throws RuntimeException if the given prefix is blank.
	 */
	void addPrefixToHeader(String prefix);
	
	//method declaration
	/**
	 * Removes the child {@link INode}s from the current {@link IMutableNode}.
	 */
	void removeChildNodes();
	
	//method declaration
	/**
	 * Removes the first child {@link INode}, the given selector selects, from the current {@link IMutableNode}.
	 * 
	 * @param selector
	 * @throws RuntimeException if
	 * the current {@link IMutableNode} does not contain a child {@link INode} the given selector selects.
	 */
	void removeFirstChildNodeThat(IElementTakerBooleanGetter<IMutableNode> selector);
	
	//method declaration
	/**
	 * Removes the first child {@link INode} with the given header from the current {@link IMutableNode}.
	 * 
	 * @param header
	 * @throws RuntimeException if
	 * the current {@link IMutableNode} does not contain a child {@link INode} with the given header.
	 */
	void removeFirstChildNodeWithHeader(String header);
}
