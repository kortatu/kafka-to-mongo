#!/usr/bin/env bash

echo Producing Messages...
echo Press q to exit.

while [[ true ]]; do
    FINISH_IP=$RANDOM
    let "FINISH_IP %= 255"
    selectedIp="\"192.168.0.${FINISH_IP}\""
    echo "My selected ip ${selectedIp}"
    cat test_kafa_message.json | jq -c "{name: .name, ip: ${selectedIp}}" | kafkacat -P -b localhost:9092 -t aTopic -P &
    read -t 0.01 -N 1 input
    if [[ $input = "q" ]] || [[ $input = "Q" ]]; then
        # The following line is for the prompt to appear on a new line.
        echo
        break
    fi
done
