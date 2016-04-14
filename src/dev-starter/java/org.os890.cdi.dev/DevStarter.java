/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.os890.cdi.dev;

import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.undertow.WARArchive;

/**
 * tested with intellij
 */
public class DevStarter {
    public static void main(String[] args) throws Exception {
        System.setProperty("faces.PROJECT_STAGE", "Development"); //only for ds, because mojarra ignores it

        //a custom port is broken - see SWARM-333
        //workaround: -Dswarm.http.port
        //+ for this demo also: -DhttpPort=...
        //System.setProperty("swarm.http.port", ConfigResolver.getProjectStageAwarePropertyValue("httpPort"));
        Container container = new Container();

        String context = ConfigResolver.getProjectStageAwarePropertyValue("serviceRoot");
        WARArchive warArchive = container.createDefaultDeployment().as(WARArchive.class).setContextRoot(context);

        container.start();
        container.deploy(warArchive);
    }
}
