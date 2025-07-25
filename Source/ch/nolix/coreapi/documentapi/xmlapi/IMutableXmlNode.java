package ch.nolix.coreapi.documentapi.xmlapi;

import ch.nolix.coreapi.attribute.fluentmutableoptionalattribute.IFluentMutableOptionalValueHolder;

public interface IMutableXmlNode
extends IFluentMutableOptionalValueHolder<IMutableXmlNode, String>, IXmlNode<IMutableXmlNode> {

  IMutableXmlNode addAttribute(IXmlAttribute attribute);

  IMutableXmlNode addAttributes(IXmlAttribute attribute, IXmlAttribute... attributes);

  IMutableXmlNode addAttributeWithNameAndValue(String name, String value);

  IMutableXmlNode addChildNode(IMutableXmlNode childNode);

  IMutableXmlNode addChildNodes(IMutableXmlNode childNode, IMutableXmlNode... childNodes);
}
