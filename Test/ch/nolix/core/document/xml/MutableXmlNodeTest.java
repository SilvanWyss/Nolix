package ch.nolix.core.document.xml;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;

final class MutableXmlNodeTest extends StandardTest {

  @Test
  void test_toString() {

    //setup
    final var testUnit = new MutableXmlNode().setName("Node");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<Node></Node>");
  }

  @Test
  void test_toString_whenHas1Attribute() {

    //setup
    final var testUnit = new MutableXmlNode().setName("Node").addAttributeWithNameAndValue("key", "value");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<Node key='value'></Node>");
  }

  @Test
  void test_toString_whenHas1ChildNode() {

    //setup
    final var testUnit = new MutableXmlNode().setName("Node").addChildNode(new MutableXmlNode().setName("ChildNode"));

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<Node><ChildNode></ChildNode></Node>");
  }

  @Test
  void test_toString_whenHas2Attributes() {

    //setup
    final var testUnit = new MutableXmlNode()
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
    final var testUnit = new MutableXmlNode()
      .setName("Node")
      .addChildNode(new MutableXmlNode().setName("ChildNode1"))
      .addChildNode(new MutableXmlNode().setName("ChildNode2"));

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("<Node><ChildNode1></ChildNode1><ChildNode2></ChildNode2></Node>");
  }
}
