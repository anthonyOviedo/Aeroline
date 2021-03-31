/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author anton
 */
public class Plane {

    public Plane() {
    }

    /**
     * @return the plane_id
     */
    public int getPlane_id() {
        return plane_id;
    }

    /**
     * @param plane_id the plane_id to set
     */
    public void setPlane_id(int plane_id) {
        this.plane_id = plane_id;
    }

    /**
     * @return the plane_name
     */
    public String getPlane_name() {
        return plane_name;
    }

    /**
     * @param plane_name the plane_name to set
     */
    public void setPlane_name(String plane_name) {
        this.plane_name = plane_name;
    }

    /**
     * @return the plane_sits
     */
    public int getPlane_sits() {
        return plane_sits;
    }

    /**
     * @param plane_sits the plane_sits to set
     */
    public void setPlane_sits(int plane_sits) {
        this.plane_sits = plane_sits;
    }

    public Plane(int plane_id, String plane_name, int plane_sits) {
        this.plane_id = plane_id;
        this.plane_name = plane_name;
        this.plane_sits = plane_sits;
    }
    private int plane_id;
    private String plane_name;
    private int plane_sits;
  
}
