#Coda45 Prometheus Adapter

This is a simple micronaut application written in kotlin that scrapes the Coda45 API to get events and connection stats.

## Features
- DOCSIS events outputted to STDOUT
- DOCSIS downstream overview sent to prometheus

## Planned Features
- DOCSIS upstream overview sent to prometheus

## Prometheus Metrics
- `api_response_time` - **Histogram**: API response time in milliseconds
- `docsis_downstream_frequency` - **Gauge**: DOCSIS downstream frequency in Hz
- `docsis_downstream_signal_strength`- **Gauge**: DOCSIS downstream signal strength in dBmV
- `docsis_downstream_snr` - **Gauge**: DOCSIS downstream signal to noise ratio in dB
- `docsis_downstream_channelId` - **Gauge**: DOCSIS downstream channel id

## Configuration
todo

## How to use
todo

## Building
Build this project by running `./gradlew build`