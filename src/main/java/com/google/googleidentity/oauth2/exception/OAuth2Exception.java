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

package com.google.googleidentity.oauth2.exception;

import java.util.Map;
import java.util.Optional;

/**
 * OAuth2 Exceptions. Will be deal in try catch clause using
 * a function in {@link com.google.googleidentity.oauth2.util.OAuth2Utils} named returnHttpError.
 * code: Http Error Code
 * errorType: OAuth2 Error Type(example:"invalid grant")
 * errorInfo: Detail error cause.
 *
 */
public class OAuth2Exception extends Exception{

    private String errorType;

    private int httpCode;

    private String errorInfo = null;

    public OAuth2Exception(){
        super();
    }

    public OAuth2Exception(int httpCode, String errorType, String errorInfo){
        super();
        this.httpCode = httpCode;
        this.errorType = errorType;
        this.errorInfo = errorInfo;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public Optional<String> getErrorType(){
        return Optional.ofNullable(errorType);
    }

    public Optional<String> getErrorInfo(){
        return Optional.ofNullable(errorInfo);
    }
}