package ch.nolix.systemtest.applicationtest.maintest;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.application.main.BackendClient;

public final class MockBackendClient extends BackendClient<MockBackendClient, Object> {

  private IChainedNode latestRunHereCommand;

  private IChainedNode latestGetDataFromHereRequest;

  @Override
  protected INode<?> getDataFromHere(final IChainedNode request) {

    GlobalValidator.assertThat(request).thatIsNamed(LowerCaseVariableCatalogue.REQUEST).isNotNull();

    latestGetDataFromHereRequest = request;

    return Node.EMPTY_NODE;
  }

  @Override
  protected void runHere(final IChainedNode command) {

    GlobalValidator.assertThat(command).thatIsNamed(LowerCaseVariableCatalogue.COMMAND).isNotNull();

    latestRunHereCommand = command;
  }

  public IChainedNode getStoredLatestGetDataFromHereRequest() {

    GlobalValidator
      .assertThat(latestGetDataFromHereRequest)
      .thatIsNamed("latest received data from here command")
      .isNotNull();

    return latestGetDataFromHereRequest;
  }

  public IChainedNode getStoredLatestRunHereCommand() {

    GlobalValidator.assertThat(latestRunHereCommand).thatIsNamed("latest received run here command").isNotNull();

    return latestRunHereCommand;
  }
}
