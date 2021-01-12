package com.starwars.quasar.services;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.starwars.quasar.objects.PositionVO;
import com.starwars.quasar.objects.SatelliteVO;

import java.util.ArrayList;
import java.util.List;

@Service
public class SatelliteManageService implements InitializingBean {

	private List<SatelliteVO> satellitesInformation;

	@Override
	public void afterPropertiesSet() throws Exception {

		satellitesInformation = new ArrayList<SatelliteVO>();
		
		PositionVO p1 = new PositionVO(5d, 4d);
		SatelliteVO s1 = new SatelliteVO(p1, "kenobi");
		satellitesInformation.add(s1);
		
		PositionVO p2 = new PositionVO(4d, -3d);
		SatelliteVO s2 = new SatelliteVO(p2, "skywalker");
		satellitesInformation.add(s2);
		
		PositionVO p3 = new PositionVO(-4d, 13d);
		SatelliteVO s3 = new SatelliteVO(p3, "sato");	
		satellitesInformation.add(s3);
	}
		
	public List<SatelliteVO> getSatellitesInformation() {
		return satellitesInformation;
	}

	public void setSatellitesInformation(List<SatelliteVO> satellitesInformation) {
		this.satellitesInformation = satellitesInformation;
	}
}