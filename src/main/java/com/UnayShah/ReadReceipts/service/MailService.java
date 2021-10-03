package com.UnayShah.ReadReceipts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UnayShah.ReadReceipts.model.Mail;
import com.UnayShah.ReadReceipts.model.MailStatus;
import com.UnayShah.ReadReceipts.repository.MailRepository;

@Service
public class MailService {

	@Autowired
	MailRepository mailRepository;
	
	public List<Mail> getAll(){
		return mailRepository.findAll();
	}

	public String generateId() {
		Mail mail = mailRepository.save(new Mail());
		return mail.getId();
	}

	public Boolean activateId(String id) {
		if (mailRepository.existsById(id)) {
			Mail mail = mailRepository.findById(id).get();
			if (mail.activateMailStatus()) {
				mailRepository.save(mail);
				return true;
			}
		}
		return false;
	}

	public void readId(String id) {
		if (mailRepository.existsById(id)) {
			Mail mail = mailRepository.findById(id).get();
			mail.changeMailStatusRead();
			mailRepository.save(mail);
		}
	}

	public MailStatus checkMailStatus(String id) {
		if (mailRepository.existsById(id)) {
			return mailRepository.findById(id).get().getMailStatus();

		}
		return null;
	}

	public Boolean deleteId(String id) {
		if (mailRepository.existsById(id)) {
			mailRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
