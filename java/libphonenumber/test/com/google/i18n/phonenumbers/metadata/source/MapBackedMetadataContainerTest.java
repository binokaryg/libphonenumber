/*
 * Copyright (C) 2022 The Libphonenumber Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.i18n.phonenumbers.metadata.source;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import com.google.i18n.phonenumbers.Phonemetadata.PhoneMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MapBackedMetadataContainerTest {

  private static final String REGION_CODE = "US";
  private static final Integer COUNTRY_CODE = 41;
  private static final PhoneMetadata PHONE_METADATA =
      PhoneMetadata.newBuilder().setId(REGION_CODE).setCountryCode(COUNTRY_CODE);

  @Test
  public void getMetadataBy_shouldReturnNullForNullRegionCode() {
    assertNull(MapBackedMetadataContainer.byRegionCode().getMetadataBy(null));
  }

  @Test
  public void getMetadataBy_shouldReturnNullForNonExistingRegionCode() {
    assertNull(MapBackedMetadataContainer.byRegionCode().getMetadataBy(REGION_CODE));
  }

  @Test
  public void getMetadataBy_shouldReturnMetadataForExistingRegionCode() {
    MapBackedMetadataContainer<String> metadataContainer =
        MapBackedMetadataContainer.byRegionCode();

    metadataContainer.accept(PHONE_METADATA);

    assertSame(PHONE_METADATA, metadataContainer.getMetadataBy(REGION_CODE));
  }

  @Test
  public void getMetadataBy_shouldReturnNullForNullCountryCode() {
    assertNull(MapBackedMetadataContainer.byCountryCallingCode().getMetadataBy(null));
  }

  @Test
  public void getMetadataBy_shouldReturnNullForNonExistingCountryCode() {
    assertNull(MapBackedMetadataContainer.byCountryCallingCode().getMetadataBy(COUNTRY_CODE));
  }

  @Test
  public void getMetadataBy_shouldReturnMetadataForExistingCountryCode() {
    MapBackedMetadataContainer<Integer> metadataContainer =
        MapBackedMetadataContainer.byCountryCallingCode();

    metadataContainer.accept(PHONE_METADATA);

    assertSame(PHONE_METADATA, metadataContainer.getMetadataBy(COUNTRY_CODE));
  }
}