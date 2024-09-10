package io.azar.tooling.logback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.argument.StructuredArguments;
import net.logstash.logback.mask.ValueMasker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LogMaskApplication {

    private Logger logger = LoggerFactory.getLogger(LogMaskApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LogMaskApplication.class, args);
    }


    @PostMapping("/api/employee")
    public ResponseEntity<SaveEmployeeResponseDto> save(@RequestBody SaveEmployeeRequestDto request) throws JsonProcessingException {
        logger.error("incoming request payload", StructuredArguments.kv("payload", request));
        logger.error("To String incoming request payload {}" , request.toString());

      //  logger.error("message key is apikey=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxx1234f", new RuntimeException("stacktrace key is apikey=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxx1234f"));
        //logger.error("message key is Authorization=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0ZXN0X3VzZXIiLCJpc3MiOiJ0ZXN0X3NlcnZlciIsInBob25lIjoiU29tZUNvbGUiLCJleHAiOjE2OTQ3MDc0NDB9.l35Q87234567890", new RuntimeException("stacktrace key is Authorization=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0ZXN0X3VzZXIiLCJpc3MiOiJ0ZXN0X3NlcnZlciIsInBob25lIjoiU29tZUNvbGUiLCJleHAiOjE2OTQ3MDc0NDB9.l35Q87234567890"));

        String msg = new ObjectMapper().writeValueAsString(request);
//        System.out.println(msg);
        String log = "incoming request payload " + msg;
        logger.info(log);

        return new ResponseEntity<>(new SaveEmployeeResponseDto(), HttpStatus.OK);
    }
}
