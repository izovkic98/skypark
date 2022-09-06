package com.tvz.skypark.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.dto.ReservationDetailsDto;
import com.tvz.skypark.exception.ReservationDateIsIncorrectException;
import com.tvz.skypark.exception.ReservationNotFoundException;
import com.tvz.skypark.security.UserPrinciple;
import com.tvz.skypark.service.ReservationService;
import com.tvz.skypark.utils.FileDownloadUtil;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@PostMapping
	public ResponseEntity<?> saveReservation(@RequestBody final ReservationDetailsDto reservation) {
		try {
			return new ResponseEntity<>(reservationService.saveReservation(reservation), HttpStatus.CREATED);
		} catch (ReservationDateIsIncorrectException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}

	}

	@GetMapping("")
	public ResponseEntity<?> getAllReservationsOfUser(@AuthenticationPrincipal UserPrinciple userPrinciple) {
		return ResponseEntity.ok(reservationService.findAllReservationsOfUser(userPrinciple.getId()));
	}

	@GetMapping("user/{username}")
	public List<ReservationDetailsDto> getReservationsByUsername(@PathVariable String username) {
		return reservationService.getReservationsByUsername(username);
	}

	@GetMapping("id/{id}")
	public ReservationDetailsDto getReservationById(@PathVariable Long id) {
		return reservationService.getReservationById(id);
	}

	@GetMapping("/all")
	public List<ReservationDetailsDto> getAllReservations() {
		return reservationService.findAllReservations();
	}
	
	@GetMapping("/all/current")
	public List<ReservationDetailsDto> getAllCurrentReservationsOfUser(@AuthenticationPrincipal UserPrinciple userPrinciple) {
		return reservationService.findAllCurrentReservations(userPrinciple.getId());
	}
	
	

	@PutMapping
	private ResponseEntity<?> updateReservation(@RequestBody ReservationDetailsDto updatedReservation) {
		try {
			return new ResponseEntity<>(reservationService.updateReservation(updatedReservation), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}

	}
	
	@PutMapping("/change-status")
	private ResponseEntity<?> changeReservationStatus(@RequestBody ReservationDetailsDto updatedReservation) {
		try {
			return new ResponseEntity<>(reservationService.changeReservationStatus(updatedReservation), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}

	}

	@DeleteMapping("{reservationId}")
	public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId) {
		try {
			reservationService.deleteReservation(reservationId);
		} catch (ReservationNotFoundException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

    
   @GetMapping(value = "/print/{fileCode}", produces = MediaType.APPLICATION_PDF_VALUE)
   public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
       FileDownloadUtil downloadUtil = new FileDownloadUtil();
        
       Resource resource = null;
       try {
           resource = downloadUtil.getFileAsResource(fileCode);
       } catch (IOException e) {
    	   e.printStackTrace(System.out);
           return ResponseEntity.internalServerError().build();
       }
        
       if (resource == null) {
           return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
       }
        
       String contentType = "application/pdf";
       String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
        
       return ResponseEntity.ok()
               .contentType(MediaType.parseMediaType(contentType))
               .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
               .body(resource);       
   }


		

}
