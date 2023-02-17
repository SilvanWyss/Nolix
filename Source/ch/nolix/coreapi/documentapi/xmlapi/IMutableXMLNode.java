//package declaration
package ch.nolix.coreapi.documentapi.xmlapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi.IFluentOptionalValueHolder;

//interface
public interface IMutableXMLNode
extends IFluentOptionalValueHolder<IMutableXMLNode, String>, IXMLNode<IMutableXMLNode> {
	
	//method declaration
	IMutableXMLNode addAttribute(IXMLAttribute attribute);
	
	//method declaration
	IMutableXMLNode addAttributes(IXMLAttribute firstAttribute, IXMLAttribute... attributes);
	
	//method declaration
	IMutableXMLNode addAttributeWithNameAndValue(String name, String value);
	
	//method declaration
	IMutableXMLNode addChildNode(IMutableXMLNode childNode);
	
	//method declaration
	IMutableXMLNode addChildNodes(IMutableXMLNode firstChildNode, IMutableXMLNode... childNodes);
}
