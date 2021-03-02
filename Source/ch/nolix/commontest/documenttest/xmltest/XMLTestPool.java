//package declaration
package ch.nolix.commontest.documenttest.xmltest;

//own import
import ch.nolix.common.basetest.TestPool;

//class
public final class XMLTestPool extends TestPool {
	
	//constructor
	public XMLTestPool() {
		super(XMLAttributeTest.class, XMLNodeTest.class);
	}
}
