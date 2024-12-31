package ch.nolix.system.webgui.atomiccontrol.link;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.coreapi.webapi.webproperty.LinkTarget;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.linkapi.ILink;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class LinkHtmlBuilder implements IControlHtmlBuilder<ILink> {

  @Override
  public IHtmlElement createHtmlElementForControl(final ILink control) {
    return HtmlElement.withTypeAndAttributesAndInnerText(
      HtmlElementTypeCatalogue.A,
      createHtmlAttributesForControl(control),
      control.getDisplayText());
  }

  private IContainer<? extends IHtmlAttribute> createHtmlAttributesForControl(final ILink control) {

    final ILinkedList<IHtmlAttribute> htmlAttribtues = LinkedList.createEmpty();

    htmlAttribtues.addAtEnd(createTargetHtmlAttributeForControl(control));

    if (control.hasUrl()) {
      htmlAttribtues.addAtEnd(HtmlAttribute.withNameAndValue("href", control.getUrl()));
    }

    return htmlAttribtues;
  }

  private HtmlAttribute createTargetHtmlAttributeForControl(final ILink control) {

    final var target = control.getTarget();

    return createTargetHtmlAttributeForTarget(target);
  }

  private HtmlAttribute createTargetHtmlAttributeForTarget(final LinkTarget target) {
    return switch (target) {
      case CURRENT_TAB ->
        HtmlAttribute.withNameAndValue("target", "_self");
      case NEW_TAB ->
        HtmlAttribute.withNameAndValue("target", "_blank");
      default ->
        throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.TARGET, target);
    };
  }
}
