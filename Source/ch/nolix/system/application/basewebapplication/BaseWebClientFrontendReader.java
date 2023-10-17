//package declaration
package ch.nolix.system.application.basewebapplication;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;

//class
final class BaseWebClientFrontendReader implements IFrontEndReader {

  //attribute
  private final BaseWebClient<?, ?> parentBackendWebClient;

  //constructor
  private BaseWebClientFrontendReader(final BaseWebClient<?, ?> parentBackendWebClient) {

    GlobalValidator.assertThat(parentBackendWebClient).thatIsNamed("parent backend web client").isNotNull();

    this.parentBackendWebClient = parentBackendWebClient;
  }

  //static method
  public static BaseWebClientFrontendReader forBackendWebClient(
      final BaseWebClient<?, ?> backendWebClient) {
    return new BaseWebClientFrontendReader(backendWebClient);
  }

  //method
  @Override
  public IContainer<byte[]> getFilesFromClipboard() {
    return parentBackendWebClient.internalGetFilesFromClipboardOfCounterpart();
  }

  //method
  @Override
  public String getTextFromClipboard() {
    return parentBackendWebClient.internalGetTextFromClipboardOfCounterpart();
  }

  //method
  @Override
  public ISingleContainer<byte[]> readFileToBytes() {
    return parentBackendWebClient.internalReadOptionalFileFromCounterpart();
  }
}
