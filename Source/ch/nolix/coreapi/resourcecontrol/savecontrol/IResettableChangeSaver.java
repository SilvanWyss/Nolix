package ch.nolix.coreapi.resourcecontrol.savecontrol;

import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.state.statemutation.Resettable;

public interface IResettableChangeSaver extends GroupCloseable, IChangeSaver, Resettable {

  int getSaveCount();
}
