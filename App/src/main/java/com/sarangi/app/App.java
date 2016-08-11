/*
 * @(#) App.java 2.0    June 9, 2016
 *
 * Mahendra Thapa
 *
 * Institue of Engineering
 */

package com.sarangi.app;

import java.io.*;
import java.util.*;

import com.beust.jcommander.JCommander;

import com.sarangi.app.commands.*;
import com.sarangi.learningmodel.ann.*;
import com.sarangi.learningmodel.svm.*;
import com.sarangi.learningmodel.*;
import com.sarangi.structures.FeatureType;

/**
 * A main class for interfacing all the other sub-classes.
 *
 * <p>Includes the main method which extract the features of the training set and test set.
 *
 *
 *
 * @author  Mahendra Thapa
 *
 */

public class App
{

        /*CONSTRUCTORS **********************************************/

        private App(){

        }

        /**
         * Extract the features from the training and testing sets of data and stores in the
         * training.txt and testing.txt.
         *
         * @param   args    Take an argument from the command line terminal if any.
         *
         */

        public static void main( String[] args )
                throws FileNotFoundException, IOException
        {

                CommandLine cm = new CommandLine();

                JCommander jc = new JCommander(cm);

                CommandExtract extract = new CommandExtract();
                jc.addCommand("extract",extract);

                jc.parse(args);

                if (jc.getParsedCommand().equals("extract")) {
                        System.out.println(extract.folder);
                        System.out.println(extract.file);
                }

                /*
                String trainingFilename = "src/resources/song/songFeatures/features.txt";
                String testFilename = "src/resources/song/songFeatures/test.txt";

                FeatureExtractor.extractFeature(trainingFilename,new String("src/resources/song/Genre_training"));
                FeatureExtractor.extractFeature(testFilename,new String("src/resources/song/Genre_testing"));

                ClassifierRunner runner = new ClassifierRunner(new String[]{"classical","hiphop","jazz","pop","rock"});
                //ClassifierRunner runner = new ClassifierRunner(new String[]{"high_arousal","low_arousal"});
                //runner.runCrossValidation(trainingFilename, FeatureType.SARANGI_MFCC,10,"SVM");
                runner.run(trainingFilename,testFilename,FeatureType.SARANGI_ALL,"SVM");
                */
        }
}
