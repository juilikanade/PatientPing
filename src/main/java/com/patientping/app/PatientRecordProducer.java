package com.patientping.app;

import java.util.Scanner;

/**
 * The class which reads the input file and produces lies/patient records to be consumed by consumer
 */
public class PatientRecordProducer implements Runnable {
    /**
     * The Queue which is shared between producer and consumer and has patient records
     */
    private PatientRecordQueue patientRecordQueue;

    // Get file from resources folder
    ClassLoader classLoader = getClass().getClassLoader();
    java.io.InputStream stream;

    public PatientRecordProducer(PatientRecordQueue patientRecordQueue,
                                 String fileName) {
        this.patientRecordQueue = patientRecordQueue;
        java.net.URL uri = classLoader.getResource(fileName);
        stream = classLoader.getResourceAsStream(fileName);

    }


    public void run() {


        if (stream != null) {
            try {
                Scanner scanner = new Scanner(stream);

                if (scanner.hasNextLine())
                    scanner.nextLine();
                while (scanner.hasNextLine()) {

                    patientRecordQueue.put(scanner.nextLine());

                }
                scanner.close();

                this.patientRecordQueue.continueProducing = Boolean.FALSE;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("The input file does not exist");
        }


    }
}
