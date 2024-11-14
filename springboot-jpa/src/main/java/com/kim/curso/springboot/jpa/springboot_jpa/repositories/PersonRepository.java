package com.kim.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kim.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.kim.curso.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("select p from Person p where p.programmingLanguage=?1")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p From Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p From Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p From Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);

    @Query("select p.name from Person p where p.id=?1")
    String obtainNameById(Long id);

    @Query("select concat(p.name, ' ', p.lastname) from Person p where p.id=?1")
    String obtainFullNameById(Long id);

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtainPersonFullDataList();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
    Object[] obtainPersonFullDataById(long id);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> obtainAllMixPerson();

    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> obtainAllpersonalizedOjectPerson();

    @Query("select new com.kim.curso.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> obtainAllpersonalizedOjectPersonDto();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select distinct(p.programmingLanguage) from Person p")
    List<String> findAllProgrammingLanguageDistinct();

    @Query("select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllProgrammingLanguageDistinctCount();

    @Query("select concat(p.name, ' ', p.lastname) from Person p")
    List<String> obtainAllFullName();

    @Query("select p.name || ' ' || p.lastname from Person p")
    List<String> obtainAllFullName2();

    @Query("select upper(p.name || ' ' || p.lastname) from Person p")
    List<String> obtainAllFullNameUpper();

    @Query("select lower(p.name || ' ' || p.lastname) from Person p")
    List<String> obtainAllFullNameLower();

    @Query("select p.id, upper(p.name), lower(p.lastname), upper(p.programmingLanguage) from Person p")
    List<Object[]> obtainPersonFullDataListCase();

    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name asc")
    List<Person> findAllBetweenId(long n1, Long n2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name desc, p.lastname asc")
    List<Person> findAllBetweenName(String c1, String c2);

    List<Person> findByIdBetweenOrderByNameAsc(long id1, Long id2);

    List<Person> findByNameBetweenOrderByNameDescLastnameAsc(String c1, String c2);

    @Query("select p from Person p order by p.name desc, p.lastname asc")
    List<Person> obtainAllOrdered();

    @Query("select count(p) from Person p")
    Long getTotalPerson();

    @Query("select min(p.id) from Person p")
    Long getMinId();

    @Query("select max(p.id) from Person p")
    Long getMaxId();

    @Query("select p.name, length(p.name) from Person p")
    List<Object[]> obtainPersonNameLength();

}
