package org.yang.li.functions;

import org.apache.flink.api.java.tuple.Tuple2;
import org.junit.Assert;
import org.junit.Test;
import org.yang.li.funcations.DocToIPPortMap;
import org.yang.li.message.FlowDocument;


public class DocToIPPortMapTest {
   @Test
   public void testMap() throws Exception {
      DocToIPPortMap map = new DocToIPPortMap();
      FlowDocument doc = new FlowDocument();
      doc.src_address = "10.10.10.10";
      doc.dst_port = 5050;
      doc.timestamp = System.currentTimeMillis();
      doc.engine_id = 100;
      Tuple2<String, Integer> result = map.map(doc);
      Assert.assertEquals(doc.src_address, result.f0);
      Assert.assertEquals(Integer.valueOf(doc.dst_port), result.f1);
   }
}
