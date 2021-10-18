package com.UnayShah.ReadReceipts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.UnayShah.ReadReceipts.model.MailStatus;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MailServiceTest {

	@Autowired
	MailService mailService;

	private static String mailId;

	@Order(1)
	@Test
	public void generateId() {
		mailId = mailService.generateId().replace("<img src=\"https://read-receipts-app.herokuapp.com/readId/", "").replace("\" style=\"display:none\"}>", "");
		assertNotNull(mailId);
	}

	@Order(2)
	@Test
	public void getStatusInactiveTest() {
		assertNotNull(mailService.checkMailStatus(mailId));
		assertNull(mailService.checkMailStatus(UUID.randomUUID().toString()));
		assertEquals(MailStatus.INACTIVE, mailService.checkMailStatus(mailId));
	}

	@Order(3)
	@Test
	public void activateMailTest() {
		assertNotNull(mailService.checkMailStatus(mailId));
		assertTrue(mailService.activateId(mailId));
		assertFalse(mailService.activateId(UUID.randomUUID().toString()));
		assertEquals(MailStatus.UNREAD, mailService.checkMailStatus(mailId));
	}

	@Order(4)
	@Test
	public void readMailTest() {
		assertNotNull(mailService.checkMailStatus(mailId));
		mailService.readId(mailId);
		assertEquals(MailStatus.READ, mailService.checkMailStatus(mailId));
	}
	@Order(5)
	@Test
	public void mailAccessTimesTest() {
		assertNotNull(mailService.checkMailStatus(mailId));
		mailService.readId(mailId);
		assertEquals(2, mailService.checkMailAccessTimes(mailId).size());
	}

	@Order(6)
	@Test
	public void deleteMailTest() {
		assertEquals(MailStatus.READ, mailService.checkMailStatus(mailId));
		assertTrue(mailService.deleteId(mailId));
		assertFalse(mailService.deleteId(mailId));
	}
}
