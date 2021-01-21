package com.alipay.sofa.registry.server.data.slot;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;

public final class SlotMetrics {
    private SlotMetrics() {
    }

    static final class Manager {
        private static final Counter   LEADER_UPDATE_COUNTER         = Counter
                                                                         .build()
                                                                         .namespace("data")
                                                                         .subsystem("slot")
                                                                         .name(
                                                                             "leader_update_total")
                                                                         .help(
                                                                             "count leader update")
                                                                         .register();

        private static final Gauge     LEADER_ASSIGN_GAUGE           = Gauge
                                                                         .build()
                                                                         .namespace("data")
                                                                         .subsystem("slot")
                                                                         .name(
                                                                             "leader_assign_total")
                                                                         .help("leader assign")
                                                                         .register();

        private static final Gauge     FOLLOWER_ASSIGN_GAUGE         = Gauge
                                                                         .build()
                                                                         .namespace("data")
                                                                         .subsystem("slot")
                                                                         .name(
                                                                             "follower_assign_total")
                                                                         .help("follower assign")
                                                                         .register();

        private static final Gauge     LEADER_MIGRATING_GAUGE        = Gauge
                                                                         .build()
                                                                         .namespace("data")
                                                                         .subsystem("slot")
                                                                         .name(
                                                                             "leader_migrating_total")
                                                                         .help(
                                                                             "count leader is migrating")
                                                                         .labelNames("slot")
                                                                         .register();

        private static final Counter   LEADER_MIGRATING_FAIL_COUNTER = Counter
                                                                         .build()
                                                                         .namespace("data")
                                                                         .subsystem("slot")
                                                                         .name(
                                                                             "leader_migrating_fail_total")
                                                                         .help(
                                                                             "count leader migrating fail")
                                                                         .labelNames("slot",
                                                                             "session").register();

        private static final Histogram LEADER_MIGRATING_HISTOGRAM    = Histogram
                                                                         .build()
                                                                         .namespace("data")
                                                                         .subsystem("slot")
                                                                         .name(
                                                                             "leader_migrating_secs")
                                                                         .help(
                                                                             "migrating in seconds.")
                                                                         .labelNames("slot")
                                                                         .register();

        static void observeLeaderUpdateCounter() {
            LEADER_UPDATE_COUNTER.inc();
        }

        static void observeLeaderAssignGauge(int num) {
            LEADER_ASSIGN_GAUGE.set(num);
        }

        static void observeFollowerAssignGauge(int num) {
            FOLLOWER_ASSIGN_GAUGE.set(num);
        }

        static void observeLeaderMigratingStart(int slotId) {
            LEADER_MIGRATING_GAUGE.labels(String.valueOf(slotId)).inc();
        }

        static void observeLeaderMigratingFinish(int slotId) {
            LEADER_MIGRATING_GAUGE.labels(String.valueOf(slotId)).dec();
        }

        static void observeLeaderMigratingFail(int slotId, String sessionIp) {
            LEADER_MIGRATING_FAIL_COUNTER.labels(String.valueOf(slotId), sessionIp).inc();
        }

        static void observeLeaderMigratingHistogram(int slotId, long millis) {
            // seconds
            LEADER_MIGRATING_HISTOGRAM.labels(String.valueOf(slotId)).observe(millis / 1000d);
        }
    }

    static final class Sync {
        private static final Counter SYNC_SESSION_ID_COUNTER      = Counter
                                                                      .build()
                                                                      .namespace("data")
                                                                      .subsystem("sync")
                                                                      .name("sync_session_id_total")
                                                                      .help(
                                                                          "count sync session dataInfoIds")
                                                                      .labelNames("slot")
                                                                      .register();

        private static final Counter SYNC_SESSION_ID_NUM_COUNTER  = Counter
                                                                      .build()
                                                                      .namespace("data")
                                                                      .subsystem("sync")
                                                                      .name(
                                                                          "sync_session_id_num_total")
                                                                      .help(
                                                                          "count sync session dataInfoId's num")
                                                                      .labelNames("slot")
                                                                      .register();

        private static final Counter SYNC_SESSION_PUB_COUNTER     = Counter
                                                                      .build()
                                                                      .namespace("data")
                                                                      .subsystem("sync")
                                                                      .name(
                                                                          "sync_session_pub_total")
                                                                      .help(
                                                                          "count sync session pubs")
                                                                      .labelNames("slot")
                                                                      .register();

        private static final Counter SYNC_SESSION_PUB_NUM_COUNTER = Counter
                                                                      .build()
                                                                      .namespace("data")
                                                                      .subsystem("sync")
                                                                      .name(
                                                                          "sync_session_pub_num_total")
                                                                      .help(
                                                                          "count sync session pub's num")
                                                                      .labelNames("slot")
                                                                      .register();

        static void observeSyncSessionId(int slotId, int idNum) {
            final String str = String.valueOf(slotId);
            SYNC_SESSION_ID_COUNTER.labels(str).inc();
            SYNC_SESSION_ID_NUM_COUNTER.labels(str).inc(idNum);
        }

        static void observeSyncSessionPub(int slotId, int pubNum) {
            final String str = String.valueOf(slotId);
            SYNC_SESSION_PUB_COUNTER.labels(str).inc();
            SYNC_SESSION_PUB_NUM_COUNTER.labels(str).inc(pubNum);
        }

    }
}