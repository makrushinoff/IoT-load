package ua.kpi.iot.iotloadsimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("ua.kpi.iot.iotloadsimulator.property")
public class IoTLoadSimulatorApplication {

  public static void main(String[] args) {
    SpringApplication.run(IoTLoadSimulatorApplication.class, args);
  }

}
