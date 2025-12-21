package ch.nolix.systemapi.webgui.controltool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.cssmodel.ICssRule;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 * @param <C> is the type of the {@link IControl}s a {@link IControlCssBuilder}
 *            is for.
 * @param <S> is the type of the {@link IControlStyle} of the {@link IControl}s
 *            a {@link IControlCssBuilder} is for.
 */
public interface IControlCssBuilder<C extends IControl<C, S>, S extends IControlStyle<S>> {
  IContainer<ICssRule> createCssRulesForControl(C control);
}
