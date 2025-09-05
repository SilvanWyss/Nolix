package ch.nolix.coreapitest.attributeapi.mandatoryattributeapi;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

public final class MockNameHolder implements INameHolder {
  private final String name;

  private MockNameHolder(final String name) {
    this.name = name;
  }

  public static MockNameHolder withName(final String name) {
    return new MockNameHolder(name);
  }

  @Override
  public String getName() {
    return name;
  }
}
