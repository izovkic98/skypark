package com.tvz.skypark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/skypark")
public class SkyparkController {
	
	
//	@Autowired
//	private BolnicaRepository bolnicaRepository;
//	@GetMapping("")
//	public List<Bolnica> createHospital(){
//		return bolnicaRepository.findAll();
//	}
//	@GetMapping("")
//	public List<Bolnica> getAllHospitals(){
//		return bolnicaRepository.findAll();
//	}
//	
//	@PostMapping("/createBolnica")
//	public Bolnica createBolnica(@RequestBody Bolnica bolnica) {
//		return bolnicaRepository.save(bolnica);
//	}
//	
//	@GetMapping("")
//	public List<Bolnica> updateHospitalById(){
//		return bolnicaRepository.findAll();
//	}
//	@GetMapping("")
//	public List<Bolnica> deleteHospitalById(){
//		return bolnicaRepository.findAll();
//	}

}
