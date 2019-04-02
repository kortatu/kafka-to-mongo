#!/usr/bin/env bash

docker exec -ti kafka /opt/kafka/bin/kafka-topics.sh --create --zookeeper 192.168.0.68:2181 --replication-factor --partitions 4 --topic aTopic

