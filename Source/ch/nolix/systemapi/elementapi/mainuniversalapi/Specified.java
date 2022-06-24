//package declaration
package ch.nolix.systemapi.elementapi.mainuniversalapi;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.document.xml.XMLNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
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
	void fillUpAttributesInto(LinkedList<Node> list);
	
	//method
	/**
	 * @return the attributes of the current {@link Specified}.
	 */
	default LinkedList<Node> getAttributes() {
		
		final var attributes = new LinkedList<Node>();
		
		fillUpAttributesInto(attributes);
		
		return attributes;
	}
	
	//method
	/**
	 * @return the specification of the current {@link Specified}.
	 */
	default Node getSpecification() {
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
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	default Node getSpecificationWithHeader(final String header) {
		return Node.withHeaderAndAttributes(header, getAttributes());
	}
	
	//method
	/**
	 * @return the specification of the current {@link Specified} without header.
	 */
	default Node getSpecificationWithoutHeader() {
		return Node.withAttributes(getAttributes());
	}
	
	//method
	/**
	 * @return a formated {@link String} representation of the current {@link Specified}.
	 */
	default String toFormatedString() {
		return getSpecification().toFormatedString();
	}
	
	//method
	/**
	 * @return a XML representation of the current {@link Specified}.
	 */
	default XMLNode toXML() {
		return getSpecification().toXML();
	}
	
	//method
	/**
	 * @return a XML representation of the current {@link Specified} with the given header.
	 * @param header
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	default XMLNode toXMLWithHeader(final String header) {
		return getSpecificationWithHeader(header).toXML();
	}
	
	//method
	/**
	 * @return a XML representation of the current {@link Specified} without header.
	 */
	default XMLNode toXMLWithoutHeader() {
		return getSpecificationWithoutHeader().toXML();
	}
}
