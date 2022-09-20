package co.develhope.CRUDtest.services;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public boolean switchValue(int number) {
        if (number%2 == 0) {
            return true;
        } else return false;
    }
}
