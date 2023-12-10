package ua.kpi.iot.iotloadsimulator.thread;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import ua.kpi.iot.iotloadsimulator.dto.MessageDto;
import ua.kpi.iot.iotloadsimulator.property.SensorsConnectionProperty;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
public class ClimateThread extends Thread {

  private final Random random = new Random(new Date().getTime());

  private final SensorsConnectionProperty.ConnectionProperty connectionProperty;
  private final String address;

  @Override
  public void run() {
    try {
      MqttClient subscriber = new MqttClient(connectionProperty.brokerUri(), address);
      MqttConnectOptions options = new MqttConnectOptions();
      options.setAutomaticReconnect(true);
      options.setCleanSession(true);
      options.setUserName(connectionProperty.username());
      options.setPassword(connectionProperty.password().toCharArray());
      subscriber.connect(options);
      log.info("Connected from {}", address);
      while(true) {
        try {
          Thread.sleep(random.nextInt(1000) + 500);
          MqttMessage message = new MqttMessage();
          message.setQos(1);
          MessageDto messageDto = this.generateWhetherDto();
          message.setPayload(messageDto.toString().getBytes());
          subscriber.publish(connectionProperty.topicName(), message);
          log.info("Send {}", messageDto);
        } catch (Exception e) {
          log.error("Error :", e);
        }
      }

    } catch (Exception e) {
      log.error("Error :", e);
    }
  }

  private MessageDto generateWhetherDto() {
    double temperature = random.nextDouble(ClimateRange.highestTemperature - ClimateRange.lowestTemperature) + ClimateRange.lowestTemperature;
    double humidity = random.nextDouble(ClimateRange.highestHumidity - ClimateRange.lowestHumidity) + ClimateRange.lowestHumidity;
    return new MessageDto(
        address,
        ZonedDateTime.now(),
        temperature,
        humidity
        );
  }
}
