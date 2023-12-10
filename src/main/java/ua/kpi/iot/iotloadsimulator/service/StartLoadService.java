package ua.kpi.iot.iotloadsimulator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ua.kpi.iot.iotloadsimulator.property.SensorsConnectionProperty;
import ua.kpi.iot.iotloadsimulator.thread.ClimateThread;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StartLoadService {

  private final SensorsConnectionProperty sensorsConnectionProperty;

  @EventListener(ApplicationReadyEvent.class)
  public void loadServer() {
    log.info("Start loading server with {} sensors", sensorsConnectionProperty.addresses().size());

    List<ClimateThread> threads = sensorsConnectionProperty.addresses().stream()
        .map(address -> new ClimateThread(sensorsConnectionProperty.connection(), address))
        .toList();
    threads
        .forEach(Thread::start);

  }
}
