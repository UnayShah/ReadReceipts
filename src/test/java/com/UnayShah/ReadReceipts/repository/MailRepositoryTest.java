package com.UnayShah.ReadReceipts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.UnayShah.ReadReceipts.model.Mail;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MailRepositoryTest {
	@Autowired
	MailRepository mailRepository;

	private static Mail mail;

	@BeforeAll
	public static void initialize() {
		mail = new Mail();
	}

	@Order(1)
	@Test
	public void saveTest() {
		assertEquals(mailRepository.save(mail), mail);
	}

	@Order(2)
	@Test
	public void findTest() {
		assertTrue(mailRepository.existsById(mail.getId()));
		assertTrue(new ReflectionEquals(mailRepository.findById(mail.getId()).get()).matches(mail));
	}

	@Order(3)
	@Test
	public void writeActivateTest() {
		mail.activateMailStatus();
		assertTrue(mailRepository.existsById(mail.getId()));
		assertNotEquals(mailRepository.findById(mail.getId()).get(), mail);
		assertEquals(mailRepository.save(mail), mail);
		assertTrue(new ReflectionEquals(mailRepository.findById(mail.getId()).get()).matches(mail));
	}

	@Order(4)
	@Test
	public void writeReadTest() {
		mail.changeMailStatusRead();
		assertTrue(mailRepository.existsById(mail.getId()));
		assertNotEquals(mailRepository.findById(mail.getId()).get(), mail);
		assertEquals(mailRepository.save(mail), mail);
		assertTrue(new ReflectionEquals(mailRepository.findById(mail.getId()).get()).matches(mail));
	}

	@Order(5)
	@Test
	public void deleteTest() {
		assertTrue(mailRepository.existsById(mail.getId()));
		mailRepository.deleteById(mail.getId());
		assertFalse(mailRepository.existsById(mail.getId()));
	}

}
