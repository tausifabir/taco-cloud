package sia.tacocloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.domain.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/designTwo")
@SessionAttributes("tacoOrderTwo")
public class DesignTacoControllerV2 {


    @GetMapping
    public String showDesignTwoForm() {
        return "designTwo";
    }

    @PostMapping
    public String processTaco(@Valid TacoTwo taco,
                              Errors errors,
                              @ModelAttribute TacoOrderTwo tacoOrder) {
        if(errors.hasErrors()){
            return "designTwo";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/ordersTwo/currentTwo";
    }


    @ModelAttribute(name = "tacoOrderTwo")
    public TacoOrderTwo orderTwo() {
        return new TacoOrderTwo();
    }

    @ModelAttribute(name = "tacoTwo")
    public TacoTwo taco() {
        return new TacoTwo();
    }


    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<IngredientTwo> ingredients = Arrays.asList(
                new IngredientTwo("FLTO", "Flour Tortilla", IngredientTwo.Type.WRAP),
                new IngredientTwo("COTO", "Corn Tortilla", IngredientTwo.Type.WRAP),
                new IngredientTwo("GRBF", "Ground Beef", IngredientTwo.Type.PROTEIN),
                new IngredientTwo("CARN", "Carnitas", IngredientTwo.Type.PROTEIN),
                new IngredientTwo("TMTO", "Diced Tomatoes", IngredientTwo.Type.VEGGIES),
                new IngredientTwo("LETC", "Lettuce", IngredientTwo.Type.VEGGIES),
                new IngredientTwo("CHED", "Cheddar", IngredientTwo.Type.CHEESE),
                new IngredientTwo("JACK", "Monterrey Jack", IngredientTwo.Type.CHEESE),
                new IngredientTwo("SLSA", "Salsa", IngredientTwo.Type.SAUCE),
                new IngredientTwo("SRCR", "Sour Cream", IngredientTwo.Type.SAUCE)
        );

        IngredientTwo.Type[] types = IngredientTwo.Type.values();
        for (IngredientTwo.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    private Iterable<IngredientTwo> filterByType(List<IngredientTwo> ingredients,
                                                 IngredientTwo.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
