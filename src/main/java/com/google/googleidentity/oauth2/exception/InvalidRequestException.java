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

/**
 * OAuth2Exception with type "invalid_request"
 */
public class InvalidRequestException extends OAuth2Exception {

    enum ErrorCode{
        NO_REDIRECT_URI,
        REDIRECT_URI_MISMATCH,
        NO_CLIENT_ID,
        NONEXISTENT_CLIENT_ID,
        NO_AUTHORIZATION_REQUEST,
        NO_USER_CONSENT,
        NO_RESPONSE_TYPE
    }

    private static final String INVALID_REQUEST = "invalid_request";

    private final ErrorCode errorCode;

    public InvalidRequestException(String errorCode) {
        super();
        this.errorCode = ErrorCode.valueOf(errorCode);
    }

    public String getErrorType(){
        return INVALID_REQUEST;
    }

    public String getErrorDescription() {
        switch(errorCode){
            case NO_RESPONSE_TYPE:
                return "No Response Type!";
            case NO_CLIENT_ID:
                return "No Client ID!";
            case NONEXISTENT_CLIENT_ID:
                return "Client ID does not exist!";
            case NO_REDIRECT_URI:
                return "No Redirect Uri!";
            case REDIRECT_URI_MISMATCH:
                return "Redirect Uri Mismatch!";
            default:
                throw new IllegalArgumentException(String.valueOf(errorCode));
        }
    }

    /**
     * mainly use for tests
     */
    @Override
    public boolean equals(Object object) {
        if(!(object instanceof  InvalidRequestException)){
            return false;
        }

        return ((InvalidRequestException) object).hashCode() == hashCode();
    }

    /**
     * mainly use for tests
     */
    @Override
    public int hashCode() {
        return errorCode.hashCode();
    }

}