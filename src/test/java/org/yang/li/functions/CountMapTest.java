package org.yang.li.functions;

import org.apache.flink.api.java.tuple.Tuple2;
import org.junit.Test;
import org.yang.li.funcations.CountMap;
import static org.junit.Assert.assertEquals;

public class CountMapTest {
   @Test
   public void testMap() throws Exception {
      CountMap map = new CountMap();
      Tuple2<String, Integer> data = new Tuple2<>("10.10.10.10", 5050);
      Tuple2<String, Integer> result = map.map(data);

      assertEquals(data.f0, result.f0);
      assertEquals(Integer.valueOf(1), result.f1);

   }
}
