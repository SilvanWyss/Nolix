//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlAttributeNameCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IUploader;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;

//class
public final class UploaderHtmlBuilder implements IControlHtmlBuilder<IUploader> {

  //method
  @Override
  public IHtmlElement createHtmlElementForControl(final IUploader control) {
    return HtmlElement.withTypeAndAttributes(
      HtmlElementTypeCatalogue.INPUT,
      ImmutableList.withElement(
        HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.TYPE, "file"),
        HtmlAttribute.withNameAndValue("multiple", "none"),
        HtmlAttribute.withNameAndValue("data-uploader", "any")));
  }
}
