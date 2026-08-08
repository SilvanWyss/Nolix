/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.sessionserver;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.net.sessionserver.AbstractBackendClient;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class MockBackendClient extends AbstractBackendClient<MockBackendClient, Object> {
  private IChainedNode latestRunHereCommand;

  private IChainedNode latestGetDataFromHereRequest;

  @Override
  protected Node<?> provideData(final IChainedNode request) {
    Validator.assertThat(request).thatIsNamed(LowerCaseVariableNameCatalog.REQUEST).isNotNull();

    latestGetDataFromHereRequest = request;

    return ImmutableNode.EMPTY_NODE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void provideRun(final IChainedNode command) {
    Validator.assertThat(command).thatIsNamed(LowerCaseVariableNameCatalog.COMMAND).isNotNull();

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
