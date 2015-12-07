package com.patientping.app;

import java.util.Comparator;
/**
 * Compare 2 patients depending on the frequency of their first names
 * @author juilipanse-kanade
 *
 */
public class FrequencyComparator implements Comparator<Patient> {


	public int compare(Patient o1, Patient o2) {

		if (o1.getFrequency() > o2.frequency)
			return 1;
		else if (o1.getFrequency() < o2.frequency)
			return -1;
		else
			return 0;
	}

}
