/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.core;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;

/**
 * Specifies a basic set of messaging operations.
 * 
 * <p>Implemented by {@link MessagingTemplate}. Even though most calling code
 * will depend on the template directly (e.g. to access setter methods), this
 * interface is a useful option to enhance testability, as it can easily be mocked
 * or stubbed.
 *
 * <p>Defines a variety of methods for sending and receiving {@link Message}s
 * across {@link MessageChannel}s including the use of converters where necessary.
 * Convenience methods also support sending and receiving based on channel name,
 * where the template will delegate to its {@link ChannelResolver} to locate the
 * actual {@link MessageChannel} instance.
 *
 * @author Mark Fisher
 * @since 2.0
 * @see MessagingTemplate
 */
public interface MessagingOperations {

	//-------------------------------------------------------------------------
	// Convenience methods for sending messages
	//-------------------------------------------------------------------------

	/**
	 * Send a message to the default channel.
	 * <p>This will only work with a default channel specified!
	 * @param message the message to send
	 * @throws MessagingException if an error occurs during message sending
	 */
	<P> boolean send(Message<P> message) throws MessagingException;

	/**
	 * Send a message to the specified channel.
	 * @param channel the channel to which the message will be sent
	 * @param message the message to send
	 * @throws MessagingException if an error occurs during message sending
	 */
	<P> boolean send(MessageChannel channel, Message<P> message) throws MessagingException;

	/**
	 * Send a message to the specified channel.
	 * @param channelName the name of the channel to which the message will be sent
	 * (to be resolved to an actual channel by a ChannelResolver)
	 * @param message the message to send
	 * @throws MessagingException if an error occurs during message sending
	 */
	//TODO: <P> boolean send(String channelName, Message<P> message) throws MessagingException;


	//-------------------------------------------------------------------------
	// Convenience methods for sending auto-converted messages
	//-------------------------------------------------------------------------

	// TODO: convert and send methods...

	/**
	 * Send the given object to the default channel, converting the object
	 * to a message with a configured MessageConverter.
	 * <p>This will only work with a default channel specified!
	 * @param message the object to convert to a message
	 * @throws MessagingException if an error occurs
	 */
	//void convertAndSend(Object message) throws MessagingException;

	/**
	 * Send the given object to the specified channel, converting the object
	 * to a message with a configured MessageConverter.
	 * @param channel the channel to send this message to
	 * @param message the object to convert to a message
	 * @throws MessagingException if an error occurs
	 */
	//void convertAndSend(MessageChannel channel, Object message) throws MessagingException;

	/**
	 * Send the given object to the specified channel, converting the object
	 * to a message with a configured MessageConverter.
	 * @param channelName the name of the channel to send this message to
	 * (to be resolved to an actual channel by a ChannelResolver)
	 * @param message the object to convert to a message
	 * @throws MessagingException if an error occurs
	 */
	//void convertAndSend(String destinationName, Object message) throws MessagingException;

	/**
	 * Send the given object to the default destination, converting the object
	 * to a JMS message with a configured MessageConverter. The MessagePostProcessor
	 * callback allows for modification of the message after conversion.
	 * <p>This will only work with a default destination specified!
	 * @param message the object to convert to a message
	 * @param postProcessor the callback to modify the message
	 * @throws JmsException checked JMSException converted to unchecked
	 */
	//void convertAndSend(Object message, MessagePostProcessor postProcessor) throws MessagingException;

	/**
	 * TODO: either define a MessagePostProcessor that accepts a builder or accept HeaderMapper here
	 */
	//void convertAndSend(MessageChannel channel, Object message, MessagePostProcessor postProcessor) throws MessagingException;

	/**
	 * TODO: see above
	 */
	//void convertAndSend(String channelName, Object message, MessagePostProcessor postProcessor) throws MessagingException;


	//-------------------------------------------------------------------------
	// Convenience methods for receiving messages
	//-------------------------------------------------------------------------

	/**
	 * Receive a message synchronously from the default channel, but only
	 * wait up to a specified time for delivery.
	 * <p>This method should be used carefully, since it will block the thread
	 * until the message becomes available or until the timeout value is exceeded.
	 * <p>This will only work with a default channel specified!
	 * @return the message received from the default channel or <code>null</code> if the timeout expires
	 * @throws MessagingException if an error occurs during message reception
	 */
	<P> Message<P> receive() throws MessagingException;

	/**
	 * Receive a message synchronously from the specified channel, but only
	 * wait up to a specified time for delivery.
	 * <p>This method should be used carefully, since it will block the thread
	 * until the message becomes available or until the timeout value is exceeded.
	 * @param channel the channel from which a message should be received
	 * @return the message received from the channel or <code>null</code> if the timeout expires
	 * @throws MessagingException if an error occurs during message reception
	 */
	<P> Message<P> receive(PollableChannel channel) throws MessagingException;

	/**
	 * Receive a message synchronously from the specified channel, but only
	 * wait up to a specified time for delivery.
	 * <p>This method should be used carefully, since it will block the thread
	 * until the message becomes available or until the timeout value is exceeded.
	 * @param channelName the name of the channel from which a message should be received
	 * (to be resolved to an actual channel by a ChannelResolver)
	 * @return the message received from the channel or <code>null</code> if the timeout expires
	 * @throws MessagingException if an error occurs during message reception
	 */
	// TODO: <P> Message<P> receive(String channelName) throws MessagingException;


	// TODO: receiveSelected(selector), receiveSelected(channel, selector), receiveSelected(channelName, selector) ? 

	//-------------------------------------------------------------------------
	// Convenience methods for receiving auto-converted messages
	//-------------------------------------------------------------------------

	// TODO: receive and convert methods...

	/**
	 * Receive a message synchronously from the default channel, but only
	 * wait up to a specified time for delivery. Convert the message into an
	 * object with a configured MessageConverter.
	 * <p>This method should be used carefully, since it will block the thread
	 * until the message becomes available or until the timeout value is exceeded.
	 * <p>This will only work with a default channel specified!
	 * @return the message received from the channel or <code>null</code> if the timeout expires.
	 * @throws MessagingException if an error occurs during message reception
	 */
	//Object receiveAndConvert() throws JmsException;

	/**
	 * Receive a message synchronously from the specified channel, but only
	 * wait up to a specified time for delivery. Convert the message into an
	 * object with a configured MessageConverter.
	 * <p>This method should be used carefully, since it will block the thread
	 * until the message becomes available or until the timeout value is exceeded.
	 * @param channel the channel from which a message should be received
	 * @return the message received from the channel or <code>null</code> if the timeout expires.
	 * @throws MessagingException if an error occurs during message reception
	 */
	//Object receiveAndConvert(PollableChannel channel) throws JmsException;

	/**
	 * Receive a message synchronously from the specified channel, but only
	 * wait up to a specified time for delivery. Convert the message into an
	 * object with a configured MessageConverter.
	 * <p>This method should be used carefully, since it will block the thread
	 * until the message becomes available or until the timeout value is exceeded.
	 * @param channelName the name of the channel from which a message should be received
	 * (to be resolved to an actual channel by a ChannelResolver)
	 * @return the message received from the channel or <code>null</code> if the timeout expires.
	 * @throws MessagingException if an error occurs during message reception
	 */
	//Object receiveAndConvert(String channelName) throws JmsException;

}
