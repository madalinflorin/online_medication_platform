import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.UUID;


public class Publisher {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {


        ConnectionFactory factory = new ConnectionFactory();
        //factory.setHost("localhost");
        factory.setHost("bonobo.rmq.cloudamqp.com");
        factory.setVirtualHost("odteqqbe");
        factory.setPassword("N6UR6kMooSpLoPStQ1-bQbc-iWBTejbr");
        factory.setUsername("odteqqbe");

        try (Connection connection = factory.newConnection();

             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            try {
                File myObj = new File("activity.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {


                    String message = myReader.nextLine();
                    String[] components = message.split("\t\t");


                    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

                    Date startDate = formatter.parse(components[0]);
                    Date endDate = formatter.parse(components[1]);


                    UUID id = UUID.fromString("2a9ccf2c-7878-449c-a686-c434e69b758a");
                    long start = startDate.getTime();
                    long end = endDate.getTime();
                    String activity = components[2].replaceAll("\\s$", "");

                    PatientActivity patientActivity = new PatientActivity(id,activity,start,end);

                    ObjectMapper objectMapper = new ObjectMapper();

                    message = objectMapper.writeValueAsString(patientActivity);


                    channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
                    System.out.println(" [x] Sent '" + message + "'");

                    Thread.sleep(1000);

                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }


    }
}
