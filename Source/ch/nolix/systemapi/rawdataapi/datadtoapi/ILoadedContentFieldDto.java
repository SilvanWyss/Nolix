package ch.nolix.systemapi.rawdataapi.datadtoapi;

import java.util.Optional;

public interface ILoadedContentFieldDto {

  String getColumnName();

  Optional<Object> getOptionalValue();
}
