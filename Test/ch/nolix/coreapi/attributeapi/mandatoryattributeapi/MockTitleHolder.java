package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

public final class MockTitleHolder implements ITitleHolder {

  private final String title;

  private MockTitleHolder(final String title) {
    this.title = title;
  }

  public static MockTitleHolder withTitle(final String title) {
    return new MockTitleHolder(title);
  }

  @Override
  public String getTitle() {
    return title;
  }
}
