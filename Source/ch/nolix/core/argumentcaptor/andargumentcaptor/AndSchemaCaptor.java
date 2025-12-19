package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 */
public class AndSchemaCaptor<S, N> extends ArgumentCaptor<S, N> {
  public AndSchemaCaptor() {
  }

  public AndSchemaCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andSchema(final S schema) {
    return setArgumentAndGetNext(schema);
  }

  public final S getStoredSchema() {
    return getStoredArgument();
  }
}
