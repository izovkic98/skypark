package com.tvz.skypark.utils;

public class ParkUtils {
	

	public enum VehicleManufacturer {

		
		Abarth,
		Audi, 
		Bentley,
		Cadillac,
		Chevrolet,
		Chrysler,
		Citroën,
		Cupra,
		Dacia,
		Daewoo,
		Daihatsu,
		Dodge,
		DFSK,
		DS_Automobiles,
		Ferrari,
		Fiat,
		Fisker, 
		Ford,
		GMC,
		Great_Wall_Motor,
		Honda,
		Hummer,
		Hyundai,
		Infiniti, 
		Isuzu,
		Iveco,
		Jaguar,
		Jeep,
		Kia,
		Lada,
		Lamborghini,
		Lancia,
		Land_Rover,
		Lexus,
		Lincoln,
		Lotus,
		Mahindra,
		Maserati,
		Mazda,
		Mercedes_Benz,
		MG,
		MINI,
		Mitsubishi,
		Nissan,
		Opel,
		Peugeot,
		Polestar, 
		Pontiac,
		Porsche,
		Puch,
		Renault,
		Rolls_Royce,
		Rover,
		Saab,
		Seat,
		Smart,
		Ssang_Yong,
		Subaru,
		Suzuki,
		Škoda,
		Tesla,
		Toyota,
		UAZ,
		Volvo,
		VW,
		Wartburg,
		XEV,
		Yugo,
		Zastava,
		

	}
	
	public enum VehicleType {
		
		MINIVAN, COUPE, TOURING, SUPERCAR, HATCHBACK, CABRIOLET, SEDAN, PICKUP, SUV;

	}
	
	
	public enum Role {
		
		USER, ADMIN;

	}
	
	public enum ReservationStatus {
		
		APPROVED("APPROVED"), DENIED("DENIED"), IN_PROCESS("IN PROCESS");
		
		private final String name;

		private ReservationStatus(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}
	
	public enum ParkingType {

		I_ZONE("I. ZONE"), II_ZONE("II. ZONE");

		private final String name;

		private ParkingType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}
	
	public enum ParkingStatus {
		Free, Occupied
	}
	
}
