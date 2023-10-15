//package declaration
package ch.nolix.coreapi.documentapi.xmlapi;

import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi.IFluentOptionalValueHolder;

//interface
public interface IMutableXmlNode
    extends IFluentOptionalValueHolder<IMutableXmlNode, String>, IXmlNode<IMutableXmlNode> {

  // method declaration
  IMutableXmlNode addAttribute(IXmlAttribute attribute);

  // method declaration
  IMutableXmlNode addAttributes(IXmlAttribute attribute, IXmlAttribute... attributes);

  // method declaration
  IMutableXmlNode addAttributeWithNameAndValue(String name, String value);

  // method declaration
  IMutableXmlNode addChildNode(IMutableXmlNode childNode);

  // method declaration
  IMutableXmlNode addChildNodes(IMutableXmlNode childNode, IMutableXmlNode... childNodes);
}
