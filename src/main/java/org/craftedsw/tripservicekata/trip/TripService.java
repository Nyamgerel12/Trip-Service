package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
		if (loggedUser.equals(user)) {
			return findTripsByUser(user);
		}
		boolean isFriend = false;
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				return findTripsByUser(user);
			}
			return new ArrayList<Trip>();
	} 
	
	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}
	protected List<Trip> findTripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}
	
}
