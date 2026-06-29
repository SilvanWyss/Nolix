/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.forargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 * @param <N> the type of the next thing of a {@link ForNodeDatabaseCaptor}.
 */
public class ForNodeDatabaseCaptor<N> extends AbstractArgumentCaptor<IMutableNode<?>, N> {
  public ForNodeDatabaseCaptor() {
  }

  public ForNodeDatabaseCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    Validator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    return setArgumentAndGetStoredSuccessor(nodeDatabase);
  }

  public final N forTemporaryInMemoryNodeDatabase() {
    final var nodeDatabase = MutableNode.createEmpty();

    return forNodeDatabase(nodeDatabase);
  }

  public final IMutableNode<?> getStoredNodeDatabase() {
    return getStoredArgument();
  }
}
