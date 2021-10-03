package com.UnayShah.ReadReceipts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.UnayShah.ReadReceipts.model.Mail;
import com.UnayShah.ReadReceipts.model.MailStatus;
import com.UnayShah.ReadReceipts.service.MailService;

@Controller
public class MailController {

	@Autowired
	MailService mailService;

	@GetMapping
	public ResponseEntity<List<Mail>> getAll() {
		return new ResponseEntity<>(mailService.getAll(), HttpStatus.ACCEPTED);
	}

	@GetMapping("generateId")
	public ResponseEntity<String> generateId() {
		return new ResponseEntity<>(mailService.generateId(), HttpStatus.ACCEPTED);
	}

	@GetMapping("activateId/{id}")
	public ResponseEntity<Boolean> activateId(@PathVariable String id) {
		return new ResponseEntity<>(mailService.activateId(id), HttpStatus.ACCEPTED);
	}

	@GetMapping("readId/{id}")
	public ResponseEntity<Void> readId(@PathVariable String id) {
		mailService.readId(id);
		;
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@GetMapping("checkMailStatus/{id}")
	public ResponseEntity<MailStatus> readStatus(@PathVariable String id) {
		return new ResponseEntity<>(mailService.checkMailStatus(id), HttpStatus.ACCEPTED);
	}

	@GetMapping("deleteId/{id}")
	public ResponseEntity<Boolean> deleteId(@PathVariable String id) {
		return new ResponseEntity<>(mailService.deleteId(id), HttpStatus.ACCEPTED);
	}

	@GetMapping("deleteAll")
	public ResponseEntity<Void> deleteAll() {
		mailService.deleteAll();
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
