package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreate {

    public String firstName;
    public String lastName;
    public String email;
    public String gender;
    public String phone;
    public UserLocation location;

    public static UserCreate createUser(){
        Faker faker = new Faker(new Locale("en-US"));
        UserLocation location = UserLocation.builder()
                .street(faker.address().streetAddress())
                .city("Miami")
                .state("Florida")
                .country("US")
                .build();

        return UserCreate.builder()
                .firstName("John")
                .lastName("Smith")
                .email(faker.internet().emailAddress())
                .gender("male")
                .phone(faker.phoneNumber().cellPhone())
                .location(location)
                .build();
    }



}
