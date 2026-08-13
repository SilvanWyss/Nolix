/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.base;

import java.util.Optional;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.gui.main.IFrontEndReader;

final class FrontendReader implements IFrontEndReader {
  private final AbstractWebClient<?, ?> parentBackendWebClient;

  private FrontendReader(final AbstractWebClient<?, ?> parentBackendWebClient) {
    Validator.assertThat(parentBackendWebClient).thatIsNamed("parent backend web client").isNotNull();

    this.parentBackendWebClient = parentBackendWebClient;
  }

  public static FrontendReader forBackendWebClient(
    final AbstractWebClient<?, ?> backendWebClient) {
    return new FrontendReader(backendWebClient);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<byte[]> getFilesFromClipboard() {
    return parentBackendWebClient.internalGetFilesFromClipboardOfCounterpart();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTextFromClipboard() {
    return parentBackendWebClient.internalGetTextFromClipboardOfCounterpart();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<byte[]> readFileToBytes() {
    return parentBackendWebClient.internalReadOptionalFileFromCounterpart();
  }
}
