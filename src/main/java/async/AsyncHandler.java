/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package async;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * @author jsanda
 */
@Path("/")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class AsyncHandler {

    private static final ListeningExecutorService threadPool = MoreExecutors.listeningDecorator(
            Executors.newFixedThreadPool(4));

    @POST
    @Path("/{tenantId}/metrics/numeric/{id}/data")
    public void addData(
            @Suspended final AsyncResponse asyncResponse,
            @PathParam("tenantId") final String tenantId,
            @PathParam("id") String id,
            List<Integer> values) {

        Callable<Response> callable = () -> Response.status(Response.Status.OK).build();
        ListenableFuture<Response> future = threadPool.submit(callable);
        Futures.addCallback(future, new FutureCallback<Response>() {
            @Override
            public void onSuccess(Response response) {
                asyncResponse.resume(response);
            }

            @Override
            public void onFailure(Throwable t) {
                String msg = "Failed to perform operation due to an error: " + t.getMessage();
                asyncResponse.resume(Response.serverError().entity(msg).build());
            }
        }, threadPool);
    }

}
