package com.sra.service.reservationoperations;

import com.sra.model.Reservation;
import com.sra.model.Space;
import com.sra.config.Config;
import com.sra.repository.SpacesDataRepository;
import com.sra.util.Reader;
import com.sra.util.listviewers.SpaceListViewer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ReservationSpaceSelector {

    private final Reader reader;
    private final SpacesDataRepository spacesDataRepository;

    public void selectSpace(Reservation newReservation) throws IOException {

        while (true) {
            boolean spaceNameIsOnTheList = false;
            System.out.println(Config.YELLOW_COLOUR + "Please insert the name of the space to be reserved." + Config.RESET_COLOUR);
            new SpaceListViewer(spacesDataRepository).printList();
            String userInput = reader.readLine();

            for (Space space : spacesDataRepository.getData()) {
                if (userInput.equalsIgnoreCase(space.getName())) {
                    newReservation.setSpace(space);
                    spaceNameIsOnTheList = true;
                    break;
                }
            }

            if (!spaceNameIsOnTheList) {
                System.out.println(Config.NO_SPACE_WITH_NAME);
            }
            else break;
        }
    }
}
