//package declaration
package ch.nolix.commontest.documenttest;

import ch.nolix.commontest.documenttest.chainednodetest.ChainedNodeTestPool;
import ch.nolix.commontest.documenttest.filenodetest.FileNodeTestPool;
import ch.nolix.commontest.documenttest.nodetest.NodeTestPool;
import ch.nolix.commontest.documenttest.xmltest.XMLTestPool;
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class DocumentTestPool extends TestPool {
	
	//constructor
	public DocumentTestPool() {
		super(new ChainedNodeTestPool(), new FileNodeTestPool(), new NodeTestPool(), new XMLTestPool());
	}
}
