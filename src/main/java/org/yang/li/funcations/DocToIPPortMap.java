package org.yang.li.funcations;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.yang.li.message.FlowDocument;

public class DocToIPPortMap implements MapFunction<FlowDocument, Tuple2<String, Integer>> {
   @Override
   public Tuple2<String, Integer> map(final FlowDocument flowDocument) throws Exception {
      return new Tuple2<>(flowDocument.src_address, flowDocument.dst_port);
   }
}
