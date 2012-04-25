package com.telkau.examples.eip;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.message.GenericMessage;

import com.telkau.examples.eip.data.Book;

/**
 * Simple application to test connectivity to JMS broker and test messaging via
 * EIP sitting atop JMS
 * 
 * @author elegantmush
 * 
 */
public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		log.debug("Starting Main.main()");

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {
				"/jmsContext.xml", "/eipContext.xml" });

		// Retrieve channel from the Spring container context
		MessageChannel eipOutCh = (MessageChannel) ctx.getBean("eipOut");

		// Create a message with a Book value type as content to send across JMS
		Book princessBrideBook = new Book("The Princess Bride", "William Goldman",
				new GregorianCalendar(1973, Calendar.AUGUST, 31).getTime());

		GenericMessage<Book> bookMsg = new GenericMessage<Book>(princessBrideBook);

		// Send message to the EIP channel
		log.debug("Sent book:{}", princessBrideBook);
		eipOutCh.send(bookMsg, 1000);

		// Wait a bit to allow message to be received
		Thread.sleep(500);

		// If successful, we should see the message being received by the
		// @ServiceActivator
		// annotated method in the EipInMessageReceiver class

		// Shutdown the application context.
		ctx.close();
		log.debug("Completing Main.main()");
	}
}
