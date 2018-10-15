package configuration;

import java.util.Arrays;
import model.Breed;
import model.Horse;
import model.Rider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.HorseService;

@Configuration
public class HorseServiceConfiguration {

    @Bean
    HorseService horseService() {
        return new HorseService(Arrays.asList(
            abbervailDreamHorse(),
            adagioHorse(),
            krasavchikHorse(),
            genericHorse(),
            afternoonDelightHorse(),
            palHorse()
        ));
    }

    @Bean
    Breed mustangBreed() {
        Breed breed = new Breed();
        breed.setName("Mustang");
        breed.setColor("Coat color");
        breed.setCountryOfOrigin("North America");
        return breed;
    }

    @Bean
    Breed americanSaddlebredBreed() {
        Breed breed = new Breed();
        breed.setName("American Saddlebred");
        breed.setColor("Any");
        breed.setCountryOfOrigin("United States of America (Kentucky)");
        return breed;
    }

    @Bean
    Breed americanWarmbloodBreed() {
        Breed breed = new Breed();
        breed.setName("American Warmblood");
        breed.setColor("Any");
        breed.setCountryOfOrigin("United States of America");
        return breed;
    }

    @Bean
    Breed russianDonBreed() {
        Breed breed = new Breed();
        breed.setName("Russian Don");
        breed.setColor("Bay, black, gray or chestnut");
        breed.setCountryOfOrigin("Russia");
        return breed;
    }

    @Bean
    Breed sokolskiBreed() {
        Breed breed = new Breed();
        breed.setName("Sokolski Horse");
        breed.setColor("Chestnut");
        breed.setCountryOfOrigin("Poland");
        return breed;
    }

    @Bean
    Rider panRider() {
        Rider rider = new Rider();
        rider.setName("Pan Mateusz Nowak");
        rider.setUniformColor("White and red");
        return rider;
    }

    @Bean
    Rider johnDaleRider() {
        Rider rider = new Rider();
        rider.setName("John Dale");
        rider.setUniformColor("Black");
        return rider;
    }

    @Bean
    Rider bobDylanRider() {
        Rider rider = new Rider();
        rider.setName("Bob Dylan");
        rider.setUniformColor("White");
        return rider;
    }

    @Bean
    Rider genericRider() {
        Rider rider = new Rider();
        rider.setName("Generic Name");
        rider.setUniformColor("Generic");
        return rider;
    }

    @Bean
    Rider dirtySanchezRider() {
        Rider rider = new Rider();
        rider.setName("Dirty Sanchez");
        rider.setUniformColor("Dirty");
        return rider;
    }

    @Bean
    Rider igorUkrainetsRider() {
        Rider rider = new Rider();
        rider.setName("Igor Ukrainets");
        rider.setUniformColor("Blue and yellow");
        return rider;
    }

    @Bean
    Horse abbervailDreamHorse() {
        Horse horse = new Horse();
        horse.setName("Abbervail Dream");
        horse.setNumber(1);
        horse.setVelocity(1.15);
        horse.setBreed(mustangBreed());
        horse.setRider(dirtySanchezRider());
        return horse;
    }

    @Bean
    Horse adagioHorse() {
        Horse horse = new Horse();
        horse.setName("Adagio");
        horse.setNumber(2);
        horse.setVelocity(1.08);
        horse.setBreed(americanSaddlebredBreed());
        horse.setRider(bobDylanRider());
        return horse;
    }

    @Bean
    Horse krasavchikHorse() {
        Horse horse = new Horse();
        horse.setName("Krasavchik");
        horse.setNumber(3);
        horse.setVelocity(1.03);
        horse.setBreed(russianDonBreed());
        horse.setRider(igorUkrainetsRider());
        return horse;
    }

    @Bean
    Horse genericHorse() {
        Horse horse = new Horse();
        horse.setName("Generic");
        horse.setNumber(4);
        horse.setVelocity(1.05);
        horse.setBreed(mustangBreed());
        horse.setRider(genericRider());
        return horse;
    }

    @Bean
    Horse afternoonDelightHorse() {
        Horse horse = new Horse();
        horse.setName("Afternoon Delight");
        horse.setNumber(5);
        horse.setVelocity(0.95);
        horse.setBreed(americanWarmbloodBreed());
        horse.setRider(johnDaleRider());
        return horse;
    }

    @Bean
    Horse palHorse() {
        Horse horse = new Horse();
        horse.setName("Pal");
        horse.setNumber(6);
        horse.setVelocity(0.98);
        horse.setBreed(sokolskiBreed());
        horse.setRider(panRider());
        return horse;
    }
}
