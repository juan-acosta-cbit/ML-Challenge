package com.starwars.quasar.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.starwars.quasar.ParametersMessage;
import com.starwars.quasar.exceptions.QuasarException;
import com.starwars.quasar.objects.MessageSatelliteRequest;

@Service
public class RecoveryMessageService {

	public String getMessage(List<MessageSatelliteRequest> satellites) throws QuasarException {

		int                messageListSize;		
		int                wordsMessageSize;
		String             decryptedMessage = new String();
		StringBuilder      completedWord    = new StringBuilder("");
		List<List<String>> messagesList     = satellites.stream().map(MessageSatelliteRequest::getMessage).collect(Collectors.toList());		
		
		try {
			messageListSize = messagesList.get(0).size();
		} catch (Exception e) {
			throw new QuasarException(ParametersMessage.BAD_MESSAGE);
		}

		if (!messagesList.stream().allMatch(message -> message.size() == messageListSize ? true : false)) {
			throw new QuasarException(ParametersMessage.BAD_MESSAGE);
		}		

		for (int i = 0; i < messageListSize; i++) 
		{
			wordsMessageSize = 0;
			for (List<String> itemList : messagesList) 
			{
				wordsMessageSize = wordsMessageSize + i;
				if (!"".equalsIgnoreCase(itemList.get(i).toString())) {
					if (completedWord.length() == 0) {
						completedWord.append(itemList.get(i).toString());
						if (i == messageListSize - 1) {
							decryptedMessage += completedWord.toString();
							wordsMessageSize++;
						} else {
							decryptedMessage += completedWord.toString() + " ";
							wordsMessageSize++;
						}
					} else if (!completedWord.toString().equalsIgnoreCase(itemList.get(i).toString())) {
						throw new QuasarException(ParametersMessage.BAD_MESSAGE);
					}
				}
			}

			if (completedWord.length() == 0) {
				throw new QuasarException(ParametersMessage.BAD_MESSAGE);
			}

			completedWord.delete(0, completedWord.length());
		}

		return decryptedMessage.toString();
	}	

}