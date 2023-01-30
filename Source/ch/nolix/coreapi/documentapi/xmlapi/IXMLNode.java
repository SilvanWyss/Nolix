//package declaration
package ch.nolix.coreapi.documentapi.xmlapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi.FluentOptionalNamable;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IXMLNode<N extends IXMLNode<N>> extends FluentOptionalNamable<N> {
	
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
