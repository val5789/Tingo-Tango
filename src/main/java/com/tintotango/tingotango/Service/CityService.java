package com.tintotango.tingotango.Service;

import com.tintotango.tingotango.Exceptions.KidsException;
import com.tintotango.tingotango.Model.City;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class CityService {
    private List<City> cityList;

    public CityService() {
        cityList = new ArrayList<>();

        cityList.add(new City("Manizales", "01"));
        cityList.add(new City("Pereira", "02"));
        cityList.add(new City("Pitalito", "03"));
        cityList.add(new City("Mocoa", "04"));
    }

    public String addCity(City newCity) {
        cityList.add(newCity);
        return "Adicionada";
    }

    public String deleteById(String cityId) {
        cityList.removeIf(city -> city.getCityId().equals(cityId));
        return "Eliminado";
    }

    public City getCityById(String cityId) throws KidsException {
        for (City city : cityList) {
            if (city.getCityId().equals(cityId)) {
                return city;
            }
        }
        throw new KidsException("La id ingresada no corresponde a una ciudad");
    }

    public String updateCity(String cityId, City updatedCity) throws KidsException {
        boolean matchFound = false;
        for (int i = 0; i < cityList.size(); i++) {
            if (cityList.get(i).getCityId().equals(cityId)) {
                cityList.set(i, updatedCity);
                matchFound = true;
                return "Ciudad actualizada";
            }
        }
        if (!matchFound) {
            throw new KidsException("La id es incorrecta o no se encontro");
        } else {
            return "Ciudad actualizada";
        }

    }
}
