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

package com.google.googleidentity.oauth2.authorizationcode;

import com.google.googleidentity.oauth2.request.OAuth2Request;

import java.util.Optional;

/** AuthorizationCode store interface */
interface CodeStore {

  /** @return the related request and delete the code */
  Optional<OAuth2Request> consumeCode(String code);

  /**
   * Set the code for the request, failed when a code is already exist, the code Service should
   * generate a new code and try again
   *
   * @return success or not
   */
  boolean setCode(String code, OAuth2Request request);

  /**
   * reset data
   */
  void reset();
}
