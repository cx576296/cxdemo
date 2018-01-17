import com.cx.DBACTION.EntityAction;
import com.cx.DBENTITY.Entity;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaComsumer {

    public static void comsumer(){

        //配置Consumer
        Properties props = new Properties();
        props.put("zookeeper.connect", "10.84.3.18:2181,10.84.3.19:2181,10.84.3.20:2181");
        // group 代表一个消费组
        props.put("group.id", "roop");

        // kafka重试超时,总时间要大于zk超时
        // rebalance时的最大尝试次数
        props.put("rebalance.max.retries", "3");
        props.put("rebalance.backoff.ms", "2000");

        // zk连接超时
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "2000");
        //往zookeeper上写offset的频率
        props.put("auto.commit.interval.ms", "1000");
        //如果offset出了返回，则 smallest: 自动设置reset到最小的offset. largest : 自动设置offset到最大的offset. 其它值不允许，会抛出异常.
        props.put("auto.offset.reset", "smallest");
        ConsumerConfig conf = new ConsumerConfig(props);
        ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(conf);
        Map<String, Integer> topicStrams = new HashMap<String, Integer>();
        topicStrams.put("shanxitest", 1);

        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreamsMap = consumer.createMessageStreams(topicStrams);
        List<KafkaStream<byte[], byte[]>> messageStreams = messageStreamsMap.get("shanxitest");
        //阻塞
        for(final KafkaStream<byte[], byte[]> kafkaStream : messageStreams){
           new Thread(new Runnable() {
               @Override
               public void run() {
                   for (MessageAndMetadata<byte[], byte[]> mm : kafkaStream) {
                       //连接数据库
                       EntityAction entityAction = new EntityAction();
                       String msg = new String(mm.message());
                       System.out.println("Comsumer:" + "\t" + msg);

                       String[] msgarray = msg.split(" ");
                       Entity entity = new Entity();
                       //entity.setId(Integer.parseInt(msgarray[0]));
                       entity.setName(msgarray[0]);
                       entity.setPwd(msgarray[1]);

                       try {
                           entityAction.add(entity);
                           System.out.println("add entity");
                       } catch (Exception e) {
                           e.printStackTrace();
                       }finally{
                           try{
                              entityAction.close();
                           }catch (SQLException e){
                               e.printStackTrace();
                           }
                       }
                   }
               }
           }).start();
        }
    }
}

