package org.yang.li.message;

/**
 * Simplified version of FlowDocument for the Assignment
 */
public class FlowDocument {
   public int engine_id;
   public long timestamp;
   public int dst_port;
   public String src_address;

   @Override
   public String toString() {
      return "FlowDocument{" +
            "engine_id=" + engine_id +
            ", timestamp=" + timestamp +
            ", dst_port=" + dst_port +
            ", src_address='" + src_address + '\'' +
            '}';
   }
}
