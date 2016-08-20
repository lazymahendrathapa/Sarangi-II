/**
 * @(#) DatasetUtil.java 2.0     July 29, 2016
 *
 * Bijay Gurung
 *
 * Insitute of Engineering
 */

package com.sarangi.learningmodel;

import com.sarangi.structures.*;
import com.sarangi.audioFeatures.*;
import com.sarangi.audioTools.*;
import com.sarangi.app.Config;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * Class for holding the dataset for training and testing
 *
 *
 * @author Bijay Gurung
 */

public class DatasetUtil {

    /**
     * Get the data for the given song.
     *
     * @param song The song whose data is to be returned.
     *
     */
    public static double[] getSongData(Song song) {


        double[] dataset = new double[Config.DATASET_SIZE];

        int i=0;

        for (FeatureType feature: Config.features) {

            double[] featureData = song.getFeature(feature);

            for (int j=0; j<feature.getLength(); j++) {
                dataset[i] = featureData[j];
                i++;
            }

        }


        return dataset;

    }


    /**
     * Get the LearningDataset object on a song by song basis.
     *
     * @param dataSongs The songs to use.
     * @param labelArray The array of label strings.
     * @param featureType The type of feature to extract into the dataset.
     *
     * @return The LearningDataset object corresponding to the given input.
     *
     */

    public static LearningDataset getSongwiseDataset(List<Song> dataSongs, String[] labelArray) {

        LearningDataset learningDataset = new LearningDataset();

        learningDataset.labelIndices = new int[dataSongs.size()];

        LoggerHandler loggerHandler = LoggerHandler.getInstance();

        learningDataset.dataset = new double[dataSongs.size()][Config.DATASET_SIZE];

        int i = 0;

        for (Song song : dataSongs) {

            try {

                learningDataset.dataset[i] = getSongData(song);

                learningDataset.labelIndices[i] = getIndexOfLabel(song.getSongName(),labelArray);

                ++i;

            }catch (LabelNotFoundException le) {

                loggerHandler.loggingSystem(LoggerHandler.LogType.LEARNING_MODEL,
                        Level.SEVERE,
                        ExceptionPrint.getExceptionPrint(le));

                continue;

            }

        }


        return learningDataset;

    }


    /**
     * Get the index of the labelArray member which contains the given string.
     *
     * @param str The string which is supposed to contain a label string.
     * @param labelArray The array of labels.
     *
     * @return The index
     *
     */
    
    public static int getIndexOfLabel(String str, String[] labelArray) 
        throws LabelNotFoundException {
    
        for (int i=0; i<labelArray.length; i++) {
            if (str.contains(labelArray[i])) {
                return (i+1);
            }
        }
    
        throw new LabelNotFoundException("Label not found: "+str);
    
    }

    /**
     * Convert the given int array to an array of doubles.
     *
     * @param arr The int array to be converted.
     *
     * @return The double array.
     *
     */
    
    public static double[] convertIntArrayToDoubleArray(int[] arr) {

        if (arr == null) {
            return null;
        }
    
        double[] retArray = new double[arr.length];
        for (int i=0; i<arr.length; i++) {
            retArray[i] = (double) arr[i];
        }
    
        return retArray;
    }

    /**
     * Return array of doubles using an array of floats
     * 
     * @param input Array of floats
     *
     * @return Array of doubles
     *
     */
    public static double[] convertFloatsToDoubles(float[] input) {
        if (input == null) {
            return null; // Or throw an exception - your choice
        }
    
        double[] output = new double[input.length];
    
        for (int i = 0; i < input.length; i++) {
            output[i] = input[i];
        }
        return output;
    }


}
