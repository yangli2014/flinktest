package org.yang.li;

import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.yang.li.funcations.CountMap;
import org.yang.li.funcations.DocToIPPortMap;
import org.yang.li.message.FlowDocument;
import org.yang.li.utils.DataGenerator;

/**

 */
public class DataSetProcessor {
   public static void main(String[] args) throws Exception {
      ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
      DataSet<FlowDocument> docDataSet = env.fromCollection(DataGenerator.createMsgList()); //collect data
      //transform to (dst_port, src_ip) pairs and distinct
      DataSet<Tuple2<String, Integer>> ipPortSet = docDataSet.map(new DocToIPPortMap()).distinct(0, 1);
      //transform to each pair as count 1 and aggregate.
      DataSet<Tuple2<String, Integer>> countByIPSet = ipPortSet.map(new CountMap()).groupBy(0).sum(1);
      //sort and select top 5
      DataSet<Tuple2<String, Integer>> sorted = countByIPSet.sortPartition(1, Order.DESCENDING).setParallelism(1).first(5);

      System.out.println("Top 5 IP addresses with different port");
      sorted.collect().forEach(t2 -> System.out.println("IP: " + t2.f0 +" Port count: " + t2.f1));
   }
}
