package com.trj.tlib.dialog;


public class LessonsBean  implements TPVPickerViewData {

    private int lessons;

    public LessonsBean() {
    }

    public LessonsBean(int lessons) {
        this.lessons = lessons;
    }

    public int getLessons() {
        return lessons;
    }

    public void setLessons(int lessons) {
        this.lessons = lessons;
    }

    @Override
    public String getPickerViewText() {
        return "第"+lessons+"节";
    }
}
