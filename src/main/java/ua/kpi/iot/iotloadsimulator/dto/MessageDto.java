package ua.kpi.iot.iotloadsimulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

  private String address;
  private ZonedDateTime timestamp;
  private Double temperature;
  private Double humidity;

  @Override
  public String toString() {
    return new StringJoiner(";")
        .add(Objects.isNull(address) ? "" : address)
        .add(Objects.isNull(timestamp) ? "" : DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSx").format(timestamp))
        .add(Objects.isNull(temperature) ? "" : String.format("%.2f", temperature))
        .add(Objects.isNull(humidity) ? "" : String.format("%.2f", humidity))
        .toString();
  }

}
