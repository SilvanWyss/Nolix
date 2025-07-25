package ch.nolix.system.application.main;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;
import ch.nolix.coreapi.net.target.IServerTarget;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

/**
 * @author Silvan Wyss
 * @version 2021-06-29
 */
public final class LocalServer extends AbstractServer<LocalServer> {

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
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedDefaultApplication(Application<?, ?> defaultApplication2) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedApplication(IApplication<?, ?> application) {
    //Does nothing.
  }
}
