//package declaration
package ch.nolix.coreapi.documentapi.xmlapi;

//interface
public interface IMutableXMLNode extends IXMLNode<IMutableXMLNode> {
	
	//method declaration
	IMutableXMLNode addAttribute(IXMLAttribute attribute);
	
	//method declaration
	IMutableXMLNode addAttributes(IXMLAttribute firstAttribute, IXMLAttribute... attributes);
	
	//method declaration
	IMutableXMLNode addChildNode(IMutableXMLNode childNode);
	
	//method declaration
	IMutableXMLNode addChildNodes(IMutableXMLNode firstChildNode, IMutableXMLNode... childNodes);
	
	//TODO: Create OptionalValuable.
	boolean hasValue();
}
