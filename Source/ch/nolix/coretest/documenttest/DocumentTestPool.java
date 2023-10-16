//package declaration
package ch.nolix.coretest.documenttest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.documenttest.chainednodetest.ChainedNodeTestPool;
import ch.nolix.coretest.documenttest.nodetest.NodeTestPool;
import ch.nolix.coretest.documenttest.xmltest.XmlTestPool;

//class
public final class DocumentTestPool extends TestPool {

  //constructor
  public DocumentTestPool() {
    super(new ChainedNodeTestPool(), new NodeTestPool(), new XmlTestPool());
  }
}
