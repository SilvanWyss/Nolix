/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.validationlabel;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webatomiccontrol.validationlabel.IValidationLabel;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class ValidationLabelHtmlBuilder implements IControlHtmlBuilder<IValidationLabel> {
  /**
   * {@inheritDoc}
   */
  @Override
  public IHtmlElement createHtmlElementForControl(final IValidationLabel control) {
    return //
    HtmlElement.withTypeAndInnerText(
      HtmlElementTypeCatalog.DIV,
      ValidationLabelHtmlBuilderHelper.getHtmlDivInnerTextForValidationLabel(control));
  }
}
