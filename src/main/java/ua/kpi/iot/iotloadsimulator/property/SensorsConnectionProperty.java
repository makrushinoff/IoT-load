package ua.kpi.iot.iotloadsimulator.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "sensors-connection")
public record SensorsConnectionProperty(ConnectionProperty connection, List<String> addresses) {

  public record ConnectionProperty (
      String brokerUri,
      String topicName,
      String username,
      String password
  ) {}

}
