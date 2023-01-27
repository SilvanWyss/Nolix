//package declaration
package ch.nolix.coreapi.documentapi.xmlapi;

import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi.FluentOptionalNamable;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface IXMLNode<N extends IXMLNode<N>> extends FluentOptionalNamable<N> {
	
	//method declaration
	IContainer<N> getRefChildNodes();
}
