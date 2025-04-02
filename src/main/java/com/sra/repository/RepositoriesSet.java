package com.sra.repository;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Getter
@Transactional
@Repository
public class RepositoriesSet {

    private final CustomersDataRepository customersDataRepository;
    private final ReservationsRepository reservationsRepository;
    private final SpacesDataRepository spacesDataRepository;

}
