package org.yang.li;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamUtils;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.yang.li.funcations.CountMap;
import org.yang.li.funcations.DocToIPPortMap;
import org.yang.li.funcations.IPPortUniqueFilter;
import org.yang.li.message.FlowDocument;
import org.yang.li.utils.DataGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StreamProcessor {
   public static void main(String[] args) throws Exception {
      StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
      DataStream<FlowDocument> docDataStream = env.fromCollection(DataGenerator.createMsgList());
      List<Tuple2<String, Integer>> list = new ArrayList<>();
      DataStream<Tuple2<String, Integer>> ipPortStream = docDataStream.map(new DocToIPPortMap())
            .filter(new IPPortUniqueFilter());
      DataStream<Tuple2<String, Integer>> counterStream = ipPortStream.map(new CountMap())
            .keyBy(v->v.f0)
            .sum(1);
      Iterator<Tuple2<String, Integer>> myOutput = DataStreamUtils.collect(counterStream);
      while (myOutput.hasNext()) {
         list.add(myOutput.next());
      }

      Collections.sort(list, (x, y) -> x.f1.compareTo(y.f1));

      System.out.println("Top 5 IP addresses with different port");
      for(int i = list.size()-1; i > list.size()-5; i-- ) {
         Tuple2<String, Integer> t2 = list.get(i);
         System.out.println("IP: " + t2.f0 +" Port count: " + t2.f1);
      }
      counterStream.print().setParallelism(1);
      env.execute("test");
   }

}
