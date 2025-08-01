package ch.nolix.coreapi.web.cssmodel;

import ch.nolix.coreapi.container.base.IContainer;

public interface ICss {

  IContainer<? extends ICssRule> getRules();

  String toStringWithoutEnclosingBrackets();
}
