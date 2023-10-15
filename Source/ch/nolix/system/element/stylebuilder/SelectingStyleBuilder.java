//package declaration
package ch.nolix.system.element.stylebuilder;

//own imports
import ch.nolix.system.element.style.SelectingStyle;

//class
public final class SelectingStyleBuilder extends BaseSelectingStyleBuilder<SelectingStyleBuilder> {

  // method
  public SelectingStyle build() {
    return new SelectingStyle(
        getSelectorIdContainer(),
        getSelectorTypeContainer(),
        getSelectorRoles(),
        getSelectorTokens(),
        getAttachingAttributes(),
        getSubStyles());
  }

  // method
  @Override
  protected SelectingStyleBuilder asConcrete() {
    return this;
  }
}
