package com.patientping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.patientping.app.PatientRecordConsumer;
import com.patientping.app.PatientRecordProducer;
import com.patientping.app.PatientRecordQueue;

/**
 * The main class which creates the PatientRecordProducer and PatientRecordConsumer
 *
 */
public class PatientApp {

	public static void main(String[] args) {
		try {
			//Usage of producer consumer pattern.
			PatientRecordQueue recQueue = new PatientRecordQueue();
			ExecutorService threadPool = Executors.newFixedThreadPool(2);//1 producer 1 consumer
			threadPool.execute(new PatientRecordConsumer(recQueue));
			Future<?> producerStatus = threadPool
					.submit(new PatientRecordProducer(recQueue,
							"PatientPingCodeTest.csv"));//TODO Hard coding needs to be removed
			// this will wait for the producer to finish its execution.
			producerStatus.get();
			threadPool.shutdown();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
