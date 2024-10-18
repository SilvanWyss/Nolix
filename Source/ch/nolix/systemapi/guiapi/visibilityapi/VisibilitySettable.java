package ch.nolix.systemapi.guiapi.visibilityapi;

public interface VisibilitySettable<VS extends VisibilitySettable<VS>> extends VisibilityRequestable {

  VS setInvisible();

  VS setVisible();
}
