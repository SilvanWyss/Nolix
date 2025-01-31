package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelbasepi.CATEGORIZABLE;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelbasepi.ContentTypeAssignable;
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableCardinalityHolder;
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.MandatorynessRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;

public interface ICategorizableField
extends
CATEGORIZABLE<ICategorizableField>,
ContentTypeAssignable<ICategorizableField>,
EmptinessRequestable,
IFluentMutableNameHolder<ICategorizableField>,
IFluentMutableCardinalityHolder<ICategorizableField>,
MandatorynessRequestable {

  ICategorizableField getStoredBaseField();

  ICategorizableObject getStoredParentObject();

  IContent getStoredContent();

  boolean inheritsFromBaseField();
}
