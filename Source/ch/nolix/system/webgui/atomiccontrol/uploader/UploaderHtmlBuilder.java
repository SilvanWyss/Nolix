package ch.nolix.system.webgui.atomiccontrol.uploader;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.html.IHtmlElement;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.uploaderapi.IUploader;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class UploaderHtmlBuilder implements IControlHtmlBuilder<IUploader> {

  @Override
  public IHtmlElement createHtmlElementForControl(final IUploader control) {
    return HtmlElement.withTypeAndAttributes(
      HtmlElementTypeCatalog.INPUT,
      ImmutableList.withElement(
        HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.TYPE, "file"),
        HtmlAttribute.withNameAndValue("multiple", "none"),
        HtmlAttribute.withNameAndValue("data-uploader", "any")));
  }
}
