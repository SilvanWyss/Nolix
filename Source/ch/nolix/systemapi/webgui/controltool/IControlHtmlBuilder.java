/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controltool;

import ch.nolix.baseapi.html.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 * @param <C> the type of the {@link Control}s a {@link IControlHtmlBuilder}
 *            is for.
 */
public interface IControlHtmlBuilder<C extends Control<C, ?>> {
  IHtmlElement createHtmlElementForControl(C control);
}
