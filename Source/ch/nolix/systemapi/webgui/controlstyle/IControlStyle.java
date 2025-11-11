package ch.nolix.systemapi.webgui.controlstyle;

public interface IControlStyle<S extends IControlStyle<S>>
extends
IBackgroundStyle<S>,
IBorderStyle<S>,
IControlBaseStyle<S>,
ICornerStyle<S>,
IShadowStyle<S>,
ISizeStyle<S>,
IPaddingStyle<S> {
  //This interface is just an union of other interfaces.
}
