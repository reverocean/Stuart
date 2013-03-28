package com.github.smart.service;

import com.github.smart.domain.Address;
import com.github.smart.domain.Individual;
import com.github.smart.domain.Profile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testHibernateApplicationContext.xml"})
@TestExecutionListeners({DirtiesContextTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DefaultCustomerServiceTest
{
    static String[] brands = new String[]{"GIO", "Suncorp", "AAMI", "Bingle", "EverydaySuper", "APIA", "Shaun", "CIL", "SuncorpBank", "JCI"};
    static String[] names = new String[]{"Hermila Coe",
            "Carley Pruden",
            "Cortez Hensler",
            "Oda Temples",
            "Dorian Perryman",
            "Karan Fretz",
            "Lacey Obrien",
            "Tom Dengler",
            "Mui Gendron",
            "Dorotha Simien",
            "Elfriede Schroder",
            "Jorge Macdowell",
            "Cody Pyne",
            "Sindy Pino",
            "Genna Morning",
            "Michelle Giannone",
            "Belkis Garg",
            "John Rourke",
            "Tracee Burrough",
            "Bud Choat",
            "Dierdre Chicoine",
            "Amber Greer",
            "Jong Cotter",
            "Glayds Maguire",
            "Letha Ruben",
            "Frankie Gilbert",
            "Chloe Culligan",
            "Janee Caraveo",
            "In Henn",
            "Candida Ebert",
            "Ollie Hellwig",
            "Lynne Edler",
            "Bernardina Burgo",
            "Glen Dimery",
            "Wen Yedinak",
            "Darla Laguardia",
            "Marget Hoffmann",
            "Maida Ruse",
            "Julieta Meikle",
            "Cristal Seelig",
            "Lavonda Trump",
            "Imogene Mccarley",
            "Teofila Puchalski",
            "Violet Cobbins",
            "Cuc Forney",
            "Lakita Burtch",
            "Willy Shumaker",
            "Sadye Sica",
            "Theron Cargile",
            "Clemmie Canino",
            "Leola Illingworth",
            "Eryn Bigby",
            "Meredith Haver",
            "Shonna Acre",
            "Lan Marse",
            "Elizbeth Utsey",
            "Machelle Marshal",
            "Jacquelin Vuong",
            "Gricelda Laymon",
            "Kerrie Munos",
            "Demetrius Fennessey",
            "Latanya Visitacion",
            "Mckinley Bradshaw",
            "Audrea Tourigny",
            "Mirian Mcvey",
            "Son Vassell",
            "Leda Zeigler",
            "Bernardine Minarik",
            "Raquel Akerley",
            "Kasie Mcduffee",
            "Ulrike Oathout",
            "Lean Munez",
            "Layla Troche",
            "Dionna Minelli",
            "Daphine Rist",
            "Allan Mikkelsen",
            "Warner Slaubaugh",
            "Clorinda Tompkins",
            "Melissia Reppert",
            "Epifania Conroy",
            "Zandra Boettcher",
            "Star Kingrey",
            "Wilhelmina Cardon",
            "Briana Kenyon",
            "Savanna Bubb",
            "Jinny Willsey",
            "Angle Chillemi",
            "Cherlyn Krall",
            "Casey Meritt",
            "Lynna Rudolph",
            "Daren Bowie",
            "Harland Hauff",
            "Maryellen Robnett",
            "Kerry Wickliffe",
            "Jenae Traxler",
            "Lon Adelman",
            "Barbie Dejonge",
            "Dorian Perino",
            "Ashton Money",
            "Annis Range"};

    @Autowired
    CustomerService customerService;

    @Autowired
    RecommendationService recommendationService;

    @Autowired
    LessThanService lessThanService;

    @Autowired
    ProfileService profileService;


    @Test
    public void test()
    {
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 10; i++)
            {
                Individual individual = new Individual();
                individual.setName(names[i]);
                individual.setDateOfBirth(new Date(System.currentTimeMillis()));
                individual.setEmail(names[i] + "@gmail.com");
                individual.setRegisterTime(new Timestamp(System.currentTimeMillis()));
                individual.setGender(getRandomGender());
                Address address = new Address();
                address.setAddressLine("whatever you want");
                address.setCity("chengdu");
                address.setPostCode("123432");
                address.setState("sichuan");
                address.setStreet("tianfu");
                individual.setAddress(address);
                int random = new Random().nextInt(10);
                Profile profile = new Profile();
                profile.setBrand(brands[random]);
                profile.setIndividual(individual);
                profileService.save(profile);
            }
        }
    }

    private String getRandomGender()
    {
        int random1 = new Random().nextInt(2);
        if (random1 == 0)
        {
            return "MALE";
        }
        return "FEMALE";
    }


}
