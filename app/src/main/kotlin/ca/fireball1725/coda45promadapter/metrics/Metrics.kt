package ca.fireball1725.coda45promadapter.metrics

import com.doordash_oss.metricsprocessor.Metric
import com.doordash_oss.metricsprocessor.MetricType

@Metric(MetricType.Histogram, labels = ["url"])
object api_response_time

@Metric(MetricType.Gauge, labels = ["portId"])
object docsis_downstream_frequency

@Metric(MetricType.Gauge, labels = ["portId"])
object docsis_downstream_signal_strength

@Metric(MetricType.Gauge, labels = ["portId"])
object docsis_downstream_snr

@Metric(MetricType.Gauge, labels = ["portId"])
object docsis_downstream_channelId
