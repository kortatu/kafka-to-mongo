#!/usr/bin/env bash
siege -c 300 -d 1 -t 40s -l -m "Blocking 300" http://localhost:8081/messagesB/search/byIp/192.168.0.82