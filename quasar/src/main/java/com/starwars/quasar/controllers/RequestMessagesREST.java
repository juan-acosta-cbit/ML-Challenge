
package com.starwars.quasar.controllers;

import com.starwars.quasar.ParametersMessage;
import com.starwars.quasar.exceptions.*;
import com.starwars.quasar.objects.*;
import com.starwars.quasar.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestMessagesREST {

	@Autowired
	private LocationManageService locationService;
	
	@Autowired
	private RecoveryMessageService recoveryMessageService;
	
	@Autowired
	private SatelliteInformationManegeService satelliteServices;

	@PostMapping("/topsecret")
	public MessageResponse getLocation(@RequestBody MessageRequest message) {
		
		String          retrieveMessage  = new String();
		PositionVO      locationMessage  = new PositionVO();
		MessageResponse topSecretMessage = new MessageResponse();
		MessageResponse messageResponse  = new MessageResponse();
		
		try {
			if(message.getSatellites().size() < 1)
				throw new QuasarException(ParametersMessage.SATELLITE_NOT_FOUND);
		}catch(Exception e) {
			throw new QuasarException(ParametersMessage.SATELLITE_NOT_FOUND);
		}		

		retrieveMessage = recoveryMessageService.getMessage(message.getSatellites());
		locationMessage = locationService.getLocation(message.getSatellites());		
		topSecretMessage.setMessage(retrieveMessage);
		topSecretMessage.setPosition(locationMessage);
		messageResponse = topSecretMessage;

		return messageResponse;
	}

	@PostMapping("/topsecret_split/{satellite_name}")
	public MessageSplitResponse postMessageAndLocation(@PathVariable String satellite_name,	@RequestBody MessageSplitRequest message) {
		
		MessageSplitResponse    topSecretMessage = new MessageSplitResponse();		
		MessageSatelliteRequest messageRequest = new MessageSatelliteRequest();
		
		messageRequest.setDistance(message.getDistance());
		messageRequest.setMessage(message.getMessage());
		messageRequest.setName(satellite_name);

		try {
			satelliteServices.isSaveMessage(messageRequest);	
		}catch(Exception e) {
			throw new QuasarException(ParametersMessage.SATELLITE_NOT_FOUND);
		}
		
		topSecretMessage.setMessage(ParametersMessage.MESSAGE_RECIVED);

		return topSecretMessage;
	}

	@GetMapping("/topsecret_split/{satellite_name}")
	public MessageResponse getMessageAndLocation(@PathVariable String satellite_name, @RequestBody MessageSplitRequest message) {
		
		String                  retrieveMessage  = new String();
		MessageResponse         topSecretMessage = new MessageResponse();		
		MessageSatelliteRequest messageRequest   = new MessageSatelliteRequest();
		PositionVO              positionMessage  = new PositionVO();		
		MessageResponse         messageResponse  = new MessageResponse();
			
		messageRequest.setDistance(message.getDistance());
		messageRequest.setMessage(message.getMessage());
		messageRequest.setName(satellite_name);

		if (satelliteServices.isSaveMessage(messageRequest)) {
			retrieveMessage  = recoveryMessageService.getMessage(satelliteServices.getMessageRequestList());
			positionMessage  = locationService.getLocation(satelliteServices.getMessageRequestList());
			topSecretMessage = new MessageResponse();
			topSecretMessage.setMessage(retrieveMessage);
			topSecretMessage.setPosition(positionMessage);
			messageResponse  = topSecretMessage;
			
			return messageResponse;
			
		} else {
			throw new QuasarException(ParametersMessage.BAD_INFORMATION);
		}
	}

	@GetMapping("/topsecret_split")
	public MessageResponse getMessageAndLocation() {
		
		String          retrieveMessage  = new String();
		PositionVO      positionMessage  = new PositionVO();
		MessageResponse topSecretMessage = new MessageResponse();
		MessageResponse messageResponse  = new MessageResponse();
		
		try {
			retrieveMessage  = recoveryMessageService.getMessage(satelliteServices.getMessageRequestList());
			positionMessage  = locationService.getLocation(satelliteServices.getMessageRequestList());
			topSecretMessage = new MessageResponse();
			topSecretMessage.setMessage(retrieveMessage);
			topSecretMessage.setPosition(positionMessage);
			messageResponse  = topSecretMessage;

			return messageResponse;
			
		} catch (Exception e) {
			throw new QuasarException(ParametersMessage.BAD_INFORMATION);
		}
	}
}