package com.sra.service.spaceoperations;
import com.sra.config.Config;
import com.sra.model.*;
import com.sra.repository.RepositoriesSet;
import com.sra.util.UtilitiesToolSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SpaceDeleter {

    private final UtilitiesToolSet utilitiesToolSet;
    private final RepositoriesSet repositoriesSet;

    public void start() throws IOException {

        if (repositoriesSet.getSpacesDataRepository().isEmpty()) System.out.println(Config.NO_EXISTING_SPACES);
        else {
            System.out.println(Config.YELLOW_COLOUR + "Please provide a name of the space to be deleted." +
                            "\nExisting reservations related to the space (if any) will be deleted as well." + Config.RESET_COLOUR);
            utilitiesToolSet.getSpaceListViewer().printList();
            String userInput = utilitiesToolSet.getReader().readLine();
            deleteSpaceReservations(userInput);
            deleteCustomersReservations(userInput);
            if (!deleteSpace(userInput)) System.out.println(Config.NO_SPACE_WITH_NAME);
        }
    }

    //Deletes reservations related to the space, if any
    private void deleteSpaceReservations(String userInput) {
        List<Reservation> currentReservationsList = repositoriesSet.getReservationsRepository().getData();
        Iterator<Reservation> iterator = currentReservationsList.iterator();

        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getSpace().getName().equalsIgnoreCase(userInput)) {
                repositoriesSet.getReservationsRepository().removeReservation(reservation);
                iterator.remove(); // Safe removal using Iterator
            }
        }
    }

    //Deletes related reservations from each customer, if any
    private void deleteCustomersReservations(String userInput) {
        List<Customer> currentCustomersList = repositoriesSet.getCustomersDataRepository().getData();

        for (Customer customer : currentCustomersList) {
            for (int i = 0; i < customer.getReservations(repositoriesSet.getReservationsRepository()).size(); i++) {
                if (customer.getReservations(repositoriesSet.getReservationsRepository()).get(i).getSpace().getName().equalsIgnoreCase(userInput)) {
                    customer.getReservations(repositoriesSet.getReservationsRepository()).remove(customer.getReservations(repositoriesSet.getReservationsRepository()).get(i));
                    break;
                }
            }
        }

    }

    //Searched and deletes space, returns true if a space has been successfully deleted
    private boolean deleteSpace(String userInput) {
        boolean spaceIsOnTheList = false;
        List<Space> currentSpaceList = repositoriesSet.getSpacesDataRepository().getData();
        Iterator<Space> iterator = currentSpaceList.iterator();

        while (iterator.hasNext()) {
            Space space = iterator.next();
            if (space.getName().equalsIgnoreCase(userInput)) {
                repositoriesSet.getSpacesDataRepository().removeSpace(space);
                iterator.remove();
                spaceIsOnTheList = true;
                System.out.println(Config.GREEN_COLOUR + "The space named " + space.getName() + " and all related reservations (if any) have been successfully deleted!" + Config.RESET_COLOUR);
            }
        }

        return spaceIsOnTheList;
    }


}

