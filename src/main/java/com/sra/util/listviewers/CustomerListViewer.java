package com.sra.util.listviewers;

import com.sra.config.Config;
import com.sra.model.Customer;
import com.sra.repository.CustomersDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomerListViewer implements ListViewer {

    private final CustomersDataRepository customersDataRepository;

    public void printList() {

            if (customersDataRepository.isEmpty()) {
                System.out.println(Config.NO_EXISTING_CUSTOMERS);
            }
            else {
                System.out.println(Config.YELLOW_COLOUR + "The following customers are registered in the system:" + Config.RESET_COLOUR);
                for (Customer customer : customersDataRepository.getData()) {
                    System.out.println(Config.YELLOW_COLOUR + " -" + customer.getName() + Config.RESET_COLOUR);
                }
            }
    }
}
