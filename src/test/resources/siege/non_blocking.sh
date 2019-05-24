#!/usr/bin/env bash
siege -c 300  -t 40s -l -m "Non Blocking 300" http://localhost:8081/messages/search/byIp/192.168.0.82