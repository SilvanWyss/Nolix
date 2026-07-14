/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.validationlabel;

import ch.nolix.base.web.htmlmodel.HtmlElement;
import ch.nolix.baseapi.web.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.baseapi.web.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.control.validationlabel.IValidationLabel;
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
