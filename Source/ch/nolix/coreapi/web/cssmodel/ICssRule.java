package ch.nolix.coreapi.web.cssmodel;

import ch.nolix.coreapi.container.base.IContainer;

public interface ICssRule {
  IContainer<? extends ICssProperty> getProperties();

  String getSelector();

  ICssRule withPrefixedSelector(String selectorPrefix);
}
