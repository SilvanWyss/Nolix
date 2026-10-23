/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.clientserver;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.net.clientserver.AbstractBackendClient;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.chainednode.ChainedNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class MockBackendClient extends AbstractBackendClient<MockBackendClient, Object> {
  private ChainedNode latestRunHereCommand;

  private ChainedNode latestGetDataFromHereRequest;

  /**
   * @return the latest get data from here request the current
   *         {@link MockBackendClient} has received
   * @throws RuntimeException if the current {@link MockBackendClient} has not
   *                          received a get data from here request
   */
  public ChainedNode getStoredLatestGetDataFromHereRequest() {
    Validator
      .assertThat(latestGetDataFromHereRequest)
      .thatIsNamed("latest received data from here command")
      .isNotNull();

    return latestGetDataFromHereRequest;
  }

  /**
   * @return the latest run here command the current {@link MockBackendClient} has
   *         received
   * @throws RuntimeException if the current {@link MockBackendClient} has not
   *                          received a run here command
   */
  public ChainedNode getStoredLatestRunHereCommand() {
    Validator.assertThat(latestRunHereCommand).thatIsNamed("latest received run here command").isNotNull();

    return latestRunHereCommand;
  }

  @Override
  protected Node<?> provideData(final ChainedNode request) {
    Validator.assertThat(request).thatIsNamed(LowerCaseVariableNameCatalog.REQUEST).isNotNull();

    latestGetDataFromHereRequest = request;

    return ImmutableNode.EMPTY_NODE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void provideRun(final ChainedNode command) {
    Validator.assertThat(command).thatIsNamed(LowerCaseVariableNameCatalog.COMMAND).isNotNull();

    latestRunHereCommand = command;
  }
}
