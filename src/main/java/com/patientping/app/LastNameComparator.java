package com.patientping.app;

import java.util.Comparator;
/**
 * Compare 2 patients depending on their last name
 * @author juilipanse-kanade
 *
 */
public class LastNameComparator implements Comparator<Patient> {


	public int compare(Patient obj1, Patient obj2) {

		if (obj1.lastName == obj2.lastName) {
			return 0;
		}
		if (obj1.lastName == null) {
			return -1;
		}
		if (obj2.lastName == null) {
			return 1;
		}
		return obj1.lastName.compareToIgnoreCase(obj2.lastName);
	}

}
