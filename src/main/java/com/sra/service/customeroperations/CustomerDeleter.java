package com.sra.service.customeroperations;

import com.sra.config.Config;
import com.sra.model.Customer;
import com.sra.model.Reservation;
import com.sra.repository.RepositoriesSet;
import com.sra.util.UtilitiesToolSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerDeleter {

    private final RepositoriesSet repositoriesSet;
    private final UtilitiesToolSet utilitiesToolSet;

    public void start() throws IOException {

        if (repositoriesSet.getCustomersDataRepository().isEmpty()) System.out.println(Config.NO_EXISTING_CUSTOMERS);
        else {
            System.out.println(Config.YELLOW_COLOUR + "Please provide a name of the customer to be deleted." +
                    "\nExisting reservations related to the customer (if any) will be deleted as well." + Config.RESET_COLOUR);
            utilitiesToolSet.getCustomerListViewer().printList();
            String userInput = utilitiesToolSet.getReader().getReader().readLine();
            deleteCustomerReservations(userInput);
            if (!deleteCustomer(userInput)) System.out.println(Config.NO_CUSTOMER_WITH_NAME);
        }
    }

    //Deletes reservations related to the customer, if any
    private void deleteCustomerReservations(String userInput) {
        List<Reservation> currentReservationsList = repositoriesSet.getReservationsRepository().getData();
        Iterator<Reservation> iterator = currentReservationsList.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getCustomer().getName().equalsIgnoreCase(userInput)) {
                iterator.remove();
                repositoriesSet.getReservationsRepository().removeReservation(reservation);
            }
        }

    }

    //Searched and deletes customer, returns true if a customer has been successfully deleted
    private boolean deleteCustomer(String userInput){

        boolean customerIsOnTheList = false;
        List<Customer> currentCustomersList = repositoriesSet.getCustomersDataRepository().getData();
        Iterator<Customer> iterator = currentCustomersList.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getName().equalsIgnoreCase(userInput)) {
                iterator.remove();
                repositoriesSet.getCustomersDataRepository().removeCustomer(customer);
                customerIsOnTheList = true;

                System.out.println(Config.GREEN_COLOUR + "The customer named " + customer.getName() + " and all related reservations (if any) have been successfully deleted!" + Config.RESET_COLOUR);
            }
        }
        return customerIsOnTheList;
    }


}

