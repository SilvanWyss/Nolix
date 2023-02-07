//package declaration
package ch.nolix.coreapi.documentapi.xmlapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi.FluentOptionalNameable;
import ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi.OptionalValued;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IXMLNode<N extends IXMLNode<N>> extends FluentOptionalNameable<N>, OptionalValued<String> {
	
	//method declaration
	IContainer<IXMLAttribute> getAttributes();
	
	//method declaration
	boolean containsAttributes();
	
	//method declaration
	boolean containsChildNodes();
	
	//method declaration
	IContainer<N> getRefChildNodes();
	
	//method declaration
	boolean hasMixedContent();
}
