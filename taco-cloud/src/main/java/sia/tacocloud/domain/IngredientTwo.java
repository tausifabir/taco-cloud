package sia.tacocloud.domain;

import lombok.Data;

@Data
public class IngredientTwo {

    private final String id;

    private final String name;

    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
