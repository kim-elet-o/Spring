package com.kim.curso.springboot.jpa.springboot_jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.kim.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
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
		
		personalizedQueriesAggregation();
	}

	@Transactional(readOnly = true)
	public void list() {
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = repository.findByProgrammingLanguage("java");
		//List<Person> persons = repository.buscarByProgrammingLanguage("java");
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

	@Transactional(readOnly = true)
	public void personalizedQueries() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Introduce el id para obtener el nombre asociado:");
		Long id = scanner.nextLong();
		scanner.close();

		System.out.println("************************** Consulta del nombre de una persona mediante su Id **************************");
		System.out.println(repository.obtainNameById(id));

		System.out.println("************************** Consulta del complñeto nombre de una persona mediante su Id **************************");
		System.out.println(repository.obtainFullNameById(id));

		System.out.println("************************** Consulta con campos personalizados por el id **************************");
		Object[] fields = repository.obtainPersonFullDataById(id);
		System.out.println(Arrays.toString((Object[]) fields[0]));

		System.out.println("************************** Consulta con campos personalizados de todas las personas **************************");
		List<Object[]> list =repository.obtainPersonFullDataList();
		list.forEach(o -> System.out.println(Arrays.toString(o)));
	}

	@Transactional(readOnly = true)
	public void personalizedQueries2() {
		System.out.println("******************* Consulta por objecto persona y lenguaje de programacion *******************");
		List<Object[]> personsRegs = repository.obtainAllMixPerson();
		personsRegs.forEach(reg -> {
			System.out.println("Lenguaje de programacion: " + reg[1] + ", Persona: " + reg[0]);
		});

		System.out.println("******************* Consulta que puebla y devuelve objectos entity de una instancia personalizada *******************");
		List<Person> persons = repository.obtainAllpersonalizedOjectPerson();
		persons.forEach(System.out::println);

		System.out.println("******************* Consulta que puebla y devuelve objectos Dto de una instancia personalizada *******************");
		List<PersonDto> personDtos = repository.obtainAllpersonalizedOjectPersonDto();
		personDtos.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		System.out.println("******************* Consulta con nombres de personas *******************");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("******************* Consulta con nombres unicos de personas *******************");
		names = repository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("******************* Consulta con Lenguajes de programacion unicos *******************");
		List<String> programmingLanguages = repository.findAllProgrammingLanguageDistinct();
		programmingLanguages.forEach(System.out::println);

		System.out.println("******************* Consulta con total de Lenguajes de programacion unicos *******************");
		Long total = repository.findAllProgrammingLanguageDistinctCount();
		System.out.println(total);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperAndLowerCase() {
		System.out.println("******************* Consulta nombres completos de personas *******************");
		List<String> fullNames = repository.obtainAllFullName();
		fullNames.forEach(System.out::println);

		System.out.println("******************* Otra consulta nombres completos de personas *******************");
		fullNames = repository.obtainAllFullName2();
		fullNames.forEach(System.out::println);

		System.out.println("******************* Consulta nombres completos de personas en mayusculas *******************");
		fullNames = repository.obtainAllFullNameUpper();
		fullNames.forEach(System.out::println);

		System.out.println("******************* Consulta nombres completos de personas en minusculas *******************");
		fullNames = repository.obtainAllFullNameLower();
		fullNames.forEach(System.out::println);

		System.out.println("******************* Consulta nombres completos de personas en minusculas *******************");
		List<Object[]> data = repository.obtainPersonFullDataListCase();
		data.forEach(o -> System.out.println(Arrays.toString(o)));
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween() {
		System.out.println("******************* Consultas por rangos *******************");
		List<Person> persons = repository.findAllBetweenId(2L, 5L);
		persons.forEach(System.out::println);

		persons = repository.findAllBetweenName("J", "P");
		persons.forEach(System.out::println);

		persons = repository.findByIdBetweenOrderByNameAsc(2L, 5L);
		persons.forEach(System.out::println);

		persons = repository.findByNameBetweenOrderByNameDescLastnameAsc("J", "P");
		persons.forEach(System.out::println);

		persons = repository.obtainAllOrdered();
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesAggregation() {
		System.out.println("******************* Consulta con el total de registros *******************");
		Long count = repository.getTotalPerson();
		System.out.println(count);

		System.out.println("******************* Consulta con el id mas bajo *******************");
		Long min = repository.getMinId();
		System.out.println(min);

		System.out.println("******************* Consulta con el id mas alto *******************");
		long max = repository.getMaxId();
		System.out.println(max);

		System.out.println("******************* Consulta con los nombres y su longitud *******************");
		List<Object[]> regs = repository.obtainPersonNameLength();
		regs.forEach(reg -> System.out.println("Nombre: " + reg[0] + " Longitud: " + reg[1]));
	}

	@Transactional
	public void create() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce el nombre:");
		String name = scanner.next();
		System.out.println("Introduce el apellido:");
		String lastname = scanner.next();
		System.out.println("Introduce el lenguaje de programacion:");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastname, programmingLanguage);
		Person personNew = repository.save(person);
		System.out.println(personNew.toString());

		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional
	public void update() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce el Id de la persona:");
		Long Id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(Id);
		//optionalPerson.ifPresent(person -> {
			if (optionalPerson.isPresent()) {
				Person person = optionalPerson.get();

				System.out.println(person);
				System.out.println("Introduce el lenguaje de programacion:");
				String programmingLanguage = scanner.next();
				person.setprogrammingLanguage(programmingLanguage);
				Person personUpdated = repository.save(person);
				System.out.println(personUpdated);
			} else {
				System.out.println("La persona no existe en la BD:");
			}
		//}); 

		scanner.close();
	}

	@Transactional
	public void delete() {
		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce el Id de la persona:");
		Long Id = scanner.nextLong();

		repository.deleteById(Id);

		repository.findAll().forEach(System.out::println);

		scanner.close();
	}

	@Transactional
	public void delete2() {
		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduce el Id de la persona:");
		Long Id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(Id);

		optionalPerson.ifPresentOrElse(
			person -> repository.delete(person),
			() -> System.out.println("La persona no existe en la BD:"));

		repository.findAll().forEach(System.out::println);

		scanner.close();
	}
}
