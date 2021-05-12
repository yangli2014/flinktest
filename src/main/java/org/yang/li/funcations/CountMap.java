package org.yang.li.funcations;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;

//Map each entry as one to count
public class CountMap implements MapFunction<Tuple2<String, Integer>, Tuple2<String, Integer>> {

   @Override
   public Tuple2<String, Integer> map(final Tuple2<String, Integer> t2) throws Exception {
      return new Tuple2<>(t2.f0, 1);
   }
}
