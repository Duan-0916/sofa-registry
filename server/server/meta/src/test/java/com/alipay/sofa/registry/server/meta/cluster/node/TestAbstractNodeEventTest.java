/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.registry.server.meta.cluster.node;

import com.alipay.sofa.registry.log.Logger;
import com.alipay.sofa.registry.log.LoggerFactory;
import com.alipay.sofa.registry.server.meta.lease.impl.DefaultLeaseManagerTest;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAbstractNodeEventTest {

    private Logger logger = LoggerFactory.getLogger(TestAbstractNodeEventTest.class);

    @Test
    public void testGetNode() {
        DefaultLeaseManagerTest.SimpleNode simpleNode = new DefaultLeaseManagerTest.SimpleNode(
            "127.0.0.1");
        AbstractNodeEvent event = new AbstractNodeEvent(simpleNode) {
        };
        Assert.assertEquals(simpleNode, event.getNode());
        logger.info("[testGetNode] {}", event);
    }
}