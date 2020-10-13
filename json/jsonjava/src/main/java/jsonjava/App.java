package jsonjava;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value=Dog.class, name="dog"), @JsonSubTypes.Type(value=Cat.class, name="cat")})
class Animal {
    public String name;

    @Override
    public String toString() {
        return String.format("Name: %s", name);
    }
}

@JsonTypeName("dog")
class Dog extends Animal {
    public double barkVolume;
}

@JsonTypeName("cat")
class Cat extends Animal {
    boolean likeCream;
    public int lives;

    @Override
    public String toString() {
        return String.format("%s, likeCream:%b, lives: %d", super.toString(), likeCream, lives);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        final var dog= new Dog();
        dog.name = "lacy";
        dog.barkVolume = 1.5;

        final var cat = new Cat();
        cat.name = "fluffy";
        cat.likeCream = true;
        cat.lives = 21;

        final Animal animal = cat;

        String result = new ObjectMapper().writeValueAsString(animal);
        System.out.println(result);

        final var dog1 = new ObjectMapper().readerFor(Animal.class).readValue(result);
        System.out.println(dog1);
    }
}
