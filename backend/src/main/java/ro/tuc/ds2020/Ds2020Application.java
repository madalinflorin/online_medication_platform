package ro.tuc.ds2020;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.tuc.ds2020.controllers.Subscriber;
import ro.tuc.ds2020.repositories.MedicalPlansDetailRepository;
import ro.tuc.ds2020.services.MedicationPlanService;
import ro.tuc.ds2020.services.MedicationPlanServiceImpl;

import java.util.TimeZone;

@SpringBootApplication
@Validated
@CrossOrigin
public class Ds2020Application extends SpringBootServletInitializer {

    private final MedicalPlansDetailRepository medicalPlansDetailRepository;


    @Autowired
    public Ds2020Application(MedicalPlansDetailRepository medicalPlansDetailRepository) {

        this.medicalPlansDetailRepository = medicalPlansDetailRepository;

    }
    @Bean(name = "/booking")
    HttpInvokerServiceExporter accountService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(new MedicationPlanServiceImpl(medicalPlansDetailRepository));
        exporter.setServiceInterface(MedicationPlanService.class);
        return exporter;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Ds2020Application.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowCredentials(true).allowedOrigins("*").allowedMethods("*");
            }
        };
    }

    public static void main(String[] args) throws Exception {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Subscriber.exchange();
        SpringApplication.run(Ds2020Application.class, args);
    }



}
