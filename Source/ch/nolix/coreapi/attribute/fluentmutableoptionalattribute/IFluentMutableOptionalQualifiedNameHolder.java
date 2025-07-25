package ch.nolix.coreapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalQualifiedNameHolder;

/**
 * A {@link IFluentMutableOptionalQualifiedNameHolder} is a
 * {@link IOptionalQualifiedNameHolder} whose name can be set and removed
 * programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2024-02-11
 * @param <H> is the type of a
 *            {@link IFluentMutableOptionalQualifiedNameHolder}.
 */
public interface IFluentMutableOptionalQualifiedNameHolder<H extends IFluentMutableOptionalQualifiedNameHolder<H>>
extends IFluentMutableOptionalNameHolder<H>, IOptionalQualifiedNameHolder {
}
