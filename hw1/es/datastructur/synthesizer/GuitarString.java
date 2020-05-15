package es.datastructur.synthesizer;

import java.util.ArrayList;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {

        int capacity = (int) Math.round(SR/frequency);
        buffer = new ArrayRingBuffer<>(capacity);
        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        ArrayList<Double> checkDifferent = new ArrayList<>(buffer.capacity());

        for (int i = 0; i <buffer.capacity() ; i++) {
            buffer.dequeue();
            double r = Math.random() -0.5;
            while (checkDifferent.contains(r)){
                r = Math.random() - 0.5;
            }
            checkDifferent.add(r);
            buffer.enqueue(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {

        double dequeue = buffer.dequeue();
        double newDouble = DECAY*0.5*(dequeue+buffer.peek());
        buffer.enqueue(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {


        return buffer.peek();
    }
}

