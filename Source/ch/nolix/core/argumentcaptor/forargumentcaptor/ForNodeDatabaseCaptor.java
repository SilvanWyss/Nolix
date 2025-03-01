package ch.nolix.core.argumentcaptor.forargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public class ForNodeDatabaseCaptor<N> extends ArgumentCaptor<IMutableNode<?>, N> {

  public ForNodeDatabaseCaptor() {
  }

  public ForNodeDatabaseCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N forNodeDatabase(final IMutableNode<?> nodeDatabase) {

    Validator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    return setArgumentAndGetNext(nodeDatabase);
  }

  public final N forTemporaryInMemoryNodeDatabase() {

    final var nodeDatabase = MutableNode.createEmpty();

    return forNodeDatabase(nodeDatabase);
  }

  public final IMutableNode<?> getStoredNodeDatabase() {
    return getStoredArgument();
  }
}
