/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controltool;

import ch.nolix.baseapi.css.cssmodel.ICssRule;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 * @param <C> the type of the {@link Control}s a {@link IControlCssBuilder}
 *            is for.
 * @param <S> the type of the {@link IControlStyle} of the {@link Control}s
 *            a {@link IControlCssBuilder} is for.
 */
public interface IControlCssBuilder<C extends Control<C, S>, S extends IControlStyle<S>> {
  ExtendedIterable<ICssRule> createCssRulesForControl(C control);
}
