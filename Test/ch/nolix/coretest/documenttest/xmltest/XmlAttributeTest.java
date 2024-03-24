//package declaration
package ch.nolix.coretest.documenttest.xmltest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.document.xml.XmlAttribute;
import ch.nolix.core.testing.test.StandardTest;

//class
final class XmlAttributeTest extends StandardTest {

  //method
  @Test
  void testCase_constructor() {

    //execution
    final var result = new XmlAttribute("color", "green");

    //verification
    expect(result.getName()).isEqualTo("color");
    expect(result.getValue()).isEqualTo("green");
  }

  //method
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
