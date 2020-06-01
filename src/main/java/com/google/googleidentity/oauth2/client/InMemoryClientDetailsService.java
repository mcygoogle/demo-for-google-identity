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

package com.google.googleidentity.oauth2.client;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default InMemory ClientDetailsService for client information Store
 * An Implementation for {@link ClientDetailsService}
 */
@Singleton
public final class InMemoryClientDetailsService implements ClientDetailsService{

    private ConcurrentHashMap<String, ClientDetails> clientStore
            = new ConcurrentHashMap<>();

    @Override
    public Optional<ClientDetails> getClientByID(String clientID) {
        Optional<ClientDetails> client =
                Optional.ofNullable(clientStore.getOrDefault(clientID, null));
        return client;
    }

    @Override
    public boolean updateClient(ClientDetails client) {

        Preconditions.checkNotNull(client);

        String clientID = client.getClientId();
        if (clientID.isEmpty()) {
            throw new UnsupportedOperationException("Empty clientid");
        }
        if (!clientStore.containsKey(clientID)) {
            return false;
        }
        clientStore.put(clientID, client);
        return true;
    }

    @Override
    public boolean addClient(ClientDetails client) {

        Preconditions.checkNotNull(client);

        String clientID = client.getClientId();
        if (clientID.isEmpty()) {
            throw new UnsupportedOperationException("Empty clientid");
        }
        if (clientStore.containsKey(clientID)) {
            return false;
        }
        clientStore.put(clientID, client);
        return true;
    }

    @Override
    public List<ClientDetails> listClient() {
        return ImmutableList.copyOf(clientStore.values());
    }
}