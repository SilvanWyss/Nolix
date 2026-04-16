/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.containercontrol.singlecontainer;

import ch.nolix.system.containercontrol.singlecontainer.SingleContainer;
import ch.nolix.system.containercontrol.singlecontainer.SingleContainerHtmlBuilder;
import ch.nolix.systemapi.containercontrol.singlecontainer.ISingleContainer;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class SingleContainerHtmlBuilderTest
extends ControlHtmlBuilderTest<SingleContainerHtmlBuilder, ISingleContainer> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected ISingleContainer createControl() {
    return new SingleContainer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected SingleContainerHtmlBuilder createTestUnit() {
    return new SingleContainerHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div />";
  }
}
