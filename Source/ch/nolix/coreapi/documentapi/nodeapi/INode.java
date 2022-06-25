//package declaration
package ch.nolix.coreapi.documentapi.nodeapi;

//own imports
import ch.nolix.coreapi.attributeuniversalapi.optionalattributeuniversalapi.OptionalHeadered;
import ch.nolix.coreapi.containerapi.IContainer;

//interface
/**
 * A {@link INode} has the following attributes.
 * -0 or 1 header
 * -an arbitrary number of child {@link INode}s
 * 
 * A sub type of {@link INode} may be or may be not mutable.
 * 
 * @author Silvan Wyss
 * @date 2022-06-24
 * @param <N> is the type of a {@link INode}.
 */
public interface INode<N extends INode<N>> extends OptionalHeadered {
	
	//method declaration
	/**
	 * @return true if the current {@link INode} contains child {@link INode}s.
	 */
	boolean containsChildNodes();
	
	//method declaration
	/**
	 * @param header
	 * @return true if the current {@link INode} contains a child {@link INode} with the given header.
	 */
	boolean containsChildNodeWithHeader(String header);
	
	//method declaration
	/**
	 * @return true if the current {@link INode} contains 1 child {@link INode}.
	 */
	boolean containsOneChildNode();
		
	//method declaration
	/**
	 * @return the number of child {@link INode}s of the current {@link INode}.
	 */
	int getChildNodeCount();
	
	//method declaration
	/**
	 * @return the child {@link INode}s of the current {@link INode}.
	 */
	IContainer<N> getRefChildNodes();
	
	//method declaration
	/**
	 * @return the single child {@link INode} of the current {@link INode}.
	 * @throws RuntimeException if
	 * the current {@link INode} does not contain child {@link INode}s or contains several child {@link INode}s.
	 */
	N getRefSingleChildNode();
	
	//method declaration
	/**
	 * @return the double the single child {@link INode} of the current {@link INode} represents.
	 * @throws RuntimeException if
	 * the current {@link INode} does not contain child {@link INode}s or contains several child {@link INode}s.
	 * @throws RuntimeException if
	 * the single child {@link INode} of the current {@link INode} does not represent a double.
	 */
	double getSingleChildNodeAsDouble();
	
	//method declaration
	/**
	 * @return the int the single child {@link INode} of the current {@link INode} represents.
	 * @throws RuntimeException if
	 * the current {@link INode} does not contain child {@link INode}s or contains several child {@link INode}s.
	 * @throws RuntimeException if
	 * the single child {@link INode} of the current {@link INode} does not represent an int.
	 */
	int getSingleChildNodeAsInt();
	
	//method declaration
	/**
	 * @return the long the single child {@link INode} of the current {@link INode} represents.
	 * @throws RuntimeException if
	 * the current {@link INode} does not contain child {@link INode}s or contains several child {@link INode}s.
	 * @throws RuntimeException if
	 * the single child {@link INode} of the current {@link INode} does not represent a long.
	 */
	long getSingleChildNodeAsLong();
	
	//method declaration
	/**
	 * @return the header of the single child {@link INode} of the current {@link INode}.
	 * @throws RuntimeException if
	 * the current {@link INode} does not contain child {@link INode}s or contains several child {@link INode}s.
	 * @throws RuntimeException if the single child {@link INode} of the current {@link INode} does not have a header.
	 */
	String getSingleChildNodeHeader();
	
	//method declaration
	/**
	 * @return true if the current {@link INode} is blank.
	 * A {@link INode}, that does not have a header and that does not contain child {@link INode}s, is blank.
	 */
	boolean isBlank();
	
	//method declaration
	/**
	 * @return the boolean the current {@link INode} represents.
	 * @throws RuntimeException if the current {@link INode} does not represent a boolean.
	 */
	boolean toBoolean();
	
	//method declaration
	/**
	 * @return the double the current {@link INode} represents.
	 * @throws RuntimeException if the current {@link INode} does not represent a double.
	 */
	double toDouble();
	
	//method declaration
	/**
	 * @return the int the current {@link INode} represents.
	 * @throws RuntimeException if the current {@link INode} does not represent an int.
	 */
	int toInt();
	
	//method declaration
	/**
	 * @return the long the current {@link INode} represents.
	 * @throws RuntimeException if the current {@link INode} does not represent a long.
	 */
	int toLong();
}
