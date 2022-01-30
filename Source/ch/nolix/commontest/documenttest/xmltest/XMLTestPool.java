//package declaration
package ch.nolix.commontest.documenttest.xmltest;

import ch.nolix.core.testing.basetest.TestPool;

//class
public final class XMLTestPool extends TestPool {
	
	//constructor
	public XMLTestPool() {
		super(XMLAttributeTest.class, XMLNodeTest.class);
	}
}
