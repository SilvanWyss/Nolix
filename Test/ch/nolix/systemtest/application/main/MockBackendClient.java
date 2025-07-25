package ch.nolix.systemtest.application.main;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.application.main.AbstractBackendClient;

public final class MockBackendClient extends AbstractBackendClient<MockBackendClient, Object> {

  private IChainedNode latestRunHereCommand;

  private IChainedNode latestGetDataFromHereRequest;

  @Override
  protected INode<?> getDataFromHere(final IChainedNode request) {

    Validator.assertThat(request).thatIsNamed(LowerCaseVariableCatalog.REQUEST).isNotNull();

    latestGetDataFromHereRequest = request;

    return Node.EMPTY_NODE;
  }

  @Override
  protected void runHere(final IChainedNode command) {

    Validator.assertThat(command).thatIsNamed(LowerCaseVariableCatalog.COMMAND).isNotNull();

    latestRunHereCommand = command;
  }

  public IChainedNode getStoredLatestGetDataFromHereRequest() {

    Validator
      .assertThat(latestGetDataFromHereRequest)
      .thatIsNamed("latest received data from here command")
      .isNotNull();

    return latestGetDataFromHereRequest;
  }

  public IChainedNode getStoredLatestRunHereCommand() {

    Validator.assertThat(latestRunHereCommand).thatIsNamed("latest received run here command").isNotNull();

    return latestRunHereCommand;
  }
}
