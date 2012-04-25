package com.telkau.examples.eip.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import com.telkau.examples.eip.data.Book;

/**
 * EipInMessageHandler acts as a ServiceActivator MessageEndpoint, receiving
 * messages whose payload type is the com.telkau.examples.eip.data.Book value
 * type
 * 
 * @author elegantmush
 * 
 */

@MessageEndpoint
public class EipInMessageHandler {

	private static final Logger log = LoggerFactory.getLogger(EipInMessageHandler.class);

	@ServiceActivator(inputChannel = "eipIn")
	public void handleMessage(Book payload) {
		log.info("Received book: '{}'  by {}", payload.getTitle(), payload.getAuthor());
	}
}
