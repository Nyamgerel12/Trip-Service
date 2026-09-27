package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;
import java.util.ArrayList;


public class TripServiceTest {
    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_exception_when_user_is_not_logged_in() {
        TripService tripService = new TestableTripService(GUEST, null);

        tripService.getTripsByUser(UNUSED_USER);
    }

    @Test 
    public void should_not_return_any_trip_when_users_are_not_friends() {
        TripService tripService = new TestableTripService(REGISTERED_USER, new ArrayList<Trip>());
        
        User otherUser = new User();
        otherUser.addFriend(ANOTHER_USER);

        List<Trip> trips = tripService.getTripsByUser(otherUser);

        Assert.assertTrue(trips.isEmpty());
    }

    private static class TestableTripService extends TripService {
        private final User loggedInUser;
        private final List<Trip> trips;

        public TestableTripService(User loggedInUser, List<Trip> trips) {
            this.loggedInUser = loggedInUser;
            this.trips = trips;
        }

        @Override
        protected User getLoggedUser() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> findTripsByUser(User user) {
            return trips;
        }
    }

	
}
