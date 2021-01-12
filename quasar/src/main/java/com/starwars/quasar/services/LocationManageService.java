package com.starwars.quasar.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.starwars.quasar.ParametersMessage;
import com.starwars.quasar.exceptions.QuasarException;
import com.starwars.quasar.objects.MessageSatelliteRequest;
import com.starwars.quasar.objects.PositionVO;
import com.starwars.quasar.objects.SatelliteVO;


@Service
public class LocationManageService {

	@Autowired
	private SatelliteManageService satelliteManageServices;

	public PositionVO getLocation(List<MessageSatelliteRequest> satellitesList)	throws QuasarException {

		List<SatelliteVO>   satellitesInfo = satelliteManageServices.getSatellitesInformation();
		Map<String, Double> satellitesNames;
		double[ ][ ]        satellitesPosition = new double[ satellitesList.size() ][ 2 ];		

		try {
			satellitesNames = satellitesList.stream().collect(Collectors.toMap(MessageSatelliteRequest::getName, MessageSatelliteRequest::getDistance));			
		} catch (Exception e) {
			throw new QuasarException(ParametersMessage.BAD_COORDINATES);
		}		

		if (satellitesNames.size() != satellitesInfo.size()) {
			throw new QuasarException(ParametersMessage.BAD_COORDINATES);
		}
		
		double[ ] satellitesDistances = new double[satellitesNames.size()];
		
		int index = 0;

		for (SatelliteVO item : satellitesInfo) {
			if (satellitesNames.get(item.getName()) != null) {
				satellitesPosition[ index ][ 0 ] = item.getLocation().getX();
				satellitesPosition[ index ][ 1 ] = item.getLocation().getY();
				satellitesDistances[ index ] = (double) satellitesNames.get(item.getName());
				index++;
			} else {
				throw new QuasarException(ParametersMessage.BAD_COORDINATES);
			}
		}

		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(satellitesPosition, satellitesDistances), new LevenbergMarquardtOptimizer());
		Optimum optimum = solver.solve();

		Double x = optimum.getPoint().getEntry( 0 );
		Double y = optimum.getPoint().getEntry( 1 );

		PositionVO position = new PositionVO(x, y);

		return position;
	}

}