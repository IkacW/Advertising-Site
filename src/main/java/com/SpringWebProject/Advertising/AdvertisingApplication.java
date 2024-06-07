package com.SpringWebProject.Advertising;

import com.SpringWebProject.Advertising.Models.Category;
import com.SpringWebProject.Advertising.Models.Embedded.Address;
import com.SpringWebProject.Advertising.Models.Listing;
import com.SpringWebProject.Advertising.Models.User;
import com.SpringWebProject.Advertising.Repositories.CategoryRepository;
import com.SpringWebProject.Advertising.Repositories.ListingRepository;
import com.SpringWebProject.Advertising.Repositories.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AdvertisingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvertisingApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			UserRepository userRepository,
			CategoryRepository categoryRepository,
			ListingRepository listingRepository
	) {
		return args -> {
			Faker faker = new Faker();
			// Hard coded category db filler
			ArrayList<String> categories = new ArrayList<>();
			categories.add("Electronics");
			categories.add("Food");
			categories.add("Fashion");
			categories.add("Furniture");
			categories.add("Car");
			categories.add("Books");
			categories.add("Makeup");
			categories.add("Makeup");
			categories.add("Sports");
			categories.add("Pets");
			for (int i = 0; i < 10; i++) {
				var category = Category.builder()
						.name(categories.get(i))
						.build();
				categoryRepository.save(category);

				var user = User.builder()
						.email(faker.internet().emailAddress())
						.username(faker.name().username())
						.password(faker.internet().password())
						.firstName(faker.name().firstName())
						.lastName(faker.name().lastName())
						.phone(faker.phoneNumber().phoneNumber())
						.address(new Address(
								faker.address().streetAddress(),
								faker.address().city(),
								faker.address().zipCode(),
								faker.address().buildingNumber()
						))
						.createdAt(LocalDateTime.now())
						.build();
				userRepository.save(user);

				List<User> users = new ArrayList<>();
				for (int j = 0; j < 2; j++) {
                    users.add(userRepository.findById(faker.number().numberBetween(1, i)).orElse(null));
				}
				var desc = faker.lorem().paragraph() + " "
						+ faker.lorem().paragraph() + " " + faker.lorem().paragraph();
				var listing = Listing.builder()
						.title(faker.job().title())
						.description(desc)
						.price(faker.number().numberBetween(200, 1000))
						.category(category)
						.createdAt(LocalDateTime.now())
						.user(user)
						.users_following(users)
						.build();
				listingRepository.save(listing);
			}

		};
	}
}
