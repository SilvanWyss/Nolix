package ch.nolix.coreapi.web.css;

import ch.nolix.coreapi.container.base.IContainer;

public interface ICssRule {

  IContainer<? extends ICssProperty> getProperties();

  String getSelector();

  ICssRule withPrefixedSelector(String selectorPrefix);
}
