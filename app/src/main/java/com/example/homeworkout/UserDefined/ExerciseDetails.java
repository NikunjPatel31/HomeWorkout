package com.example.homeworkout.UserDefined;

public class ExerciseDetails {

    int imageID;
    String exerciseTitle;
    String[] features;
    int time = 3;

    public ExerciseDetails(int imageID, String exerciseTitle, String[] features) {
        this.imageID = imageID;
        this.exerciseTitle = exerciseTitle;
        this.features = features;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getExerciseTitle() {
        return exerciseTitle;
    }

    public void setExerciseTitle(String exerciseTitle) {
        this.exerciseTitle = exerciseTitle;
    }

    public String[] getFeatures() {
        return features;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setFeatures(String[] features) {
        this.features = features;
    }
}
