/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.singlecontainer;

import ch.nolix.system.control.singlecontainer.SingleContainer;
import ch.nolix.system.control.singlecontainer.SingleContainerCssBuilder;
import ch.nolix.systemapi.control.singlecontainer.ISingleContainer;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class SingleContainerCssBuilderTest extends ControlCssBuilderTest<SingleContainerCssBuilder, ISingleContainer> {
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
  protected SingleContainerCssBuilder createTestUnit() {
    return new SingleContainerCssBuilder();
  }
}
