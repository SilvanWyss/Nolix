package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi.Abstractable;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi.ContentTypeAssignable;
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableCardinalityHolder;
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.MandatorynessRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;

public interface IAbstractableField
extends
Abstractable<IAbstractableField>,
ContentTypeAssignable<IAbstractableField>,
EmptinessRequestable,
IFluentMutableNameHolder<IAbstractableField>,
IFluentMutableCardinalityHolder<IAbstractableField>,
MandatorynessRequestable {

  IAbstractableField getStoredBaseField();

  IAbstractableObject getStoredParentObject();

  IContent getStoredContent();

  boolean inheritsFromBaseField();
}
