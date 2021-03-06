/*
    Copyright 2020 Google LLC

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.google.googleidentity.oauth2.client.seed;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Hashing;
import com.google.googleidentity.oauth2.client.ClientDetails;
import com.google.googleidentity.oauth2.client.ClientDetailsService;
import com.google.googleidentity.oauth2.client.InMemoryClientDetailsService;
import com.google.googleidentity.oauth2.util.OAuth2Enums.GrantType;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class InMemoryClientSeedModule extends AbstractModule {

  private static final String TESTCLIENTID = "google";
  private static final String TESTSECRET = "secret";
  private static final ImmutableList<String> TESTSCOPES = ImmutableList.of("read");
  private static final ImmutableList<GrantType> TESTGRANTTYPES =
      ImmutableList.of(
          GrantType.AUTHORIZATION_CODE,
          GrantType.IMPLICIT,
          GrantType.REFRESH_TOKEN,
          GrantType.JWT_ASSERTION);

  private static final String TESTREDIRECTURI = "https://www.google.com";
  private static final String TESTREDIRECTURI1 = "https://oauth-redirect.googleusercontent.com/r";
  private static final String TESTREDIRECTURI2 = "https://oauth-redirect-sandbox.googleusercontent.com/r";
  private static final String RISCURI = "https://risc.googleapis.com/v1beta/events:report";
  private static final String RISCAUD = "google_account_linking";


  @Override
  protected void configure() {}

  @Provides
  @Singleton
  public ClientDetailsService getClientDetailsService(
      InMemoryClientDetailsService clientDetailsService) {

    ClientDetails client =
        ClientDetails.newBuilder()
            .setClientId(TESTCLIENTID)
            .setSecret(Hashing.sha256().hashString(TESTSECRET, Charsets.UTF_8).toString())
            .addAllScopes(TESTSCOPES)
            .setIsScoped(true)
            .addAllGrantTypes(TESTGRANTTYPES)
            .addRedirectUris(TESTREDIRECTURI)
            .addRedirectUris(TESTREDIRECTURI1)
            .addRedirectUris(TESTREDIRECTURI2)
            .setRiscUri(RISCURI)
            .setRiscAud(RISCAUD)
            .build();
    clientDetailsService.addClient(client);
    return clientDetailsService;
  }
}
