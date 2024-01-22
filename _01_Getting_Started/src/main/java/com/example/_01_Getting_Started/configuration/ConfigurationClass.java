package com.example._01_Getting_Started.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person(String name, int age, Address address) {
}

record Address(String city, String state) {
}

@Configuration
public class ConfigurationClass {

    @Bean
    public String name() {
        return "Alex";
    }

    @Bean
    public int age() {
        return 30;
    }

    @Bean
    public Person person() {
        return new Person("Peter", 25, new Address("Rome", "Italy"));
    }

    // Creating a bean with an existing bean
    @Bean
    public Person personWithNameAgeAndAddress() {
        return new Person(name(), age(), address());
    }

    @Bean
    public Person personWithParameters(String name, int age, Address newAddress) {
        return new Person(name, age, newAddress);
    }

    @Bean
    public Person personWithPrimaryAddress(String name, int age, Address address) {
        return new Person(name, age, address);
    }

    @Bean
    public Person personWithQualifierAddress(String name, int age, @Qualifier("newAddress") Address address) {
        return new Person(name, age, address);
    }

    @Bean(name = "personAddress")
    @Primary
    public Address address() {
        return new Address("Bangalore", "Karnataka");
    }

    @Bean
    @Qualifier("newAddress")
    public Address newAddress() {
        return new Address("Vancouver", "British Columbia");
    }

}
