package DAO.Entity;

/**
 * Created by Jaho on 2017/5/23.
 */
public class Channel {

    private int id;
    private String name;
    private String intro;
    private String founder;

    public Channel(int id, String name, String intro, String founder) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.founder = founder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }
}
