//package declaration
package ch.nolix.system.application.main;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.netapi.targetapi.IServerTarget;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

//class
/**
 * @author Silvan Wyss
 * @version 2021-06-29
 */
public final class LocalServer extends BaseServer<LocalServer> {

  //method
  @Override
  public IServerTarget asTarget() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asTarget");
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityLevel() {
    return SecurityMode.NONE;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected LocalServer asConcrete() {
    return this;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedApplication(Application<?, ?> application) {
    //Does nothing.
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedDefaultApplication(Application<?, ?> defaultApplication2) {
    //Does nothing.
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedApplication(IApplication<?> application) {
    //Does nothing.
  }
}
