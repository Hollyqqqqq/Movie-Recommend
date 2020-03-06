package entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Comments_movie {
    private SimpleStringProperty user_name;//评分评论的用户
    private SimpleDoubleProperty star;
    private SimpleStringProperty comments;
    private SimpleStringProperty date;


    public Comments_movie() {

    }

    public Comments_movie(String user_name, double star, String comments, String date) {
        this.user_name = new SimpleStringProperty(user_name);
        this.star = new SimpleDoubleProperty(star);
        this.comments = new SimpleStringProperty(comments);
        this.date = new SimpleStringProperty(date);
    }

    public String getUser_name() {
        return user_name.get();
    }

    public void setUser_name(String name) {
        this.user_name = new SimpleStringProperty(name);
    }

    public double getStar() {
        return star.get();
    }

    public void setStar(double star) {
        this.star = new SimpleDoubleProperty(star);
    }

    public String getComments() {
        return comments.get();
    }

    public void setComments(String comments) {
        this.comments = new SimpleStringProperty(comments);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }
}
