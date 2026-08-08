/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.clientserver;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.baseapi.net.clientserver.IApplication;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.target.IServerTarget;

/**
 * @author Silvan Wyss
 */
public final class LocalServer extends AbstractServer<LocalServer> {
  /**
   * {@inheritDoc}
   */
  @Override
  public IServerTarget asTarget() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asTarget");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return SecurityMode.NONE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected LocalServer asConcrete() {
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedApplication(Application<?, ?> application) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedDefaultApplication(Application<?, ?> defaultApplication2) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedApplication(IApplication<?, ?> application) {
    // Does nothing.
  }
}
