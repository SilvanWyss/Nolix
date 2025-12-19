package ch.nolix.coretest.document.xml;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.xml.MutableXmlNode;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class MutableXmlNodeTest extends StandardTest {
  @Test
  void test_toString() {
    //setup
    final var testUnit = MutableXmlNode.createBlankMutableXmlNode().setName("Node");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<Node></Node>");
  }

  @Test
  void test_toString_whenHas1Attribute() {
    //setup
    final var testUnit = MutableXmlNode.createBlankMutableXmlNode().setName("Node").addAttributeWithNameAndValue("key",
      "value");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<Node key='value'></Node>");
  }

  @Test
  void test_toString_whenHas1ChildNode() {
    //setup
    final var testUnit = MutableXmlNode.createBlankMutableXmlNode().setName("Node")
      .addChildNode(MutableXmlNode.createBlankMutableXmlNode().setName("ChildNode"));

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<Node><ChildNode></ChildNode></Node>");
  }

  @Test
  void test_toString_whenHas2Attributes() {
    //setup
    final var testUnit = MutableXmlNode.createBlankMutableXmlNode()
      .setName("Node")
      .addAttributeWithNameAndValue("key1", "value1")
      .addAttributeWithNameAndValue("key2", "value2");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<Node key1='value1' key2='value2'></Node>");
  }

  @Test
  void test_toString_whenHas2ChildNodes() {
    //setup
    final var testUnit = MutableXmlNode.createBlankMutableXmlNode()
      .setName("Node")
      .addChildNode(MutableXmlNode.createBlankMutableXmlNode().setName("ChildNode1"))
      .addChildNode(MutableXmlNode.createBlankMutableXmlNode().setName("ChildNode2"));

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<Node><ChildNode1></ChildNode1><ChildNode2></ChildNode2></Node>");
  }
}
