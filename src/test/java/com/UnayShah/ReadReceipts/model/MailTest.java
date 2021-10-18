package com.UnayShah.ReadReceipts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class MailTest {

	@Mock
	Mail mail;

	@Order(1)
	@Test
	public void constructorTest() {
		mail = new Mail();
		assertNotNull(mail);
	}

	@Order(2)
	@Test
	public void mailIdTest() {
		mail = new Mail();
		assertNotNull(mail.getId());
	}

	@Order(3)
	@Test
	public void getStatusTest() {
		mail = new Mail();
		assertNotNull(mail.getId());
		assertEquals(MailStatus.INACTIVE, mail.getMailStatus());
	}

	@Order(4)
	@Test
	public void activateStatusTest() {
		mail = new Mail();
		assertNotNull(mail.getId());
		assertEquals(MailStatus.INACTIVE, mail.getMailStatus());
		assertTrue(mail.activateMailStatus());
		assertEquals(MailStatus.UNREAD, mail.getMailStatus());
	}

	@Order(5)
	@Test
	public void readStatusTest() {
		mail = new Mail();
		assertNotNull(mail.getId());
		assertEquals(MailStatus.INACTIVE, mail.getMailStatus());
		assertFalse(mail.changeMailStatusRead());
		assertTrue(mail.activateMailStatus());
		assertEquals(MailStatus.UNREAD, mail.getMailStatus());
		assertTrue(mail.changeMailStatusRead());
		assertEquals(MailStatus.READ, mail.getMailStatus());
		assertFalse(mail.activateMailStatus());
	}

	@Order(6)
	@Test
	public void accessTimesTest() {
		mail = new Mail();
		assertNotNull(mail.getId());
		assertEquals(MailStatus.INACTIVE, mail.getMailStatus());
		assertFalse(mail.changeMailStatusRead());
		assertTrue(mail.activateMailStatus());
		assertEquals(MailStatus.UNREAD, mail.getMailStatus());
		assertTrue(mail.changeMailStatusRead());
		assertEquals(mail.getAccessTimes().size(), 1);
		assertTrue(mail.changeMailStatusRead());
		assertEquals(mail.getAccessTimes().size(), 2);
	}
}
