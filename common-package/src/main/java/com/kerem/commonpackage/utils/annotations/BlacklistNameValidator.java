package com.kerem.commonpackage.utils.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlacklistNameValidator implements ConstraintValidator<BlacklistName,String> {

    private static final String  filePath = "C:\\turkcellbootcamp\\PacketService\\blacklist.txt";
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        List<String> listOfStrings = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                listOfStrings.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return !(listOfStrings.contains(name));



    }
}
