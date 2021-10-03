package com.UnayShah.ReadReceipts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.UnayShah.ReadReceipts.model.Mail;

@Repository
public interface MailRepository extends MongoRepository<Mail, String>  {
}
