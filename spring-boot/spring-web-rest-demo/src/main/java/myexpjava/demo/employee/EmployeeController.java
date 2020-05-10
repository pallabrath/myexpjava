package myexpjava.demo.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {
    @GetMapping("/employee")
    @ResponseBody
    public String listEmployees() {
        return "Pallab Rath";
    }
}
