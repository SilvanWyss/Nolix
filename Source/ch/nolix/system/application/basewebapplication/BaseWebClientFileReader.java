//package declaration
package ch.nolix.system.application.basewebapplication;

//Java imports
import java.util.Base64;

import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.system.application.basewebapplicationprotocol.CommandProtocol;

//class
final class BaseWebClientFileReader {

  //constant
  private static final int MAX_WAITING_TIME_FOR_FILE_FROM_COUNTERPART_IN_SECONDS = 60;

  //attribute
  private final BaseWebClient<?, ?> parentBackendWebClient;

  //attribute
  private boolean isWaitingForFileFromCounterpart;

  //optional attribute
  private SingleContainer<byte[]> latestOptionalFileFromCounterpart;

  //constructor
  private BaseWebClientFileReader(final BaseWebClient<?, ?> parentBackendWebClient) {

    GlobalValidator.assertThat(parentBackendWebClient).thatIsNamed("parent backend web client").isNotNull();

    this.parentBackendWebClient = parentBackendWebClient;
  }

  //static method
  public static BaseWebClientFileReader forBackendWebClient(
    final BaseWebClient<?, ?> backendWebClient) {
    return new BaseWebClientFileReader(backendWebClient);
  }

  //method
  public SingleContainer<byte[]> readOptionalFileFromCounterpart() {

    assertIsNotWaitingForFileFromCounterpart();

    isWaitingForFileFromCounterpart = true;

    parentBackendWebClient.internalRunOnCounterpart(ChainedNode.withHeader(CommandProtocol.SEND_OPTIONAL_FILE));

    GlobalSequencer
      .forMaxSeconds(MAX_WAITING_TIME_FOR_FILE_FROM_COUNTERPART_IN_SECONDS)
      .waitAsLongAs(this::isWaitingForFileFromCounterpart);

    isWaitingForFileFromCounterpart = false;

    return latestOptionalFileFromCounterpart;
  }

  //method
  public void receiveOptionalFileFromCounterpart(final IChainedNode receiveOptionalFileCommand) {
    switch (receiveOptionalFileCommand.getChildNodeCount()) {
      case 0:
        receiveOptionalFileFromCounterpart(new SingleContainer<>());
        break;
      case 1:

        final var fileString = receiveOptionalFileCommand.getSingleChildNode().getHeader();

        //Important: The received fileString is a Base 64 encoded string.
        final var bytes = Base64.getDecoder().decode(fileString.substring(fileString.indexOf(',') + 1));

        receiveOptionalFileFromCounterpart(
          new SingleContainer<>(bytes));
        break;
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument(
          "receive optional file commoand",
          receiveOptionalFileCommand);
    }
  }

  //method
  private void assertIsNotWaitingForFileFromCounterpart() {
    if (isWaitingForFileFromCounterpart()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "is already waiting for a file from the counterpart");
    }
  }

  //method
  private void assertIsWaitingForFileFromCounterpart() {
    if (!isWaitingForFileFromCounterpart()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not waiting for a file from counterpart");
    }
  }

  //method
  private boolean isWaitingForFileFromCounterpart() {
    return isWaitingForFileFromCounterpart;
  }

  //method
  private void receiveOptionalFileFromCounterpart(final SingleContainer<byte[]> optionalFile) {

    GlobalValidator.assertThat(optionalFile).thatIsNamed("optional file").isNotNull();
    assertIsWaitingForFileFromCounterpart();

    isWaitingForFileFromCounterpart = false;
    latestOptionalFileFromCounterpart = optionalFile;
  }
}
