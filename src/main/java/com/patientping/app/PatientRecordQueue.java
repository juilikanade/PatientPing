package com.patientping.app;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PatientRecordQueue {

	public ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(
			100);
	public Boolean continueProducing = Boolean.TRUE;

	public void put(String data) throws InterruptedException {
		this.queue.put(data);
	}

	public String get() throws InterruptedException {
		return this.queue.poll(1, TimeUnit.SECONDS);
	}

}
