/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.andargumentcaptor.IAndNodeDatabaseCaptor;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link AndNodeDatabaseCaptor}
 */
public class AndNodeDatabaseCaptor<S>
extends AbstractArgumentCaptor<IMutableNode<?>, S>
implements IAndNodeDatabaseCaptor<S> {
  public AndNodeDatabaseCaptor() {
  }

  public AndNodeDatabaseCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andNodeDatabase(final IMutableNode<?> nodeDatabase) {
    Validator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    return setArgumentAndGetStoredSuccessor(nodeDatabase);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andTemporaryInMemoryNodeDatabase() {
    final var nodeDatabase = MutableNode.createEmpty();

    return andNodeDatabase(nodeDatabase);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IMutableNode<?> getStoredNodeDatabase() {
    return getStoredArgument();
  }
}
