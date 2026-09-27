package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import java.util.List;


public class TripServiceTest {
    private static final User GUEST = null;
    private static final User UNUSED_USER = null;

    @Test(expected = UserNotLoggedInException.class)
    public void should_throw_exception_when_user_is_not_logged_in() {
        TripService tripService = new TestableTripService(GUEST, null);

        tripService.getTripsByUser(UNUSED_USER);
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
