package com.UnayShah.ReadReceipts.model;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Mail")
public class Mail {

	@Id
	private String id;
	private MailStatus mailStatus;
	private List<Date> accessTimes;

	public Mail() {
		this.id = UUID.randomUUID().toString();
		this.mailStatus = MailStatus.INACTIVE;
		this.accessTimes = new ArrayList<>();
	}

	public MailStatus getMailStatus() {
		return mailStatus;
	}

	public Boolean activateMailStatus() {
		if (this.mailStatus == MailStatus.INACTIVE) {
			this.mailStatus = MailStatus.UNREAD;
			return true;
		}
		return false;
	}

	public Boolean changeMailStatusRead() {
		if (this.mailStatus == MailStatus.UNREAD || this.mailStatus == MailStatus.READ) {
			this.mailStatus = MailStatus.READ;
			this.accessTimes.add(new Date());
			return true;
		}
		return false;
	}

	public String getId() {
		return this.id;
	}

	public List<Date> getAccessTimes() {
		return this.accessTimes;
	}

}
