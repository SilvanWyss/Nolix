//package declaration
package ch.nolix.core.programstructure.builder.forargumentcapturer;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public class ForNodeDatabaseCapturer<N> extends ArgumentCapturer<IMutableNode<?>, N> {

  //constructor
  public ForNodeDatabaseCapturer() {
  }

  //constructor
  public ForNodeDatabaseCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
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
