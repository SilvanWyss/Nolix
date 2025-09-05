package ch.nolix.coretest.document.xml;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.xml.XmlAttribute;
import ch.nolix.core.testing.standardtest.StandardTest;

final class XmlAttributeTest extends StandardTest {
  @Test
  void testCase_constructor() {
    //execution
    final var result = new XmlAttribute("color", "green");

    //verification
    expect(result.getName()).isEqualTo("color");
    expect(result.getValue()).isEqualTo("green");
  }

  @Test
  void testCase_toString() {
    //setup
    final var xmlAttribute = new XmlAttribute("color", "green");

    //execution
    final var result = xmlAttribute.toString();

    //verification
    expect(result).isEqualTo("color='green'");
  }
}
