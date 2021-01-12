package com.starwars.quasar.services;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starwars.quasar.objects.MessageSatelliteRequest;
import com.starwars.quasar.objects.SatelliteVO;

@Service
public class SatelliteInformationManegeService {

	@Autowired
	private SatelliteManageService satelliteManageServices;

	private List<MessageSatelliteRequest> messageRequestList = new ArrayList<MessageSatelliteRequest>();	

	public List<MessageSatelliteRequest> getMessageRequestList() {
		return messageRequestList;
	}	

	public MessageSatelliteRequest getMessageAndLocation() {
		return null;
	}
	
	public void cleanSatelliteRecived() {
		messageRequestList.removeAll(messageRequestList);
	}
	
	public Boolean isSaveMessage(MessageSatelliteRequest messageSatelliteRequest) {

		List<SatelliteVO> satellitesInfo = satelliteManageServices.getSatellitesInformation();
		Boolean           isAdded = false;

		if (satellitesInfo.stream().filter(sat -> sat.getName().equalsIgnoreCase(messageSatelliteRequest.getName())).collect(Collectors.toList()).isEmpty()) {
			return false;
		}

		if (messageRequestList.isEmpty()) {
			messageRequestList.add(messageSatelliteRequest);
			return false;
		}	

		for (int i = 0; i < messageRequestList.size(); i++) 
		{
			if (messageRequestList.get(i).getName().equalsIgnoreCase(messageSatelliteRequest.getName())) {
				messageRequestList.remove(i);
				messageRequestList.add(messageSatelliteRequest);
				isAdded = true;
			}
		}

		if (!isAdded) {
			messageRequestList.add(messageSatelliteRequest);
		}
		
		return messageRequestList.size() == 3 ? true : false;
	}
}