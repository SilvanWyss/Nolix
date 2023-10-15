//package declaration
package ch.nolix.system.webgui.linearcontainer;

//own imports
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class FloatContainerHtmlBuilder implements IControlHtmlBuilder<FloatContainer> {

  // method
  @Override
  public HtmlElement createHtmlElementForControl(final FloatContainer floatContainer) {
    return HtmlElement.withTypeAndChildElements(
        HtmlElementTypeCatalogue.DIV,
        createHtmlElementsForChildControlsOfFloatContainer(floatContainer));
  }

  // method
  private IContainer<HtmlElement> createHtmlElementsForChildControlsOfFloatContainer(
      final FloatContainer floatContainer) {
    return floatContainer.getStoredChildControls().to(this::createHtmlElementsForChildControl);
  }

  // method
  private HtmlElement createHtmlElementsForChildControl(final IControl<?, ?> childControl) {
    return HtmlElement.withTypeAndChildElement(
        HtmlElementTypeCatalogue.DIV,
        childControl.getHtml());
  }
}
