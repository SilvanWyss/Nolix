/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.controlstyle;

/**
 * @author Silvan Wyss
 * @param <S> the type of a {@link ControlStyle}.
 */
public interface ControlStyle<S extends ControlStyle<S>>
extends
BackgroundStyle<S>,
BorderStyle<S>,
ControlBaseStyle<S>,
CornerStyle<S>,
ShadowStyle<S>,
SizeStyle<S>,
PaddingStyle<S> {
  // This interface is a dedicated union of other interfaces.
}
