//package declaration
package ch.nolix.coreapi.documentapi.xmlapi;

//own imports
import ch.nolix.coreapi.attributeapi.mutableoptionalattributeuniversalapi.OptionalNamable;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface IXMLNode<N extends IXMLNode<N>> extends OptionalNamable<N> {
	
	//method declaration
	IContainer<N> getRefChildNodes();
}
