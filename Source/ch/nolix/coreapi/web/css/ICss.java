package ch.nolix.coreapi.web.css;

import ch.nolix.coreapi.container.base.IContainer;

public interface ICss {

  IContainer<? extends ICssRule> getRules();

  String toStringWithoutEnclosingBrackets();
}
