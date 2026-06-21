/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

public record EntityLoadingDto(String id, String saveStamp, ExtendedIterable<FieldDto> contentFields) {
}
