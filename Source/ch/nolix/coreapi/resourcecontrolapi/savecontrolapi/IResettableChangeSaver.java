package ch.nolix.coreapi.resourcecontrolapi.savecontrolapi;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.stateapi.statemutationapi.Resettable;

public interface IResettableChangeSaver extends GroupCloseable, IChangeSaver, Resettable {

  int getSaveCount();
}
