package com.kim.curso.springboot.jpa.springboot_jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.kim.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.kim.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		list();
		one();
		update();
	}

	@Transactional(readOnly = true)
	public void list() {
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.findByProgrammingLanguage("java");
		//List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("java");
		List<Person> persons = repository.findByProgrammingLanguageAndName("java", "Andres");

		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personsDatas =  repository.obtenerPersonData();

		personsDatas.stream().forEach(personData -> System.out.println(Arrays.toString(personData)));
	}

	@Transactional(readOnly = true)
	public void one() {
		//repository.findById(2L).ifPresent(person -> System.out.println(person));
		//repository.findById(2L).ifPresent(System.out::println);
		//repository.findOne(2L).ifPresent(System.out::println);
		//repository.findOneName("Pepe").ifPresent(System.out::println);
		//repository.findOneLikeName("Pe").ifPresent(System.out::println);
		repository.findByNameContaining("Jos").ifPresent(System.out::println);
	}

	@Transactional
	public void create() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce el nombre");
		String name = scanner.next();
		System.out.println("Introduce el apellido");
		String lastname = scanner.next();
		System.out.println("Introduce el lenguaje de programacion");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastname, programmingLanguage);
		Person personNew = repository.save(person);
		System.out.println(personNew.toString());

		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional
	public void update() {

	}

}
