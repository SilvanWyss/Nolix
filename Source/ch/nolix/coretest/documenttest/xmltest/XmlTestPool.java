//package declaration
package ch.nolix.coretest.documenttest.xmltest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class XmlTestPool extends TestPool {

  //constructor
  public XmlTestPool() {
    super(XmlAttributeTest.class, MutableXmlNodeTest.class);
  }
}
