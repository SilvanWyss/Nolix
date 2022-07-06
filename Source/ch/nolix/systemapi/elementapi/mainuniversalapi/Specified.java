//package declaration
package ch.nolix.systemapi.elementapi.mainuniversalapi;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.documentapi.xmlapi.IXMLNode;
import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface Specified {
	
	//method declaration
	/**
	 * Fills up the attributes of the current {@link Specified} into the given list.
	 * 
	 * @param list
	 */
	void fillUpAttributesInto(IMutableList<INode<?>> list);
	
	//method
	/**
	 * @return the attributes of the current {@link Specified}.
	 */
	IContainer<INode<?>> getAttributes();
	
	//method
	/**
	 * @return the specification of the current {@link Specified}.
	 */
	default INode<?> getSpecification() {
		return getSpecificationWithHeader(getSpecificationHeader());
	}
	
	//method
	/**
	 * @return the header of the specification of the current {@link Specified}.
	 */
	default String getSpecificationHeader() {
		return getClass().getSimpleName();
	}
	
	//method
	/**
	 * @param header
	 * @return the specification of the current {@link Specified} with the given header.
	 * @throws RuntimeException if the given header is null.
	 * @throws RuntimeException if the given header is blank.
	 */
	default INode<?> getSpecificationWithHeader(final String header) {
		return Node.withHeaderAndChildNodes(header, getAttributes());
	}
	
	//method
	/**
	 * @return a formated {@link String} representation of the current {@link Specified}.
	 */
	default String toFormatedString() {
		return getSpecification().toFormattedString();
	}
	
	//method
	/**
	 * @return a XML representation of the current {@link Specified}.
	 */
	default IXMLNode<?> toXML() {
		return getSpecification().toXML();
	}
	
	//method
	/**
	 * @return a XML representation of the current {@link Specified} with the given header.
	 * @param header
	 * @throws RuntimeException if the given header is null.
	 * @throws RuntimeException if the given header is blank.
	 */
	default IXMLNode<?> toXMLWithHeader(final String header) {
		return getSpecificationWithHeader(header).toXML();
	}
}
