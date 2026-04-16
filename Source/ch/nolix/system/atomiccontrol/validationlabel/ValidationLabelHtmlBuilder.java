/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.validationlabel;

import ch.nolix.base.web.htmlelementmodel.HtmlElement;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.baseapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.atomiccontrol.validationlabel.IValidationLabel;
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
