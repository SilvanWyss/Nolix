//package declaration
package ch.nolix.coreapi.documentapi.xmlapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi.IFluentMutableOptionalValueHolder;

//interface
public interface IMutableXmlNode
extends IFluentMutableOptionalValueHolder<IMutableXmlNode, String>, IXmlNode<IMutableXmlNode> {

  //method declaration
  IMutableXmlNode addAttribute(IXmlAttribute attribute);

  //method declaration
  IMutableXmlNode addAttributes(IXmlAttribute attribute, IXmlAttribute... attributes);

  //method declaration
  IMutableXmlNode addAttributeWithNameAndValue(String name, String value);

  //method declaration
  IMutableXmlNode addChildNode(IMutableXmlNode childNode);

  //method declaration
  IMutableXmlNode addChildNodes(IMutableXmlNode childNode, IMutableXmlNode... childNodes);
}
