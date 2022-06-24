//package declaration
package ch.nolix.coreapi.documentapi.nodeapi;

//own imports
import ch.nolix.core.attributeuniversalapi.optionalattributeuniversalapi.OptionalHeadered;
import ch.nolix.coreapi.containerapi.IContainer;

//interface
/**
 * A {@link INode} has the following attributes.
 * -0 or 1 header
 * -an arbitrary number of attributes which are a {@link INode} themselves
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
	 * @return true if the current {@link INode} contains attributes.
	 */
	boolean containsAttributes();
	
	//method declaration
	/**
	 * @param header
	 * @return true if the current {@link INode} contains an attribute with the given header.
	 */
	boolean containsAttributeWithHeader(String header);
	
	//method declaration
	/**
	 * @return true if the current {@link INode} contains 1 attribute.
	 */
	boolean containsOneAttribute();
		
	//method declaration
	/**
	 * @return the number of attributes of the current {@link INode}.
	 */
	int getAttribtueCount();
	
	//method declaration
	/**
	 * @return the attributes of the current {@link INode}.
	 */
	IContainer<N> getRefAttributes();
	
	//method declaration
	/**
	 * @return the single attribute of the current {@link INode}.
	 * @throws RuntimeException if
	 * the current {@link INode} does not contain attributes or contains several attributes.
	 */
	N getRefSingleAttribute();
	
	//method declaration
	/**
	 * @return the double the single attribute of the current {@link INode} represents.
	 * @throws RuntimeException if
	 * the current {@link INode} does not contain attributes or contains several attributes.
	 * @throws RuntimeException if the single attribute of the current {@link INode} does not represent a double.
	 */
	double getSingleAttributeAsDouble();
	
	//method declaration
	/**
	 * @return the int the single attribute of the current {@link INode} represents.
	 * @throws RuntimeException if
	 * the current {@link INode} does not contain attributes or contains several attributes.
	 * @throws RuntimeException if the single attribute of the current {@link INode} does not represent an int.
	 */
	int getSingleAttributeAsInt();
	
	//method declaration
	/**
	 * @return the long the single attribute of the current {@link INode} represents.
	 * @throws RuntimeException if
	 * the current {@link INode} does not contain attributes or contains several attributes.
	 * @throws RuntimeException if the single attribute of the current {@link INode} does not represent a long.
	 */
	int getSingleAttributeAsLong();
	
	//method declaration
	/**
	 * @return the header of the signle attribute of the current {@link INode}.
	 * @throws RuntimeException if
	 * the current {@link INode} does not contain attributes or contains several attributes.
	 * @throws RuntimeException if the single attribute of the current {@link INode} does not have a header.
	 */
	String getSingleAttributeHeader();
	
	//method declaration
	/**
	 * @return true if the current {@link INode} is blank.
	 * A {@link INode} that does not have a header and that does not contain attributes is blank.
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
	boolean toDouble();
	
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
