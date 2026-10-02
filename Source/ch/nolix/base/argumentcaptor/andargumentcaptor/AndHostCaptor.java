package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.andargumentcaptor.IAndHostCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.netcatalog.IPv4Catalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link AndHostCaptor}
 */
public class AndHostCaptor<S> extends AbstractArgumentCaptor<String, S> implements IAndHostCaptor<S> {
  public AndHostCaptor() {
  }

  public AndHostCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andHost(final String host) {
    Validator.assertThat(host).thatIsNamed(LowerCaseVariableNameCatalog.HOST).isNotBlank();

    return setArgumentAndGetStoredSuccessor(host);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andLocalHost() {
    return setArgumentAndGetStoredSuccessor(IPv4Catalog.LOOP_BACK_ADDRESS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getHost() {
    return getStoredArgument();
  }
}
