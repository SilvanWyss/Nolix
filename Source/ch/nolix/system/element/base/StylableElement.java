//package declaration
package ch.nolix.system.element.base;

//own imports
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
public abstract class StylableElement<SE extends IStylableElement<SE>>
    extends OptionalIdentifiableMultiTokenableElement<SE>
    implements IStylableElement<SE> {

  // method
  @Override
  public final void resetStyleRecursively() {

    resetStyle();

    getStoredChildStylableElements().forEach(IStylableElement::resetStyleRecursively);
  }

  // method declaration
  protected abstract void resetStylableElement();

  // method declaration
  protected abstract void resetStyle();

  // method
  @Override
  protected final void resetElement() {

    resetStyleRecursively();

    resetStylableElement();
  }
}
