//package declaration
package ch.nolix.commontest.documenttest;

import ch.nolix.common.testing.basetest.TestPool;
import ch.nolix.commontest.documenttest.chainednodetest.ChainedNodeTestPool;
import ch.nolix.commontest.documenttest.filenodetest.FileNodeTestPool;
import ch.nolix.commontest.documenttest.nodetest.NodeTestPool;
import ch.nolix.commontest.documenttest.xmltest.XMLTestPool;

//class
public final class DocumentTestPool extends TestPool {
	
	//constructor
	public DocumentTestPool() {
		super(new ChainedNodeTestPool(), new FileNodeTestPool(), new NodeTestPool(), new XMLTestPool());
	}
}
