package ch.nolix.system.application.basewebapplication;

import java.util.Optional;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;

final class FrontendReader implements IFrontEndReader {

  private final AbstractWebClient<?, ?> parentBackendWebClient;

  private FrontendReader(final AbstractWebClient<?, ?> parentBackendWebClient) {

    GlobalValidator.assertThat(parentBackendWebClient).thatIsNamed("parent backend web client").isNotNull();

    this.parentBackendWebClient = parentBackendWebClient;
  }

  public static FrontendReader forBackendWebClient(
    final AbstractWebClient<?, ?> backendWebClient) {
    return new FrontendReader(backendWebClient);
  }

  @Override
  public IContainer<byte[]> getFilesFromClipboard() {
    return parentBackendWebClient.internalGetFilesFromClipboardOfCounterpart();
  }

  @Override
  public String getTextFromClipboard() {
    return parentBackendWebClient.internalGetTextFromClipboardOfCounterpart();
  }

  @Override
  public Optional<byte[]> readFileToBytes() {
    return parentBackendWebClient.internalReadOptionalFileFromCounterpart();
  }
}
