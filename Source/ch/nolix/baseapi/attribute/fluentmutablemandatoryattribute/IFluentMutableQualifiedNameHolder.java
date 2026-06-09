/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.IQualifiedNameHolder;

/**
 * A {@link IFluentMutableQualifiedNameHolder} is a {@link IQualifiedNameHolder}
 * whose name can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableQualifiedNameHolder}
 */
public interface IFluentMutableQualifiedNameHolder<H extends IFluentMutableQualifiedNameHolder<H>>
extends IFluentMutableNameHolder<H>, IQualifiedNameHolder {
  //This interface is a dedicated union of other interfaces.
}
