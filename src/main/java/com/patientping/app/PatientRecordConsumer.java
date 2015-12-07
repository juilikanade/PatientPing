package com.patientping.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The class which consumes the patient records and processes it to form patient statistics
 */
public class PatientRecordConsumer implements Runnable {
	/**
	 * The queue shared between producer and consumer
	 */
	private PatientRecordQueue recQueue;
	private PatientStatistics patientStatistics;

	public PatientRecordConsumer(PatientRecordQueue recQueue) {
		this.recQueue = recQueue;
		patientStatistics = new PatientStatistics();
	}

	public void run() {
		try {
			String data = recQueue.get();

			while (recQueue.continueProducing || data != null) {
				processPatientInfo(data);
				data = recQueue.get();
			}

			patientStatistics.printStatistics();

		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * The line read from the input file is processed here to gather the different patient statistics
	 * @param patientRecord
	 */
	public void processPatientInfo(String patientRecord) {

		String gender;
		String dob = null;
		String firstName = "";
		String lastName = "";
		Patient patient = null;
		HashMap<String, Patient> maleFirstNameMap = (HashMap<String, Patient>) patientStatistics
				.getMaleFirstNameMap();
		HashMap<String, Patient> femaleFirstNameMap = (HashMap<String, Patient>) patientStatistics
				.getFemaleFirstNameMap();

		Scanner lineTokenizer = new Scanner(patientRecord).useDelimiter(",");
		patient = new Patient();
		if (lineTokenizer.hasNext()) {

			firstName = lineTokenizer.next();
			patient.setFirstName(firstName);
		}
		if (lineTokenizer.hasNext()) {

			lastName = lineTokenizer.next();
			patient.setLastName(lastName);
		}
		if (lineTokenizer.hasNext()) {

			dob = lineTokenizer.next();
			processDate(dob);
		}
		if (lineTokenizer.hasNext()) {

			gender = lineTokenizer.next();
			if (gender.equalsIgnoreCase("M")) {
				patientStatistics.increamentMenCnt();
				patientStatistics.getListMaleLastName().add(patient);
				if (maleFirstNameMap.containsKey(firstName)) {
					Patient patientTemp = maleFirstNameMap.get(firstName);
					int freq = patientTemp.getFrequency();
					patientTemp.setFrequency(freq + 1);
					maleFirstNameMap.put(firstName, patientTemp);
				} else {
					patient.setFrequency(1);
					maleFirstNameMap.put(firstName, patient);
				}

			} else if (gender.equalsIgnoreCase("F")) {

				patientStatistics.increamentFemaleCnt();
				patientStatistics.getListFemaleLastName().add(patient);
				if (femaleFirstNameMap.containsKey(firstName)) {
					Patient patientTemp = femaleFirstNameMap.get(firstName);
					int freq = patientTemp.getFrequency();
					patientTemp.setFrequency(freq + 1);
					femaleFirstNameMap.put(firstName, patientTemp);
				} else {
					patient.setFrequency(1);
					femaleFirstNameMap.put(firstName, patient);
				}
			}
			else
				System.out.println("Invalid input provided.");
		}

	}

	/**
	 * This method takes date and forms the decade statistics for the patient
	 * @param dob
	 */
	private void processDate(String dob) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");

		try {
			Map<Integer, Integer> decadeMapper = patientStatistics
					.getDecadeMapper();
			Date date = sdf.parse(dob);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);

			int decade = (Calendar.getInstance().get(Calendar.YEAR) - year) / 10;

			if (decadeMapper.containsKey(decade)) {
				decadeMapper.put(decade, decadeMapper.get(decade) + 1);
			} else {
				decadeMapper.put(decade, 1);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}