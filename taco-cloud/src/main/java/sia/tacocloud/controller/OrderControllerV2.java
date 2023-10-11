package sia.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.domain.TacoOrder;
import sia.tacocloud.domain.TacoOrderTwo;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/ordersTwo")
@SessionAttributes("tacoOrderTwo")
public class OrderControllerV2 {

    @GetMapping("/currentTwo")
    public String orderFormTwo() {
        return "orderFormTwo";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrderTwo order,
                               Errors errors,
                               SessionStatus sessionStatus) {

        if(errors.hasErrors()){
            return "orderFormTwo";
        }
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
