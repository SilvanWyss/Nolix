//package declaration
package ch.nolix.core.argumentcaptor.forargumentcaptor;

//own imports
import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public class ForNodeDatabaseCaptor<N> extends ArgumentCaptor<IMutableNode<?>, N> {

  //constructor
  public ForNodeDatabaseCaptor() {
  }

  //constructor
  public ForNodeDatabaseCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final N forNodeDatabase(final IMutableNode<?> nodeDatabase) {

    GlobalValidator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    return setArgumentAndGetNext(nodeDatabase);
  }

  //method
  public final N forTemporaryInMemoryNodeDatabase() {

    final var nodeDatabase = new MutableNode();

    return forNodeDatabase(nodeDatabase);
  }

  //method
  public final IMutableNode<?> getStoredNodeDatabase() {
    return getStoredArgument();
  }
}
