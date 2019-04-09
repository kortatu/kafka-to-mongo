#!/usr/bin/env bash

KAFKA_CONTAINER=$(docker ps | grep "wurstmeister/kafka" | awk '{print $1}')
docker exec -ti ${KAFKA_CONTAINER} /opt/kafka/bin/kafka-topics.sh --alter --zookeeper 192.168.0.68:2181 --partitions $2 --topic $1

