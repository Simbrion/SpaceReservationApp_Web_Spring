package com.sra.util;

import com.sra.util.listviewers.CustomerListViewer;
import com.sra.util.listviewers.ReservationsListViewer;
import com.sra.util.listviewers.SpaceListViewer;
import com.sra.util.userinputvalidators.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Component
public class UtilitiesToolSet {

    private final Reader reader;
    private final InputValidator inputValidator;
    private final MenuInputValidator menuInputValidator;
    private final CharsOrDigitsInputValidator charsOrDigitsInputValidator;
    private final PositiveDigitValidator positiveDigitValidator;
    private final TimeInputValidator timeInputValidator;
    private final DateInputValidator dateInputValidator;
    private final ReservationsListViewer reservationsListViewer;
    private final SpaceListViewer spaceListViewer;
    private final CustomerListViewer customerListViewer;
    private final TimeOverlapChecker timeOverlapChecker;

}
