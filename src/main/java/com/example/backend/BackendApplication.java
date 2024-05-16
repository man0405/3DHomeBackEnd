package com.example.backend;

import com.example.backend.models.entity.*;
import com.example.backend.repository.*;
import com.example.backend.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(VisitService visitService){
		return runner ->{
//			addOwner(theOwnerService);
//			addHouse(theOwnerService, theHouseRepo);
//			findOwnerById(theOwnerService)
//			findOwnerAndHouses(theOwnerService);
//			System.out.println(theHouseRepo.findBy_Id(1));
//			System.out.println(theVisitService.visitPerWeek(2).toString() + " visits this week");
//			System.out.println(theOwnerService.findVisitCustomerInfo(51));

//			addToCart(visitService);
        };
	}

//	private void addToCart(VisitService invoiceService) {
//		int[] customerList = {8, 13, 12, 10, 4, 9, 3, 11, 7, 5};
//		int[] houseList = {34,72,73,75,74,32,38,33 ,81 ,22};
//		for(int i = 0; i < 9; i++){
//			invoiceService.save(3, houseList[i]);
//		}
//	}

	private void findOwnerAndHouses(OwnerService theOwnerService) {
		int theId = 1;
		Owner theOwner = theOwnerService.getOwnerAlongWithHouses(theId);
		System.out.println(theOwner);
		System.out.println(theOwner.getHouses());
	}

	private void findOwnerById(OwnerService theOwnerService) {
		int theId = 1;
		Owner theOwner = theOwnerService.findById(theId);
		System.out.println(theOwner);
		System.out.println(theOwner.getHouses());
	}

	private void addHouse(OwnerService theOwnerService, HouseRepo theHouseRepo) {
		List<House> houses = new ArrayList<>();
//		houses.add(new House(2012188, 1, "Lindbergh", "Khaneh", "Padre Burgos", "Philippines", 4447.35, 32,FacingDirection.NORTH, 2, 2, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Rahal Dennis", "Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl."));
		houses.add(new House(2112219, 1, "Lerdahl", "Boca de Sábalo", "Mikhaylovskoye", "Russia", 5541.71, 16, FacingDirection.NORTH, 1, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Coraline Simeon", "Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem. Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus."));
		houses.add(new House(6535759, 2, "Cambridge", "Naoro Vilage", "Neyvo-Rudyanka", "Russia", 7165.82, 50, FacingDirection.NORTH, 1, 1, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Bartram Ghiron", "Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla."));
		houses.add(new House(1705707, 3, "Walton", "Belfast", "Broumov", "Czech Republic", 382.71, 70, FacingDirection.NORTH, 1, 5, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Wendel Pratton", "Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum."));
		houses.add(new House(7651183, 4, "Paget", "Anta", "San Miguelito", "Panama", 5979.32, 42, FacingDirection.NORTH, 5, 5, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Sissy Hiskey", "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus."));
		houses.add(new House(7719474, 5, "Forest", "Ngiva", "Salem", "Indonesia", 8017.41, 12, FacingDirection.NORTH, 1, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Hilliard Andrew", "Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum."));
		houses.add(new House(7202204, 6, "Cardinal", "Qinhuangdao", "Aizkraukle", "Latvia", 6723.89, 83, FacingDirection.NORTH, 2, 1, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Kimbell Walter", "Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem. Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus."));
		houses.add(new House(1714970, 7, "Larry", "Xinguara", "Kubangeceng", "Indonesia", 9929.52, 93, FacingDirection.NORTH, 4, 1, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Trumaine Obington", "Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis."));
		houses.add(new House(4782931, 8, "Everett", "Castro", "Jambeyan", "Indonesia", 8455.07, 31, FacingDirection.NORTH, 5, 1, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Geri Darbyshire", "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci."));
		houses.add(new House(1851789, 9, "Bayside", "Chicago/Aurora", "Kashira", "Russia", 470.79, 69, FacingDirection.NORTH, 1, 2, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Thorn Erangey", "Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula."));
		houses.add(new House(5859129, 10, "Heffernan", "Villavicencio", "Lo Prado", "Chile", 5371.57, 19, FacingDirection.NORTH, 5, 1, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Dar Garthside", "Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue."));
		houses.add(new House(3024339, 11, "6th", "Laoag City", "Jönköping", "Sweden", 4381.98, 6, FacingDirection.NORTH, 2, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Stella Sadry", "Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst."));
		houses.add(new House(313605, 12, "Dawn", "Maamigili", "Carromeu", "Portugal", 723.05, 58, FacingDirection.NORTH, 3, 2, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Gwenora Widdowes", "In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc."));
		houses.add(new House(9260488, 13, "Heffernan", "Macaé", "Lappajärvi", "Finland", 9715.48, 40, FacingDirection.NORTH, 2, 2, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Ogden Curryer", "Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus."));
		houses.add(new House(2807512, 14, "Chinook", "Upavon", "Bayt ‘Adhāqah", "Yemen", 8438.0, 35, FacingDirection.NORTH, 1, 3, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Gordon Baake", "Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui."));
		houses.add(new House(2322234, 15, "Sage", "Angling Lake", "Paitan Norte", "Philippines", 9028.82, 47, FacingDirection.NORTH, 1, 1, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Francisco Ivanilov", "Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus."));
		houses.add(new House(6790436, 16, "Oxford", "Wharton", "Łąka Prudnicka", "Poland", 5919.07, 17, FacingDirection.NORTH, 2, 2, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Rikki Masterson", "Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum."));
		houses.add(new House(1689115, 17, "Arrowood", "George Town", "Tall Rif‘at", "Syria", 8477.76, 23, FacingDirection.NORTH, 5, 3, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Sharla Slainey", "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat."));
		houses.add(new House(9932821, 18, "Maryland", "Pasni", "Usicayos", "Peru", 3049.82, 64, FacingDirection.NORTH, 5, 5, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Lurette Jankowski", "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis."));
		houses.add(new House(8530685, 19, "Loomis", "Zhigansk", "Granada", "Spain", 797.03, 53, FacingDirection.NORTH, 3, 3, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Cal Paskerful", "Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus."));
		houses.add(new House(2403096, 20, "Briar Crest", "Nuquí", "Leer", "South Sudan", 2176.44, 79, FacingDirection.NORTH, 3, 2, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Kelvin Buttfield", "Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue."));
		houses.add(new House(5491342, 21, "Maple Wood", "Maquinchao", "Braunschweig", "Germany", 9143.19, 95, FacingDirection.NORTH, 3, 3, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Roddy Jirusek", "Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus."));
		houses.add(new House(554816, 22, "Bayside", "Kaunas", "Liushan", "China", 8081.95, 91, FacingDirection.NORTH, 1, 5, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Melosa Mann", "Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam."));
		houses.add(new House(6963716, 23, "Maryland", "Darwaz", "Togur", "Russia", 9756.68, 56, FacingDirection.NORTH, 4, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Buddie Sonier", "Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus."));
		houses.add(new House(1944402, 24, "Sugar", "Wrocław", "Kadujangkung", "Indonesia", 311.25, 50, FacingDirection.NORTH, 1, 3, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Orelia Sterley", "Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus."));
		houses.add(new House(3709434, 25, "La Follette", "Kalbarri", "Era", "Indonesia", 4802.56, 47, FacingDirection.NORTH, 3, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Adelheid Chevins", "Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui."));
		houses.add(new House(9580094, 26, "Fair Oaks", "Glenwood Springs", "Thị Trấn Mường Lát", "Vietnam", 3429.1, 21, FacingDirection.NORTH, 2, 3, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Woody Eyckelberg", "In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus."));
		houses.add(new House(1401627, 27, "Corben", "Lalibela", "Chuntai", "China", 3693.45, 78, FacingDirection.NORTH, 3, 2, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Lacie Yakushkev", "In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi."));
		houses.add(new House(9412243, 28, "Hovde", "Bhopal", "Alikalia", "Sierra Leone", 5788.76, 19, FacingDirection.NORTH, 5, 1, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Saxe Cornilleau", "Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla."));
		houses.add(new House(2657224, 29, "Ryan", "Stockton", "Hongqi", "China", 5020.23, 75, FacingDirection.NORTH, 1, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Gary Hayball", "Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus."));
		houses.add(new House(3428422, 30, "Bartelt", "Aguascalientes", "Pulau Tiga", "Indonesia", 563.4, 28, FacingDirection.NORTH, 5, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Zachary Molloy", "Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus."));
		houses.add(new House(1470319, 31, "Chinook", "Daşoguz", "Yessentuki", "Russia", 9488.72, 47, FacingDirection.NORTH, 2, 1, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Maud Pontain", "Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis."));
		houses.add(new House(2561161, 32, "Warrior", "Shenyang", "Zhangshui", "China", 366.65, 62, FacingDirection.NORTH, 1, 5, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Chad Turnell", "Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius."));
		houses.add(new House(5610737, 33, "Bashford", "Durgapur", "Nevel’sk", "Russia", 9070.15, 75, FacingDirection.NORTH, 5, 3, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Urbano Fuggles", "Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue. Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst."));
		houses.add(new House(3223165, 34, "Everett", "LAquila", "Dabaozi", "China", 4945.32, 34, FacingDirection.NORTH, 5, 3, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Gaston Deener", "Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui."));
		houses.add(new House(6258194, 35, "Nelson", "Ivanof Bay", "Kalwaria Zebrzydowska", "Poland", 3466.6, 75, FacingDirection.NORTH, 5, 3, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Caron Irwin", "Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus."));
		houses.add(new House(6910610, 36, "Brickson Park", "Goronka", "Kyoto", "Japan", 1157.18, 50, FacingDirection.NORTH, 5, 2, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Cobby Jebb", "Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus."));
		houses.add(new House(3335713, 37, "Jenna", null, "Niyang", "China", 7906.13, 55, FacingDirection.NORTH, 1, 5, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Mohandis Skakunas", "Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh."));
		houses.add(new House(4523601, 38, "Ronald Regan", "Chernigow", "Tanlad", "Philippines", 4274.94, 74, FacingDirection.NORTH, 5, 5, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Conrado Lile", "Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus."));
		houses.add(new House(504989, 39, "Hovde", "Porto Seguro", "São José", "Brazil", 506.69, 79, FacingDirection.NORTH, 1, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Maurise Gauche", "Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt."));
		houses.add(new House(7261975, 40, "Utah", "Stony Rapids", "Pinhão", "Brazil", 6295.11, 70, FacingDirection.NORTH, 4, 5, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Connor Bes", "In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc."));
		houses.add(new House(7325972, 41, "Shoshone", "Formiga", "Hexi", "China", 9684.82, 74, FacingDirection.NORTH, 5, 3, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Bendicty Duffill", "Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus."));
		houses.add(new House(8106660, 42, "Namekagon", null, "Dagushan", "China", 6885.41, 81, FacingDirection.NORTH, 2, 1, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Cecilius Trimmill", "Nulla suscipit ligula in lacus. Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam. Nam tristique tortor eu pede."));
		houses.add(new House(1437147, 43, "Burrows", "Kuujjuarapik", "Polešovice", "Czech Republic", 4887.84, 42, FacingDirection.NORTH, 5, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Elliott d Eye", "Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices."));
		houses.add(new House(6390049, 44, "Fordem", "Detroit", "Raciążek", "Poland", 5143.75, 94, FacingDirection.NORTH, 2, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Timmie Tebbut", "Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl. Nunc nisl."));
		houses.add(new House(9949421, 45, "Northport", "Yari", "Trablice", "Poland", 1819.8, 87, FacingDirection.NORTH, 1, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Deanna Dyball", "Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit."));
		houses.add(new House(5010326, 46, "Meadow Valley", "Kaieteur Falls", "Gongrong", "China", 4576.87, 67, FacingDirection.NORTH, 2, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Rachel Reagan", "In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices."));
		houses.add(new House(2883009, 47, "Karstens", null, "Bielefeld", "Germany", 4735.18, 79, FacingDirection.NORTH, 4, 1, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Jay Allanson", "Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio."));
		houses.add(new House(6890717, 48, "American", "Yueyang", "Tumbi", "Tanzania", 3462.74, 53, FacingDirection.NORTH, 5, 2, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Darla Rosenfelder", "Phasellus in felis. Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo."));
		houses.add(new House(3311768, 49, "Holy Cross", "Bell Island", "Huangcai", "China", 2722.95, 78, FacingDirection.NORTH, 4, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Dayle Millin", "Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla."));
		houses.add(new House(6946073, 50, "Lukken", "Conceição Das Alagoas", "Valsamáta", "Greece", 8712.19, 60, FacingDirection.NORTH, 3, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Pattin Myderscough", "Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti."));
		for (int i = 1; i <= 50; i++){
			Owner theOwner = theOwnerService.findById(i);
			House theHouse = houses.get(i - 1);
			theHouse.setOwner(theOwner);
			theOwner.addHouse(theHouse);
			try{
				theOwnerService.update(theOwner);
			}catch (Exception e){
				e.printStackTrace();
			}
		}


	}

	private void addOwner(OwnerService theOwnerService) {
		Owner theOwner = new Owner("Gia", "Thinh", "giathinh824@gmail.com", "0774562721");
		theOwner.addHouse(new House(6946073, 50, "Lukken", "Conceição Das Alagoas", "Valsamáta", "Greece", 8712.19, 60, FacingDirection.NORTH, 3, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Pattin Myderscough", "Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti."));
		theOwner.addHouse(new House(3311768, 49, "Holy Cross", "Bell Island", "Huangcai", "China", 2722.95, 78, FacingDirection.NORTH, 4, 4, "https://sketchfab.com/models/83aeef52a44c43f89d892a37c853dc6e/embed", "Dayle Millin", "Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla."));
		theOwnerService.save(theOwner);
	}

}