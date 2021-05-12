package org.yang.li.funcations;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.tuple.Tuple2;

import java.util.HashSet;
import java.util.Set;

public class IPPortUniqueFilter implements FilterFunction<Tuple2<String, Integer>> {
   private static Set<Tuple2<String, Integer>> set = new HashSet<>();

   @Override
   public boolean filter(final Tuple2<String, Integer> tuple2) throws Exception {
      return set.add(tuple2);
   }
}
