/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.withargumentcaptor.IWithNodeDatabaseCaptor;
import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link WithNodeDatabaseCaptor}
 */
public class WithNodeDatabaseCaptor<S>
extends AbstractArgumentCaptor<IMutableNode<?>, S>
implements IWithNodeDatabaseCaptor<S> {
  public WithNodeDatabaseCaptor() {
  }

  public WithNodeDatabaseCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IMutableNode<?> getStoredNodeDatabase() {
    return getStoredArgument();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withNodeDatabase(final IMutableNode<?> nodeDatabase) {
    Validator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    return setArgumentAndGetStoredSuccessor(nodeDatabase);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withTemporaryInMemoryNodeDatabase() {
    final var nodeDatabase = MutableNode.createEmpty();

    return setArgumentAndGetStoredSuccessor(nodeDatabase);
  }
}
