package com.patientping.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class provided the utility methods to print patient statistics
 */
public class PatientStatistics {

    private Map<Integer, Integer> decadeMapper = new TreeMap<Integer, Integer>();
    private Map<String, Patient> maleFirstNameMap = new HashMap<String, Patient>();
    private Map<String, Patient> femaleFirstNameMap = new HashMap<String, Patient>();

    private List<Patient> listMaleLastName = new ArrayList<Patient>();
    private List<Patient> listFemaleLastName = new ArrayList<Patient>();
    int maleCount = 0;
    int femaleCount = 0;

    public PatientStatistics() {
        super();
    }

    public Map<Integer, Integer> getDecadeMapper() {
        return decadeMapper;
    }

    public void setDecadeMapper(Map<Integer, Integer> decadeMapper) {
        this.decadeMapper = decadeMapper;
    }

    public Map<String, Patient> getMaleFirstNameMap() {
        return maleFirstNameMap;
    }

    public void setMaleFirstNameMap(Map<String, Patient> maleFirstNameMap) {
        this.maleFirstNameMap = maleFirstNameMap;
    }

    public Map<String, Patient> getFemaleFirstNameMap() {
        return femaleFirstNameMap;
    }

    public void setFemaleFirstNameMap(Map<String, Patient> femaleFirstNameMap) {
        this.femaleFirstNameMap = femaleFirstNameMap;
    }

    public List<Patient> getListMaleLastName() {
        return listMaleLastName;
    }


    public List<Patient> getListFemaleLastName() {
        return listFemaleLastName;
    }


    public int getMenCount() {
        return maleCount;
    }


    public int getFemaleCount() {
        return femaleCount;
    }

    public void increamentMenCnt() {
        maleCount++;
    }

    public void increamentFemaleCnt() {
        femaleCount++;
    }

    public String getDecadeStatistics() {
        Iterator<Integer> iterator = decadeMapper.keySet().iterator();
        StringBuffer buffer = new StringBuffer();
        int decadeStart = 0;
        int noOfPatients;
        buffer.append("\nNumber of people in each decade of life:");
        while (iterator.hasNext()) {
            decadeStart = iterator.next();
            noOfPatients = decadeMapper.get(decadeStart);
            buffer.append("\n");
            buffer.append(decadeStart * 10);
            buffer.append("-");
            buffer.append(decadeStart * 10 + 9);
            buffer.append(":");
            buffer.append(noOfPatients);
        }
        return buffer.toString();
    }

    public void printStatistics() {
        System.out.println("No of men ::" + maleCount);
        System.out.println("No of women::" + femaleCount);
        System.out.println(getDecadeStatistics());

        Patient mostCommonMaleName = Collections.max(getMaleFirstNameMap()
                .values(), new FrequencyComparator());
        System.out.println("\nMost common first name - men : "
                + mostCommonMaleName.firstName);

        Patient mostCommonFName = Collections.max(getFemaleFirstNameMap()
                .values(), new FrequencyComparator());
        System.out.println("Most common first name - women :"
                + mostCommonFName.firstName);

        Collections.sort(getListMaleLastName(), new LastNameComparator());
        Collections.sort(getListFemaleLastName(), new LastNameComparator());
        System.out.println("\nWomen List Ordered By Last Name --"
                + getListFemaleLastName());

        System.out.println("\n\nMen List Ordered By Last Name ---"
                + getListMaleLastName());

    }

}
