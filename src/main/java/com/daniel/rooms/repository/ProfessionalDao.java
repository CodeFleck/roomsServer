package com.daniel.rooms.repository;

import com.daniel.rooms.model.Professional;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProfessionalDao {

    private static final Map<String, Professional> professionalMap = new HashMap<>();

    static {
        initProfessionals();
    }

    private static void initProfessionals() {
        Professional professional1 = new Professional("Stella", "medium", 20);
        Professional professional2 = new Professional("Fernanda", "large", 90);
        Professional professional3 = new Professional("Roberta", "leather", 120);

        professionalMap.put(professional1.getName(), professional1);
        professionalMap.put(professional2.getName(), professional2);
        professionalMap.put(professional3.getName(), professional3);
    }

    public static Professional getProfessional(String name) {
        return professionalMap.get(name);
    }

    public static List<Professional> getAllProfessionals() {
        Collection<Professional> c = professionalMap.values();
        List<Professional> list = new ArrayList<Professional>();
        list.addAll(c);
        return list;
    }
}