package ch.nolix.systemapi.webgui.controlstyle;

public interface IControlStyle<S extends IControlStyle<S>>
extends IBackgroundStyle<S>, IBorderStyle<S>, IControlHeadStyle<S>, ICornerStyle<S>, ISizeStyle<S>, IPaddingStyle<S> {
}
