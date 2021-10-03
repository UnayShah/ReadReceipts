package com.UnayShah.ReadReceipts.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Mail")
public class Mail {

	@Id
	private String id;
	private MailStatus mailStatus;

	public Mail() {
		this.id = UUID.randomUUID().toString();
		this.mailStatus = MailStatus.INACTIVE;
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
		if (this.mailStatus == MailStatus.UNREAD) {
			this.mailStatus = MailStatus.READ;
			return true;
		}
		return false;
	}

	public String getId() {
		return id;
	}
}
