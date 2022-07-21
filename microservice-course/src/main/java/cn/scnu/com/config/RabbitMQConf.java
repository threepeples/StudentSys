package cn.scnu.com.config;

/**
 * @author: tt
 * @Date: 2022/06/27 13:50
 */
/**
 *
 */

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConf{

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    //    获取连接工厂
    @Bean
    public ConnectionFactory factory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(rabbitMQProperties.getHost());
        factory.setPort(rabbitMQProperties.getPort());
        factory.setUsername(rabbitMQProperties.getUserName());
        factory.setPassword(rabbitMQProperties.getPassword());
        return factory;
    }
    //    获取rabbitmqAdmin
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
        RabbitAdmin admin = new RabbitAdmin(factory);
        admin.setAutoStartup(true);
        return admin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate template =new RabbitTemplate(factory);
        return template;
    }

    /**
     * 不配置这个容器，启动项目时候，rabbitmq的管控台看不到动作
     * @param factory
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory factory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
        container.setConsumerTagStrategy(new ConsumerTagStrategy() {

            @Override
            public String createConsumerTag(String queue) {
                return null;
            }
        });
        return container;
    }

    //    定义交换器
    public static final String SHARP_EXANGE_TEST_001="sharpExchangeTest001";

    public static final String SHARP_EXANGE_TEST_002="sharpExchangeTest002";

    public static final String SHARP_EXANGE_TEST_003="sharpExchangeTest003";
    //    定义队列
    public static final String SHARP_QUEUE_TEST_001="sharpQueueTest001";

    public static final String SHARP_QUEUE_TEST_002="sharpQueueTest002";

    public static final String SHARP_QUEUE_TEST_003="sharpQueueTest003";

    //    定义路由键
    public static final String SHARP_ROUTINGKEY_TEST_001="sharp.test";

    public static final String SHARP_ROUTINGKEY_TEST_002="sharp.topic.#";

    @Bean(name="sharpExchangeTest001")
    public DirectExchange getExchage_001() {
        return new DirectExchange(SHARP_EXANGE_TEST_001, true, false, null);
    }
    @Bean(name="sharpExchangeTest002")
    public TopicExchange getExchange_002() {
        return new TopicExchange(SHARP_EXANGE_TEST_002, true, false, null);
    }
    @Bean(name="sharpExchangeTest003")
    public FanoutExchange getExchange_003() {
        return new FanoutExchange(SHARP_EXANGE_TEST_003, true, false, null);
    }
    @Bean(name="sharpQueueTest001")
    public Queue getQueue_001() {
        return new Queue(SHARP_QUEUE_TEST_001, true, false, false, null);
    }
    @Bean(name="sharpQueueTest002")
    public Queue getQueue_002() {
        return new Queue(SHARP_QUEUE_TEST_002, true, false, false, null);
    }
    @Bean(name="sharpQueueTest003")
    public Queue getQueue_003() {
        return new Queue(SHARP_QUEUE_TEST_003, true, false, false, null);
    }
    //    定义绑定
    @Bean
    public Binding getDirectBinding() {
        return BindingBuilder.bind(getQueue_001()).to(getExchage_001()).with(SHARP_ROUTINGKEY_TEST_001);
    }

    @Bean
    public Binding getTopicBinding() {
        return BindingBuilder.bind(getQueue_002()).to(getExchange_002()).with(SHARP_ROUTINGKEY_TEST_002);
    }

    @Bean
    public Binding getFauoutBinding() {
        return BindingBuilder.bind(getQueue_003()).to(getExchange_003());
    }
}