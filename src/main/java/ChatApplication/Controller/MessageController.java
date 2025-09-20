package ChatApplication.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ChatApplication.Model.Message;

@RestController
public class MessageController {

	
	
	@MessageMapping("/message")
	@SendTo("/topic/return-to")
	public Message getcontent( Message message)
	{
		
		try {
			
			Thread.sleep(2000);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return message;
	}
	
}
