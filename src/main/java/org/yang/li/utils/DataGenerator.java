package org.yang.li.utils;

import org.yang.li.message.FlowDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
   public static List<FlowDocument> createMsgList() {
      List<FlowDocument> list = new ArrayList<>();
      Random random = new Random();
      int minPort = 5000, maxPort = 5100;
      int maxEngineID = 10;
      for(int i=0; i<100; i++) {
         FlowDocument document = new FlowDocument();
         document.engine_id = random.nextInt(maxEngineID);
         document.timestamp = System.currentTimeMillis();
         document.dst_port = random.nextInt(maxPort-minPort) + minPort;
         document.src_address = "192.168.6." + random.nextInt(10);
         list.add(document);
      }
      return list;
   }
}
