package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.model.Address;
import org.example.model.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

       try(InputStream inputStream = new FileInputStream(new File("C:/Users/allen/Desktop/people.xml"));){
           ObjectMapper mapper = new XmlMapper();

           TypeReference<List<Person>> typeReference = new TypeReference<List<Person>>(){};
           List<Person> people = mapper.readValue(inputStream, typeReference);
           for(Person p: people){
               System.out.println("Name is: "+p.getFirstName()+" city is "+p.getAddress().getCity()+" first car is: "+p.getCars()[0]+" age is: "+p.getAge());
           }

           Person person = new Person();
           person.setFirstName("Jack");
           person.setLastName("Ryan");
           person.setAge(29);
           person.setAddress(new Address("12 Street", "Kirkland", "98052"));
           person.setCars(new String[]{"Toyota Corolla", "Honda Accord", "Honda Civic"});

           mapper.writeValue(new File("C:/Users/allen/Desktop/peopleOut.xml"), person);

       }catch (Exception ex){
           ex.printStackTrace();
       }

    }
}
