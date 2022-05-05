package agk.wow.carrental;

import agk.wow.carrental.config.UUIDUtil;
import agk.wow.carrental.constant.UserType;
import agk.wow.carrental.model.Employee;
import agk.wow.carrental.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;


@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, PasswordEncoder bcryptEncoder) {
        return args -> {
            if (ObjectUtils.isEmpty(employeeRepository.findByEmail("lbj@wow.com"))) {
                log.info("Preloading data ");
                log.info(employeeRepository.save(new Employee(UUIDUtil.getUUID(), "Lebron",  "James", "lbj@wow.com", bcryptEncoder.encode("admin"), UserType.MANAGER.toString())).toString());
            }
        };
    }
}