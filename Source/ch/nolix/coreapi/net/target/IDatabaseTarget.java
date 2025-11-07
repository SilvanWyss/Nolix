package ch.nolix.coreapi.net.target;

import ch.nolix.coreapi.attribute.mandatoryattribute.IDatabaseNameHolder;

public interface IDatabaseTarget extends IAuthenticationServerTarget, IDatabaseNameHolder {
  //This interface is just an union of other interfaces.
}
