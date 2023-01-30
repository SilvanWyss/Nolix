//package declaration
package ch.nolix.coretest.documenttest.xmltest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class XMLTestPool extends TestPool {
	
	//constructor
	public XMLTestPool() {
		super(XMLAttributeTest.class, MutableXMLNodeTest.class);
	}
}
