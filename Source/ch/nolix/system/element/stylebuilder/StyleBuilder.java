//package declaration
package ch.nolix.system.element.stylebuilder;

//own imports
import ch.nolix.system.element.style.Style;

//class
public final class StyleBuilder extends BaseStyleBuilder<StyleBuilder> {

  //method
  public Style build() {
    return new Style(getAttachingAttributes(), getSubStyles());
  }

  //method
  @Override
  protected StyleBuilder asConcrete() {
    return this;
  }
}
