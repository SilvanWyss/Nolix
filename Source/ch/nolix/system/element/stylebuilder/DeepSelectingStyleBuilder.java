//package declaration
package ch.nolix.system.element.stylebuilder;

//own imports
import ch.nolix.system.element.style.DeepSelectingStyle;

//class
public final class DeepSelectingStyleBuilder extends BaseSelectingStyleBuilder<DeepSelectingStyleBuilder> {

  //method
  public DeepSelectingStyle build() {
    return new DeepSelectingStyle(
        getSelectorIdContainer(),
        getSelectorTypeContainer(),
        getSelectorRoles(),
        getSelectorTokens(),
        getAttachingAttributes(),
        getSubStyles());
  }

  //method
  @Override
  protected DeepSelectingStyleBuilder asConcrete() {
    return this;
  }
}
