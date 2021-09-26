package ro.tuc.ds2020.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ro.tuc.ds2020.dtos.PatientActivityDTO;
import ro.tuc.ds2020.entities.PatientActivity;
import ro.tuc.ds2020.entities.PatientActivitySuspicious;
import ro.tuc.ds2020.repositories.PatientActivityRepository;
import ro.tuc.ds2020.services.PatientService;
import ro.tuc.ds2020.services.SubscriberService;

import java.util.Date;

@Controller

public class Subscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(Subscriber.class);
    private static final String EXCHANGE_NAME = "logs";
    private static PatientActivityRepository patientActivityRepository;
    private static SubscriberService subscriberService;
    private static PatientService patientService;


    @Autowired
    public Subscriber(PatientActivityRepository patientActivityRepository,SubscriberService subscriberService,PatientService patientService) {

        this.patientActivityRepository = patientActivityRepository;
        this.subscriberService = subscriberService;
        this.patientService = patientService;


    }

    public static void exchange() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        //factory.setHost("localhost");
        factory.setHost("bonobo.rmq.cloudamqp.com");
        factory.setVirtualHost("odteqqbe");
        factory.setPassword("N6UR6kMooSpLoPStQ1-bQbc-iWBTejbr");
        factory.setUsername("odteqqbe");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages in Subscriber. To exit press CTRL+C");



        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            long timeSleeping = 0;
            long timeLeaving = 0;
            long timeBathroom = 0;

            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received in subscriber '" + message + "'");

            ObjectMapper objectMapper = new ObjectMapper();

            PatientActivityDTO personActivityDTO = objectMapper.readValue(message, PatientActivityDTO.class);


            PatientActivity patientActivity = new PatientActivity(personActivityDTO.getId_patient(),personActivityDTO.getActivity(),new Date(personActivityDTO.getStart()), new Date(personActivityDTO.getEnd()));


            if(patientActivity.getActivity().equals("Sleeping")){

                timeSleeping = personActivityDTO.getEnd() - personActivityDTO.getStart();
                if(timeSleeping >= 7*60*60*1000){


                    patientActivityRepository.save(patientActivity);
                    String username = patientService.getUsername(patientActivity.getId_patient());
                    PatientActivitySuspicious patient = new PatientActivitySuspicious(username,patientActivity.getActivity(),patientActivity.getStart_date(),patientActivity.getEnd_date());
                    patient.setWarningMessage("Sleeping more than 7 hours");
                    String messagePatient = objectMapper.writeValueAsString(patient);
                    subscriberService.sendMessage(messagePatient);

                }

            }

            if(patientActivity.getActivity().equals("Leaving")) {

                timeLeaving = personActivityDTO.getEnd() - personActivityDTO.getStart();

                if(timeLeaving >= 5*60*60*1000){


                    patientActivityRepository.save(patientActivity);
                    String username = patientService.getUsername(patientActivity.getId_patient());
                    PatientActivitySuspicious patient = new PatientActivitySuspicious(username,patientActivity.getActivity(),patientActivity.getStart_date(),patientActivity.getEnd_date());
                    patient.setWarningMessage("Leaving more than 5 hours");
                    String messagePatient = objectMapper.writeValueAsString(patient);
                    subscriberService.sendMessage(messagePatient);


                }

            }


            if(patientActivity.getActivity().equals("Toileting")||patientActivity.getActivity().equals("Showering")||patientActivity.getActivity().equals("Grooming")){

                timeBathroom = personActivityDTO.getEnd() - personActivityDTO.getStart();

                if(timeBathroom >= 30*60*1000){


                    patientActivityRepository.save(patientActivity);
                    String username = patientService.getUsername(patientActivity.getId_patient());
                    PatientActivitySuspicious patient = new PatientActivitySuspicious(username,patientActivity.getActivity(),patientActivity.getStart_date(),patientActivity.getEnd_date());
                    patient.setWarningMessage("Spend time in bathroom more than 30 minutes");
                    String messagePatient = objectMapper.writeValueAsString(patient);
                    subscriberService.sendMessage(messagePatient);

                }

            }


        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }


}
