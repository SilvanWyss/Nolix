//package declaration
package ch.nolix.commontest.documenttest;

//own import
import ch.nolix.common.basetest.TestPool;
import ch.nolix.commontest.documenttest.chainednodetest.ChainedNodeTestPool;
import ch.nolix.commontest.documenttest.filenodetest.FileNodeTestPool;
import ch.nolix.commontest.documenttest.nodetest.NodeTestPool;
import ch.nolix.commontest.documenttest.xmltest.XMLTestPool;

//class
public final class DocumentNodeTestPool extends TestPool {
	
	//constructor
	public DocumentNodeTestPool() {
		super(new ChainedNodeTestPool(), new FileNodeTestPool(), new NodeTestPool(), new XMLTestPool());
	}
}
