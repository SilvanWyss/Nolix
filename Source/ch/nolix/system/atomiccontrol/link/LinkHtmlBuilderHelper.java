/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.link;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.web.htmlmodel.HtmlAttribute;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.web.htmlattribute.LinkTarget;
import ch.nolix.baseapi.web.htmlcatalog.HtmlAttributeNameCatalog;
import ch.nolix.baseapi.web.htmlmodel.IHtmlAttribute;
import ch.nolix.systemapi.atomiccontrol.link.ILink;

/**
 * @author Silvan Wyss
 */
public final class LinkHtmlBuilderHelper {
  private LinkHtmlBuilderHelper() {
  }

  public static ExtendedIterable<? extends IHtmlAttribute> createHtmlAttributesForControl(final ILink control) {
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
    return //
    switch (target) {
      case CURRENT_TAB ->
        HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.TARGET, "_self");
      case NEW_TAB ->
        HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.TARGET, "_blank");
      default ->
        throw InvalidArgumentException.forArgumentAndArgumentName(target, LowerCaseVariableNameCatalog.TARGET);
    };
  }
}
