package model;


public class DataStudents {
    public String id;
    public String name;
    public String surname;
    public Score score;

    public boolean formCompleted;


    public static class Score{
        public String mathematics;
        public String science;
        public String computer;

    }
}
