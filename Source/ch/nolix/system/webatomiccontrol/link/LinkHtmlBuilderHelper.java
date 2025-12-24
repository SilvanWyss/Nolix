package ch.nolix.system.webatomiccontrol.link;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.web.htmlattribute.LinkTarget;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlAttribute;
import ch.nolix.systemapi.webatomiccontrol.link.ILink;

/**
 * @author Silvan Wyss
 */
public final class LinkHtmlBuilderHelper {
  private LinkHtmlBuilderHelper() {
  }

  public static IContainer<? extends IHtmlAttribute> createHtmlAttributesForControl(final ILink control) {
    final ILinkedList<IHtmlAttribute> htmlAttribtues = LinkedList.createEmpty();

    htmlAttribtues.addAtEnd(createTargetHtmlAttributeForControl(control));

    if (control.hasUrl()) {
      htmlAttribtues.addAtEnd(HtmlAttribute.withNameAndValue("href", control.getUrl()));
    }

    return htmlAttribtues;
  }

  private static HtmlAttribute createTargetHtmlAttributeForControl(final ILink control) {
    final var target = control.getTarget();

    return createTargetHtmlAttributeForTarget(target);
  }

  private static HtmlAttribute createTargetHtmlAttributeForTarget(final LinkTarget target) {
    return switch (target) {
      case CURRENT_TAB ->
        HtmlAttribute.withNameAndValue("target", "_self");
      case NEW_TAB ->
        HtmlAttribute.withNameAndValue("target", "_blank");
      default ->
        throw InvalidArgumentException.forArgumentAndArgumentName(target, LowerCaseVariableCatalog.TARGET);
    };
  }
}
