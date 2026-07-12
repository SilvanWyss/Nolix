/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.forargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.forargumentcaptor.IForNodeDatabaseCaptor;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link ForNodeDatabaseCaptor}.
 */
public class ForNodeDatabaseCaptor<S> extends AbstractArgumentCaptor<IMutableNode<?>, S>
implements IForNodeDatabaseCaptor<S> {
  public ForNodeDatabaseCaptor() {
  }

  public ForNodeDatabaseCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    Validator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    return setArgumentAndGetStoredSuccessor(nodeDatabase);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forTemporaryInMemoryNodeDatabase() {
    final var nodeDatabase = MutableNode.createEmpty();

    return forNodeDatabase(nodeDatabase);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IMutableNode<?> getStoredNodeDatabase() {
    return getStoredArgument();
  }
}
