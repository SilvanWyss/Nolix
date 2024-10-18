package ch.nolix.coreapi.resourcecontrolapi.resourcepoolapi;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;

public interface IResourcePool<R extends GroupCloseable> extends GroupCloseable {

  R borrowResource();
}
