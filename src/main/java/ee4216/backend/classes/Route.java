/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee4216.backend.classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alvaro
 */
public class Route {
    
    private List nameAddress = new ArrayList();
    private List address = new ArrayList();

    public Route(String origin, String originAddress, String name1, String ad1, String name2, String ad2, 
            String name3, String ad3, String name4,String ad4) {
        
        this.nameAddress.add(origin);
        this.nameAddress.add(name1);
        this.nameAddress.add(name2);
        this.nameAddress.add(name3);
        this.nameAddress.add(name4);
        
        this.address.add(originAddress);
        this.address.add(ad1);
        this.address.add(ad2);
        this.address.add(ad3);
        this.address.add(ad4);
    }

    public List getNameAddress() {
        return nameAddress;
    }

    public void setNameAddress(List nameAddress) {
        this.nameAddress = nameAddress;
    }

    public List getAddress() {
        return address;
    }

    public void setAddress(List address) {
        this.address = address;
    }
    
    
}
