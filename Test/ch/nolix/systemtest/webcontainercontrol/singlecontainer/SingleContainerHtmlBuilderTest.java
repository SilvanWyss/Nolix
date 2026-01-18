/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webcontainercontrol.singlecontainer;

import ch.nolix.system.webcontainercontrol.singlecontainer.SingleContainer;
import ch.nolix.system.webcontainercontrol.singlecontainer.SingleContainerHtmlBuilder;
import ch.nolix.systemapi.webcontainercontrol.singlecontainer.ISingleContainer;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class SingleContainerHtmlBuilderTest
extends ControlHtmlBuilderTest<SingleContainerHtmlBuilder, ISingleContainer> {
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
