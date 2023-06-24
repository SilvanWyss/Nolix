//package declaration
package ch.nolix.coreapi.documentapi.xmlapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi.FluentOptionalNameable;
import ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi.IOptionalValueHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IXmlNode<N extends IXmlNode<N>> extends FluentOptionalNameable<N>, IOptionalValueHolder<String> {
	
	//method declaration
	IContainer<IXmlAttribute> getAttributes();
	
	//method declaration
	boolean containsAttributes();
	
	//method declaration
	boolean containsChildNodes();
	
	//method declaration
	IContainer<N> getOriChildNodes();
	
	//method declaration
	boolean hasMixedContent();
}
